package me.zrxjava.system.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.entity.RoleDept;

import java.util.Set;

/**
 * <p>
 * 角色部门关联 服务类
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
public interface IRoleDeptService extends IService<RoleDept> {

    /**
     * 获取角色绑定自定义组织权限
     * @param roleId
     * @return
     */
    Set<Long> getCustomizeDept(Long roleId);
}
