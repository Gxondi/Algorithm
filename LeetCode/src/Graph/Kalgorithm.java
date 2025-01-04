package Graph;

import com.sun.glass.ui.Size;

import java.util.*;

//k算法，最小生成树
public class Kalgorithm {
    //并查集
    public class UnionFind{
       public HashMap<Node,Node> parent;//k是当前节点，v是当前节点的父节点
       public HashMap<Node,Integer> mapsize;//k是当前节点，v是当前节点的数值

        public UnionFind() {
            parent = new HashMap<Node, Node>();
            mapsize = new HashMap<Node, Integer>();
        }
        public void makeSets(Collection<Node> nodes) {
            parent.clear();
            mapsize.clear();
            for (Node node : nodes) {
                parent.put(node, node);//将每个元素封装起来，自己指向自己，k，v都是指向自己
                mapsize.put(node, 1);//封装起来数值是1
            }
        }
        //查找元素的父亲节点，如果两个元素父亲节点都是一样的话，说明他们已经在一个一个集合当中
        public Node find(Node n){
            Stack<Node> path = new Stack<Node>();
            //循环遍历找到此节点的父节点是自己，说明已经到顶，并且找到了父节点
            while (n!=parent.get(n)){
                path.add(n);
                n = parent.get(n);
            }
            //此时n弹出来说明，过程中产生过的‘n’都最大祖宗就是n
            //所以把这些元素的直接父节点都设置成n，
            while (!path.isEmpty()){
                parent.put(path.pop(),n);
            }
            return n;
        }
        public boolean isSameSet(Node a, Node b) {
            return find(a) == find(b);
        }
        //合并，两个点并不在一个集合中，所以讲两个点合并，集合数量-1
        public void union(Node a,Node b){
            if (a == null || b == null){
                return;
            }
            //找到各自父节点
            Node f1 = find(a);
            Node f2 = find(b);
            //父节点不相同进行
            if (f1!=f2){
                //得到次集合的size，也就是次集合中有多少数据
                int aSetsize = mapsize.get(f1);
                //得到次集合的size，也就是次集合中有多少数据
                int bSetsize = mapsize.get(f2);
                //小的数据附加到大的里面，小数据的父节点也要变成大节点的父节点
                if (aSetsize<=bSetsize){
                    parent.put(f1,f2);
                    mapsize.put(f2,aSetsize+bSetsize);
                    mapsize.remove(f1);
                }else {
                    parent.put(f2,f1);
                    mapsize.put(f1,aSetsize+bSetsize);
                    mapsize.remove(f2);
                }
            }
        }
    }
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }
    //public HashMap<Integer,Node> nodes;
    public Set<Edge> Kruska(Graph graph){
        //使用并查集
        UnionFind uf = new UnionFind();
        //格式化数据，把点都封装好
        uf.makeSets(graph.nodes.values());
        //优先级队列小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(new EdgeComparator());
        //遍历所有线加入队列中，
        /*
        for (Edge edge1 : graph.edges) {
            priorityQueue.add(edge1);
        }
        */

        //把所有线返回
        Set<Edge> result = new HashSet<Edge>();
        //不为空进行
        while (!priorityQueue.isEmpty()){
            //弹出一个线，从小到达弹出
            Edge poll = priorityQueue.poll();
            //前后节点是否在一个结合（大小线已经排好序）
            if (!uf.isSameSet(poll.from,poll.to)){
                result.add(poll);
                uf.union(poll.from,poll.to);
            }
        }
        return result;
    }
}
