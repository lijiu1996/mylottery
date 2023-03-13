package demo;

/**
 * @author Li JiaWei
 * @ClassName: SearchTest
 * @Description:
 * @Date: 2023/3/13 15:55
 * @Version: 1.0
 */
public class SearchTest {

    // leetcode 704
    // 二分查找 存在返回下标 否则返回 -1
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l] == target ? l : -1;
    }

    // leetcode 35 找到第一个>= target
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l] >= target ? l : l + 1;
    }

    // leetcode 34. 找到第一个>= 以及第一个<=
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[] {-1, -1};
        int l1 = 0, l2 = 0;
        int r1 = nums.length - 1, r2 = nums.length -1;
        // 第一个 >=
        while (l1 < r1) {
            int mid = l1 + (r1 - l1) /2;
            if (nums[mid] >= target) {
                r1 = mid;
            } else {
                l1 = mid + 1;
            }
        }
        while (l2 < r2) {
            int mid = l2 + (r2 - l2 + 1) /2;
            if (nums[mid] <= target) {
                l2 = mid;
            } else {
                r2 = mid - 1;
            }
        }
        return new int[] {nums[l1] == target ? l1: -1,nums[l2] == target ? l2 : -1};
    }

    // leetcode 69. x的平方根
    // 4 5 6 7 8 9
    // a2 <= x b2 > x
    public int mySqrt(int x) {
        if (x <= 1)
            return x;
        int l = 0, r = (1 << 31) - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (mid  <= x / mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l <= x / l ? l : l - 1;
    }
}
