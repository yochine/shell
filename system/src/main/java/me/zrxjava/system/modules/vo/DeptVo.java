package me.zrxjava.system.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.TreeNode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门Vo对象 sys_dept
 *
 * @author zrxjava
 * @date 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value=" 部门Vo对象", description="部门" )
public class DeptVo extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long deptId;
    @ApiModelProperty(value = "上级部门")
    private Long pid;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "排序")
    private Long sort;
    @ApiModelProperty(value = "状态1：有效0：无效")
    private String enabled;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @Override
    public Long getId() {
        return this.getDeptId();
    }
}
