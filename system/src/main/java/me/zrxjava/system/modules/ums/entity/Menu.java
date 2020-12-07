package me.zrxjava.system.modules.ums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import me.zrxjava.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="系统菜单")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "menuId")
    @TableId(value = "menu_id", type = IdType.ASSIGN_ID)
    private Integer menuId;

    @ApiModelProperty(value = "上级菜单ID")
    private Integer pid;

    @ApiModelProperty(value = "子菜单数目")
    private Integer subCount;

    @ApiModelProperty(value = "菜单类型0目录1菜单2按钮")
    private Integer type;

    @ApiModelProperty(value = "菜单标题")
    private String name;

    @ApiModelProperty(value = "组件名称")
    private String componentName;

    @ApiModelProperty(value = "组件路径")
    private String path;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    private String linkPath;

    @ApiModelProperty(value = "是否外链0不是1是")
    private Boolean iFrame;

    @ApiModelProperty(value = "缓存0否1是")
    private Boolean cache;

    @ApiModelProperty(value = "隐藏0否1是")
    private Boolean hidden;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "位置")
    private String position;

}
