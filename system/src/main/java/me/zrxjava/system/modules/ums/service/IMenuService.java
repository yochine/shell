package me.zrxjava.system.modules.ums.service;

import me.zrxjava.system.modules.ums.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.ums.vo.MenuTree;
import me.zrxjava.system.modules.ums.vo.MenuVo;

import java.util.List;
import java.util.Set;

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
    List<MenuVo> getByRoleId(Long roleId);

    /**
     * 查询菜单
     * @param menus
     * @param position
     * @param parentId
     * @return
     */
    List<MenuTree> buildMenuTree(Set<MenuVo> menus, String position, Integer parentId);
}
