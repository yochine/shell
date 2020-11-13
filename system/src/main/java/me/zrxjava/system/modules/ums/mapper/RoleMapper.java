package me.zrxjava.system.modules.ums.mapper;

import me.zrxjava.system.modules.ums.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<Role> getByUserId(Long userId);
}
