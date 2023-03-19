package demo;

import org.junit.jupiter.api.Test;

public class SearchTest3 {

    // leetcode 4. 寻找
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len = nums1.length + nums2.length;
        int midIndex = (len - 1) / 2;

        int max, min;
        if (nums1.length == 0) {
            max = nums2[nums2.length - 1];
            min = nums2[0];
        } else if (nums2.length == 0) {
            max = nums1[nums1.length - 1];
            min = nums1[0];
        } else {
            max = Math.max(nums2[nums2.length-1],nums1[nums1.length - 1]);
            min = Math.min(nums1[0],nums2[0]);
        }
        int l = min;
        int r = max;
        while (l < r) {
            int mid = l + (r - l + 1)/2;
            int lo = findIndexMin(nums1,nums2,mid);
            if (lo > midIndex) {
                r = mid - 1;
            } else
                l = mid;
        }

        // 3. 根据长度奇偶讨论结果
        if (len % 2 == 1)
            return l;
        int left = l;
        l = min;
        r = max;
        while (l < r) {
            int mid = l + (r - l + 1)/2;
            int lo = findIndexMin(nums1,nums2,mid);
            if (lo > midIndex + 1) {
                r = mid - 1;
            } else
                l = mid;
        }
        int right = l;
            return (left + right)/ 2d;
    }

    private int findIndexMin(int[] nums1, int[] nums2, int target) {
        int l1 = 0; int r1 = nums1.length;
        int l2 = 0; int r2 = nums2.length;
        while (l1 < r1) {
            int mid = l1 + (r1-l1)/2;
            if (nums1[mid] < target) {
                l1 = mid + 1;
            } else {
                r1 = mid;
            }
        }
        while (l2 < r2) {
            int mid = l2 + (r2-l2)/2;
            if (nums2[mid] < target) {
                l2 = mid + 1;
            } else
                r2 = mid;
        }
        return l1 + l2;
    }

    // leetcode. 240 搜索二维矩阵||
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length ; i++) {
            if (checkExist(matrix[i],target))
                return true;
        }
        return false;
    }

    private boolean checkExist(int[] num, int target) {
        int l = 0;
        int r = num.length - 1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (num[mid] < target)
                l = mid + 1;
            else if (num[mid] > target)
                r = mid - 1;
            else
                return true;
        }
        return num[l] == target;
    }

    @Test
    public void test1() {
        int[] l1 = {0,0,0,0,0};
        int[] n2 = {-1,0,0,0,0,0,1};
        findMedianSortedArrays(l1,n2);
    }
}
