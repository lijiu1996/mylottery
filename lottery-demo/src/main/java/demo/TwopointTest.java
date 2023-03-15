package demo;

/**
 * @author Li JiaWei
 * @ClassName: TwopointTest
 * @Description:
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
    }
}
