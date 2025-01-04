package 第五天图的广度和深度优先遍历;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    HashMap<Integer,Node> nodes;
    HashSet<Edge> edges;

    public Graph(){
       nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
