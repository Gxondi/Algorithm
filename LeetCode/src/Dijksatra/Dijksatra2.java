package Dijksatra;

import Graph.Edge;
import Graph.Node;

import java.util.HashMap;

public class Dijksatra2 {
    public static class Record{
        public Node node; //节点
        public int distance; //节点个数

        public Record(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
    public class NodeHeap{
        public Node[] nodes;
        //k是当前节点，v是当前节点下标
        public HashMap<Node,Integer> parentMap = new HashMap<>();
        //k是当前节点，v是当前节点到原始节点的距离
        public HashMap<Node,Integer> distanceMap = new HashMap<>();
        int size;
        //加强堆
        public NodeHeap(int size){
            nodes = new Node[size];
            parentMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        //判断是否进入堆
        public boolean IsEntered(Node node){
            return parentMap.containsKey(node);
        }
        //判断现在是否在堆，-1表示已经被用完，不能再被使用
        public boolean inheap(Node node){
            return IsEntered(node)&&parentMap.get(node)!=-1;
        }
        //发现一个node，从原始到此节点距离为distacne，判断要不要添加更新
        public void AddOrUpdateOrIgnre(Node node,int distance){
            if (inheap(node)){
                //在堆中，新老数据对比更新
                distanceMap.put(node,Math.min(distance,distanceMap.get(node)));
                //想上排序（因为小的进入堆中）
                heapInsert(node,distanceMap.get(node));
            }
            //不在堆中
            if (!IsEntered(node)){
                //堆最后一个位置给node
                nodes[size] = node;
                //更新在堆中位置下标
                parentMap.put(node,size);
                //更新距离
                distanceMap.put(node,distance);
                //排序
                heapInsert(node,size++);
            }
        }
        //recode,第一个参数是此节点，第二个是距离
        public Record pop(){
            Record re = new Record(nodes[0],distanceMap.get(nodes[0]));
            //下标的换算所以要减一
            swap(0,size-1);
            //弹出来的值，要变成-1
            parentMap.put(nodes[size-1],-1);
            //下标的值
            distanceMap.remove(nodes[size-1]);
            nodes[size-1] = null;
            heapify(size,--size);
            return re;
        }
        public void heapInsert(Node node,int index){
            while (distanceMap.get(nodes[index])<distanceMap.get(nodes[(index-1)/2])){
                swap(index,(index-1)/2);
                index = (index-1)/2;
            }
        }
        public void heapify(int size,int index){
            int left = index*2+1;
            while (left<size){
                int smallest = distanceMap.get(nodes[left])>distanceMap.get(nodes[left+1])?distanceMap.get(nodes[left+1]):distanceMap.get(nodes[left]);
                smallest = distanceMap.get(nodes[smallest])<distanceMap.get(nodes[index])?distanceMap.get(nodes[smallest]):distanceMap.get(nodes[index]);
                if (smallest == index){
                    return;
                }
                swap(smallest,index);
                index = smallest;
                left = index*2+1;
            }
        }
        private void swap(int i, int j) {
            parentMap.put(nodes[i],j);
            parentMap.put(nodes[j],i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }
    }
    /*
    * 思路：
    * 使用加强堆
    * Dijksatra如何使用加强堆
    * 加强堆需要拓展的功能AddOrUpdateOrIgnre
    * 添加，更新以及忽略
    * 当一个节点进入堆中（小根堆），如果该节点已经存于堆中，新老数据对比，
    * 如果不在堆中，添加到堆末尾
    *
    * */
    public HashMap<Node, Integer> Dijksatra2(Node node, int size){
        NodeHeap nh = new NodeHeap(size);
        //先加入一个节点进入堆中
        nh.AddOrUpdateOrIgnre(node,0);
        HashMap<Node,Integer> result = new HashMap<>();
        //堆不为空，弹出的值，找到边，找到边指向的节点（tonode）再把tonode传入AddOrUpdateOrIgnre
        //长此以往循环直到堆空为止
        while (!nh.isEmpty()){
            Record re = nh.pop();
            Node cur = re.node;
            int distance = re.distance;
            for (Edge edge : node.edges) {
                Node toNode = edge.to;
                nh.AddOrUpdateOrIgnre(toNode,distance);
            }
            result.put(cur,distance);
        }
        return result;
    }
}
