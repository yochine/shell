package me.zrxjava.system.modules.login.controller;

import com.anji.captcha.model.common.ResponseModel;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.system.modules.login.service.ImageCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author void
 * @create 2020-11-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeController {

    private final ImageCodeService imageCodeService;


    @GetMapping
    public ResponseResult<ResponseModel> getCode(String captchaType){
        return ResponseResult.success(imageCodeService.getCode(captchaType));
    }

    @PostMapping
    public ResponseResult<ResponseModel> checkCode(String pointJson,String token,String captchaType){
        return ResponseResult.success(imageCodeService.checkCode(pointJson,token,captchaType));
    }
}
