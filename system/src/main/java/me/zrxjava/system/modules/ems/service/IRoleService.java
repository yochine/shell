package me.zrxjava.system.modules.ems.service;

import me.zrxjava.system.modules.ems.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<Role> getByUserId(Long userId);

}
