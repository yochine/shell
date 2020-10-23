package me.zrxjava.system.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.zrxjava.system.modules.system.entity.RolesDepts;
import me.zrxjava.system.modules.system.mapper.RolesDeptsMapper;
import me.zrxjava.system.modules.system.service.IRolesDeptsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色部门关联 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
@Service
public class RolesDeptsServiceImpl extends ServiceImpl<RolesDeptsMapper, RolesDepts> implements IRolesDeptsService {

    @Override
    public Set<Long> getCustomizeDepts(Long roleId) {
        List<RolesDepts> rolesDepts =  this.list(new LambdaQueryWrapper<RolesDepts>().select(RolesDepts::getDeptId).eq(RolesDepts::getRoleId,roleId));
        return rolesDepts.parallelStream().map(RolesDepts::getDeptId).collect(Collectors.toSet());
    }
}
