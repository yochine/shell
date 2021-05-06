package me.zrxjava.system.modules.mapper;

import me.zrxjava.common.base.CommonMapper;
import me.zrxjava.system.modules.entity.User;
import me.zrxjava.system.modules.vo.UserVo;
import org.apache.ibatis.cursor.Cursor;

/**
 * 系统用户Mapper接口
 * 
 * @author zrxjava
 * @date 2021-04-17
 */
public interface UserMapper extends CommonMapper<User> {

    /**
     * 大数据流式查询
     * @return
     */
    Cursor<UserVo> cursorList();
}
