package Graph;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Prime {
    public Set<Edge> Prime(Graph graph){
        //优先级队列，小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(new Kalgorithm.EdgeComparator());
        //nodeset，当前节点是否被加入进去过
        HashSet<Node> NodeSet = new HashSet<Node>();
        //返回结果的的路线
        Set<Edge> result = new HashSet<Edge>();
        for (Node node : graph.nodes.values()) {//随便选一个点
            if (!NodeSet.contains(node)){//判断集合中是否有这个点
                NodeSet.add(node);//没有的话将此点加入到nodeset集合中
                for (Edge edge : node.edges) {//遍历当前节点的线
                    priorityQueue.add(edge);//把所以线按照从小到大存入优先级队列中
                }
                while (!priorityQueue.isEmpty()){//队列不为空进行
                    Edge edge = priorityQueue.poll();//弹出最小线
                    Node toNode = edge.to;//次线链接的下一个node
                    if (!NodeSet.contains(toNode)){//判断当前点是否存在nodeset集合中
                        NodeSet.add(toNode);//不存在加入
                        result.add(edge);//一条最小线出现，加入result中
                        for (Edge edge1 : toNode.edges) {//继续遍历下个节点的下属线
                            priorityQueue.add(edge1);
                        }
                    }
                }
            }
            break;
        }
        return result;
    }
}
