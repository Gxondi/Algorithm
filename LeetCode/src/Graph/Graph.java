package Graph;

import com.sun.javafx.geom.Edge;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<Integer, Node>();
        edges = new HashSet<Edge>();
    }
}
