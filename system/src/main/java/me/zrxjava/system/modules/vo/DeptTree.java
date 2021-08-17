

package me.zrxjava.system.modules.vo;

import cn.hutool.core.lang.tree.TreeNode;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门树对象
 * @author void
 */
@EqualsAndHashCode(callSuper = true)
@Data
//@Builder(toBuilder = true)
//@SuperBuilder(toBuilder = true)
@ApiModel(value = "部门树")
public class DeptTree extends TreeNode<Integer> {

//	@ApiModelProperty(value = "部门名称")
//	private String name;
//
//	/**
//	 * 是否显示被锁定
//	 */
//	private Boolean isLock = true;

	protected List<DeptTree> children = new ArrayList<>();

	public DeptTree(Integer id, Integer parentId, String name, Comparable<?> weight) {
		super(id, parentId, name,weight);
//		this.userId = userId;?
	}





}
