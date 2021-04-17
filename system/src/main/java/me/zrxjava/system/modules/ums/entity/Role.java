package me.zrxjava.system.modules.ums.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import me.zrxjava.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色对象 sys_role
 * 
 * @author zrxjava
 * @date 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@ApiModel(value="sys_role对象", description="角色表")
public class Role extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

    /** 名称 */
    private String name;

    /** 角色编码 */
    private String code;

    /** 角色级别 */
    private Long level;

    /** 描述 */
    private String description;

    /** 数据权限 */
    private String dataScope;


}
