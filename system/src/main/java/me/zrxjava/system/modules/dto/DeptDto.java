package me.zrxjava.system.modules.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zrxjava.common.validated.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门Dto对象 sys_dept
 *
 * @author zrxjava
 * @date 2021-04-11
 */
@Data
@ApiModel(value="部门dto对象", description="部门")
public class DeptDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID 不能为空" ,groups = Update.class)
    private Long deptId;
    @ApiModelProperty(value = "上级部门")
    @NotNull(message = "上级部门 不能为空")
    private Long pid;
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称 不能为空")
    private String name;
    @ApiModelProperty(value = "排序")
    private Long sort;
    @ApiModelProperty(value = "状态1：有效0：无效")
    @NotNull(message = "状态1：有效0：无效 不能为空")
    private Integer enabled;

}
