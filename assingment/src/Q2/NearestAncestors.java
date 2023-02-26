package Q2;

import java.util.*;

public class NearestAncestors {

    // Method to find the greatest common divisor (GCD) of two numbers using Euclid's algorithm
    public static int findGCD(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        } else {
            return findGCD(num2, num1 % num2);
        }
    }

    // Method to find the nearest ancestor with relative prime value
    public static int findNearestAncestor(int[] values, int[][] edges, int node) {
        // Base case: If the current node is the root node (i.e., has no parent)
        if (node == 0) {
            return -1;
        }

        int parent = -1;
        int gcdValue = 0;

        // Traverse the path from the current node to the root node
        while (node != 0 && gcdValue != 1) {
            // Find the parent of the current node
            for (int i = 0; i < edges.length; i++) {
                if (edges[i][1] == node) {
                    parent = edges[i][0];
                    break;
                }
            }
            gcdValue = findGCD(values[node], values[parent]);
            node = parent;
        }

        if (gcdValue == 1) {
            return parent;
        } else {
            return -1;
        }
    }

    // Main method to find the nearest ancestors for all nodes
    public static int[] findNearestAncestors(int[] values, int[][] edges) {
        int n = values.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // Iterate over all nodes and find their nearest ancestor with relative prime value
        for (int i = 0; i < n; i++) {
            result[i] = findNearestAncestor(values, edges, i);
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        int[] values = {3, 2, 6, 6, 4, 7, 12};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        int[] result = findNearestAncestors(values, edges);

        // Print the result
        System.out.println(Arrays.toString(result));
    }
}