package me.zrxjava.system.modules.ums.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import me.zrxjava.system.modules.ums.entity.Dept;
import me.zrxjava.system.modules.ums.mapper.DeptMapper;
import me.zrxjava.system.modules.ums.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {


    @Override
    public Set<Long> getChildDepts(Long deptId) {
        Set<Long> sets = Sets.newHashSet(deptId);
        List<Dept> deptList = this.list(new LambdaQueryWrapper<Dept>().eq(Dept::getPid,deptId));
        if (CollectionUtil.isNotEmpty(deptList)){
            deptList.forEach(dept -> {
                if (dept != null && dept.getEnabled()) {
                    List<Dept> depts = this.list(new LambdaQueryWrapper<Dept>().eq(Dept::getPid,dept.getDeptId()));
                    if (CollectionUtil.isNotEmpty(depts)) {
                        sets.addAll(getChildDepts(dept.getDeptId()));
                    }
                    sets.add(dept.getDeptId());
                }
            });
        }
        return sets;
    }

}
