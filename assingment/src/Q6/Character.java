package Q6;

public class Character {

    // Method to convert a character to a number based on the given mapping
    static int charToNum(char c) {
        if (c == 'S') return 6;
        if (c == 'I') return 5;
        if (c == 'X') return 0;
        if (c == 'E') return 8;
        if (c == 'V') return 7;
        if (c == 'N') return 2;
        if (c == 'T') return 1;
        if (c == 'W') return 3;
        if (c == 'Y') return 4;
        return -1; // return -1 for any other character
    }

    // Method to convert a word to an array of numbers based on the given mapping
    static int[] wordToNum(String word) {
        int[] nums = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            nums[i] = charToNum(word.charAt(i));
        }
        return nums;
    }

    // Method to convert an array of numbers to a single number
    static int toNum(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) { // if any number is -1, it means the mapping was not found for that character
                return -1; // return -1 to indicate invalid mapping
            }
            num = num * 10 + nums[i];
        }
        return num;
    }

    // Method to check if the given words can be added to form the given result based on the given mapping
    public static boolean isValid(String[] words, String result) {
        int cnt = 0;
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (charToNum(c) == -1) {
                    cnt++; // count the number of invalid characters in the words
                }
            }
        }
        System.out.println("cnt: " + cnt);

        int[] wordNums = wordToNum(result);
        int target = toNum(wordNums);
        System.out.println("target: " + target);

        int sum = 0;
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            int[] nums = wordToNum(word);
            int wordSum = toNum(nums);
            if (wordSum == -1) {
                return false; // return false if any word has an invalid mapping
            }
            sum += wordSum; // add the value of each word to the sum
        }
        System.out.println("sum: " + sum);

        return sum == target; // return true if the sum of the words equals the target value
    }

    // Main method to test the solution with sample input
    public static void main(String[] args) {
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        System.out.println("isValid: " + isValid(words, result));
    }
}
