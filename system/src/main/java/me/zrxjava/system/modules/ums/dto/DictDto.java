package me.zrxjava.system.modules.ums.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.common.validated.group.Update;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 数据字典Dto对象 sys_dict
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Data
@ApiModel(value="数据字典dto对象", description="数据字典")
public class DictDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID 不能为空" ,groups = Update.class)
    private Long dictId;
    @ApiModelProperty(value = "字典名称")
    @NotBlank(message = "字典名称 不能为空")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;

}
