package com.leaf.leetcode.medium;

/**
 * @create 2020/9/10 11:43
 * @package com.leaf.leetcode.medium
 * @since 1.0.0
 */
public class MinimumPathSum {

    //转载自：https://leetcode-cn.com/problems/minimum-path-sum/
    // 64. 最小路径和
    //给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    //
    //说明：每次只能向下或者向右移动一步。
    //
    //示例:
    //
    //输入:
    //[
    //  [1,3,1],
    //  [1,5,1],
    //  [4,2,1]
    //]
    //输出: 7
    //解释: 因为路径 1→3→1→1→1 的总和最小。

    // dp[i][j] 表示走到 i,j需要的最小步数
    // 公式推导
    // 1、当i=0,j>0 情况 dp[0][j] = dp[0][j - 1] + grid[0][j];
    // 2、当j=0,i>0 情况 dp[i][0] = dp[i - 1][0] + grid[i][0];

    //  [1,3,1],        [1,4,5],
    //  [1,5,1],  ----> [2,7,6],
    //  [4,2,1]         [6,8,7]

    // 3、当j>0,i>0 情况  dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);


    public static int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[row][column];
        dp[0][0] = grid[0][0];

        //计算单独走完第一行
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        //计算出单独走完第一列
        for (int j = 1; j < column; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }


        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[row - 1][column - 1];
    }

    public static void main(String[] args) {
        int[][] param = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(param));
        
    }
}