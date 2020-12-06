

package me.zrxjava.system.modules.ums.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author void
 */
@Data
@ApiModel(value = "菜单树")
public class MenuTree extends TreeNode implements Serializable {


	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String name;

	private boolean spread = false;

	/**
	 * 前端路由标识路径
	 */
	@ApiModelProperty(value = "前端路由标识路径")
	private String componentPath;

	/**
	 * 路由缓冲
	 */
	@ApiModelProperty(value = "路由缓冲")
	private String keepAlive;

	/**
	 * 权限编码
	 */
	@ApiModelProperty(value = "权限编码")
	private String permission;

	@ApiModelProperty(value = "是否外链0不是1是")
	private Boolean iFrame;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	@ApiModelProperty(value = "菜单类型,0:菜单 1:按钮")
	private Integer type;

	/**
	 * 菜单标签
	 */
	@ApiModelProperty(value = "菜单标签")
	private String label;

	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;

	/**
	 * 是否包含子节点
	 *
	 * @since 3.7
	 */
	private Boolean hasChildren;


	@ApiModelProperty(value = "缓存0否1是")
	private Boolean cache;


	public MenuTree(MenuVo menuVo) {
		this.id = menuVo.getMenuId();
		this.pid = menuVo.getPid();
		this.icon = menuVo.getIcon();
		this.name = menuVo.getName();
		this.componentPath = menuVo.getComponentPath();
		this.type = menuVo.getType();
		this.permission = menuVo.getPermission();
		this.label = menuVo.getLabel();
		this.sort = menuVo.getSort();
		this.cache = menuVo.getCache();
	}

}