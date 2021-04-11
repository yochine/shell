package me.zrxjava.system.modules.ums.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import me.zrxjava.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门对象 sys_dept
 * 
 * @author zrxjava
 * @date 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
@ApiModel(value="sys_dept对象", description="部门")
public class Dept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(value = "dept_id", type = IdType.ASSIGN_ID)
    private Long deptId;

    /** 上级部门 */
    private Long pid;

    /** 子部门数目 */
    private Long subCount;

    /** 名称 */
    private String name;

    /** 排序 */
    private Long sort;

    /** 状态1：有效0：无效 */
    private String enabled;


}
