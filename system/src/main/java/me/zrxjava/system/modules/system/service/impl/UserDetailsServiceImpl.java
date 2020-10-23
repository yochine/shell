package me.zrxjava.system.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.zrxjava.system.support.bo.AdminUserDetails;
import me.zrxjava.system.modules.system.entity.User;
import me.zrxjava.system.modules.system.service.AuthorityService;
import me.zrxjava.system.modules.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * @author void
 * @create 2020-09-17
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthorityService authorityService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,userName));
        if (Objects.nonNull(user)){
            // 数据权限
            Set<Long> deptIds = authorityService.getDeptIds(user.getUserId(),user.getDeptId());
            // 操作权限
            Set<GrantedAuthority> grantedAuthorities = authorityService.getGrantedAuthorities(user.getUserId());
            return new AdminUserDetails(user,deptIds,grantedAuthorities);
        }
        throw new UsernameNotFoundException("用户不存在！");
    }
}
