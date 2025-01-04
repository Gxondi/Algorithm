package 第五天图的广度和深度优先遍历;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS {
    public static void Graph_DFS(Node start){
        if (start == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(cur.value);
                    break;
                }
            }
        }
    }
}
