package Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceCentersFinder {

    private int[] parents;
    private int[] depth;
    private List<Integer>[] children;
    private int[] subtreeSizes;
    private int[] serviceCenters;
    private int numOfNodes;

    /**
     * Finds the minimum number of service centers needed to cover all nodes in the tree.
     *
     * @param numOfNodes the number of nodes in the tree
     * @param children   the adjacency list representing the tree
     * @return the minimum number of service centers needed
     */
    public int findMinimumServiceCenters(int numOfNodes, List<Integer>[] children) {
        this.numOfNodes = numOfNodes;
        this.children = children;
        parents = new int[numOfNodes];
        depth = new int[numOfNodes];
        subtreeSizes = new int[numOfNodes];
        serviceCenters = new int[numOfNodes];
        Arrays.fill(parents, -1);
        Arrays.fill(serviceCenters, -1);
        calculateSubtreeSizes(0, -1);
        return calculateServiceCenters(0, -1);
    }

    /**
     * Calculates the size of the subtree rooted at a given node.
     *
     * @param node   the node to start the calculation from
     * @param parent the parent node of the current node
     */
    private void calculateSubtreeSizes(int node, int parent) {
        parents[node] = parent;
        depth[node] = parent == -1 ? 0 : depth[parent] + 1;
        subtreeSizes[node] = 1;
        for (int child : children[node]) {
            calculateSubtreeSizes(child, node);
            subtreeSizes[node] += subtreeSizes[child];
        }
    }

    /**
     * Calculates the minimum number of service centers needed to cover all nodes in the tree.
     *
     * @param node   the node to start the calculation from
     * @param parent the parent node of the current node
     * @return the minimum number of service centers needed
     */
    private int calculateServiceCenters(int node, int parent) {
        if (serviceCenters[node] != -1) {
            return serviceCenters[node];
        }
        int count = 0;
        for (int child : children[node]) {
            count += calculateServiceCenters(child, node);
        }
        int option1 = count;
        int option2 = numOfNodes - subtreeSizes[node];
        serviceCenters[node] = Math.min(option1, option2) + 1;
        return serviceCenters[node];
    }

    public static void main(String[] args) {
        List<Integer>[] children = new List[5];
        for (int i = 0; i < 5; i++) {
            children[i] = new ArrayList<>();
        }
        children[0].add(1);
        children[0].add(2);
        children[0].add(3);
        ServiceCentersFinder sc = new ServiceCentersFinder();
        System.out.println(sc.findMinimumServiceCenters(5, children));
    }
}