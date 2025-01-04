package Dijksatra;

import Graph.Edge;
import Graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
//迪杰斯特拉
//记忆化搜索
public class Dijksatra {
    //有环有向图，返回到达每个点的最短路径
    public HashMap<Node,Integer> Dijksatra(Node from){
        //距离map，k当前节点，v原始点到次点距离是多少
        HashMap<Node,Integer> distancemap = new HashMap<>();
        //初始化，自己到自己，距离为0
        distancemap.put(from,0);
        //初始化，已经被选过的点
        HashSet<Node> selectedNode = new HashSet<>();
        //在hashmap中，距离最小的节点拿出来（代表最初节点a，到此节点的距离）
        Node minNode = GetMinNodeAndUnSelectedNode(distancemap,selectedNode);
        while (minNode!=null){
            //原始点到此点距离
            int distance = distancemap.get(minNode);
            //得到此点所有边
            for (Edge edge : minNode.edges) {
                //所有边指向的下个点
                Node toNode = edge.to;
                //下一个点是否在map集合中了？
                if (!distancemap.containsKey(edge.to)){
                    //如果不在，更新map中的值，把此点加入map中，原始点到该点距离是distance+edge.weight
                    distancemap.put(toNode,distance+edge.weight);
                }else {
                    //如果在，用map中旧的距离，与新的距离作比较，小的更新
                    distancemap.put(edge.to,Math.min(distancemap.get(edge.to),distance+edge.weight));
                }
            }
            selectedNode.add(minNode);
            minNode = GetMinNodeAndUnSelectedNode(distancemap,selectedNode);
        }
        return distancemap;
    }
    //记忆化搜索，此点已经加入hashmap中，直接搜索key值返回value
    private Node GetMinNodeAndUnSelectedNode(HashMap<Node, Integer> distance, HashSet<Node> selectedNode) {
        Node minNode = null;
        int mindistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> nodeIntegerEntry : distance.entrySet()) {
            Node key = nodeIntegerEntry.getKey();
            Integer value = nodeIntegerEntry.getValue();
            if (!selectedNode.contains(key)&&value<mindistance){
                minNode = key;
                mindistance = value;
            }
        }
        return minNode;
    }
}
