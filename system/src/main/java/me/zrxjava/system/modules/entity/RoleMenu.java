package me.zrxjava.system.modules.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单关联
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@Data
@Accessors(chain = true)
@Builder
@TableName("sys_role_menu")
@ApiModel(value="RoleMenu对象", description="角色菜单关联")
public class RoleMenu {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;


}
