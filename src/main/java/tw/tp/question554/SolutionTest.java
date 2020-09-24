package tw.tp.question554;

import java.util.ArrayList;
import java.util.List;

public class SolutionTest {
    public static void main(String[] args) {
//        int[][] test = {{1,2,2,1},
//                {3,1,2},
//                {1,3,2},
//                {2,4},
//                {3,1,2},
//                {1,3,1,1}};
        int[][] test = {{100000000},{100000000},{100000000}};
        List<List<Integer>> wall = createWall(test);
        int result = new Solution().leastBricks(wall);
        System.out.println(result);
    }

    public static List<List<Integer>> createWall(int[][] wallIntArray){
        List<List<Integer>> wall = new ArrayList<>();
        for (int[] i : wallIntArray) {
            List<Integer> a = new ArrayList<>();
            for (int j : i) {
                Integer tempWidth = j;
                a.add(tempWidth);
            }
            wall.add(a);
        }

        return wall;
    }
}
