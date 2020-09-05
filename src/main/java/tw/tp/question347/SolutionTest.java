package tw.tp.question347;

import java.util.Arrays;

public class SolutionTest {
    public static void main(String[] args) {
        int[] result = new Solution().topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        System.out.println(Arrays.toString(result));
    }
}
