package me.zrxjava.system.modules.ums.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zrxjava.common.validated.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 角色Dto对象 sys_role
 *
 * @author zrxjava
 * @date 2021-04-12
 */
@Data
@ApiModel(value="角色dto对象", description="角色表")
public class RoleDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID 不能为空" ,groups = Update.class)
    private Long roleId;
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称 不能为空")
    private String name;
    @ApiModelProperty(value = "角色编码")
    @NotBlank(message = "角色编码 不能为空")
    private String code;
    @ApiModelProperty(value = "角色级别")
    @NotNull(message = "角色级别 不能为空")
    private Long level;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "数据权限")
    @NotBlank(message = "数据权限 不能为空")
    private String dataScope;

    private Set<Long> deptIds;

    private Set<Long> menuIds;

}
