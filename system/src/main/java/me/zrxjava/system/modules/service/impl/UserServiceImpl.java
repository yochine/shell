package me.zrxjava.system.modules.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.utils.QueryHelper;
import me.zrxjava.system.modules.criteria.UserCriteria;
import me.zrxjava.system.modules.dto.UserDto;
import me.zrxjava.system.modules.entity.User;
import me.zrxjava.system.modules.mapper.UserMapper;
import me.zrxjava.system.modules.service.IUserService;
import me.zrxjava.system.modules.transfer.UserTransfer;
import me.zrxjava.system.modules.vo.UserVo;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * 系统用户Service业务层处理
 * 
 * @author zrxjava
 * @date 2021-04-17
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserTransfer userTransfer;
    private final UserMapper userMapper;

    /**
     * 查询系统用户
     * 
     * @param userId 系统用户ID
     * @return 系统用户
     */
    @Override
    public UserVo detail(Long userId) {
        return userTransfer.toVo(getById(userId));
    }

    /**
     * 查询系统用户列表
     * 
     * @param criteria
     * @return 系统用户
     */
    @Override
    public Page<UserVo> selectPage(UserCriteria criteria){
        Page<User> page = new Page<>(criteria.getCurrent(), criteria.getSize());
        return userTransfer.toPageVo(this.page(page, QueryHelper.getQueryWrapper(criteria,User.class)));
    }

    /**
     * 新增系统用户
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(UserDto dto) {
        return save(userTransfer.toEntity(dto));
    }

    /**
     * 修改系统用户
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(UserDto dto) {
        return updateById(userTransfer.toEntity(dto));
    }

    /**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的系统用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Set<Long> ids) {
        return removeByIds(ids);
    }


    /**
     * mybatis 流式查询
     * 采用@Transactional 保证流处于打开状态 但是要注意spring事务失效问题
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserVo> cursorList() throws IOException {
        List<UserVo> vos = new ArrayList<>();
        try (Cursor<UserVo> userVos = userMapper.cursorList()){
           userVos.forEach(vos::add);
        }
        return vos;
    }
}
