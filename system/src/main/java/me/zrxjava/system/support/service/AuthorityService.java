package me.zrxjava.system.support.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.enums.DataScopeEnum;
import me.zrxjava.sercurity.bo.UserGrantedAuthority;
import me.zrxjava.system.modules.ums.entity.Role;
import me.zrxjava.system.modules.ums.service.IDeptService;
import me.zrxjava.system.modules.ums.service.IMenuService;
import me.zrxjava.system.modules.ums.service.IRoleDeptService;
import me.zrxjava.system.modules.ums.vo.MenuVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限service
 * @author void
 * @create 2020-09-20
 */
@Service
@RequiredArgsConstructor
public class AuthorityService
{

    private final IDeptService deptService;

    private final IRoleDeptService roleDeptService;

    private final IMenuService menuService;

    /**
     * 用户数据权限
     * 当Set包含0时为超管权限 为空则为个人权限 不为空不包含0 为自定义或其他权限
     * @param roles 用户具有的角色集合
     * @param deptId
     * @return
     */
    public Set<Long> getDeptIds(List<Role> roles, Long deptId) {

        // 用于存储部门id
        Set<Long> deptIds = Sets.newHashSet();
        // 获取对应的部门ID
        for (Role role : roles) {
            DataScopeEnum dataScopeEnum = DataScopeEnum.find(Integer.parseInt(role.getDataScope()));
            switch (Objects.requireNonNull(dataScopeEnum)) {
                case THIS_LEVEL:
                    deptIds.add(deptId);
                    break;
                case INCLUDE_CHILD_LEVEL:
                    deptIds.addAll(deptService.getChildDepts(deptId));
                    break;
                case CUSTOMIZE:
                    deptIds.addAll(roleDeptService.getCustomizeDept(role.getRoleId()));
                    break;
                case ALL:
                    deptIds.add(0L);
                    break;
                default:
                    return deptIds;
            }
        }
        return deptIds;
    }

    /**
     * 用户角色操作权限（细粒化到按钮）
     * @param roles 用户具有的角色集合
     * @param isAdmin
     * @return
     */
    public Set<GrantedAuthority> getGrantedAuthorities(List<Role> roles, Boolean isAdmin) {
        Set<String> sets = Sets.newHashSet();
        if (isAdmin){
            sets.add("Super_Admin");
            return sets.stream().map(UserGrantedAuthority::new).collect(Collectors.toSet());
        }
        roles.forEach(role -> {
            List<MenuVo> menus = menuService.getByRoleId(role.getRoleId());
            if (CollectionUtil.isNotEmpty(menus)){
                sets.addAll(menus.stream().filter(menu -> StrUtil.isNotBlank(menu.getPermission()))
                        .map(MenuVo::getPermission).collect(Collectors.toSet()));
            }
        });
        return sets.stream().map(UserGrantedAuthority::new).collect(Collectors.toSet());
    }
}
