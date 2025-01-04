package Graph;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSandDFS {
    public class node{
        int value;
        node[] nexts;

        public node(int value, node[] nexts) {
            this.value = value;
            this.nexts = nexts;
        }
    }
    public void BFS(node head){
        if (head == null){
            return;
        }
        HashSet<node> hashSet = new HashSet<>();
        Queue<node> queue = new LinkedList<>();
        queue.add(head);
        hashSet.add(head);
        while (!queue.isEmpty()){
            node cur = queue.poll();
            System.out.println(cur.value);
            for (node next : cur.nexts) {
                if (!hashSet.contains(next)){
                    queue.add(next);
                    hashSet.add(next);
                }
            }
        }
    }
    public void DFS(node head){
        if (head == null){
            return;
        }
        HashSet<node> hashSet = new HashSet<>();
        Stack<node> stack = new Stack<>();
        stack.push(head);
        hashSet.add(head);
        System.out.println(head.value);
        while (!stack.isEmpty()){
            node cur = stack.pop();
            for (node next : cur.nexts) {
                if (!hashSet.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    hashSet.add(next);
                    System.out.println(next.value);
                    break;//直接跳出整个for循环
                }
            }
        }
    }
}
