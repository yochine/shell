package me.zrxjava.system.modules.system.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户管理查询
 * @author void
 * @create 2020-10-04
 */
@Data
@ApiModel(value="User查询对象")
public class UserCriteria implements Serializable {

    @ApiModelProperty(value = "用户名")
    private String name;

    private Boolean enabled;

    private Long deptId;

    private String deptName;


}
