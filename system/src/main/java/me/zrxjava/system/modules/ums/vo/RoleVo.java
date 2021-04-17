package me.zrxjava.system.modules.ums.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BaseEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色Vo对象 sys_role
 *
 * @author zrxjava
 * @date 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value=" 角色Vo对象", description="角色表" )
public class RoleVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID")
    private Long roleId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "角色编码")
    private String code;
    @ApiModelProperty(value = "角色级别")
    private Long level;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "数据权限")
    private String dataScope;

    private Set<Long> deptIds;

    private Set<Long> menuIds;

}
