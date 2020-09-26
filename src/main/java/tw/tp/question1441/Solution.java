package tw.tp.question1441;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> resultList = new ArrayList<>();
        int maxNum = target[target.length - 1];
        int targetIndex = 0;
        for (int i = 1; i <= maxNum; i++) {
            int targetValue;
            try {
                targetValue = target[targetIndex];
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            resultList.add("Push");
            if (i == targetValue) {
                targetIndex++;
            } else {
                resultList.add("Pop");
            }
        }

        return resultList;
    }
}
