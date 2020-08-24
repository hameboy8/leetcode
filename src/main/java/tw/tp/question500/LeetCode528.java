package tw.tp.question500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode528 {
    public static void main(String[] args) {
        LeetCode528.Solution test = new LeetCode528.Solution(new int[]{1, 3});
        test.pickIndex();
        test.pickIndex();
        test.pickIndex();
        test.pickIndex();
    }

    public static void test(int[] testIntArr) {
    }

    static class Solution {
        int[] w;
        HashMap<Integer, Double> arrWithWeight;

        public Solution(int[] w) {
            this.w = w;
            this.arrWithWeight = new HashMap<>();
            double wSum = Arrays.stream(w).sum();
            for (int index = 0; index < w.length; index++) {
                double indexWeight = w[index] / wSum;
                arrWithWeight.put(index, indexWeight);
            }
            arrWithWeight.keySet().stream().forEach(tempIndex -> {
                if (tempIndex != 0) {
                    double preWeight = arrWithWeight.get(tempIndex - 1);
                    double curWeight = arrWithWeight.get(tempIndex);
                    arrWithWeight.put(tempIndex, preWeight + curWeight);
                }
            });
        }

        public int pickIndex() {
            double randomNum = Math.random();
            AtomicBoolean findFlag = new AtomicBoolean(false);
            AtomicInteger findIndex = new AtomicInteger(-1);
            arrWithWeight.forEach((tempIndex, tempWeight) -> {
                if (
                    !findFlag.get() &&
                    (tempWeight >= randomNum)
                ) {
                    findFlag.set(true);
                    findIndex.set(tempIndex);
                }
            });
            return findIndex.get();
        }
    }

}

