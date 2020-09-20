package me.zrxjava.system.service.impl;

import me.zrxjava.system.entity.UsersRoles;
import me.zrxjava.system.mapper.UsersRolesMapper;
import me.zrxjava.system.service.IUsersRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@Service
public class UsersRolesServiceImpl extends ServiceImpl<UsersRolesMapper, UsersRoles> implements IUsersRolesService {

}
