package me.zrxjava.sercurity.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * @author void
 * @create 2020-09-21
 */
@Data
public class LoginUser implements Serializable {

    String userName;

    String password;

   // String verifyCode;
}
