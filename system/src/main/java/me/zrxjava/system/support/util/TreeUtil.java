
package me.zrxjava.system.support.util;

import lombok.experimental.UtilityClass;
import me.zrxjava.common.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author void
 */
@UtilityClass
public class TreeUtil {

	/**
	 * 两层循环实现建树
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {

		List<T> trees = new ArrayList<>();

		for (T treeNode : treeNodes) {

			if (root.equals(treeNode.getPid())) {
				trees.add(treeNode);
			}

			for (T it : treeNodes) {
				if (it.getPid().equals(treeNode.getId())) {
					if (treeNode.getChildren() == null) {
						treeNode.setChildren(new ArrayList<>());
					}
					treeNode.add(it);
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Long root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getPid())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId().equals(it.getPid())) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}


	public <T extends TreeNode> List<T> listToTree(List<T> treeNodes) {
		List<T> newList = new ArrayList<>();
		Map<Long, T> map = treeNodes.parallelStream().collect(Collectors.toMap(T::getId, t -> t));
		for (T node : treeNodes){
			T parent = map.get(node.getPid());
			if (parent != null){
				if (parent.getChildren() == null){
					parent.add(node);
				}else {
					List<TreeNode> children = parent.getChildren();
					children.add(node);
					parent.setChildren(children);
				}
			}else {
				newList.add(node);
			}
		}
		return newList;
	}

}
