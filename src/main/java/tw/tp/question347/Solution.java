package tw.tp.question347;

import java.util.*;

public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] resultIntArr = new int[k];

        Map<Integer,Integer> countMap = createCountMap(nums);

        List<Map.Entry<Integer, Integer>> list = createAndSortEntrySet(countMap.entrySet());

        Iterator<Map.Entry<Integer, Integer>> iter = list.iterator();
        for (int i = 0; i < k; i++) {
            if(iter.hasNext()){
                Map.Entry<Integer, Integer> tempMapEntry = iter.next();
                resultIntArr[i] = tempMapEntry.getKey();
            }else{
                break;
            }
        }

        return resultIntArr;
    }

    public Map<Integer, Integer> createCountMap(int[] nums){
        Map<Integer,Integer> resultMap = new HashMap();
        for (int i : nums) {
            if(resultMap.get(i) == null){
                resultMap.put(i, 1);
            }else{
                int tempCount = resultMap.get(i);
                tempCount++;
                resultMap.put(i, tempCount);
            }
        }
        return resultMap;
    }
    public List<Map.Entry<Integer, Integer>> createAndSortEntrySet(Set<Map.Entry<Integer, Integer>> entrySet){
        List<Map.Entry<Integer, Integer>> resultList = new ArrayList(entrySet);
        Collections.sort(resultList, new Comparator<Map.Entry<Integer, Integer>>(){
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                int cmp1 = b.getValue().compareTo(a.getValue());
                if (cmp1 != 0) {
                    return cmp1;
                } else {
                    return a.getKey().compareTo(b.getKey());
                }
            }
        });

        return resultList;
    }
}
