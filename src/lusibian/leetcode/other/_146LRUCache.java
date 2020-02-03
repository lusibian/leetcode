package lusibian.leetcode.other;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.HashMap;
import java.util.Map;

public class _146LRUCache {
    // 使用双向链表作为双向队列记录节点，使用map查找节点，插入和查找操作，时间复杂度都为O(1)
    class LRUCache {
        class DListNode {
            int key;
            int value;
            DListNode pre;
            DListNode next;
        }
        DListNode head;
        DListNode end;
        Map<Integer, DListNode> map;
        int maxMapSize;

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity);
            maxMapSize = capacity;
            head = null;
            end = null;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                DListNode tempNode = map.get(key);
                move2End(tempNode);
                return tempNode.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)) {
                // 已存在key，修改value，将节点移至队尾
                DListNode node = map.get(key);
                node.value = value;
                move2End(node);
            } else {
                // 新key，创建新节点，放到队尾，插入map
                DListNode newNode = new DListNode();
                newNode.key = key;
                newNode.value = value;
                newNode.pre = end;
                newNode.next = null;
                if(head == null) {
                    head = newNode;
                } else {
                    end.next = newNode;
                }
                end = newNode;
                map.put(key, newNode);

                // 若容量超出，移除队头的最不常使用节点
                if(map.size() > maxMapSize) {
                    DListNode tempNode = head;
                    head = head.next;
                    if(head != null) {
                        head.pre = null;
                    }
                    map.remove(tempNode.key);
                }
            }
        }

        private void move2End(DListNode node) {
            if(node != end){
                if(node == head){
                    head = node.next;
                } else {
                    node.pre.next = node.next;
                }
                node.next.pre = node.pre;

                node.pre = end;
                node.next = null;
                end.next = node;
                end = node;
            }
        }
    }
}
