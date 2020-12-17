package me.zrxjava.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据权限参数
 * @author void
 * @create 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap<Object, Object> {
    /**
     * 限制范围的字段名称
     */
    private String scopeName = "dept_id";

    /**
     * 具体的数据范围
     */
    private List<Long> deptIds = new ArrayList<>();

    /**
     * 是否只查询本部门
     */
    private Boolean isOnly = false;
}
