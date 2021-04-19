package me.zrxjava.system.support.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.component.redis.RedisUtil;
import me.zrxjava.system.modules.bo.AdminUserDetails;
import me.zrxjava.system.modules.entity.Role;
import me.zrxjava.system.modules.entity.User;
import me.zrxjava.system.modules.service.IRoleService;
import me.zrxjava.system.modules.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author void
 * @create 2020-09-17
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;

    private final AuthorityService authorityService;

    private final IRoleService roleService;

    private final RedisUtil redisUtil;

    @Value("${login.user.expire}")
    private Long USER_EXPIRE;
    @Value("${login.user.admin}")
    private String USER_ADMIN;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AdminUserDetails adminUserDetails = redisUtil.getCacheObject(USER_ADMIN + ":" + userName);
        if (Objects.nonNull(adminUserDetails)) return adminUserDetails;
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,userName));
        if (Objects.nonNull(user)){
            // 查询用户角色
            List<Role> roles = roleService.getByUserId(user.getUserId());
            // 数据权限
            Set<Long> deptIds = authorityService.getDeptIds(roles,user.getDeptId());
            // 操作权限
            Set<GrantedAuthority> grantedAuthorities = authorityService.getGrantedAuthorities(roles,user.getIsAdmin().equals(1));
            Set<Long>  roleIds = roles.parallelStream().map(Role::getRoleId).collect(Collectors.toSet());
            AdminUserDetails userDetails =  new AdminUserDetails(user,deptIds,grantedAuthorities,roleIds);
            redisUtil.setCacheObject(USER_ADMIN + ":" + userName,userDetails,USER_EXPIRE);
            return userDetails;
        }
        throw new UsernameNotFoundException("用户不存在!");
    }
}
