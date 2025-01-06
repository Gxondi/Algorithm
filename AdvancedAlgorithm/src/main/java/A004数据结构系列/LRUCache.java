package A004数据结构系列;

import java.util.HashMap;

/**
 * 题目：实现LRU结构
 */
public class LRUCache {
    class DoubleNode {
        public int key;
        public int val;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    class DoubleList{
        private DoubleNode head;
        private DoubleNode tail;

        public DoubleList() {
            head=null;
            tail=null;
        }

        public void addNode(DoubleNode newNode){
            if (newNode == null){
                return;
            }
            if(head == null && tail == null){
                head=newNode;
                tail=newNode;
            }else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        public void moveNodeToTail(DoubleNode node){
            if(tail == node){
                return;
            }
            if(head == node){
                head = node.next;
                head.last = null;
            }else{
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        public DoubleNode removeHead(){
            if(head == null){
                return null;
            }
            DoubleNode ans = head;
            if(head == tail){
                head = null;
                tail = null;
            }else {
                head = ans.next;
                ans.next = null;
                head.last = null;
            }
            return ans;
        }

    }

    private HashMap<Integer,DoubleNode> keyNodeMap = new HashMap<>();

    private DoubleList nodeList;
    private final int capacity;

    public LRUCache(int cap) {
        keyNodeMap = new HashMap<>();
        nodeList = new DoubleList();
        capacity = cap;
    }

    public int get(int key){
        if (keyNodeMap.containsKey(key)){
            DoubleNode ans = keyNodeMap.get(key);
            nodeList.moveNodeToTail(ans);
            return ans.val;
        }
        return -1;
    }
    //新增/更新
    public void put(int key,int value){
        //更新
        if(keyNodeMap.containsKey(key)){
            DoubleNode ans = keyNodeMap.get(key);
            ans.val = value;
            nodeList.moveNodeToTail(ans);
        }else {
            if(keyNodeMap.size() == capacity){
                keyNodeMap.remove(nodeList.removeHead().key);
            }else {
                DoubleNode newNode = new DoubleNode(key,value);
                keyNodeMap.put(key,newNode);
                nodeList.addNode(newNode);
            }
        }
    }
}
