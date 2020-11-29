package me.zrxjava.system.modules.ums.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zrxjava.common.annotation.ResponseExcel;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.utils.gmhelper.SM2Util;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.common.validated.group.Update;
import me.zrxjava.system.modules.ums.criteria.UserCriteria;
import me.zrxjava.system.modules.ums.dto.UserDto;
import me.zrxjava.system.modules.ums.entity.User;
import me.zrxjava.system.modules.ums.service.IUserService;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
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

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
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

    public static void main(String[] args) throws InvalidCipherTextException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

        String test = "123456";
        String content = "049fe8b087656a7501d8cfd22d38cd9bdf47fc0571103b6e889f3c4cd0f77dcae5ad12bfcbf3254011c0f9bd07cbad4d2456bdaaa4756af7e10a6f78c29558b758c9de354933a39efea3c0923846c468e4037dbcabb6e503819edb61f6d871c1a11edc8c471e76";
        String privateKey = "3A2C8E1BB7B922FC7CB8E32FE7EFB6C1F3C0BF3ABAFE5560552BF67DA55BFD4B";
        String publicKey = "040471008F95FFD0E1F8AD1CC886E09402F45CC8A935DAE145B88B3768C80BF6E18879AAE458FEFBBB7114F6D9F11192860359FA50B403293F00592A6061B59F8F";
        byte[] enc = SM2Util.encrypt(SM2Engine.Mode.C1C3C2, publicKey, test.getBytes());
        String miwen = ByteUtils.toHexString(enc);
        System.out.println(miwen);
        content = "0496f119e9b52e5f062bffe97ebf2c8d644253790252219ebb75dbe47410da426810c238c87559c3595f9a72751cd5c517ae5dac879449d73b5c0d040a68289ff76eca6139404d4c241738d0709a818cd048dbf199fd5e32476fe5e73604c5f1e956fe1dc2985a";
        byte[] dec = SM2Util.decrypt(SM2Engine.Mode.C1C3C2, privateKey, ByteUtils.fromHexString(content));
        System.out.println(new String(dec, StandardCharsets.UTF_8));


    }

}

