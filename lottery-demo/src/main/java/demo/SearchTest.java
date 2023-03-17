package demo;

import org.junit.jupiter.api.Test;

/**
 * @author Li JiaWei
 * @ClassName: SearchTest
 * @Description:
 *      二分查找 问题一 确定开闭空间 [mid ]
 * @Date: 2023/3/13 15:55
 * @Version: 1.0
 */
public class SearchTest {

    // leetcode 704
    // 二分查找 存在返回下标 否则返回 -1
    public int search1(int[] nums, int target) {
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

    // leetcode 878. 第n个神奇数字
    // 109 * 8 * 10&
    public int nthMagicalNumber(int n, int a, int b) {
        long l = 0;
        long r = (long)a * b * n;

        int m1 = gcdm(a,b);
        while (l < r) {
            long mid = l + (r - l) / 2;
            long tmp = mid / a + mid / b - mid / m1;
            if (tmp < n) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return (int ) (l % (10_0000_0000 + 7));
    }

    private int gcd(int p, int q) {
        int a = Math.max(p,q);
        int b = Math.min(p,q);
        while (b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }

    private int gcdm(int p, int q) {
        return p / gcd(p,q) * q;
    }

    // leetcode 162. 寻找某一个峰值
    // a[n] >= a[n-1]
    // ----
    //
    public int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums[0] > nums[1]) {
            return nums[0];
        }
        if (nums[nums.length -1] > nums[nums.length - 2]) {
            return nums[nums.length - 1];
        }

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r-l + 1)/2;
            if (nums[mid] > nums[mid - 1]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    @Test
    public void xuanzhanTest() {
        int[] nums = {5,1,2,3,4};
    }


    // leetcode33. 搜索旋转排序数组
    // 旋转排序数组切一半 一定一段有序 一段无序
    // 搜索条件还是 >= target 最左边
    //
    public int search3(int[] nums, int target) {
        // 我只能说 脑子要长出来了
//       int l = 0;
//       int r = nums.length - 1;
//       int lv = nums[l];
//       int rv = nums[r];
//       while (l < r) {
//           int mid = l +(r-l)/2;
//           if (target == nums[mid])
//               return mid;
//           // left == nums[mid]
//           // if(left == nums[mid])
//           //
//           if (nums[mid] >= lv) {
//               // 左边有序右边无序
//               if (lv<= target && target <= nums[mid])
//                   r = mid;
//               else
//                   l = mid + 1;
//           } else if (nums[mid] < lv) {
//               // 右边有序左边无序
//               if (target >= nums[mid] && target <= rv)
//                   l = mid + 1;
//               else
//                   r = mid - 1;
//           }
//       };
//       return target == nums[l] ? l : -1;

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r-l+1)/2;
            if (nums[mid] >= nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        int max = l;
        int min = l == nums.length - 1 ? 0 : max + 1;
        if (target >= nums[0] && target <= nums[max]) {
            l = 0; r = max;
        } else if(target >= nums[min] && target <= nums[nums.length - 1]) {
            l = min; r = nums.length - 1;
        } else
            return -1;

        while (l < r) {
            int mid = l + (r - l)/2;
            if (nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return nums[l] == target ? l : -1;
    }

    @Test
    public void testGcd() {
        nthMagicalNumber(10_0000_0000,40000,40000);
    }

    @Test
    public void testxuan() {
        int[] nums = {1,2,3,4,5};
        System.out.println(findMin(nums));
    }

    // leetcode 153. 旋转数组查找一个值
    // 二分查找旋转数组最小值
    public int findMin1(int[] nums) {
        // 旋转点左边都大于0 右边都不大于0
        int l = 0;
        int r = nums.length - 1;
        if (nums[r] >= nums[l])
            return nums[l];
        while (l < r) {
            int mid = l + (r-l)/2;
            if (nums[mid] < nums[0]) {
                r = mid;
            } else
                l = mid + 1;

        }
        return nums[l];
    }

    // leetcode 154. 寻找旋转排序数组最小值||
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        if (nums[l] < nums[r])
            return nums[l];
        else if (nums[l] == nums[r]) {
            int i = r;
            for (i = nums.length-1; i > l; i--) {
                if (nums[i-1] > nums[i])
                    break;
            }
            return nums[i];
        }
        while (l < r) {
            int mid = l + (r - l)/2;
            if (nums[mid] < nums[0]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }


    // leetcode 852. 山脉数组的峰顶索引
    public int peakIndexInMountainArray(int[] arr) {
        int l = 1;
        int r = arr.length - 2;
        // arr.length >= 3
        while (l < r) {
            int mid = l + (r - l)/2;
            if (arr[mid] > arr[mid-1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid+1]) {
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return l;
    }

    // leetcode 240. 搜索二维矩阵
    // 有点复杂 搞不定
//    public boolean searchMatrix(int[][] matrix, int target) {
//
//    }

    // leetcode 275. H指数
    //  问题是如果不满足的话 把h变大还是变小的问题
    //  0 --- ho --- hl --- n
    //  至少有h篇论文被引用了至少h次
    // 说明 n - h >= hlow
    //  其余 n - h 篇不超过h次
    // 说明 n - h - 1 <= hhigh
    //   hlow <= n - h <= hhigh + 1
    //  n - h > hh 说明 h
    //  如果不满足 h是变
    // n - h < hlow  h一定要变小
    //  核心条件 n - h >= hlow
    // h变大 mid 变小
    // h变小 mid 变大

    // mid是 h

    @Test
    public void testH() {
        int[] nums = {0};
        int i = hIndex(nums);
        System.out.println(i);
    }

    //
    public int hIndex(int[] citations) {
        int l = 0;
        int n = citations.length;
        int r = n - 1;
        while (l < r) {
            int mid = l + (r - l + 1)/2;
            int hl = search_min(citations,mid);
            if (n - hl >= mid) {
                // h
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    // 寻找target的下界
    // 1 2 2 4
    // 0
    public int search_min(int[] nums,int target) {
        int l = 0;
        int r = nums.length ;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    // leetcode 875. 爱吃香蕉的珂珂
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = 10_0000_0000;
        while (l < r) {
            int mid = l + (r-l)/2;
            // 可以吃掉 mid 变小
            if (checkB(piles,h,mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // 1 2 3 4 5 6 7 8
    // [ ]      [ ]

    private boolean checkB(int[] piles, int h, int mid) {
        int count = 0;
        for (int i = 0; i < piles.length; i++) {
            count += (piles[i] + mid - 1) / mid ;
            if (count > h)
                return false;
        }
        return true;
    }

}
