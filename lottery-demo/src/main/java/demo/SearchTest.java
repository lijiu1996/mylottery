package demo;

import org.junit.jupiter.api.Test;

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
    // [a2 a2+1)

    // 2 * 2 < 5 3 * 3 > 5
    public int mySqrt(int x) {
        if (x <= 1)
            return x;
        int l = 0;
        int r = x/2;
        while (l < r) {
            int mid = l + (r - l)/ 2;
            if ((long)mid * mid <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    // leetcode 367. 有效的
    // x * x <= target
    public boolean isPerfectSquare(int num) {

        int l = 1;
        int r = num / 2;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if ((long)mid * mid <= num) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l * l == num ? true : false;
    }


    @Test
    public void testt() {
        int i = nthUglyNumber(7, 7, 7, 7);
        System.out.println(i);
    }

    // leetcode 1201. 丑数III
    public int nthUglyNumber(int n, int a, int b, int c) {
        if (a == 1 || b == 1 || c == 1)
            return n;
        long m1 = gcdm(a,b);
        long m2 = gcdm(b,c);
        long m3 = gcdm(a,c);
        long m4 = gcdm(m1,c);

        int l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = l + (r-l)/2;
            int tmp = findT(mid,a,b,c,m1,m2,m3,m4);
            if (tmp < n) {
                l = mid + 1;
            } else if (tmp > n) {
                r = mid - 1;
            } else {
                if (mid % a == 0 || mid % b == 0 || mid % c == 0)
                    return mid;
                r = mid - 1;
            }
        }
        return l;
    }

    public int findT(long m, int a, int b, int c, long m1, long m2, long m3, long m4) {
        long t = m / a + m / b + m / c - m / m1 - m / m2 - m / m3 + m / m4;
        return (int)t;
    }

    // 数组最大数和最小数的最大公约数
    public int findGCD(int[] nums) {
        int max = 0, min = 1011;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] < min)
                min = nums[i];
            if (nums[i] > max)
                max = nums[i];
        }
        return (int)gcd(max,min);
    }

    private long gcdm(long a, long b) {
        return a * b / gcd(a,b);
    }

    // 15 18 30
    // 3 90
    // 2 3 5
    // 6 5
    private long gcd(long p, long q) {
        long a = Math.max(p,q);
        long b = Math.min(p,q);
        while (b != 0) {
            long mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }
}
