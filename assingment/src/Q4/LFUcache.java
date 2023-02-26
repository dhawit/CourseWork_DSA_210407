package Q4;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// LFU Cache implementation
class LFUCache {
    // HashMap to store the key and node
    private Map<Integer, Node> cacheMap = new HashMap<>();
    // HashMap to store the key and its frequency count
    private Map<Integer, Integer> countMap = new HashMap<>();
    // TreeMap to store the frequency count and the linked list of nodes having that frequency
    private TreeMap<Integer, FrequencyList> frequencyMap = new TreeMap<>();
    private final int capacity;



    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key) || capacity == 0) {
            return -1;
        }

        // Remove element from current frequency list and move it to the next one in O(1) time
        Node nodeToDelete = cacheMap.get(key);
        Node node = new Node(key, nodeToDelete.getValue());
        int frequency = countMap.get(key);
        frequencyMap.get(frequency).remove(nodeToDelete);
        removeIfListEmpty(frequency);
        cacheMap.remove(key);
        countMap.remove(key);
        cacheMap.put(key, node);
        countMap.put(key, frequency + 1);
        frequencyMap.computeIfAbsent(frequency + 1, k -> new FrequencyList()).add(node);
        return cacheMap.get(key).getValue();
    }

    public void put(int key, int value) {
        if (!cacheMap.containsKey(key) && capacity > 0){

            Node node = new Node(key, value);

            if (cacheMap.size() == capacity) {
                // remove the first node(LRU) from the list the of smallest frequency(LFU)
                int lowestCount = frequencyMap.firstKey();
                Node nodeToDelete = frequencyMap.get(lowestCount).head();
                frequencyMap.get(lowestCount).remove(nodeToDelete);
                removeIfListEmpty(lowestCount);

                int keyToDelete = nodeToDelete.getKey();
                cacheMap.remove(keyToDelete);
                countMap.remove(keyToDelete);
            }
            frequencyMap.computeIfAbsent(1, k -> new FrequencyList()).add(node);
            cacheMap.put(key, node);
            countMap.put(key, 1);

        }
        else if(capacity > 0){
            Node node = new Node(key, value);
            Node nodeToDelete = cacheMap.get(key);
            int frequency = countMap.get(key);
            frequencyMap.get(frequency).remove(nodeToDelete);
            removeIfListEmpty(frequency);
            cacheMap.remove(key);
            countMap.remove(key);
            cacheMap.put(key, node);
            countMap.put(key, frequency + 1);
            frequencyMap.computeIfAbsent(frequency + 1, k -> new FrequencyList()).add(node);


        }
    }

    // Remove frequency key from the frequencyMap if corresponding frequency list is empty
    private void removeIfListEmpty(int frequency) {
        if (frequencyMap.get(frequency).len() == 0) {
            frequencyMap.remove(frequency);
        }
    }

    // Node class to store the key, value and previous and next node references
    private class Node {
        private int key;
        private int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    // FrequencyList class to store the linked list of nodes having same frequency count
    private class FrequencyList {
        private int length;
        private Node head;
        private Node tail;

        public void add(Node node) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            length++;
        }

        public void remove(Node node) {

            if (node.next == null) tail = node.prev;
            else node.next.prev = node.prev;

            if (node.prev == null) head = node.next;
            else node.prev.next = node.next;

            length--;
        }

        public Node head() {
            return head;
        }

        public int len() {
            return length;
        }
    }
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2); // create a cache with capacity of 2

        // add elements to cache
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));    // returns 1
        cache.put(3, 3);                     // evicts key 2
        System.out.println(cache.get(2));    // returns -1 (not found)
        System.out.println(cache.get(3));    // returns 3
        cache.put(4, 4);                     // evicts key 1
        System.out.println(cache.get(1));    // returns -1 (not found)
        System.out.println(cache.get(3));    // returns 3
        System.out.println(cache.get(4));    // returns 4
    }

}