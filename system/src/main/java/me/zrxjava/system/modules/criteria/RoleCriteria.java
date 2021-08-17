package me.zrxjava.system.modules.criteria;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BasePage;

import java.io.Serializable;

/**
 * 角色查询对象 sys_role
 *
 * @author zrxjava
 * @date 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="角色查询对象", description="角色表")
public class RoleCriteria extends BasePage implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "角色编码")
    private String code;
    @ApiModelProperty(value = "数据权限")
    private String dataScope;

}
