package demo;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 *  滑动窗口练习
 *      -- 变种双指针
 */
public class WindowTest {

    @Test
    public void strTest1() {
        lengthOfLongestSubstring("abcabcbb");
    }

    // leetcode 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        int l,r;
        char[] chars = s.toCharArray();
        for (l = r = 0; r < s.length(); r++) {
            if (!set.contains(chars[r])) {
                set.add(chars[r]);
            } else {
                res = Math.max(r - l,res);
                while (set.contains(chars[r])) {
                    set.remove(chars[l]);
                    l++;
                }
                r--;
            }
        }
        res = Math.max(r - l,res);
        return res;
    }

    // leetcode 76. 最小覆盖子串
//    public String minWindow(String s, String t) {
//
//    }

    // leetcode 187. 重复的DNA序列
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() -10; i++) {
            String substring = s.substring(i, i + 10);
            Integer integer = map.get(substring);
            if (integer == null) {
                map.put(substring,1);
            } else if (integer == 1) {
                res.add(substring);
                map.put(substring,2);
            }
        }
        return res;
    }

    // leetcode 219. 存在重复元素II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int poi = map.getOrDefault(nums[i], -1);
            if (poi != -1) {
                if (i - poi <= k)
                    return true;
            }
            map.put(nums[i],i);
        }
        return false;
    }

}
