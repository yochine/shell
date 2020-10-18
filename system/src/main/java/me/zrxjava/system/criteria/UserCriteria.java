package me.zrxjava.system.criteria;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import lombok.Data;
import me.zrxjava.system.entity.Dept;

import java.io.Serializable;

/**
 * 用户管理查询
 * @author void
 * @create 2020-10-04
 */
@Data
public class UserCriteria implements Serializable {


    @BindQuery(field = "nick_name")
    private String name;

    private Boolean enabled;

    private Long deptId;

    @BindQuery(comparison = Comparison.EQ, entity= Dept.class, field="name", condition="this.dept_id=dept_id")
    private String deptName;


}
