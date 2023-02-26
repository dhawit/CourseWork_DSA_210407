package Q8;

import java.util.ArrayList;
import java.util.List;

public class EvenNumberFinder {
    public static int findKthMissingEvenNumber(int[] numbers, int k) {
        List<Integer> missingEvens = new ArrayList<>();
        int j = 0;
        for (int i = numbers[0]; i < numbers[numbers.length - 1]; i += 2) {
            if (j < numbers.length && numbers[j] == i) {
                j++;
                continue;
            }
            missingEvens.add(i);
            if (missingEvens.size() == k) {
                return i;
            }
        }
        return numbers[numbers.length - 1] + 2 * k;
    }

    public static void main(String[] args) {
        int[] numbers = {0, 2, 6, 18, 22};
        int k = 6;
        System.out.println(findKthMissingEvenNumber(numbers, k));
    }
}
