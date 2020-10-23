package me.zrxjava.system.modules.ems.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zrxjava.common.group.Update;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author void
 * @create 2020-10-18
 */
@Data
@ApiModel(value="UserDto对象", description="系统用户")
public class UserDto {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "id不能为空",groups = Update.class)
    private Long userId;

    @ApiModelProperty(value = "部门id")
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "手机号码")
    @NotBlank(message = "用户名不能为空")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "头像地址")
    private String avatarName;

    @ApiModelProperty(value = "头像真实路径")
    private String avatarPath;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "是否为admin账号")
    @NotNull(message = "是否为admin账号不能为空")
    private Boolean isAdmin;

    @ApiModelProperty(value = "状态：1启用、0禁用")
    @NotNull(message = "状态不能为空")
    private Boolean enabled;

    @ApiModelProperty(value = "修改密码的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime pwdResetTime;
}
