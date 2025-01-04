package 第五天图的广度和深度优先遍历;

public class CreateGraph {
    public static void CreateGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));//value
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));//value
            }
            //生成两个节点，但是节点中只包含value值
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            //线，把两个节点连接起来
            Edge newEdge = new Edge(weight,fromNode,toNode);
            //from点只想to点
            fromNode.nexts.add(toNode);
            //两个节点的出入度发生变化
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }

    }
}
