package demo;

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

    public int removeDuplicates(int[] nums) {
        int fast = 0, slow = 0;
        for (;fast < nums.length; ) {
            while (fast < nums.length && nums[fast] == nums[slow])
                fast++;

        }
        return -1;
    }
}
