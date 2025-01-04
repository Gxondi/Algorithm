package Tire;
//前戳树
public class Trie_Tree {
    public class Node{
        public int pass;
        public int end;
        public Node[] Next;

        public Node() {
            pass = 0;
            end = 0;
            Next = new Node[26];
        }
    }
    public class Trie{
        private Node root;
        public Trie(){
            root = new Node();
        }
        /*
            Insert方法：
            首先判断word是否为空不为空
            把根节点赋值给临时遍历node
            再把word字符串转后成字符数组遍历
            因此会产生第一个node，因此node.pass++
            for循环进行处理如果该路线为null则创建新的node
        */
        public void InSert(String word){
            if (word == null){return;}
            Node node1 = root;
            char[] str = word.toCharArray();
            int path = 0;
            node1.pass++;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node1.Next[path] == null){
                    node1.Next[path] = new Node();
                }
                node1 = node1.Next[path];
                node1.pass++;
            }
            node1.end++;
        }
        //Search（word单词之前加过几次）word单词之前加过几次
        public int Search(String word){
            if (word == null){return 0;}
            Node node1 = root;
            char[] str = word.toCharArray();
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (node1.Next[index] == null){
                    return 0;
                }
                node1 = node1.Next[index];
            }
            return node1.end;
        }
        //prefixNumber 所有加入的字符串中有多少以pre为前戳的
        public int prefixNumber(String pre){
            if (pre == null){return 0;}
            Node node1 = root;
            char[] str = pre.toCharArray();
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (node1.Next[i] == null){
                    return 0;
                }
                node1 = node1.Next[index];
            }
            return node1.pass;
        }
        public void delete(String word){
            if(Search(word)!=0){
                char[] Str = word.toCharArray();
                Node node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < Str.length; i++) {
                    path = Str[i] - 'a';
                    if(--node.Next[path].pass == 0){
                        node.Next[path] = null;
                        return;
                    }
                    node = node.Next[path];
                }
                node.end--;
            }
        }
    }

}
