package me.zrxjava.system.modules.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author void
 * @create 2020-09-21
 */
@Data
public class LoginUser implements Serializable {


    private String username;

    private String password;

    /** captcha验证码  */
    private String captchaVerification;



   // String verifyCode;
}
