package tw.tp.question0;

import java.util.*;

public class LeetCode23 {
    static Solution2 solution = new Solution2();
    public static void main(String[] args) {
        ListNode[] a= new ListNode[]{
        };
        ListNode result = solution.mergeKLists(a);
        System.out.println(result);
    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            List<Integer> valArr = new ArrayList();
            for(ListNode tempListNode : lists){
                valArr.addAll(listNodeToArr(tempListNode));
            }
            valArr.sort((a,b)-> a-b);
            Integer[] resultArr = new Integer[valArr.size()];
            resultArr = valArr.toArray(resultArr);

            int[] c= new int[resultArr.length];
            for(int i= 0; i<resultArr.length;i++){
                c[i]= resultArr[i];
            }
            return createListNodeInSmartWay(c);
        }
        public List<Integer> listNodeToArr(ListNode listNode){
            List<Integer> valArr = new ArrayList();
            while (listNode != null){
                valArr.add(listNode.val);
                listNode = listNode.next;
            }
            return valArr;
        }
        public ListNode createListNodeInSmartWay(int ...val) {
            if(val.length == 0){
                return null;
            }
            ListNode result = new ListNode(val[0]);
            if(val.length > 1){
                result.next = createListNodeInSmartWay(Arrays.copyOfRange(val, 1, val.length));
            }
            return result;
        }
    }

    static class Solution2 {
        public ListNode mergeKLists(ListNode[] lists) {
            List<Integer> valArr = new ArrayList();
            ListNode oriListNode = new ListNode();
            ListNode preListNode = oriListNode;
            for(ListNode tempListNode : lists){
                preListNode.next = tempListNode;
                while(preListNode.next != null){
                    preListNode = preListNode.next;
                }
            }
            valArr.addAll(listNodeToArr(oriListNode.next));
            valArr.sort((a,b)-> a-b);
            ListNode resultListNode = null;
            ListNode tempResult = null;
            Iterator<Integer> valIter = valArr.iterator();
            while (valIter.hasNext()){
                if(resultListNode == null){
                    resultListNode = new ListNode( valIter.next() );
                    tempResult = resultListNode;
                }else{
                    tempResult.next = new ListNode(valIter.next() );
                    tempResult = tempResult.next;
                }
            }

            return resultListNode;
        }
        public List<Integer> listNodeToArr(ListNode listNode){
            List<Integer> valArr = new ArrayList();
            while (listNode != null){
                valArr.add(listNode.val);
                listNode = listNode.next;
            }
            return valArr;
        }
        public ListNode createListNodeInSmartWay(int ...val) {
            if(val.length == 0){
                return null;
            }
            ListNode result = new ListNode(val[0]);
            if(val.length > 1){
                result.next = createListNodeInSmartWay(Arrays.copyOfRange(val, 1, val.length));
            }
            return result;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(int ...val) {
            this.val = val[0];
            if(val.length > 1){
                this.next = new ListNode(Arrays.copyOfRange(val, 1, val.length));
            }
        }
        @Override
        public String toString() {
            if (this.next != null) {
                return String.valueOf(this.val) + " -> " + this.next;
            } else {
                return String.valueOf(this.val);
            }
        }
    }
}
