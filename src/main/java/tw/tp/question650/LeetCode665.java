package tw.tp.question650;

import java.util.Arrays;

public class LeetCode665 {
    public static Solution solution = new Solution();
    public static void main(String[] args) {
        test(new int[]{1,2,3,4});
        test(new int[]{1,4,3,4});
        test(new int[]{3,4,2,3});
        test(new int[]{5,7,1,8});
        test(new int[]{4,2,3});
        test(new int[]{1,4,2,3});
    }
    public static void test(int[] testIntArr){
        int[] originalArr = Arrays.copyOf(testIntArr, testIntArr.length);
        Boolean result = solution.checkPossibility(testIntArr);
        System.out.printf("%s\t: %s => %s\n", result, Arrays.toString(originalArr), Arrays.toString(testIntArr));
    }
}

class Solution {
    public boolean checkPossibility(int[] nums) {
        int checkCount = 0;
        if(nums == null){
            return false;
        }
        for(int i= 0; i< nums.length; i++){
            if(i == 0){
                continue;
            }
            if(nums[i] >= nums[i-1]){
            }else{
                if( checkCount > 0 ){
                    return false;
                }else{
                    checkCount++;
                    if(i == 1 || nums[i] >= nums[i-2]){
                        nums[i-1] = nums[i];
                    }else{
                        nums[i] = nums[i-1];
                    }
                }
            }
        }
        return true;
    }
}
