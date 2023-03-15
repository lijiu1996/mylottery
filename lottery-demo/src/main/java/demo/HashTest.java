package demo;

import org.junit.jupiter.api.Test;

import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.stream.Collectors;

public class HashTest {

    // leetcode 1002 寻找共用字符
    // 单词 转化为 map -> {a :'1',b : '2' , 3, 5};
    // a 1 2 3 4 5
    public List<String> commonChars(String[] words) {
        List<String> res = new ArrayList<>();
        Map<Character,List<Integer>> countMap = new HashMap<>();
        // 遍历单词 转换为字母表
        for (String word : words) {
            Map <Character,Integer> count = new HashMap<>();
            for (int i = 0 ; i < word.length(); i++) {
                char c = word.charAt(i);
                count.merge(c,1,(old,v)-> old + v);
            }
            count.forEach((k,v) -> {
                countMap.compute(k, (key,old) -> {
                    if (old == null) {
                        old = new ArrayList<>();
                    }
                    old.add(v);
                    return old;
                });
            });
        }
        for (Character character : countMap.keySet()) {
            List<Integer> integers = countMap.get(character);
            if (integers.size() != words.length) {
                continue;
            }
            Integer min = Collections.min(integers);
            for (int i = 1; i <= min; i++) {
                res.add(String.valueOf(character));
            }
        }
        return res;
    }


    // leetcode 242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        Map<Character,Integer> m1 = new HashMap<>();
        Map<Character,Integer> m2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            m1.merge(c,1,(old,v) -> old + v);
            char c2 = t.charAt(i);
            m2.merge(c2,1,(old,v) -> old +v);
        }
        for (Character c : m1.keySet()) {
            if (!m1.get(c).equals(m2.get(c)))
                return false;
        }
        return true;
    }

    // leetcode 387.
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] dic1 = new int[26];
        int[] dic2 = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            dic1[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            dic2[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (dic1[i] > dic2[i])
                return false;
        }
        return true;
    }

    // leetcode 49. 字母异位词分组
    // 本题核心 如何进行char[]的保存
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> collect = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            collect.compute(s,(k,old) -> {
                if (old == null) {
                    old = new ArrayList<>();
                }
                old.add(str);
                return old;
            });
        }
        return collect.values().stream().collect(Collectors.toList());
    }

    // leetcode 438
//    public List<Integer> findAnagrams(String s, String p) {
//
//    }

    // leetcode 349. 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        int[] count1 = new int[1010];
        int[] count2 = new int[1010];
        for (int i = 0; i < nums1.length; i ++) {
            count1[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            count2[nums2[i]]++;
        }
        
        for (int i = 0 ; i < 1010; i++) {
            int i1 = count1[i];
            int i2 = count2[i];
            if (i1 > 0 && i2 > 0) {
                    res.add(i);

            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    // leetcode 350. 两个数组的交集
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        int[] count1 = new int[1010];
        int[] count2 = new int[1010];
        for (int i = 0; i < nums1.length; i ++) {
            count1[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            count2[nums2[i]]++;
        }

        for (int i = 0 ; i < 1010; i++) {
            int i1 = count1[i];
            int i2 = count2[i];
            if (i1 > 0 && i2 > 0) {
                for (int j = 0; j < Math.min(i1,i2); j++) {
                    res.add(i);
                }
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    // leetcode 202 快乐数
    public boolean isHappy(int n) {
        // 2 4 16 37 58 106 37
        Set<Integer> procedure = new HashSet<>();
        // 执行一次计算 如果结果在set中 返回
        procedure.add(n);
        while (true) {
            int res = 0;
            while (n > 0) {
                res += (n % 10) * (n % 10);
                n /= 10;
            }
            if (res == 1)
                return true;
            if (procedure.contains(res))
                return false;
            procedure.add(res);
            n = res;
        }
    }

    // leetcode 1.
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            map.put(nums[i],i);
        for (int i = 0; i < nums.length; i++) {
            Integer t = map.get(target - nums[i]);
            if (t != null) {
                return new int[] {i,t};
            }
        }
        return null;
    }

    // leetcode 454. 四数相加
    // 加速方案是这样的 就是没有必要说非得遍历i j k
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums1.length ; i++) {
            for (int j = 0; j < nums2.length; j++) {
                count.merge(nums1[i] + nums2[j], 1, (old,v) -> old+v);
            }
        }

        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length ; j++) {
                Integer integer = count.get(0 - nums3[i] - nums4[j]);
                if (integer != null) {
                    res += integer;
                }
            }
        }
        return res;
    }

    // 三数之和 时间复杂度超限
    // 双指针法优势 去重十分方便
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Set<String> distin = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0 ; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    String s = "" + nums[i] + "_" + nums[j] + "_" + nums[k];
                    if (!distin.contains(s)) {
                        distin.add(s);
                        List<Integer> num = List.of(nums[i], nums[j], nums[k]);
                        res.add(num);
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void testFourSum() {
        int[] nums = new int[] {10_0000_0000,10_0000_0000,10_0000_0000,10_0000_0000};
        int target = -294967296;
        fourSum(nums,target);
    }

    // 四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length -3 ; i++) {
            if (i != 0 && nums[i-1] == nums[i])
                continue;
            for (int j = i+1; j < nums.length - 2; j++) {
                if (j != i + 1 && nums[j-1] == nums[j])
                    continue;
                int m = j + 1;
                int n = nums.length - 1;
                while (m < n) {
                    long sum = (long)nums[i] + nums[j] + nums[m] + nums[n];
                    if (sum > target) {
                        n--;
                    } else if (sum < target) {
                        m++;
                    } else {
                        ArrayList<Integer> list = new ArrayList<>();
                        Collections.addAll(list,nums[i],nums[j],nums[m],nums[n]);
                        res.add(list);
                        m++; n--;
                        while (m < n && nums[m] == nums[m-1] ) {
                            m++;
                        }
                        while (m < n && nums[n] == nums[n+1])
                            n--;
                    }
                }
            }
        }
        return res;
    }

    // 344. 反转字符串
    // 相当于是原地反转数组
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length /2; i++) {
            char tmp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = tmp;
        }
    }

    // 541. 反转字符串||
    // 每2k个字符进行反转 其中
    //  | k = 2
    // [1 2 3 4 5 6 ]
    // 判断当前剩余个数
    // <k 直接开始反转 反转全部
    // k <= <2k 反转前k个
    // = 2k 反转前k个
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2*k) {
            int remain = chars.length - i;
            int l,r;
            if (remain >= k) {
                l = i;
                r = l + k - 1;
            } else {
                l = i;
                r = chars.length - 1;
            }
            while (l < r) {
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++;
                r--;
            }
        }
        return new String(chars);
    }

    // 剑指offer05 替换空格
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                stringBuilder.append("%20");
            } else
                stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    // leetcode 151 反转单词
    // xxxx  x   xx xx
    // ||
    @Test
    public void test4() {
        String[] list = {"the sky is blue"};
        for (String s : list) {
            String s1 = reverseWords(s);
            System.out.println(s1);
        }
    }

    // 死的这么简单嘛
    // [the  sky  is  blud]
    public String reverseWords(String s) {
        // s.length = ()
        char[] chars = s.toCharArray();
        int start = 0, end = s.length() - 1;
        while (chars[start] == ' ')
            start++;
        while (chars[end] == ' ')
            end--;
        int fast,slow;
        for (fast = slow = start; fast <= end; ) {
            if (chars[fast] != ' ')
                chars[slow++] = chars[fast++];
            else {
                while (fast <= end && chars[fast] == ' ')
                    fast++;
                chars[slow++] = ' ';
            }
        }
        int newend = slow - 1;
        reverse(chars,start,newend);
        int fast2,slow2;
        for (fast2 = slow2 = start; fast2 <= newend;) {
            while (fast2 <= newend && chars[fast2] != ' ')
                fast2++;
            reverse(chars,slow2,fast2 - 1);
            slow2 = fast2 = fast2 + 1;
        }
        return new String(chars,start,newend - start + 1);
    }

    // 剑指Offer 58
    // 将前n个字符移至字符串尾部
    // abcdefg k = 2
    // gfedcba
    // cdefgab
    public String reverseLeftWords(String s, int n) {
        // 先整体反转
        // 再各自反转
        char[] chars = s.toCharArray();
        reverse(chars,0,chars.length-1);
        int l = s.length() - n;
        reverse(chars,0, l - 1);
        reverse(chars,l , chars.length - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;end--;
        }
    }


    // 统计字符串字符个数
    @Test
    public void test1() {

        int[] i1 = new int[] {1,2,3};
        int[] i2 = new int[] {1,2,3};

        boolean equals = Arrays.equals(i1, i2);
        System.out.println(equals);

        String t = "cbb";
    }

    @Test
    public void test2() {
        String s = "  —123   456 ";
        String[] s1 = s.split(" ");
        System.out.println(Arrays.toString(s1));
    }

    @Test
    public void test3() {
        String s = "abcdefg";
        char[] chars = s.toCharArray();
        int indexto = 3;
        String s1 = new String(chars, 1, 3);
        System.out.println(s1);
    }
}
