package demo;

import org.junit.jupiter.api.Test;

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

    // 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,Integer> collect = new HashMap<>();
        Set<String> collect2 = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            collect.merge(nums[i],1, (old,v) -> old +v);
        }
        for (int i = 0; i < nums.length ; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int find = 0 - nums[i] - nums[j];
                Integer integer = collect.get(find);
                if (integer == null)
                    continue;
                if (integer == 1 && (find == nums[i] || find == nums[j]))
                    continue;
                List<Integer> num = new ArrayList<>();
                num.add(nums[i]);
                num.add(nums[j]);
                num.add(find);
                Collections.sort(num);
                StringBuilder keyb = new StringBuilder();
                for (int num1 : nums) {
                    keyb.append(num1);
                    keyb.append("_");
                }
                String key = keyb.toString();
                if (!collect2.contains(key)) {
                    collect2.add(key);
                    res.add(num);
                }
            }
        }
        return res;
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
}
