package me.zrxjava.system.modules.ums.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import me.zrxjava.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * 数据字典Vo对象 sys_dict
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value=" 数据字典Vo对象", description="数据字典" )
public class DictVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long dictId;
    @ApiModelProperty(value = "字典名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;

}
