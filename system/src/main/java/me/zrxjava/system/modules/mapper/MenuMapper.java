package me.zrxjava.system.modules.mapper;

import me.zrxjava.common.base.CommonMapper;
import me.zrxjava.system.modules.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
public interface MenuMapper extends CommonMapper<Menu> {

    /**
     * 根据角色id查找菜单
     * @param roleId
     * @return
     */
    @Select("SELECT m.* FROM sys_menu m INNER JOIN sys_role_menu rm on m.menu_id = rm.menu_id  WHERE m.hidden =0 and rm.role_id = #{roleId}")
    List<Menu> getByRoleId(Long roleId);
}
