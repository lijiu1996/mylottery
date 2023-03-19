package demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Li JiaWei
 * @ClassName: DpTest
 * @Description:    动态规划相关专题
 * @Date: 2023/3/15 16:19
 * @Version: 1.0
 */
public class DpTest {

    // leetcode 55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int farest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (farest >= i) {
                farest = Math.max(farest,i + nums[i]);
            } else
                break;
        }
        return farest >= nums.length - 1;
    }

    // leetcode 45. 跳跃游戏II
    public int jump(int[] nums) {
        int tmplen = 0;
        int far = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tfar = Math.min(far,nums.length - 1);
            int j;
            for (j = i; j <= tfar; j++) {
                far = Math.max(far,j + nums[j]);
            }
            i = tfar;
            tmplen++;
        }
        return tmplen;
    }

    @Test
    public void testJump() {
        jump(new int[]{1,2,3});
    }
}
