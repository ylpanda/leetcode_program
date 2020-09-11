package com.leaf.leetcode.medium;

/**
 * @create 2020/9/10 13:56
 * @package com.leaf.leetcode.medium
 * @since 1.0.0
 */
public class LongestPalindromicSubstring {

    //转载自： https://leetcode-cn.com/problems/longest-palindromic-substring/
    //5. 最长回文子串
    //给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    //
    //示例 1：
    //
    //输入: "babad"
    //输出: "bab"
    //注意: "aba" 也是一个有效答案。
    //示例 2：
    //
    //输入: "cbbd"
    //输出: "bb"

    public static String longestPalindrome(String s) {
        if (null == s || s.length() < 2) {
            return s;
        }
        int length = s.length();
        int start = 0;
        int subLength = 1;
        //dp[i][j]表示s i到j是回文数 那么dp[i+1][j-1]也是回文数
        //i<j
        //如果 s长度如果小于等于3 那么只需要判断s[i]==s[j] 所以有j-i+1<=3  j-i<=2 并且s[i]==s[j] 时dp[i][j]==true
        //s[i]==s[j]时dp[i][j]=dp[i+1][j-1]
        //还得记录下起始位置和长度
        //当dp[i][j]为true时， j-i+1>subLength 时 将subLength=j-i+1; i<start时 start=i
        //
        Boolean[][] dp = new Boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    if (j - i + 1 > subLength) {
                        subLength = j - i + 1;
                        start = i;
                    }
                }
            }

        }
        return s.substring(start, subLength + start);
    }



    public static void main(String[] args) {
        String param = "ac";
        System.out.println(longestPalindrome(param));

    }

}