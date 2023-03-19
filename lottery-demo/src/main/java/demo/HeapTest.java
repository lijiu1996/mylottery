package demo;

import demo.TwopointTest.ListNode;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class HeapTest {

    //  leetcode 215. 数组中第k大元素
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
        }
        int res = -1;
        for (int i = 0; i < k; i++) {
            res = heap.poll();
            System.out.println(res);
        }
        return res;
    }

    //  leetcode 373. 查找和最小的k对数字
    //  多路归并问题
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0] + a[1]));
        for (int i = 0; i < nums1.length; i++) {
            heap.add(new int[] {nums1[i],nums2[0],i,0} );
        }

        List<List<Integer>> res =new ArrayList<>();
        int total = Math.min(m * n, k);
        for (int i = 0; i < total; i++) {
            int[] poll = heap.poll();
            res.add(List.of(poll[0],poll[1]));
            if (poll[3] < n - 1) {
                // 没到头继续放
                heap.add(new int[] {nums1[poll[2]],nums2[poll[3] + 1],poll[2],poll[3] + 1});
            }
        }
        return res;
    }

    @Test
    public void heapTest1() {
        int[] num1 = {1,2};
        int[] num2 = {3};
        kSmallestPairs(num1,num2,3);
    }

    // leetcode 264. 丑数
    public int nthUglyNumber(int n) {

        int[] res = new int[n];
        Set<Integer> hashSet = new HashSet<>();
        res[0] = 1;
        hashSet.add(1);

        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 1; i < n;) {
            // 每次比较多个值 同时输出一个值 无敌了

            queue.add(new int[] {res[idx2] * 2,2});
            queue.add(new int[] {res[idx3] * 3,3});
            queue.add(new int[] {res[idx5] * 5,5});
            int[] poll = queue.poll();
            if (poll[1] == 2)
                idx2++;
            else if (poll[1] ==3)
                idx3++;
            else
                idx5++;
            if (!hashSet.contains(poll[0])) {
                res[i] = poll[0];
                i++;
            }
        }
        return res[n];
    }

    // leetcode 786. 第k个最小的素数分数
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingDouble(
                a -> arr[a[0]] * 1.0 / arr[a[1]]
        ));
        for (int i = 0; i < arr.length-1; i++) {
            heap.add(new int[] {i,arr[arr.length-1]});
        }
        int[] poll = null;
        for (int i = 0; i < k; i++) {
            // 执行k次输出
            poll = heap.poll();
            int x = poll[0];
            int y = poll[1];
            // 还能继续放
            if (y - 1 > x) {
                heap.add(new int[] {x,y-1});
            }
        }
        return new int[] {arr[poll[0]],arr[poll[1]]};
    }

    // leetcode 313. 超级丑数
    public int nthSuperUglyNumber(int n, int[] primes) {
        long[] res = new long[n];
        int[] index = new int[primes.length];

        // 存放已经出现过的丑数
        Set<Integer> hashset = new HashSet<>();

        res[0] = 1;
        hashset.add(1);

        PriorityQueue<long[]> heap = new PriorityQueue<>((Comparator.comparingLong(a ->
                a[0])));
        for (int i = 0; i < primes.length; i++) {
            heap.add(new long[] {primes[i],i});
        }

        // 一共需要再添加n个丑数
        for (int i = 1; i < n; i++) {
            long[] poll = heap.poll();
            int i1 = (int)poll[1];
            index[i1]++;
            if (hashset.contains((int)poll[0])) {
                // 本次无效
                i--;
            } else {
                res[i] = poll[0];
                hashset.add((int)poll[0]);
            }
            heap.add(new long[] {res[index[i1]] * primes[i1], i1});
        }
        return (int)res[n-1];
    }

    //
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode();
        ListNode rear = res;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                rear.next = list1;
                list1 = list1.next;
            } else {
                rear.next = list2;
                list2 = list2.next;
            }
            rear = rear.next;
        }

        if (list1 != null) {
            rear.next = list1;
        } else {
            rear.next = list2;
        }

        return res.next;
    }

    // leetcode 23. 合并k个升序链表
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode res = new ListNode();
        ListNode rear = res;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(
                Comparator.comparingInt(listnode -> listnode.val)
        );

        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }

        while (!heap.isEmpty()) {
            ListNode poll = heap.poll();
            rear.next = heap.poll();
            rear = rear.next;
            if (poll.next != null)
                heap.add(poll.next);
        }

        return res.next;
    }

    @Test
    public void testchou() {
        int[] nums = {2,7,13,19};
        nthSuperUglyNumber(12,nums);
    }
}
