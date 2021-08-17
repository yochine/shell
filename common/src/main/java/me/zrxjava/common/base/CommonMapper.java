package me.zrxjava.common.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author void
 * @create 2020-12-17
 */
public interface CommonMapper<T> extends BaseMapper<T> {

    /**
     * 根据 entity 条件，查询全部记录
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @param scope 数据权限范围
     * @return List<T>
     */
    List<T> selectListByScope(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper, DataScope scope);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     * @param page 分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @param scope 数据权限范围
     * @return Page
     */
    <E extends IPage<T>> E selectPageByScope(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper,
                                             DataScope scope);

    /**
     * 根据 Wrapper 条件，查询总记录数
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @param scope 数据权限范围
     * @return Integer
     */
    Integer selectCountByScope(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper, DataScope scope);

    /**
     * 批量插入 仅适用于 mysql
     * <li> 注意: 这是自选字段 insert !!,如果个别字段在 entity 里为 null
     * 但是数据库中有配置默认值, insert 后数据库字段是为 null 而不是默认值 </li>
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(List<T> entityList);
}
