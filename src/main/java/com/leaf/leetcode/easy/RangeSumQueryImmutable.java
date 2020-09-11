package com.leaf.leetcode.easy;

/**
 * @create 2020/9/9 10:20
 * @package com.leaf.leetcode.dynamicprogramming
 * @since 1.0.0
 */
public class RangeSumQueryImmutable {

    //303. 区域和检索 - 数组不可变
    //给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
    //
    //示例：
    //
    //给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
    //
    //sumRange(0, 2) -> 1
    //sumRange(2, 5) -> -1
    //sumRange(0, 5) -> -3

    private int[] dp = null;

    public RangeSumQueryImmutable(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }
        dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + dp[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return dp[j];
        } else {
            return dp[j] - dp[i - 1];
        }
    }
}