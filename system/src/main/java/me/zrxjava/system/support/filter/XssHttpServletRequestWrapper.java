package me.zrxjava.system.support.filter;

import cn.hutool.core.util.EscapeUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * XSS过滤处理
 *
 * @author void
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    /**
     * @param request
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }


    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        if(StringUtils.isNotBlank(parameter)){
            return EscapeUtil.escapeHtml4(parameter);
        }
        return null;
    }

    @Override
    public String[] getParameterValues(String name)
    {
        String[] values = super.getParameterValues(name);
        if (values != null)
        {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++)
            {
                // Whitelist的基本方法有四种：
                // none：只保留了文本；
                // simpleText：简单的文本属性b, em, i, strong, u。
                // basic：a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q,small,strike, strong, sub, sup, u, ul。
                // basicWithImages：a, b, blockquote, br, cite, code, dd, dl,dt, em, i, li, ol, p, pre, q, small, strike, strong, sub, sup, u, ul、img、src。
                // Relaxed：a, b, blockquote,br, caption, cite, code, col, colgroup, dd, dl, dt, em, h1, h2, h3, h4, h5, h6,i, img, li, ol, p, pre, q, small, strike, strong, sub, sup, table, tbody, td,tfoot, th, thead, tr, u, ul。
                // 四种所包含的属性越来越多，如果还不满足，可以使用addAttributes、addEnforcedAttribute、addProtocols、addTags。
                // 防xss攻击和过滤前后空格
                escapeValues[i] = Jsoup.clean(values[i], Whitelist.relaxed()).trim();
            }
            return escapeValues;
        }
        return super.getParameterValues(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        // 非json类型，直接返回
        if (!isJsonRequest())
        {
            return super.getInputStream();
        }

        // 为空，直接返回
        String json = IOUtils.toString(super.getInputStream(), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(json))
        {
            return super.getInputStream();
        }

        // xss过滤
        json = EscapeUtil.escapeHtml4(json).trim();
        final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream()
        {
            @Override
            public boolean isFinished()
            {
                return true;
            }

            @Override
            public boolean isReady()
            {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener)
            {
            }

            @Override
            public int read() throws IOException
            {
                return bis.read();
            }
        };
    }

    /**
     * 是否是Json请求
     * 
     */
    public boolean isJsonRequest()
    {
        String header = super.getHeader(HttpHeaders.CONTENT_TYPE);
        return MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(header);
    }
}