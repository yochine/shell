package me.zrxjava.system.modules.ums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import me.zrxjava.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("sys_role_dept")
@ApiModel(value="RoleDept对象", description="角色部门关联")
public class RoleDept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long deptId;


}