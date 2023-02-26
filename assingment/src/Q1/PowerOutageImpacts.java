package Q1;//1b
import java.util.ArrayList;
import java.util.List;

public class PowerOutageImpacts {


    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int targetDevice = 4;
        List<Integer>[] adjList = createAdjacencyList(edges);
        List<Integer> impactedDevices = getImpactedDevices(targetDevice, adjList);
        System.out.println("Impacted Device List: " + impactedDevices);
    }

    // function to create an adjacency list from the given edges
    public static List<Integer>[] createAdjacencyList(int[][] edges) {
        int n = edges.length + 1; // number of network devices
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList[u].add(v);
            adjList[v].add(u);
        }
        return adjList;
    }

    // function to get the impacted device list due to power outage on a certain device
    public static List<Integer> getImpactedDevices(int targetDevice, List<Integer>[] adjList) {
        int n = adjList.length; // number of network devices
        boolean[] visited = new boolean[n];
        List<Integer> impactedDevices = new ArrayList<>();
        dfs(targetDevice, -1, visited, adjList, impactedDevices);
        return impactedDevices;
    }

    // DFS function to mark all the impacted devices due to power outage
    public static void dfs(int currentDevice, int parent, boolean[] visited, List<Integer>[] adjList, List<Integer> impactedDevices) {
        visited[currentDevice] = true;
        for (int connectedDevice : adjList[currentDevice]) {
            if (!visited[connectedDevice]) {
                dfs(connectedDevice, currentDevice, visited, adjList, impactedDevices);
                if (connectedDevice != parent && connectedDevice != currentDevice) {
                    impactedDevices.add(connectedDevice);
                }
            }
        }
    }
}
