

package me.zrxjava.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author void
 */
@Data
@ApiModel(value = "树形节点")
public class TreeNode {

	@ApiModelProperty(value = "当前节点id")
	protected Long id;

	@ApiModelProperty(value = "父节点id")
	protected Long pid;

	@ApiModelProperty(value = "子节点列表")
	protected List<TreeNode> children = new ArrayList<>();

	public <T extends TreeNode> void add(T node) {
		children.add(node);
	}

}
