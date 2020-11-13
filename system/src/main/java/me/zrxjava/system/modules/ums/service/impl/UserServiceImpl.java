package me.zrxjava.system.modules.ums.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.zrxjava.system.modules.ums.entity.User;
import me.zrxjava.system.modules.ums.mapper.UserMapper;
import me.zrxjava.system.modules.ums.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @Async
    public void test(String name) {
        log.info("进入user的test方法，name：{}",name);
    }
}
