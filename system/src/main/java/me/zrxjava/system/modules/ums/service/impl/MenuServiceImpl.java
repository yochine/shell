package me.zrxjava.system.modules.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.system.modules.ums.entity.Menu;
import me.zrxjava.system.modules.ums.enums.MenuTypeEnum;
import me.zrxjava.system.modules.ums.mapper.MenuMapper;
import me.zrxjava.system.modules.ums.service.IMenuService;
import me.zrxjava.system.modules.ums.transfer.MenuTransfer;
import me.zrxjava.system.modules.ums.vo.MenuTree;
import me.zrxjava.system.modules.ums.vo.MenuVo;
import me.zrxjava.system.support.constants.CacheConstants;
import me.zrxjava.system.support.constants.SystemConstants;
import me.zrxjava.system.support.util.TreeUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    private final MenuMapper menuMapper;

    private final MenuTransfer menuTransfer;

    @Override
    @Cacheable(value = CacheConstants.MENU_INFO, key = "#roleId", unless = "#result.isEmpty()")
    public List<MenuVo> getByRoleId(Long roleId) {
        return menuTransfer.toVos(menuMapper.getByRoleId(roleId));
    }


    @Override
    public List<MenuTree> buildMenuTree(Set<MenuVo> menus, String position, Integer parentId) {
        List<MenuTree> menuList = menus.stream().filter(menuPositionPredicate(position))
                .map(MenuTree::new).sorted(Comparator.comparing(MenuTree::getSort)).collect(Collectors.toList());
        parentId = parentId == null ? SystemConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.buildByRecursive(menuList,parentId);
    }

    /**
     * menu 位置断言
     * @param position 位置
     * @return Predicate
     */
    private Predicate<MenuVo> menuPositionPredicate(String position) {
        return vo -> {
            if (SystemConstants.MENU_POSITION_TOP.equals(position)) {
                return SystemConstants.MENU_POSITION_TOP.equals(vo.getPosition());
            }
            // 其他查询 左侧 + 顶部
            return !MenuTypeEnum.BUTTON.getType().equals(vo.getType());
        };
    }

}