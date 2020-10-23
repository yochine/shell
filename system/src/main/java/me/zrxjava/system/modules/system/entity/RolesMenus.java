package me.zrxjava.system.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import me.zrxjava.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色菜单关联
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_roles_menus")
@ApiModel(value="RolesMenus对象", description="角色菜单关联")
public class RolesMenus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;


}
