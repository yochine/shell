package me.zrxjava.system.modules.ums.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import me.zrxjava.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * 数据字典详情Vo对象 sys_dict_detail
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value=" 数据字典详情Vo对象", description="数据字典详情" )
public class DictDetailVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long dictDetailId;
    @ApiModelProperty(value = "字典id")
    private Long dictId;
    @ApiModelProperty(value = "字典名称")
    private String dictName;
    @ApiModelProperty(value = "字典标签")
    private String label;
    @ApiModelProperty(value = "字典值")
    private String value;
    @ApiModelProperty(value = "排序")
    private Integer dictSort;

}
