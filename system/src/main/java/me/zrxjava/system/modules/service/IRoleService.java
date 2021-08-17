package me.zrxjava.system.modules.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.criteria.RoleCriteria;
import me.zrxjava.system.modules.dto.RoleDto;
import me.zrxjava.system.modules.entity.Role;
import me.zrxjava.system.modules.vo.RoleVo;

import java.util.List;
import java.util.Set;

/**
 * 角色Service接口
 * 
 * @author zrxjava
 * @date 2021-04-12
 */
public interface IRoleService extends IService<Role> {
    /**
     * 查询角色
     * 
     * @param roleId 角色ID
     * @return 角色
     */
    public RoleVo detail(Long roleId);


    /**
     * 查询角色列表
     * 
     * @param  criteria
     * @return 角色集合
     */
     public Page<RoleVo> selectPage(RoleCriteria criteria);

    /**
     * 新增角色
     * 
     * @param dto
     * @return 结果
     */
    public Boolean add(RoleDto dto);

    /**
     * 修改角色
     * 
     * @param dto
     * @return 结果
     */
    public Boolean edit(RoleDto dto);

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的角色ID
     * @return 结果
     */
    public Boolean delete(Set<Long> ids);

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<Role> getByUserId(Long userId);

}
