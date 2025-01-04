package 第五天图的广度和深度优先遍历;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void Graph_BFS(Node start){
        if (start == null){
            return;
        }
        //申请一个队列存值，申请一个set表，登录，防止重复
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
