package me.zrxjava.system.modules.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zrxjava.common.validated.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统用户Dto对象 sys_user
 *
 * @author zrxjava
 * @date 2021-04-17
 */
@Data
@ApiModel(value="系统用户dto对象", description="系统用户")
public class UserDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID 不能为空" ,groups = Update.class)
    private Long userId;
    @ApiModelProperty(value = "部门名称")
    @NotNull(message = "部门名称 不能为空")
    private Long deptId;
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名 不能为空")
    private String username;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "性别")
    @NotBlank(message = "性别 不能为空")
    private String gender;
    @ApiModelProperty(value = "手机号码")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "头像地址")
    private String avatarName;
    @ApiModelProperty(value = "头像真实路径")
    private String avatarPath;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码 不能为空")
    private String password;
    @ApiModelProperty(value = "是否为admin账号")
    @NotNull(message = "是否为admin账号 不能为空")
    private Integer isAdmin;
    @ApiModelProperty(value = "状态：1启用、0禁用")
    @NotNull(message = "状态：1启用、0禁用 不能为空")
    private Integer enabled;

}
