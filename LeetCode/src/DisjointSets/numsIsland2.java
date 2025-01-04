package DisjointSets;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import java.util.ArrayList;
import java.util.List;
/**
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * 传进数组，根据数组，进行岛屿个数增加
 * [0,0][0,1][0,2][1,1]
 *  1 1 1
 *  1 0 0
 *  0 0 0
 *  思路
 *  1.返回是个数组链表 List<Integer> ans = new ArrayList<>();
 *  2.数组显然是二维数组，遍历二维数组，取到每个一维数组的值 [0,0][0,1][0,2][1,1]
 *  3.调用connect方法uf.connect(positions[0],positions[1])
 *  4.connect方法接受传进来的一维数组的数值，来判断地图上此位置是否为0为0说明可以在这里建造岛屿，size变成1；
 *  5.一系列，数据初始化完成后，开始union判断，结合
 *
 */
public class numsIsland2 {
    //图的大小m*n
    public List<Integer> numsIsland2(int m,int n,int[][] position){
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind(m,n);
        for (int[] positions : position) {
             ans.add(uf.connect(positions[0],positions[1]));
        }
        return ans;
    }
    public class UnionFind{
        int[] parent;
        int[] size;
        int sets;
        int row;
        int col;
        int[] help;

        public UnionFind(int m,int n) {
            row = m;
            col = n;
            int len = row*col;
            parent = new int [len];
            size = new int[len];
            help = new int[len];
            sets = 0;
        }
        public int index(int r,int c){
            return r*col+c;
        }
        public int Find(int i){
            int hi = 0;
            while (i!=parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >=0 ; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }
        public int connect(int r,int c){
            int index = index(r,c);
            if (size[index] == 0){
                size[index] = 1;
                parent[index] = index;
                //sets++ 说明很多个集合被创建出来，
                sets++;
                Union(r-1,c,r,c);
                Union(r+1,c,r,c);
                Union(r,c-1,r,c);
                Union(r,c+1,r,c);
            }
            return sets;
        }
        public void Union(int r1,int c1,int r2,int c2){
            if (r1<0||r1==row||r2<0||r2==row||c1<0||c1==col||c2<0||c2==col){
                return;
            }
            int i1 = index(r1,c1);
            int i2 = index(r2,c2);
            if (size[i1] == 0||size[i2] == 0){
                return;
            }
            int f1 = Find(i1);
            int f2 = Find(i2);
            if (f1!=f2){
                if (size[f1]>size[f2]){
                    size[f1] += size[f2];
                    parent[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
            }
            //sets-- 说明集合被融合
            sets--;
        }
    }
}
