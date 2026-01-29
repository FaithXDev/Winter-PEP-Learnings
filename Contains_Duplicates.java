import java.util.HashSet;

public class Contains_Duplicates {
    public boolean containsDuplicate(int[] nums) {
        // Use a HashSet to store seen elements because checking for existence is O(1)
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            // If the number is already in the set, we found a duplicate
            if (set.contains(num)) {
                return true;
            }
            // Otherwise, add the number to the set
            set.add(num);
        }

        // If loop completes without finding duplicates, return false
        return false;
    }

    public static void main(String[] args) {
        Contains_Duplicates solution = new Contains_Duplicates();

        // Test case 1
        int[] nums1 = { 1, 2, 3, 1 };
        System.out.println("Input: [1, 2, 3, 1]");
        System.out.println("Output: " + solution.containsDuplicate(nums1) + " (Expected: true)");

        // Test case 2
        int[] nums2 = { 1, 2, 3, 4 };
        System.out.println("Input: [1, 2, 3, 4]");
        System.out.println("Output: " + solution.containsDuplicate(nums2) + " (Expected: false)");

        // Test case 3
        int[] nums3 = { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };
        System.out.println("Input: [1, 1, 1, 3, 3, 4, 3, 2, 4, 2]");
        System.out.println("Output: " + solution.containsDuplicate(nums3) + " (Expected: true)");
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Hash Set
 * ------------------
 * The problem asks to check if any value appears at least twice in the array.
 * We need a data structure that allows fast lookups to check if an element has
 * been seen before.
 * 
 * 1. Data Structure:
 * - A `HashSet` is ideal because it supports `add` and `contains` operations in
 * O(1) average time.
 * 
 * 2. Algorithm:
 * - Initialize an empty `HashSet`.
 * - Iterate through each element in the input array `nums`.
 * - Before adding an element, check if it already exists in the `HashSet`.
 * - If yes, return `true`.
 * - If no, add it to the `HashSet`.
 * - If the loop finishes, it means all elements were unique, so return `false`.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We traverse the array once. Each lookup and insertion in HashSet takes O(1)
 * on average.
 * Therefore, for N elements, it takes O(N) time.
 * 
 * Space Complexity: O(N)
 * - In the worst case (all elements unique), the HashSet will store all N
 * elements.
 */