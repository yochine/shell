package me.zrxjava.system.modules.ums.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author void
 * @create 2020-12-14
 */
@Data
public class DeptVo {

    @ApiModelProperty(value = "ID")
    private Long deptId;

    @ApiModelProperty(value = "上级部门")
    private Long pid;

    @ApiModelProperty(value = "子部门数目")
    private Integer subCount;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer deptSort;

    @ApiModelProperty(value = "状态1：有效0：无效")
    private Boolean enabled;
}
