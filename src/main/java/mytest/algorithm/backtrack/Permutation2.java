package mytest.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutation2 {
    List<List<Integer>> result = new ArrayList<>();
    boolean[] used;

    public static void main(String[] args) {
        System.out.println(new Permutation2().permuteUnique(new int[]{1, 2, 1}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        // 对数组排序，以便后面跳过重复项
        Arrays.sort(nums);
        used = new boolean[nums.length];
        permute(nums, track);
        return result;
    }

    private void permute(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            result.add(new ArrayList<>(track));
            return;
        }
        // pre记录前一个访问过的值，used记录递归时访问的位置，每次递归时，访问的值就记录为true，深层递归时跳过此项
        int pre = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (pre == nums[i] || used[i]) { // 可以使用 if ((i>0 && nums[i-1] == nums[i]) || used[i]) 代替
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            pre = nums[i];
            permute(nums, track);
            used[i] = false;
            track.removeLast();
        }
    }
}
