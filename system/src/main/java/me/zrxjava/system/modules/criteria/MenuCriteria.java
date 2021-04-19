package me.zrxjava.system.modules.criteria;


import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zrxjava.common.annotation.Query;

import java.io.Serializable;

/**
 * 系统菜单查询对象 sys_menu
 *
 * @author zrxjava
 * @date 2021-02-24
 */
@Data
@ApiModel(value="系统菜单查询对象", description="系统菜单")
public class MenuCriteria  implements Serializable{

    @ApiModelProperty(value = "菜单位置")
    private String position;

    @ApiModelProperty(value = "菜单类型0目录1菜单2按钮")
    private Long type;

    @ApiModelProperty(value = "菜单标题")
    @Query(type = SqlKeyword.LIKE)
    private String label;

    @Query(type = SqlKeyword.ORDER_BY)
    private Integer sort;

}
