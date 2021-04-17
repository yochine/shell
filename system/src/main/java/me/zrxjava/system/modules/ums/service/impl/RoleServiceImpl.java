package me.zrxjava.system.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.system.modules.ums.criteria.RoleCriteria;
import me.zrxjava.system.modules.ums.dto.RoleDto;
import me.zrxjava.system.modules.ums.entity.Role;
import me.zrxjava.system.modules.ums.entity.RoleDept;
import me.zrxjava.system.modules.ums.entity.RoleMenu;
import me.zrxjava.system.modules.ums.mapper.RoleDeptMapper;
import me.zrxjava.system.modules.ums.mapper.RoleMapper;
import me.zrxjava.system.modules.ums.mapper.RoleMenuMapper;
import me.zrxjava.system.modules.ums.service.IRoleService;
import me.zrxjava.system.modules.ums.transfer.RoleTransfer;
import me.zrxjava.system.modules.ums.vo.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
/**
 * 角色Service业务层处理
 * 
 * @author zrxjava
 * @date 2021-04-12
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    private final RoleTransfer roleTransfer;
    private final RoleMapper mapper;
    private final RoleMenuMapper roleMenuMapper;
    private final RoleDeptMapper roleDeptMapper;

    /**
     * 查询角色
     * 
     * @param roleId 角色ID
     * @return 角色
     */
    @Override
    public RoleVo detail(Long roleId) {
        return roleTransfer.toVo(getById(roleId));
    }

    /**
     * 查询角色列表
     * 
     * @param criteria
     * @return 角色
     */
    @Override
    public Page<RoleVo> selectPage(RoleCriteria criteria){
        Page<RoleVo> page = new Page<>(criteria.getCurrent(), criteria.getSize());
        return mapper.page(page,criteria);
    }

    /**
     * 新增角色
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(RoleDto dto) {
        Role role = roleTransfer.toEntity(dto);
        boolean b = save(role);
        if (null != dto.getDeptIds()){
            dto.getDeptIds().forEach(deptId ->roleDeptMapper.insert(RoleDept.builder()
                    .roleId(role.getRoleId()).deptId(deptId).build()));
        }
        if (null != dto.getMenuIds()){
            dto.getMenuIds().forEach(menuId -> roleMenuMapper.insert(RoleMenu.builder()
            .roleId(role.getRoleId()).menuId(menuId).build()));
        }
        return b;
    }

    /**
     * 修改角色
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(RoleDto dto) {
        roleDeptMapper.delete(new LambdaQueryWrapper<RoleDept>().eq(RoleDept::getRoleId,dto.getRoleId()));
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId,dto.getRoleId()));
        if (null != dto.getMenuIds()){
            dto.getMenuIds().forEach(menuId -> roleMenuMapper.insert(RoleMenu.builder()
                    .roleId(dto.getRoleId()).menuId(menuId).build()));
        }
        if (null != dto.getDeptIds()){
            dto.getDeptIds().forEach(deptId ->roleDeptMapper.insert(RoleDept.builder()
                    .roleId(dto.getRoleId()).deptId(deptId).build()));
        }
        return updateById(roleTransfer.toEntity(dto));
    }

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的角色ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Set<Long> ids) {
        roleDeptMapper.delete(new LambdaQueryWrapper<RoleDept>().in(RoleDept::getRoleId,ids));
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getRoleId,ids));
        return removeByIds(ids);
    }


    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> getByUserId(Long userId) {
        return mapper.getByUserId(userId);
    }
}
