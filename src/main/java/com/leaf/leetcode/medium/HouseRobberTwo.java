package com.leaf.leetcode.medium;

/**
 * @create 2020/9/11 9:53
 * @package com.leaf.leetcode.medium
 * @since 1.0.0
 */
public class HouseRobberTwo {

    //转载自： https://leetcode-cn.com/problems/house-robber-ii/
    //213. 打家劫舍 II
    //你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //
    //给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
    //
    //示例 1:
    //
    //输入: [2,3,2]
    //输出: 3
    //解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    //示例 2:
    //
    //输入: [1,2,3,1]
    //输出: 4
    //解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
    //     偷窃到的最高金额 = 1 + 3 = 4 。

    public static int rob(int[] nums) {
        //dp[i]=dp[i]+dp[i-2];
        //需要计算 0到length-2  与1到length-1 的最大值
        if (null == nums) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        return Math.max(maxRob(nums, 0, length - 2), maxRob(nums, 1, length - 1));

    }

    private static int maxRob(int[] nums, int p, int q) {
        int first = 0;
        int second = 0;
        int max = 0;
        for (int i = p; i <= q; i++) {
            max = Math.max(second + nums[i], first);
            second = first;
            first = max;
        }
        System.out.println(max);
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 5, 3, 6, 4, 7, 1, 5, 3, 6, 4, 7, 1, 5, 3, 6, 4, 7, 1, 5, 3, 6, 4, 2, 1, 1, 2};
        System.out.println(rob(nums));
    }
}