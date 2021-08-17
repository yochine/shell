package me.zrxjava.system.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BaseEntity;

import java.io.Serializable;

/**
 * 系统用户Vo对象 sys_user
 *
 * @author zrxjava
 * @date 2021-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value=" 系统用户Vo对象", description="系统用户" )
public class UserVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID")
    private Long userId;
    @ApiModelProperty(value = "部门名称")
    private Long deptId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "头像地址")
    private String avatarName;
    @ApiModelProperty(value = "是否为admin账号")
    private Integer isAdmin;
    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Integer enabled;

}
