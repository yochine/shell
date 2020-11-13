package me.zrxjava.system.support.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import me.zrxjava.common.enums.DataScopeEnum;
import me.zrxjava.system.modules.ums.entity.Menu;
import me.zrxjava.system.modules.ums.entity.Role;
import me.zrxjava.system.modules.ums.service.IDeptService;
import me.zrxjava.system.modules.ums.service.IMenuService;
import me.zrxjava.system.modules.ums.service.IRoleService;
import me.zrxjava.system.modules.ums.service.IRolesDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限service
 * @author void
 * @create 2020-09-20
 */
@Service
public class AuthorityService
{

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IRolesDeptsService rolesDeptsService;

    @Autowired
    private IMenuService menuService;

    /**
     * 用户数据权限
     * @param userId
     * @param deptId
     * @return
     */
    public Map<Integer, Set<Long>> getDeptIds(Long userId, Long deptId) {

        Map<Integer, Set<Long>> map = Maps.newHashMap();
        // 查询用户角色
        List<Role> roles = roleService.getByUserId(userId);
        // 获取对应的部门ID
        for (Role role : roles) {
            // 用于存储部门id
            Set<Long> deptIds = Sets.newHashSet();
            DataScopeEnum dataScopeEnum = DataScopeEnum.find(role.getDataScope());
            switch (Objects.requireNonNull(dataScopeEnum)) {
                case THIS_LEVEL:
                    deptIds.add(deptId);
                    map.put(DataScopeEnum.THIS_LEVEL.getValue(),deptIds);
                    break;
                case INCLUDE_CHILD_LEVEL:
                    deptIds.addAll(deptService.getChildDepts(deptId));
                    map.put(DataScopeEnum.INCLUDE_CHILD_LEVEL.getValue(),deptIds);
                    break;
                case CUSTOMIZE:
                    deptIds.addAll(rolesDeptsService.getCustomizeDepts(role.getRoleId()));
                    map.put(DataScopeEnum.CUSTOMIZE.getValue(),deptIds);
                    break;
                case ALL:
                    map.put(DataScopeEnum.ALL.getValue(),deptIds);
                    break;
                default:
                    deptIds.add(-1L);
                    map.put(DataScopeEnum.SELF_LEVEL.getValue(),deptIds);
                    return map;
            }
        }
        return map;
    }

    /**
     * 用户操作权限（细粒化到按钮）
     * @param userId
     * @param isAdmin
     * @return
     */
    public Set<GrantedAuthority> getGrantedAuthorities(Long userId, Boolean isAdmin) {
        Set<String> sets = Sets.newHashSet();
        if (isAdmin){
            sets.add("Super_Admin");
            return sets.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        }
        // 查询用户角色
        List<Role> roles = roleService.getByUserId(userId);
        roles.forEach(role -> {
            List<Menu> menus = menuService.getByRoleId(role.getRoleId());
            if (CollectionUtil.isNotEmpty(menus)){
                sets.addAll(menus.stream().filter(menu -> StrUtil.isNotBlank(menu.getPermission()))
                        .map(Menu::getPermission).collect(Collectors.toSet()));
            }
        });
        return sets.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
