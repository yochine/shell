package me.zrxjava.system.modules.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BasePage;

import java.io.Serializable;

/**
 * 系统用户查询对象 sys_user
 *
 * @author zrxjava
 * @date 2021-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="系统用户查询对象", description="系统用户")
public class UserCriteria extends BasePage implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门名称")
    private Long deptId;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Integer enabled;

}
