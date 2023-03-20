package demo;

import demo.TwopointTest.ListNode;

import java.util.*;

public class TwoPointTest1 {

    // leetcode 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        Set<Character> hashset = new HashSet<>();
        for (int i = 0; i < chars.length ; i++) {
            int j = i;
            while (j < chars.length && !hashset.contains(chars[j])) {
                j++;
                hashset.add(chars[j]);
            }
            res = Math.max(j - i,res);
            i = j - 1;
            hashset.clear();
        }
        return res;
    }

    // leetcode 19. 删除链表的倒数第N个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        // n == sz
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // leetcode 11. 盛最多水的容器
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = 0;
        while (l < r) {
            res = Math.max(res,Math.min(height[l],height[r]) * (r-l));
            if (height[l] < height[r]) {
                // 移动左边 找一个更大值
                int j = l + 1;
                while (j < height.length && height[j] <= height[l]) {
                    j++;
                }
                l = j;
            } else {
                // 移动右边 找一个更大值
                int j = r - 1;
                while (j >=0 && height[j] <= height[r]) {
                    j--;
                }
                r = j;
            }
        }
        return res;
    }

    // leetcode 15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int l = i + 1; int r = nums.length - 1;
            while (l < r) {
                int tmpsum = nums[i] + nums[l] + nums[r];
                if (tmpsum < 0) {
                    l++;
                } else if (tmpsum > 0) {
                    r--;
                } else {
                    res.add(List.of(nums[i],nums[l],nums[r]));
                    l++; r--;
                    while (l < r && nums[l] == nums[l-1]) {
                        l++;
                    }
                }
            }
        }
        return res;
    }

    // leetcode 16. 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        int dif = Math.abs(res - target);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int tsum = nums[i] + nums[l] + nums[r];
                if (Math.abs(tsum - target) < dif) {
                    dif = Math.abs(tsum - target);
                    res = tsum;
                }
                if (tsum < target) {
                    l++;
                    while (l < r && nums[l] == nums[l-1])
                        l++;
                } else if (tsum > target) {
                    r--;
                    while (l < r && nums[r] == nums[r+1])
                        r--;
                } else {
                    return target;
                }
            }
        }
        return res;
    }

    // leetcode 141. 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // leetcode 142. 环形链表II
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = fast;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode tmp = head;
                while (tmp != slow) {
                    tmp = tmp.next;
                    slow = slow.next;
                }
                return tmp;
            }
        }
        return null;
    }

    // leetcode 26. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        int fast;
        int slow;
        for (fast = slow = 0; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    // leetcode 809. 情感丰富的文字
    public int expressiveWords(String s, String[] words) {

        char[] chars = new char[110];
        int[]  count = new int[110];
        int index = 0;
        int indexn = 0;
        // 先解析出s的字母组
        for (int i = 0 ; i < s.length(); i++) {
            int j = i+1;
            for (; j < s.length() && s.charAt(j) == s.charAt(i);j++);
            chars[index] = s.charAt(i);
            count[index] = j - i;
            index++;
            i = j - 1;
        }

        int res = 0;
        outer:
        for (String word : words) {
            indexn = 0;
            for (int i = 0; i < word.length(); i++) {
                int j = i + 1;
                for (; j < s.length() && word.charAt(j) == word.charAt(i); j++);
                char c = word.charAt(i);
                int countt = j-i;
                if (c != chars[indexn]) {
                    continue outer;
                }
                if (countt > count[indexn] || countt < count[indexn] && count[indexn] < 3)
                    continue outer;
                i = j - 1;
                indexn++;
                if (indexn > index)
                    continue outer;
            }
            if (indexn == index)
                res++;
        }
        return res;
    }

    // leetcode 27. 移除元素
    public int removeElement(int[] nums, int val) {
        int fast,slow;
        for (fast = slow = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    // leetcode 413
    public int numberOfArithmeticSlices(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            // diff
            int j = i + 1;
            int diff = nums[j] - nums[i];
            for (;j < nums.length && nums[j] - nums[j - 1] == diff;j++);
            if (j - i >= 3)
                res += (j - i -1) * (j - i - 2) /2;
            i = j - 2;
        }
        return res;
    }

    // leetcode 436. 寻找右区间
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] res = new int[n];

        int[][] tmp = new int[intervals.length][2];
        for (int i = 0; i < n; i++) {
            tmp[i][0] = intervals[i][0]; // intervals 的左下标
            tmp[i][1] = i;               // 该数组在intervals中的索引点
        }

        Arrays.sort(tmp,Comparator.comparingInt(
                array -> array[0]
        ));
        for (int i = 0; i < n; i++) {
            int tmpR = intervals[i][1];
            int l = 0;
            int r = n;
            while (l < r) {
                int mid = l + (r-l)/2;
                if (tmp[mid][0] >= tmpR) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            res[i] = l == n ? -1 : tmp[l][2];
        }
        return res;
    }

    // leetcode 475. 供暖器
    public int findRadius(int[] houses, int[] heaters) {
        int redius = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i;
        int j = findMinGong(heaters,houses[0]);
        for (i = 0; i < houses.length ; i++) {
            int tmpRedis;
            if (j == 0) {
                tmpRedis = heaters[j] - houses[i];
            } else if (j == heaters.length) {
                tmpRedis = houses[i] - heaters[j - 1];
            } else {
                tmpRedis = Math.min(houses[i] - heaters[j-1],heaters[j] - houses[i]);
            }
            redius = Math.max(redius,tmpRedis);
            if (i == houses.length - 1)
                break;
            // 更新j
            while (j < heaters.length && heaters[j] < houses[i+1]) {
                j++;
            }
        }
        return redius;
    }

    // target 为房子位置, 寻找到第一个大于target的供暖器索引 若不存在 返回heaters.length
    public int findMinGong(int [] heaters, int target) {
        int l = 0;
        int r = heaters.length ;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (heaters[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    // leetcode 160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a!= null || b != null) {
            if (a == b)
                return a;
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return null;
    }

    // leetcode 86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode();
        ListNode more = new ListNode();
        ListNode lessRear = less;
        ListNode moreRear = more;
        while (head != null) {
            if (head.val < x) {
                lessRear.next = head;
                lessRear = lessRear.next;
            } else {
                moreRear.next = head;
                moreRear = moreRear.next;
            }
        }
        lessRear.next = more.next;
        moreRear.next = null;
        return less.next;
    }

    // leetcode 345. 反转字符串中的元音字母
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        String yuanyin = "aeiou";
        for (int i = 0; i < yuanyin.length(); i++) {
            set.add(yuanyin.charAt(i));
        }
        int l = 0;
        int r = s.length()-1;
        char[] chars = s.toCharArray();
        while (l < r) {
            // 忽略辅音
            while (l < r && !set.contains(chars[l]))
                l++;
            while (l < r && !set.contains(chars[r]))
                r--;
            if (l < r) {
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++; r--;
            }
        }
        return new String(chars);
    }
}
