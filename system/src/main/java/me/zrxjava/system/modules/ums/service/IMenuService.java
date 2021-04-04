package me.zrxjava.system.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.ums.criteria.MenuCriteria;
import me.zrxjava.system.modules.ums.dto.MenuDto;
import me.zrxjava.system.modules.ums.entity.Menu;
import me.zrxjava.system.modules.ums.vo.MenuTree;
import me.zrxjava.system.modules.ums.vo.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单Service接口
 * 
 * @author zrxjava
 * @date 2021-02-24
 */
public interface IMenuService extends IService<Menu>
{

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
    List<MenuTree> buildMenuTree(Set<MenuVo> menus, String position, Long parentId);

    /**
     * 查询系统菜单
     * 
     * @param menuId 系统菜单ID
     * @return 系统菜单
     */
    public MenuVo detail(Long menuId);


    /**
     * 查询系统菜单列表
     *
     * @param  criteria
     * @return 系统菜单集合
     */
     public List<MenuVo> selectList(MenuCriteria criteria);

    /**
     * 新增系统菜单
     * 
     * @param dto
     * @return 结果
     */
    public Boolean add(MenuDto dto);

    /**
     * 修改系统菜单
     * 
     * @param dto
     * @return 结果
     */
    public Boolean edit(MenuDto dto);

    /**
     * 批量删除系统菜单
     * 
     * @param ids 需要删除的系统菜单ID
     * @return 结果
     */
    public Boolean delete(Set<Long> ids);

}
