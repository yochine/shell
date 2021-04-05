package me.zrxjava.system.modules.ums.criteria;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BasePage;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

/**
 * 数据字典查询对象 sys_dict
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="数据字典查询对象", description="数据字典")
public class DictCriteria extends BasePage implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;

}
