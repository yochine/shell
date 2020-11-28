package me.zrxjava.system.modules.ums.controller;


import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.asymmetric.SM2Engine;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zrxjava.common.annotation.ResponseExcel;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.common.validated.group.Update;
import me.zrxjava.system.modules.ums.criteria.UserCriteria;
import me.zrxjava.system.modules.ums.dto.UserDto;
import me.zrxjava.system.modules.ums.entity.User;
import me.zrxjava.system.modules.ums.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/ums/user")
@Api(value = "用户管理",tags = "用户服务")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/list")
    @ApiOperation("用户列表")
    @PreAuthorize("@ps.check('ums:user:list')")
    public ResponseResult<List<User>> list(UserCriteria criteria){
        List<User> users =  userService.list();
        return ResponseResult.success(users);
    }

    @GetMapping("/export")
    @ApiOperation("导出用户列表")
    @PreAuthorize("@ps.check('ums:user:export')")
    @ResponseExcel(name = "user",password = "123")
    public List<User> export(UserCriteria criteria){
        List<User> users =  userService.list();
        return users;
    }

    @PostMapping
    @ApiOperation("新增用户")
    @PreAuthorize("@ps.check('ums:user:add')")
    @ApiOperationSupport(ignoreParameters = {"dto.id"})
    public ResponseResult<Boolean> add(@Validated(Insert.class) @RequestBody UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return ResponseResult.setBody(userService.save(user));
    }

    @PutMapping
    @ApiOperation("修改用户")
    @PreAuthorize("@ps.check('ums:user:update')")
    public ResponseResult<Boolean> update(@Validated(Update.class) @RequestBody UserDto dto){
        User user = new User();
        return ResponseResult.setBody(userService.save(user));
    }

    @GetMapping
    @ApiOperation("用户详情")
    @PreAuthorize("@ps.check('ums:user:detail')")
    public ResponseResult<Boolean> detail(Long userId){
        User user = new User();
        return ResponseResult.setBody(userService.save(user));
    }

    @DeleteMapping
    @ApiOperation("删除用户")
    @PreAuthorize("@ps.check('ums:user:delete')")
    public ResponseResult<Boolean> delete(Long userId){
        User user = new User();
        return ResponseResult.setBody(userService.save(user));
    }

    public static void main(String[] args) {

        String text = "我是一段测试aaaa";
//        String text = "046e8ede5ade80d45160ecb2f2b98fda8e89d9ddb2bf8a008ba4bfd74db7c2885cd6f8b587d6dc45f6286f5092757fc7c40fe3110ac3f98c2796a1eff0d3374aea825b32cd5bc9c5802d0f64b0f1c92c9e008b21c73373e4a71917449982d2fa6ddc2964248d93033280bf";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();
//        String pri = HexUtil.encodeHexStr(privateKey);
        String pri = "148f8ff4c698e18f874c52fc8f1237a5e541535fd70d8dacc034c6f3f81c19cc";
//        System.out.println(pri);
//        String pub = HexUtil.encodeHexStr(publicKey);
        String pub = "04e0749ec558b28aff7d997c6a40fec66f0f120ee8b369dba4b62ff342955f99063b84c5f67a7bef8f30ece2cfb78000714805d3d814453e86ef1cc2ed051b4600";
//        System.out.println(pub);

//        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        SM2 sm2 = SmUtil.sm2(HexUtil.decodeHex(pri), HexUtil.decodeHex(pub));
// 公钥加密，私钥解密
//        SM2 sm2 =  SmUtil.sm2();
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        System.out.println(encryptStr);
        sm2.setMode(SM2Engine.SM2Mode.C1C3C2);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(text, KeyType.PrivateKey));
        System.out.println(decryptStr);
    }

}

