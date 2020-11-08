package me.zrxjava.system.modules.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.system.modules.system.entity.User;

import java.util.List;

/**
 * @author void
 * @create 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends User {

    private String deptName;

//    private List<Role> roles;

    private List<String> roleNames;

//    private Dept dept;

}
