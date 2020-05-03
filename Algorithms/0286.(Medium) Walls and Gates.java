/****************************************************************************************************
286. Walls and Gates

Difficulty: Medium

You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. 
(We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.)

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 
Given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:
 3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
****************************************************************************************************/


class Solution {
    public void wallsAndGates(int[][] rooms) {
        //记录四个方向，向 上下左右（i +- 1, j +- 1）方向前进。
        final int[][] DIRS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
        //     return rooms;
        // }
        
        Queue<int[]> queue = new LinkedList<>();
        //遍历一遍二维数组，将为 0 （即是 gate 的格子）放入队列 queue 中。
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            //从当前点向 上下左右 前进一格。如果前进到达的新的这一格为 INF，则 ＋ 1。并且放入队列 queue 中，未来继续遍历它的四个方向。
            for (int[] pairs : DIRS) {
                int x = i + pairs[0];
                int y = j + pairs[1];
                if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] != Integer.MAX_VALUE) {
                    continue;
                }
                rooms[x][y] = rooms[i][j] + 1;
                queue.add(new int[] {x, y});
            }
        }
        //return rooms;
    }
}


