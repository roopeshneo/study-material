package roo.solution;

/**
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 */
public class P11PalindromeSubString {

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expandAroundCenter(s, i, i);
            count += expandAroundCenter(s, i, i + 1);
        }
        return count;
    }

    private int expandAroundCenter(String str, int left, int right) {
        int count = 0;
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        var palindromeSubstr = new P11PalindromeSubString();
        var input = "aaabbbaaaabcbca";
        var actual = palindromeSubstr.countSubstrings(input);
        System.out.println("Result count: "+actual);
    }
}
