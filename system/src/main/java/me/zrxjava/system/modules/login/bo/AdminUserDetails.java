package me.zrxjava.system.modules.login.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import me.zrxjava.system.modules.ums.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author void
 * @create 2020-09-20
 */
@Getter
@AllArgsConstructor
@ToString
public class AdminUserDetails implements UserDetails, Serializable {

    private final User user;

    private final Set<Long> dataScopes;

    private final Set<GrantedAuthority> grantedAuthorities;

    private final Set<Long> roleIds;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}