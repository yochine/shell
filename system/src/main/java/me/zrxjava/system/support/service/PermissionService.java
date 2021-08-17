package me.zrxjava.system.support.service;

import me.zrxjava.system.support.util.SecurityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author void
 * @create 2020-11-13
 */

@Service("ps")
public class PermissionService {

    public Boolean check(String ...permissions){
        // 获取当前用户的所有权限
        List<String> elPermissions = SecurityUtil.getCurrentUser().getGrantedAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return elPermissions.contains("Super_Admin") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }

}
