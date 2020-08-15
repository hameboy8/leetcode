package tw.tp.question500;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
//testCommit
public class LeetCode515 {
    public static void main(String[] args) {

        new Solution().largestValues(new TreeNode());
    }
}

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resultList = new LinkedList<Integer>();
        if(root == null){
            return resultList;
        }
        TreeNode[] currentTreeNodeList = new TreeNode[]{root};

        do{
            if(resultList.size() > 0){
                currentTreeNodeList = nextLevel(currentTreeNodeList);
            }
            int maximumNum = getMaximumNum(currentTreeNodeList);
            resultList.add(maximumNum);
        }while(hasNext(currentTreeNodeList));

        return resultList;
    }
    public boolean hasNext(TreeNode[] nodeArr){
        for(TreeNode node : nodeArr){
            if(
                    node!= null &&
                            (   node.left != null ||
                                    node.right!= null
                            )
            ){
                return true;
            }
        }

        return false;
    }
    public int getMaximumNum(TreeNode[] nodeArr){
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        for(TreeNode tempNode : nodeArr){
            if(tempNode != null){
                treeSet.add(tempNode.val);
            }
        }
        return treeSet.last();
    }
    public TreeNode[] nextLevel(TreeNode[] nodeArr){
        TreeNode[] resultTreeList = new TreeNode[nodeArr.length * 2];
        for(int i= 0; i < nodeArr.length; i++){
            if(nodeArr[i] == null){
                resultTreeList[2*i] = resultTreeList[2*i + 1] = null;
            }else{
                TreeNode leftTreeNode = nodeArr[i].left;
                TreeNode rightTreeNode = nodeArr[i].right;
                resultTreeList[2*i] = leftTreeNode;
                resultTreeList[2*i+1] = rightTreeNode;
            }
        }
        return Arrays.stream(resultTreeList).filter(treeNode -> treeNode!= null).toArray(TreeNode[]::new);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}