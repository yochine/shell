package me.zrxjava.system.modules.system.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * 权限service
 * @author void
 * @create 2020-09-20
 */
public interface AuthorityService {

    /**
     * 用户数据权限
     * @param userId
     * @param deptId
     * @return
     */
    Set<Long> getDeptIds(Long userId, Long deptId);

    /**
     * 用户操作权限（细粒化到按钮）
     * @param userId
     * @return
     */
    Set<GrantedAuthority> getGrantedAuthorities(Long userId);
}
