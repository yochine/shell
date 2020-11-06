package me.zrxjava.common.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * 获取地址类
 *
 * @author void
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    /** IP地址查询 */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    /** 未知地址 */
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        // 内网不查询
        if (NetUtil.isInnerIP(ip))
        {
            return "内网IP";
        }
        try
        {
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("ip", ip);
            paramMap.put("json", true);
            String rspStr = HttpUtil.get(IP_URL, paramMap);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            String region = obj.getString("pro");
            String city = obj.getString("city");
            return String.format("%s %s", region, city);
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常 {}", ip);
        }

        return UNKNOWN;
    }
}
