package me.zrxjava.system.modules.ems.service;

import me.zrxjava.system.modules.ems.entity.RolesDepts;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色部门关联 服务类
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
public interface IRolesDeptsService extends IService<RolesDepts> {

    /**
     * 获取角色绑定自定义组织权限
     * @param roleId
     * @return
     */
    Set<Long> getCustomizeDepts(Long roleId);
}
