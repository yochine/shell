package me.zrxjava.system.support.util;

import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.exception.BadRequestException;
import me.zrxjava.system.modules.login.bo.AdminUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * 获取当前登录的用户
 * @author void
 */
@Slf4j
public class SecurityUtil {

    /**
     * 获取当前登录的用户
     * @return UserDetails
     */
    public static AdminUserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "当前登录状态过期");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            return (AdminUserDetails) authentication.getPrincipal();
        }
        throw new BadRequestException(HttpStatus.UNAUTHORIZED, "找不到当前登录的信息");
    }

    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }

    /**
     * 获取系统用户ID
     * @return 系统用户ID
     */
    public static Long getCurrentUserId() {
        AdminUserDetails userDetails = getCurrentUser();
        return userDetails.getUser().getUserId();
    }

    /**
     * 获取当前用户的数据权限
     * @return /
     */
    public static Set<Long> getCurrentUserDataScopes(){
        AdminUserDetails userDetails = getCurrentUser();
        return userDetails.getDataScopes();
    }

    /**
     * 获取当前用户的角色id集合
     * @return /
     */
    public static Set<Long> getCurrentUserRoleIds(){
        AdminUserDetails userDetails = getCurrentUser();
        return userDetails.getRoleIds();
    }


}
