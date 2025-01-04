package DisjointSets;

import org.omg.PortableServer.ForwardRequest;

import java.awt.event.HierarchyBoundsAdapter;

public class numsIsland1 {
    public int numsIsland(char[][] m){
        int row = m.length;//行数
        int col = m[0].length;//列数
        UnionFind uf = new UnionFind(m);
        for (int i = 1; i < row; i++) {
            if (m[i-1][0] == '1'){
                uf.union(i-1,0,i,0);
            }
        }
        for (int j = 1; j < row; j++) {
            if (m[0][j-1] == '1'){
                uf.union(0,j-1,0,j);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (m[i][j] == '1'){
                    if (m[i-1][j] == '1'){
                        uf.union(i-1,j,i,j);

                    }
                    if (m[i][j-1] == '1'){
                        uf.union(i,j-1,i,j);
                    }
                }
            }
        }
        return uf.sets;
    }
    public class UnionFind{
        int[] parent;
        int[] size;
        int row;
        int col;
        int sets;
        int[] help;
        //对数据进行初始化，如果等于‘1’，那么设置好父节点，设置好size大小，设置好有几个集合sets；
        public UnionFind(char[][] m) {
            row = m.length; //行
            col = m[0].length;//列
            int len = row * col;//数组开辟大小
            parent = new int[len];
            size = new int[len];
            sets = 0;//目前集合数量
            help = new int[len];//辅助数组也就是栈。
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (m[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }
        private int index(int r, int c) {
            return r*col+c;
        }
        public int findFather(int i){
            int hi = 0;
            while(i!=parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            //此时i弹出来说明i的父节点就是i本身，找到父亲！
            //然后从辅助数组中拿到值，把值的父节点设置成i
            for (hi--; hi >=0 ; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }
        public void union(int r1,int c1,int r2 ,int c2){
            int i1 = index(r1,c1);
            int i2 = index(r2,c2);
            int f1 = findFather(i1);
            int f2 = findFather(i2);
            if (f1!=f2){
                if (size[f1]>size[f2]){
                    size[f1] += size[f2];
                    parent[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }
    }

}
