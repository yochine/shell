package me.zrxjava.system.modules.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.TreeNode;

import java.io.Serializable;

/**
 * @author void
 * @create 2020-12-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVo extends TreeNode implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long menuId;

    @ApiModelProperty(value = "上级菜单ID")
    private Long pid;

    @ApiModelProperty(value = "子菜单数目")
    private Integer subCount;

    @ApiModelProperty(value = "菜单类型0目录1菜单2按钮")
    private Integer type;

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

    @ApiModelProperty(value = "菜单标题")
    private String label;

    @ApiModelProperty(value = "是否外链0不是1是")
    private Boolean iFrame;

    @ApiModelProperty(value = "缓存0否1是")
    private Boolean cache;

    @ApiModelProperty(value = "隐藏0否1是")
    private Boolean hidden;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "位置")
    private String position;


    @Override
    public Long getId() {
        return this.getMenuId();
    }
}
