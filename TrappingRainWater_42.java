public class TrappingRainWater_42 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int waterTrapped = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    waterTrapped += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    waterTrapped += rightMax - height[right];
                }
                right--;
            }
        }

        return waterTrapped;
    }
}

/**
 * LeetCode 42. Trapping Rain Water
 * 
 * Approach: Two Pointers Look-forward
 * 1. The amount of water that can be trapped above a specific bar is determined
 * by
 * min(max_height_left, max_height_right) - height_of_bar.
 * 2. Instead of finding the left max and right max for each bar by precomputing
 * arrays (which
 * takes O(n) space), we can use two pointers `left` and `right` starting at
 * both ends.
 * 3. We maintain `leftMax` and `rightMax` variables as we traverse the array.
 * 4. If `height[left] < height[right]`, it means the water trapped at `left` is
 * bounded
 * by `leftMax` (since there's a taller bar to the right ensuring the right
 * bound is at least
 * `height[right]`).
 * - Update `leftMax = max(leftMax, height[left])`.
 * - Add `leftMax - height[left]` to total water.
 * - Move `left` pointer to the right.
 * 5. If `height[left] >= height[right]`, the water trapped at `right` is
 * bounded by `rightMax`.
 * - Update `rightMax = max(rightMax, height[right])`.
 * - Add `rightMax - height[right]` to total water.
 * - Move `right` pointer to the left.
 * 6. The process continues until `left` and `right` pointers meet. Time
 * complexity: O(n), Space: O(1).
 */
