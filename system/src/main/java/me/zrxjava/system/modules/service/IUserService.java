package me.zrxjava.system.modules.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.criteria.UserCriteria;
import me.zrxjava.system.modules.dto.UserDto;
import me.zrxjava.system.modules.entity.User;
import me.zrxjava.system.modules.vo.UserVo;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 系统用户Service接口
 * 
 * @author zrxjava
 * @date 2021-04-17
 */
public interface IUserService extends IService<User> {
    /**
     * 查询系统用户
     * 
     * @param userId 系统用户ID
     * @return 系统用户
     */
    public UserVo detail(Long userId);


    /**
     * 查询系统用户列表
     * 
     * @param  criteria
     * @return 系统用户集合
     */
     public Page<UserVo> selectPage(UserCriteria criteria);

    /**
     * 新增系统用户
     * 
     * @param dto
     * @return 结果
     */
    public Boolean add(UserDto dto);

    /**
     * 修改系统用户
     * 
     * @param dto
     * @return 结果
     */
    public Boolean edit(UserDto dto);

    /**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的系统用户ID
     * @return 结果
     */
    public Boolean delete(Set<Long> ids);

    /**
     * 大数据用户流式查询
     *
     * @return
     */
    List<UserVo> cursorList() throws IOException;
}
