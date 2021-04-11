package me.zrxjava.system.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zrxjava.system.modules.ums.entity.RoleDept;
import me.zrxjava.system.modules.ums.mapper.RoleDeptMapper;
import me.zrxjava.system.modules.ums.service.IRoleDeptService;
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
public class RoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, RoleDept> implements IRoleDeptService {

    @Override
    public Set<Long> getCustomizeDept(Long roleId) {
        List<RoleDept> roleDepts =  this.list(new LambdaQueryWrapper<RoleDept>().select(RoleDept::getDeptId).eq(RoleDept::getRoleId,roleId));
        return roleDepts.parallelStream().map(RoleDept::getDeptId).collect(Collectors.toSet());
    }
}
