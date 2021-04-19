package me.zrxjava.system.modules.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BaseEntity;

/**
 * 数据字典详情对象 sys_dict_detail
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_detail")
@ApiModel(value="sys_dict_detail对象", description="数据字典详情")
public class DictDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(value = "dict_detail_id", type = IdType.ASSIGN_ID)
    private Long dictDetailId;

    /** 字典id */
    private Long dictId;

    /** 字典名称 */
    private String dictName;

    /** 字典标签 */
    private String label;

    /** 字典值 */
    private String value;

    /** 排序 */
    private Integer dictSort;


}
