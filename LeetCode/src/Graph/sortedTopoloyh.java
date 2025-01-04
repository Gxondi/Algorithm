package Graph;

import sun.plugin.javascript.navig.Link;

import java.util.*;

//拓扑排序
public class sortedTopoloyh {
    public List<Node> sort(Graph graph){
        //k是某个节点，value是剩余的入度
        HashMap<Node,Integer> inMap = new HashMap<Node, Integer>();
        //入度是零的话进入队列
        Queue<Node> zeroInQueue = new LinkedList<Node>();
        for (Node value : graph.nodes.values()) {
            inMap.put(value,value.in);
            if (value.in == 0){
                zeroInQueue.add(value);
            }
        }
        List<Node> ans = new ArrayList<Node>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            ans.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }


}
