package me.zrxjava.system.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.utils.QueryHelper;
import me.zrxjava.system.modules.ums.criteria.MenuCriteria;
import me.zrxjava.system.modules.ums.dto.MenuDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 系统菜单Service业务层处理
 * 
 * @author zrxjava
 * @date 2021-02-24
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    private final MenuTransfer menuTransfer;

    private final MenuMapper menuMapper;
    
    @Override
    @Cacheable(value = CacheConstants.MENU_INFO, key = "#roleId", unless = "#result.isEmpty()")
    public List<MenuVo> getByRoleId(Long roleId) {
        return menuTransfer.toVos(menuMapper.getByRoleId(roleId));
    }


    @Override
    public List<MenuTree> buildMenuTree(Set<MenuVo> menus, String position, Long parentId) {
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
    

    /**
     * 查询系统菜单
     * 
     * @param menuId 系统菜单ID
     * @return 系统菜单
     */
    @Override
    public MenuVo detail(Long menuId) {
        return menuTransfer.toVo(getById(menuId));
    }

    /**
     * 查询系统菜单列表
     * 
     * @param criteria
     * @return 系统菜单
     */
    @Override
    public List<MenuVo> selectList(MenuCriteria criteria){
        List<MenuVo> menuVos = menuTransfer.toVos(this.list(QueryHelper.getQueryWrapper(criteria,Menu.class)));
        if (CollUtil.isEmpty(menuVos)){
            return null;
        }
        return TreeUtil.listToTree(menuVos);
    }

    /**
     * 新增系统菜单
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(MenuDto dto) {
        return save(menuTransfer.toEntity(dto));
    }

    /**
     * 修改系统菜单
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(MenuDto dto) {
        return updateById(menuTransfer.toEntity(dto));
    }

    /**
     * 批量删除系统菜单
     * 
     * @param ids 需要删除的系统菜单ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Set<Long> ids) {
        return removeByIds(ids);
    }


}
