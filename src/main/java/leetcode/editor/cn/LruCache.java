package leetcode.editor.cn;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * <p>
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
 * 限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * <p>
 * 提示：
 * <p>
 * 1 <= capacity <= 3000
 * 0 <= key <= 3000
 * 0 <= value <= 104
 * 最多调用 3 * 104 次 get 和 put
 */


import java.util.HashMap;
import java.util.Map;

public class LruCache {

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        class Node {
            private int key;
            private int value;
            private Node pre;
            private Node next;

            public Node() {

            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, Node> map;
        private int capacity;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        private void removeNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void addHeader(Node node) {
            Node tempNode = head.next;
            head.next = node;
            node.pre = head;
            node.next = tempNode;
            tempNode.pre = node;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addHeader(node);
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                //map中含有这个key，需要将这个key移动到head
                Node node = map.get(key);
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                Node node = new Node(key, value);
                map.put(key, node);
                addHeader(node);
                if (map.size() > capacity) {
                    map.remove(tail.pre.key);
                    removeNode(tail.pre);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}