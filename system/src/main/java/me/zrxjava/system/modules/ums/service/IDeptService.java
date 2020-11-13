package me.zrxjava.system.modules.ums.service;

import me.zrxjava.system.modules.ums.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 获取子级以下部门集合(包含自身组织)
     * @param deptId
     * @return
     */
    Set<Long> getChildDepts(Long deptId);

}
