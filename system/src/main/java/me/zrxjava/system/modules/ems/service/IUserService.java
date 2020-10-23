package me.zrxjava.system.modules.ems.service;

import me.zrxjava.system.modules.ems.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
public interface IUserService extends IService<User> {

    void test(String name);
}
