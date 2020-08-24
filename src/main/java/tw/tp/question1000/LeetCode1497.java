package tw.tp.question1000;

import java.util.HashMap;
import java.util.Map;

public class LeetCode1497 {
    public static Solution solution = new Solution();

    public static void main(String[] args) {
        test(new int[]{1,2,3,4,5,10,6,7,8,9},5);  //true
        test(new int[]{1,2,3,4,5,6},7);  //true
        test(new int[]{1,2,3,4,5,6},10);  //false
        test(new int[]{-10,10},2);  //true
        test(new int[]{-1, 1, -2, 2, -3, 3, -4, 4}, 3);  //true
        test(new int[]{-4, -7, 5, 2, 1, 10, 4, -8}, 2);  //true
    }

    public static void test(int[] testArr, int testK) {
        boolean result = solution.canArrange(testArr, testK);
        System.out.println(result);
    }
}

class Solution {
    public boolean canArrange(int[] arr, int k) {
        boolean resultFlag = true;
        Map<Integer, Integer> numMap = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            Integer tempI = arr[i] % k;
            if (tempI == 0) {
                continue;
            }
            Integer tempICount = numMap.get(tempI);
            if (tempICount == null) {
                numMap.put(tempI, 1);
            } else {
                tempICount++;
                numMap.put(tempI, tempICount);
            }
        }
        System.out.println(numMap);
        for (Integer tempI : numMap.keySet()) {
            Integer tempICount, negativeTempI, negativeTempICount, gapTempI, gapTempICount;
            tempICount = numMap.get(tempI);
            if (tempICount == 0) {
                continue;
            }
            negativeTempI = -tempI;
            negativeTempICount = numMap.get(negativeTempI);
            negativeTempICount = (negativeTempICount == null) ? 0 : negativeTempICount;

            gapTempI = (tempI > 0) ? k - tempI : -(k + tempI);
            gapTempICount = numMap.get(gapTempI);
            gapTempICount = (gapTempICount == null) ? 0 : gapTempICount;

            System.out.printf("""
                    tempI:%s\tnegativeTempI:%s\tgapTempI:%s
                    tempICount:%s\tnegativeTempICount:%s\tgapTempICount:%s
                    """, tempI, negativeTempI, gapTempI, tempICount, negativeTempICount, gapTempICount);
            if (tempICount > (negativeTempICount + gapTempICount)) {
                resultFlag = false;
                System.out.printf("""
                        tempI:%s\tnegativeTempI:%s\tgapTempI:%s
                        tempICount:%s\tnegativeTempICount:%s\tgapTempICount:%s
                        """, tempI, negativeTempI, gapTempI, tempICount, negativeTempICount, gapTempICount);
                break;
            } else {
                if (tempI == negativeTempI) {
                    if (tempICount % 2 == 0) {
                    } else if (
                        tempICount % 2 == 1 &&
                        numMap.get(gapTempI) != null &&
                        gapTempICount > 0
                    ) {
                        numMap.put(gapTempI, gapTempICount - 1);
                    } else {
                        resultFlag = false;
                        break;
                    }
                } else if (tempI == gapTempI) {
                    if (tempICount % 2 == 0) {
                    } else if (
                        tempICount % 2 == 1 &&
                        numMap.get(negativeTempI) != null &&
                        negativeTempICount > 0
                    ) {
                        numMap.put(negativeTempI, negativeTempICount - 1);
                    } else {
                        resultFlag = false;
                        break;
                    }
                } else if (negativeTempICount == 0) {
                    numMap.put(gapTempI, gapTempICount - tempICount);
                } else if (gapTempICount == 0) {
                    numMap.put(negativeTempI, negativeTempICount - tempICount);
                } else if (tempICount - negativeTempICount >= 0) {
                    numMap.put(negativeTempI, 0);
                    numMap.put(gapTempI, gapTempICount - (tempICount - negativeTempICount));
                } else if (tempICount - gapTempICount >= 0) {
                    numMap.put(gapTempI, 0);
                    numMap.put(negativeTempI, negativeTempICount - (tempICount - gapTempICount));
                } else {
                    if ((negativeTempICount - tempICount) > 0) {
                        numMap.put(negativeTempI, negativeTempICount - tempICount);
                    } else {
                        numMap.put(negativeTempI, 0);
                        numMap.put(gapTempI, gapTempICount - (tempICount - negativeTempICount));
                    }
                }
            }
            numMap.put(tempI, 0);
        }
        System.out.println(numMap);
        return resultFlag;
    }
}
