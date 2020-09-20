package me.zrxjava.system.service.impl;

import me.zrxjava.system.entity.User;
import me.zrxjava.system.mapper.UserMapper;
import me.zrxjava.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
