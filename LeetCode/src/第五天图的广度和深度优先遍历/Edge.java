package 第五天图的广度和深度优先遍历;

/*
* 权重，
* 来自哪里节点
* 去哪里节点
* */
public class Edge {
    Integer weight;
    Node from;
    Node to;

    public Edge(Integer weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
