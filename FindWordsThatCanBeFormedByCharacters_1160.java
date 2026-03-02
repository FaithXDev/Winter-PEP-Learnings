public class FindWordsThatCanBeFormedByCharacters_1160 {
    public int countCharacters(String[] words, String chars) {
        int[] charCount = new int[26];
        for (char c : chars.toCharArray()) {
            charCount[c - 'a']++;
        }

        int totalLength = 0;

        for (String word : words) {
            int[] wordCount = new int[26];
            boolean canForm = true;
            for (char c : word.toCharArray()) {
                wordCount[c - 'a']++;
                if (wordCount[c - 'a'] > charCount[c - 'a']) {
                    canForm = false;
                    break;
                }
            }
            if (canForm) {
                totalLength += word.length();
            }
        }

        return totalLength;
    }
}

/**
 * LeetCode 1160. Find Words That Can Be Formed by Characters
 * 
 * Approach: Frequency Counting / Hash Map
 * 1. The problem asks us to find words that can be completely formed by the
 * characters in the string `chars`.
 * 2. First, count the frequency of each character in the string `chars` and
 * store it in an array
 * `charCount` of size 26 (since the strings only contain lowercase English
 * letters).
 * 3. Then, iterate through each string in the `words` array.
 * 4. For each string, count the frequency of its characters using a temporary
 * array `wordCount`.
 * 5. Check if the string can be formed: a string can be formed if for every
 * character 'a' to 'z',
 * the frequency in `wordCount` is less than or equal to the frequency in
 * `charCount`.
 * 6. If the string can be formed, add its length to a running total.
 * 7. Return the total sum of lengths at the end.
 */
