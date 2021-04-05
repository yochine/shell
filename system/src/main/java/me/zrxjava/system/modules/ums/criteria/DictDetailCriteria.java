package me.zrxjava.system.modules.ums.criteria;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BasePage;

import java.io.Serializable;

/**
 * 数据字典详情查询对象 sys_dict_detail
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="数据字典详情查询对象", description="数据字典详情")
public class DictDetailCriteria extends BasePage implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典id")
    private Long dictId;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

}
