package me.zrxjava.system.modules.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统菜单Dto对象 sys_menu
 *
 * @author zrxjava
 * @date 2021-02-24
 */
@Data
@ApiModel(value="系统菜单dto对象", description="系统菜单")
public class MenuDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long menuId;
    @ApiModelProperty(value = "上级菜单ID")
    private Long pid;
    @ApiModelProperty(value = "菜单位置")
    @NotBlank(message = "菜单位置 不能为空")
    private String position;
    @ApiModelProperty(value = "菜单类型0目录1菜单2按钮")
    @NotNull(message = "菜单类型0目录1菜单2按钮 不能为空")
    private Long type;
    @ApiModelProperty(value = "菜单标题")
    @NotBlank(message = "菜单标题 不能为空")
    private String label;
    @ApiModelProperty(value = "组件名称")
    private String componentName;
    @ApiModelProperty(value = "组件路径")
    private String path;
    @ApiModelProperty(value = "排序")
    private Long sort;
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

}
