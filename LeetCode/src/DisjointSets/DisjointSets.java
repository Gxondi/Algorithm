package DisjointSets;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
//并查集
//是一种数据结构，用于处理一些不交集（Disjoint sets，一系列没有重复元素的集合）的合并及查询问题。
// 并查集支持如下操作： 查询：查询某个元素属于哪个集合，通常是返回集合内的一个“代表元素”。
public class DisjointSets {
    public class node<V>{
        V value;

        public node(V value) {
            this.value = value;
        }
    }
    public class Union<V>{
        public HashMap<V,node<V>> nodes = new HashMap<>();
        public HashMap<node<V>,node<V>> parent = new HashMap<>();
        public HashMap<node<V>,Integer> mapsize = new HashMap<>();

        public Union(List<V> values) {
            nodes = new HashMap<>();
            parent = new HashMap<>();
            mapsize = new HashMap<>();
            for (V cur : values) {
                node<V> node = new node<V>(cur);
                nodes.put(cur,node);
                parent.put(node,node);
                mapsize.put(node,1);
            }
        }
        public node<V> findFather(node<V> cur){
            Stack<node<V>> path = new Stack<>();
            while (cur!=parent.get(cur)){
                path.push(cur);
                cur = parent.get(cur);
            }
            while (!path.isEmpty()){
                parent.put(path.pop(),cur);
            }
            return cur;
        }
        public boolean isSameSet(V a,V b){
            return findFather(nodes.get(a))==findFather(nodes.get(b));
        }
        public void union(V a, V b){
            node<V> ahead = findFather(nodes.get(a));
            node<V> bhead = findFather(nodes.get(b));
            if (ahead!=bhead){
                int aSetSize = mapsize.get(ahead);
                int bSetSize = mapsize.get(bhead);
                node<V> big = aSetSize>=bSetSize?ahead:bhead;
                node<V> small = big == ahead?bhead:ahead;
                parent.put(small,big);
                mapsize.put(big,aSetSize+bSetSize);
                mapsize.remove(small);
            }
         }
    }

}
