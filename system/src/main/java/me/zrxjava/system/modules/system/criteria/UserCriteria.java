package me.zrxjava.system.modules.system.criteria;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户管理查询
 * @author void
 * @create 2020-10-04
 */
@Data
public class UserCriteria implements Serializable {


    private String name;

    private Boolean enabled;

    private Long deptId;

    private String deptName;


}
