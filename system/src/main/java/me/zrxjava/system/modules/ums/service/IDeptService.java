package me.zrxjava.system.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.ums.dto.DeptDto;
import me.zrxjava.system.modules.ums.entity.Dept;
import me.zrxjava.system.modules.ums.vo.DeptVo;

import java.util.List;
import java.util.Set;

/**
 * 部门Service接口
 * 
 * @author zrxjava
 * @date 2021-04-11
 */
public interface IDeptService extends IService<Dept>
{
    /**
     * 查询部门
     * 
     * @param deptId 部门ID
     * @return 部门
     */
    public DeptVo detail(Long deptId);


    /**
     * 查询部门列表
     * @param  name
     * @return 部门集合
     */
     public List<DeptVo> selectList(String name);

    /**
     * 新增部门
     * 
     * @param dto
     * @return 结果
     */
    public Boolean add(DeptDto dto);

    /**
     * 修改部门
     * 
     * @param dto
     * @return 结果
     */
    public Boolean edit(DeptDto dto);

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的部门ID
     * @return 结果
     */
    public Boolean delete(Set<Long> ids);

    /**
     * 获取子级以下部门集合(包含自身组织)
     * @param deptId
     * @return
     */
    Set<Long> getChildDepts(Long deptId);

}
