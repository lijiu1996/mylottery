package demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Li JiaWei
 * @ClassName: TwopointTest
 * @Description: 讲道理 双指针类型急需练习
 *               Tree 和 List 都是背模板
 *               但是双指针是真的需要时常联系滴
 * @Date: 2023/3/15 16:45
 * @Version: 1.0
 */
public class TwopointTest {

    // leetcode 26
    public int removeDuplicates1(int[] nums) {
        int fast = 0, slow = 0;
        for (;fast < nums.length; ) {
            // [1 2 2 3]
            while (fast < nums.length && nums[fast] == nums[slow])
                fast++;
            if (fast < nums.length) {
                nums[++slow] = nums[fast++];
            }
        }
        return slow;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    // leetcode 83
    // 1 - - 2 -- 3 -- 4
    public ListNode deleteDuplicates1(ListNode head) {
//        if (head == null)
//            return head;
        ListNode fast = head,slow = head;
        while (fast != null) {
            if(fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        return head;
    }

    // leetcode 27. 移除元素
    public int removeElement(int[] nums, int val) {
        int first = 0; int last = 0;
        for (;first < nums.length; first++) {
            if (nums[first] != val) {
                nums[last++] = nums[first];
            }
        }
        return last;
    }

    // leetcode 283
    public void moveZeroes(int[] nums) {
        int fast,slow;
        for (fast = 0,slow = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0)
                nums[slow++] = nums[fast];
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // leetcode 167
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum > target) {
                r--;
            } else if (sum < target) {
                l++;
            } else {
                return new int[] {l+1,r+1};
            }
        }
        return null;
    }

    // leetcode5. 最长回文子串
    // 如果 xxxxx
    // baba 最长 vs abad 最长
    // bab aba
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        int[] tres = {0,0}; // 缓存最长的子串结果
        for (int i =0; i < dp.length; i++) {
            for(int j = 0 ; j <= i ; j++) {
                dp[i][j] = 1;
            }
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j + i < s.length(); j++) {
                int k = j + i;
                if (chars[k] == chars[j] && dp[j+1][k-1] == 1) {
                    dp[j][k] = 1;
                    tres[0] = j;
                    tres[1] = k;
                }
            }
        }
        return new String(chars,tres[0],tres[1] - tres[0] + 1);
    }

    // leetcode 125. 验证回文串
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int fast = 0,slow = 0;
        for (;fast < chars.length; fast++) {
            if (chars[fast] >= 'a' && chars[fast] <= 'z'
            || chars[fast] >= '0' && chars[fast] <= '9') {
                chars[slow++] = chars[fast];
            } else if (chars[fast] >= 'A' && chars[fast] <= 'Z') {
                chars[slow++] = (char) (chars[fast] - 'A' + 'a');
            }
        }
        int l = 0, r = slow - 1;
        while (l < r) {
            if (chars[l] != chars[r])
                return false;
            l++;r--;
        }
        return true;
    }

    // leetcode 131. 分割回文串
//    public List<List<String>> partition(String s) {
//
//    }

    // leetcode 82. 删除排序链表中的重复元素||
    public ListNode deleteDuplicates(ListNode head) {
        return null;
    }

    // leetcode 9. 回文数
    public boolean isPalindrome(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        int l = 0, r = chars.length -1;
        while (l < r) {
            if (chars[l] != chars[r])
                return false;
            l++;r--;
        }
        return true;
    }


    // leetcode 80. 出现次数超过两次的只出现两次
    // 你没搞懂一件事 应该是slow 慢慢长
    // a a
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2)
            return nums.length;
        int fast = 2; int slow = 2;
        for (;fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 2])
                nums[slow++] = nums[fast];
        }
        return slow;
    }

    @Test
    public void test1() {
        String s = "helloaaa";
        System.out.println(s.substring(2,5));
    }
}
