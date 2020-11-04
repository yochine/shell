package me.zrxjava.system.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import me.zrxjava.common.enums.DataScopeEnum;
import me.zrxjava.system.modules.system.entity.Menu;
import me.zrxjava.system.modules.system.entity.Role;
import me.zrxjava.system.modules.system.service.AuthorityService;
import me.zrxjava.system.modules.system.service.IDeptService;
import me.zrxjava.system.modules.system.service.IMenuService;
import me.zrxjava.system.modules.system.service.IRoleService;
import me.zrxjava.system.modules.system.service.IRolesDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限service实现
 * @author void
 * @create 2020-09-20
 */
@Service
public class AuthorityServiceImpl implements AuthorityService
{

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IRolesDeptsService rolesDeptsService;

    @Autowired
    private IMenuService menuService;

    @Override
    public Set<Long> getDeptIds(Long userId, Long deptId) {
        // 用于存储部门id
        Set<Long> deptIds = Sets.newHashSet();
        // 查询用户角色
        List<Role> roles = roleService.getByUserId(userId);
        // 获取对应的部门ID
        for (Role role : roles) {
            DataScopeEnum dataScopeEnum = DataScopeEnum.find(role.getDataScope());
            switch (Objects.requireNonNull(dataScopeEnum)) {
                case THIS_LEVEL:
                    deptIds.add(deptId);
                    break;
                case INCLUDE_CHILD_LEVEL:
                    deptIds.addAll(deptService.getChildDepts(deptId));
                    break;
                case CUSTOMIZE:
                    deptIds.addAll(rolesDeptsService.getCustomizeDepts(role.getRoleId()));
                    break;
                case SELF_LEVEL:
                    deptIds.add(-1L);
                    break;
                default:
                    return deptIds;
            }
        }
        return deptIds;
    }

    @Override
    public Set<GrantedAuthority> getGrantedAuthorities(Long userId) {
        Set<String> sets = Sets.newHashSet();
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
