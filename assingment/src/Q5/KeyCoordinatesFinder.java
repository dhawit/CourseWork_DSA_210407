package Q5;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class KeyCoordinatesFinder {

    // Method to find the key coordinates from the given array of rectangles
    public int[][] getKeyCoordinates(int[][] rectangles) {

        // Store the start and end points of each rectangle in a TreeMap
        TreeMap<Integer, Integer> pointsMap = new TreeMap<>();
        for (int[] rectangle : rectangles) {
            pointsMap.put(rectangle[0], Math.max(pointsMap.getOrDefault(rectangle[0], 0), rectangle[2]));
            pointsMap.put(rectangle[1], 0);
        }

        // Keep track of the current height while iterating through the map
        int currHeight = 0;
        int[][] result = new int[pointsMap.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : pointsMap.entrySet()) {
            int x = entry.getKey();
            int h = entry.getValue();
            if (h != currHeight) {
                result[i][0] = x;
                result[i][1] = currHeight = h;
                i++;
            }
        }

        // Return the key coordinates
        return Arrays.copyOfRange(result, 0, i);
    }

    public static void main(String[] args) {
        // Example usage
        KeyCoordinatesFinder obj = new KeyCoordinatesFinder() ;
        int[][] rectangles = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        int[][] result = obj.getKeyCoordinates(rectangles);
        System.out.println(Arrays.deepToString(result));
    }
}

