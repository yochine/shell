package me.zrxjava.system.modules.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.system.modules.ums.entity.Menu;
import me.zrxjava.system.modules.ums.mapper.MenuMapper;
import me.zrxjava.system.modules.ums.service.IMenuService;
import me.zrxjava.system.modules.ums.transfer.MenuTransfer;
import me.zrxjava.system.modules.ums.vo.MenuTree;
import me.zrxjava.system.modules.ums.vo.MenuVo;
import me.zrxjava.system.support.util.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
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
    public List<MenuVo> getByRoleId(Long roleId) {
        return menuTransfer.toVos(menuMapper.getByRoleId(roleId));
    }


    @Override
    public List<MenuTree> buildMenuTree(Set<MenuVo> menus, String position, Integer parentId) {
        String finalPosition = position == null ? "left" : position;
        List<MenuTree> menuList = menus.stream().filter(vo -> vo.getPosition().equalsIgnoreCase(finalPosition))
                .map(MenuTree::new).sorted(Comparator.comparing(MenuTree::getSort)).collect(Collectors.toList());
        parentId = parentId == null ? 0 : parentId;
        return TreeUtil.buildByRecursive(menuList,parentId);
    }

}