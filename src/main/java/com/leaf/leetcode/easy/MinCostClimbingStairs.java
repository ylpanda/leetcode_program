package com.leaf.leetcode.easy;

/**
 * @create 2020/9/8 16:38
 * @package com.leaf.leetcode.dynamicprogramming
 * @since 1.0.0
 */
public class MinCostClimbingStairs {

    //数组的每个索引作为一个阶梯，第i个阶梯对应着一个非负数的体力花费值cost[i](索引从0开始)。
    //
    //每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
    //
    //您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
    //
    //示例1:
    //输入: cost = [10, 15, 20]
    //输出: 15
    //解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
    //示例 2:
    //
    //输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
    //输出: 6
    //解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
    //注意：cost的长度将会在[2, 1000]。每一个cost[i] 将会是一个Integer类型，范围为[0, 999]。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public static int minCostClimbingStairs(int[] cost) {
        //爬上第i级台阶
        //dp[i]代表爬上第i级台阶最小消耗
        //存在 dp[i-2]+cost[i]  或者 dp[i-1]+cost[i] 两种方式
        //最终dp[i]=Math.min(dp[i-2],dp[i-1])+cost[i];

        for (int i = 2; i < cost.length; i++) {
            cost[i] = Math.min(cost[i - 1], cost[i - 2]) + cost[i];
            System.out.println("cost[" + i + "] " + cost[i]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }

    public static void main(String[] args) {
        int[] cost = new int[]{1, 1};
        System.out.println(minCostClimbingStairs(cost));
    }

}