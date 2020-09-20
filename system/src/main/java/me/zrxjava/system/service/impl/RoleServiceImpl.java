package me.zrxjava.system.service.impl;

import me.zrxjava.system.entity.Role;
import me.zrxjava.system.mapper.RoleMapper;
import me.zrxjava.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public List<Role> getByUserId(Long userId) {
        return mapper.getByUserId(userId);
    }
}
