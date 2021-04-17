package me.zrxjava.system.modules.ums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色部门关联
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
@Data
@TableName("sys_role_dept")
@ApiModel(value="RoleDept对象", description="角色部门关联")
@Builder
@Accessors(chain = true)
public class RoleDept {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long deptId;


}
