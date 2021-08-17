package me.zrxjava.system.modules.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import me.zrxjava.system.modules.dto.DeptDto;
import me.zrxjava.system.modules.entity.Dept;
import me.zrxjava.system.modules.mapper.DeptMapper;
import me.zrxjava.system.modules.service.IDeptService;
import me.zrxjava.system.modules.transfer.DeptTransfer;
import me.zrxjava.system.modules.vo.DeptVo;
import me.zrxjava.system.support.util.TreeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 部门Service业务层处理
 * 
 * @author zrxjava
 * @date 2021-04-11
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    private final DeptTransfer deptTransfer;

    /**
     * 查询部门
     * 
     * @param deptId 部门ID
     * @return 部门
     */
    @Override
    public DeptVo detail(Long deptId) {
        return deptTransfer.toVo(getById(deptId));
    }

    /**
     * 查询部门列表
     * 
     * @param name
     * @return 部门
     */
    @Override
    public List<DeptVo> selectList(String name){
        LambdaQueryWrapper<Dept> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(Dept::getSort);
        if (StrUtil.isNotBlank(name)){
            queryWrapper.like(Dept::getName,name);
        }
        List<DeptVo> deptVos = deptTransfer.toVos(this.list(queryWrapper));
        if (CollUtil.isEmpty(deptVos)){
            return null;
        }
        return TreeUtil.listToTree(deptVos);
    }

    /**
     * 新增部门
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(DeptDto dto) {
        return save(deptTransfer.toEntity(dto));
    }

    /**
     * 修改部门
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(DeptDto dto) {
        return updateById(deptTransfer.toEntity(dto));
    }

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的部门ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Set<Long> ids) {
        return removeByIds(ids);
    }


    @Override
    public Set<Long> getChildDepts(Long deptId) {
        Set<Long> sets = Sets.newHashSet(deptId);
        List<Dept> deptList = this.list(new LambdaQueryWrapper<Dept>().eq(Dept::getPid, deptId));
        if (CollectionUtil.isNotEmpty(deptList)) {
            deptList.forEach(dept -> {
                if (dept != null && "1".equals(dept.getEnabled())) {
                    List<Dept> depts = this.list(new LambdaQueryWrapper<Dept>().eq(Dept::getPid, dept.getDeptId()));
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
