package me.zrxjava.system.modules.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import me.zrxjava.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典对象 sys_dict
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
@ApiModel(value="sys_dict对象", description="数据字典")
public class Dict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(value = "dict_id", type = IdType.ASSIGN_ID)
    private Long dictId;

    /** 字典名称 */
    private String name;

    /** 描述 */
    private String description;


}
