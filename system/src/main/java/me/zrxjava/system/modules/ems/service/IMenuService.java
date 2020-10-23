package me.zrxjava.system.modules.ems.service;

import me.zrxjava.system.modules.ems.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询该角色具有的菜单权限
     * @param roleId
     * @return
     */
    List<Menu> getByRoleId(Long roleId);
}
