package me.zrxjava.system.modules.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zrxjava.common.validated.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 数据字典详情Dto对象 sys_dict_detail
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@ApiModel(value="数据字典详情dto对象", description="数据字典详情")
public class DictDetailDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID 不能为空" ,groups = Update.class)
    private Long dictDetailId;
    @ApiModelProperty(value = "字典id")
    private Long dictId;
    @ApiModelProperty(value = "字典名称")
    @NotNull(message = "字典名称不能为空" ,groups = Update.class)
    private String dictName;
    @ApiModelProperty(value = "字典标签")
    @NotBlank(message = "字典标签 不能为空")
    private String label;
    @ApiModelProperty(value = "字典值")
    @NotBlank(message = "字典值 不能为空")
    private String value;
    @ApiModelProperty(value = "排序")
    private Integer dictSort;

}
