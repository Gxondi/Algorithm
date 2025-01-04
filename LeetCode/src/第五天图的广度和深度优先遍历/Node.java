package 第五天图的广度和深度优先遍历;

import java.util.ArrayList;

public class Node {
    Integer value;
    Integer out;//出度
    Integer in;//入度

    ArrayList<Node> nexts;//邻居
    ArrayList<Edge> edges;//边
    Node(Integer value){
        this.value = value;
        this.in = 0;
        this.out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
