package demo;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * @author Li JiaWei
 * @ClassName: SearchTest2
 * @Description:    二分查找 根据新方法练习？
 * @Date: 2023/3/16 10:02
 * @Version: 1.0
 */
public class SearchTest2 {

    // 存在返回下标 不存在返回
    // 如果是 l <= r l的位置可能会出界的
    //  出口的l是有可能出界的
    // 如果是 l < r  l == r时也是出口
    //  出口的l是绝对不会出界的
    //  是否会出界看你的更新方案 如果是[0, length)
    //  更新为[mid + 1,

    // leetcode 704. 搜索插入未知
    public int search(int[] nums, int target) {
//        int l = 0, r = nums.length - 1;
//        while (l <= r) {
//            int mid = l + (r - l)/2;
//            if (nums[mid] == target)
//                return mid;
//            else if (nums[mid] > target) {
//                r = mid - 1;
//            } else {
//                l = mid + 1;
//            }
//        }
//        return -1;

//        int l = 0, r = nums.length;
//        while (l < r) {
//            int mid = l + (r - l)/2;
//            if (nums[mid] == target)
//                return mid;
//            else if (nums[mid] > target) {
//                r = mid - 1;
//            } else {
//                l = mid + 1;
//            }
//        }
//        return nums[l] == target ? l : -1;
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == nums.length)
            return -1;
        return nums[l] == target ? l : -1;
    }


    public int searchInsert(int[] nums, int target) {
//       int l = 0, r = nums.length - 1;
//       while (l <= r) {
//           int mid = l + (r - l)/2;
//           if (nums[mid] >= target) {
//               if (l == r)
//                   return mid;
//               r = mid;
//           } else {
//               l = mid + 1;
//           }
//       }
//       return l;

        // 如果是不等区间的二分查找 l 没有出去的可能性
        // 此处 + 右边全是满足条件的 左边全是不满足条件的 l
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0 ; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0 ; i < q; i++) {
            int query = scanner.nextInt();
            query(a,query);
        }
    }

    // 在a中寻找元素query的上下界
    // 第一个满足条件 >=3 全都满足条件的话就是从0开始
    // 全都不满足条件的话就是从 length开始
    // 找下界的话 条件是>= target
    // 找上界的话 条件是<= target 0 1 2 3 4
    public static void query(int[] a, int query) {
        int l,r;
        l = 0; r = a.length - 1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (a[mid] >= query) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int min = a[l] != query ? -1 : l;

        l = 0; r = a.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1)/2;
            if (a[mid] <= query) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int max = a[l] != query ? -1 : l;
        System.out.println(min + " " + max);
    }

    // leetcode 1011. 在D天内送达包裹的能力
    // 运载能力最小值y y越大 day越小
    // 使y最小 且同时满足 day <= days

    // 设运载能力 为p 则day = 500 i / p
    public int shipWithinDays(int[] weights, int days) {
        int sum = 0, max = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (weights[i] > max)
                max = weights[i];
        }
        int l = max;
        int r = sum;

        while (l < r) {
            int mid = l + (r-l)/2;
            int tday = calcuDays(weights,mid);
            if (tday > days) {
                l = mid + 1;
            } else if (tday <= days) {
                r = mid;
            }
        }
        return l;
    }

    public int calcuDays(int[] weights, int p) {
        int total = 1;
        int batchSum = 0;
        for (int i = 0; i < weights.length; i++) {
            if (batchSum + weights[i] > p) {
                total++;
                batchSum = weights[i];
            } else
                batchSum += weights[i];
        }
        return total;
    }

    // leetcode 1482. 制作m束花所需的最少天数
    // 天数越大 能摘得花越多
    // 可摘花束 >= m 的 最小day

    // 不能摘到m数花则返回-1
    public int minDays(int[] bloomDay, int m, int k) {
        if (m > bloomDay.length / k)
            return -1;
        int min = 10_0000_0001;
        int max = 0;
        for (int i = 0 ; i < bloomDay.length; i++) {
            if (bloomDay[i] < min)
                min = bloomDay[i];
            if (bloomDay[i] > max)
                max = bloomDay[i];
        }
        int l = min; // 取到min的时候 至少能摘0朵把
        int r = max; // 取到max的时候 能摘最多 length/k朵
        while (l < r) {
            int mid = l + (r - l)/2;
            int tsum = checkFlower(bloomDay,k,mid);
            System.out.println(tsum + " " + mid);
            if (tsum >= m) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // 74 .
    // 该二维矩阵本质上还是一维矩阵
    // 实际坐标 x - 1 * n + y
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int l = 0;
        int r = m * n - 1;
        int tx,ty;

        while (l < r) {
            int mid = l + (r-l)/2;
            tx = mid / n;
            ty = mid % n;
            if (matrix[tx][ty] < target) {
                l = mid + 1;
            } else if (matrix[tx][ty] > target) {
                r = mid - 1;
            } else {
                return true;
            }
        }

        tx = l / n;
        ty = l % n;
        return matrix[tx][ty] != target ? false : true;
    }



    @Test
    public void flowerTest() {
        int[] days = {1,10,3,10,2};

//        boolean b = checkFlower(days, 3, 1, 3);
//        System.out.println(b);
    }
    // 经过day天后可以摘取m朵花
    // [x x x _ _  x x _ _ x]
    private int checkFlower(int[] bloomDay, int k, int day) {
        int tsum = 0;
        int fast,slow;
        for (fast = slow = 0; fast < bloomDay.length; fast++) {
            if (bloomDay[fast] > day) {
                tsum += (fast - slow ) / k;
                while (fast < bloomDay.length && bloomDay[fast] > day) {
                    fast++;
                }
                // 两种可能 fast 跑到头了 或者 找到了下一个<= day的开了的花
                if (fast == bloomDay.length) {
                    return tsum ;
                } else {
                    slow = fast;
                }
            }
        }
        // 最终的这段也要加入到计算
        tsum += (fast - slow) / k;
        return tsum ;
    }
}
