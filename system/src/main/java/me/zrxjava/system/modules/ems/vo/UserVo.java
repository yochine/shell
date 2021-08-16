package me.zrxjava.system.modules.ems.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.system.modules.ems.entity.User;

import java.util.List;

/**
 * @author void
 * @create 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends User {

    private String deptName;

//    @BindEntityList(entity = Role.class, condition = "role_id=role_id")
//    private List<Role> roles;

    private List<String> roleNames;

//    @BindEntity(entity = Dept.class, condition="dept_id=dept_id")
//    private Dept dept;

}
