package tw.tp.question554;


import java.util.*;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int wallRowCount = wall.size();
        int wallWidth = getWallWidth(wall);

        Map<Integer, Integer> verticalLineMap = getVerticalLineMap(wall, wallRowCount, wallWidth);

        int maxUncrossCount = verticalLineMap.values().stream().reduce(0, (pre, cur) -> {
            return cur > pre ? cur : pre;
        }).intValue();
        return wallRowCount - maxUncrossCount;
    }

    public int getWallWidth(List<List<Integer>> wall) {
        if (wall.size() == 0) {
            return 0;
        }
        int wallWidth = wall.get(0).stream().reduce(
                0, (sum, bricksWidth) -> sum + bricksWidth
        );
        return wallWidth;
    }

    public Map<Integer, Integer> getVerticalLineMap(List<List<Integer>> wall, int wallRowCount, int wallWidth) {
        Map<Integer, Integer> verticalLineMap = new HashMap<>();
        for (int rowNum = 0; rowNum < wallRowCount; rowNum++) {
            int verticalLineIndex = 0;
            for (int colNum = 0; colNum < wall.get(rowNum).size(); colNum++) {
                int columnWidth = wall.get(rowNum).get(colNum);
                verticalLineIndex = verticalLineIndex + columnWidth;
                if (verticalLineIndex == wallWidth) {
                    break;
                } else if (verticalLineMap.containsKey(verticalLineIndex)) {
                    int uncrossCount = verticalLineMap.get(verticalLineIndex);
                    verticalLineMap.put(verticalLineIndex, uncrossCount + 1);
                } else {
                    verticalLineMap.put(verticalLineIndex, 1);
                }
            }
        }
        return verticalLineMap;
    }

}