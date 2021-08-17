package me.zrxjava.system.modules.service;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 登录验证码处理类
 * @author void
 * @create 2020-11-20
 */

@Service
@AllArgsConstructor
public class ImageCodeService {

    private final CaptchaService captchaService;

    /**
     * 获取验证码
     * @return
     */
    public ResponseModel getCode(String captchaType) {
        CaptchaVO vo = new CaptchaVO();
        vo.setCaptchaType(captchaType);
        return captchaService.get(vo);
    }


    /**
     * 校验 验证码
     * @param
     * @return
     */
    public ResponseModel checkCode(String pointJson,String token,String captchaType) {
        CaptchaVO vo = new CaptchaVO();
        vo.setPointJson(pointJson);
        vo.setToken(token);
        vo.setCaptchaType(captchaType);
        return captchaService.check(vo);
    }

}
