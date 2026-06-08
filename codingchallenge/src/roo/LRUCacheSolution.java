package roo;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheSolution {

    public static class CacheNode {
        int key;
        int value;
        CacheNode prev;
        CacheNode next;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[ "+key+" , "+value+"]";
        }
    }

    public static class DoublyLinkedList {
        CacheNode head;
        CacheNode tail;

        public DoublyLinkedList() {
            head = new CacheNode(-1, -1);
            tail = new CacheNode(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        private void moveToHead(CacheNode node) {
            remove(node);
            addToHead(node);
        }

        private void remove(CacheNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(CacheNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public CacheNode removeTail() {
            CacheNode rem = tail.prev;
            remove(rem);
            return rem;
        }
    }

    public static class LRUCache {
        Map<Integer, CacheNode> map;
        DoublyLinkedList internalList;
        int capacity;
        int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.internalList = new DoublyLinkedList();
            this.map = new HashMap<>(size);
        }

        public int get(int key) {
            CacheNode node = map.get(key);
            if (node == null) return -1;
            internalList.moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            CacheNode node = map.get(key);
            if (node != null) {
                node.value = value;
                internalList.moveToHead(node);
            } else {
                CacheNode newNode = new CacheNode(key, value);
                map.put(key, newNode);
                internalList.addToHead(newNode);
                size++;
                if (size > capacity) {
                    CacheNode tail = internalList.removeTail();
                    map.remove(tail.key);
                    size--;
                }
            }
        }

        @Override
        public String toString() {
            return map.toString();
        }
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        assert cache.get(1) == 10: "Test 1 Failed: Key 1 should return 10";
        cache.put(2, 20);
        cache.put(3, 30);
        assert cache.get(1) == -1: "Test 2 Failed: Key 1 should be evicted";
        assert cache.get(2) == 20 : "Test 2 Failed: Key 2 should still exist";
        cache.put(2, 25); // Updates key 2, moves to head
        cache.put(4, 40); // Evicts key 3 (since 2 was moved to head)
        assert cache.get(3) == -1 : "Test 3 Failed: Key 3 should be evicted";
        assert cache.get(2) == 25 : "Test 3 Failed: Key 2 should return updated value";

        System.out.println("All unit tests passed successfully!"+cache);

    }
}
