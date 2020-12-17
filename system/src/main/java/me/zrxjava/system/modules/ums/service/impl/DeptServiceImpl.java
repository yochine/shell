package me.zrxjava.system.modules.ums.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import me.zrxjava.system.modules.ums.entity.Dept;
import me.zrxjava.system.modules.ums.mapper.DeptMapper;
import me.zrxjava.system.modules.ums.service.IDeptService;
import me.zrxjava.system.modules.ums.transfer.DeptTransfer;
import me.zrxjava.system.modules.ums.vo.DeptTree;
import me.zrxjava.system.modules.ums.vo.DeptVo;
import me.zrxjava.system.support.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {


    private final DeptTransfer deptTransfer;

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

    @Override
    public List<DeptTree> buildTree() {
        List<DeptVo> depts = deptTransfer.toVos(this.list());
        Set<Long> deptIds = SecurityUtil.getCurrentUserDataScopes();
        //个人数据权限
        if(CollectionUtil.isEmpty(deptIds)){
            return null;
        }
        if (!deptIds.contains(0L)){
            depts = depts.parallelStream().filter(deptVo -> deptIds.contains(deptVo.getDeptId())).collect(Collectors.toList());
        }
//        return TreeUtil.buildByRecursive(menuList,parentId);
        return null;
    }

}
