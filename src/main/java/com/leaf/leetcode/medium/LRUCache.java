package com.leaf.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.NodeChangeEvent;

/**
 * @author yubis
 * @create 2021/1/1 12:11
 * @package com.leaf.leetcode.medium
 * @since 1.0.0
 */
public class LRUCache {

    //https://leetcode-cn.com/problems/lru-cache/
    //146. LRU 缓存机制
    //运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
    //实现 LRUCache 类：
    //
    //LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
    //int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    //void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
    //
    //
    //进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

    class CacheNode {

        private int key;
        private int value;
        private CacheNode pre;
        private CacheNode next;

        CacheNode() {

        }

        CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    private Map<Integer, CacheNode> map;
    private CacheNode head;
    private CacheNode tail;
    private int capacity;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new CacheNode();
        tail = new CacheNode();
        head.next = tail;
        tail.pre = head;
    }

    //删除某个节点元素
    public void removeNode(CacheNode cacheNode) {
        cacheNode.pre.next = cacheNode.next;
        cacheNode.next.pre = cacheNode.pre;
    }

    //将某个node添加到头节点
    public void addHead(CacheNode cacheNode) {
        //取出真是数据开始节点，也就是虚节点的next节点
        CacheNode firstNode = head.next;
        //虚节点的next节点就为cacheNode(新节点)
        head.next = cacheNode;
        //cacheNode的pre节点为虚节点（head节点）
        cacheNode.pre = head;
        //cache节点的next节点为原真是节点
        cacheNode.next = firstNode;
        //原真实节点的pre节点 cache节点
        firstNode.pre = cacheNode;
    }

    //将cacheNode移动到head,先从链表中移除该节点，然后移动到头
    public void moveToHead(CacheNode cacheNode) {
        removeNode(cacheNode);
        addHead(cacheNode);
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            //map中含有这个key，那么直接取出，并且将这个key和value移动到 head
            CacheNode cacheNode = map.get(key);
            moveToHead(cacheNode);
            return cacheNode.value;
        }
        return -1;

    }

    public void put(int key, int value) {
        CacheNode cacheNode = map.get(key);
        if (null != cacheNode) {
            cacheNode.key = key;
            cacheNode.value = value;
            moveToHead(cacheNode);
        } else {
            //cacheNode 为空说明是新增
            cacheNode = new CacheNode(key, value);
            map.put(key, cacheNode);
            addHead(cacheNode);
            if (map.size() > capacity) {
                map.remove(tail.pre.key);
                removeNode(tail.pre);
            }
        }
    }

}