package tw.tp.question144;

import tw.tp.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<Integer>();

        if(root != null){
            addTreeNodeValueToList(root, resultList);
        }

        return resultList;
    }
    public void addTreeNodeValueToList(TreeNode treeNode, List<Integer> list){
        list.add(treeNode.val);
        if(treeNode.left != null){
            addTreeNodeValueToList(treeNode.left, list);
        }
        if(treeNode.right != null){
            addTreeNodeValueToList(treeNode.right, list);
        }
    }
}
