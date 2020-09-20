package me.zrxjava.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import me.zrxjava.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色部门关联
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_roles_depts")
@ApiModel(value="RolesDepts对象", description="角色部门关联")
public class RolesDepts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long deptId;


}
