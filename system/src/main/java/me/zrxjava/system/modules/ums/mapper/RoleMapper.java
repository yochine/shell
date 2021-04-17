package me.zrxjava.system.modules.ums.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zrxjava.common.base.CommonMapper;
import me.zrxjava.system.modules.ums.criteria.RoleCriteria;
import me.zrxjava.system.modules.ums.entity.Role;
import me.zrxjava.system.modules.ums.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色Mapper接口
 * 
 * @author zrxjava
 * @date 2021-04-12
 */
public interface RoleMapper extends CommonMapper<Role> {

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<Role> getByUserId(Long userId);

    /**
     * 分页查询
     * @param page
     * @param criteria
     * @return
     */
    Page<RoleVo> page(Page<RoleVo> page, @Param("criteria") RoleCriteria criteria);
}
