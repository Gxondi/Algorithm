package Graph;

import java.util.*;

//拓扑排序，缓存法
public class TopologicalByorder {
    public class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
    public static class Record{
        public DirectedGraphNode node; //节点
        public long nodes; //节点个数

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }
    public class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
        }
    }
    public List<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode,Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur,order);
        }
        ArrayList<Record> records = new ArrayList<>();
        for (Record value : order.values()) {
            records.add(value);
        }
        records.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : records) {
            ans.add(record.node);
        }
        return ans;
    }

    private Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode,Record> order) {
        if (order.containsKey(cur)){
            return order.get(cur);
        }
        long nodes = 0;
        for (DirectedGraphNode neighbor : cur.neighbors) {
            nodes+=f(neighbor,order).nodes;
        }
        Record ans = new Record(cur,nodes+1);
        order.put(cur,ans);
        return ans;
    }
}
