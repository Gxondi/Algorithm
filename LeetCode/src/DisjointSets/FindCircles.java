package DisjointSets;

import LinkList.PalindromeList;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;

public class FindCircles {
    public int FindCirlesNumers(int[][] nums){
        int n = nums.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (nums[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.sets;
    }
    public class UnionFind{
        public int[] parent;
        public int[] sizes;
        public int[] help;
        public int sets;

        public UnionFind(int n) {
            parent = new int[n];
            sizes = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
        }
        public int find(int i){
            int hi = 0;
            while (i!=parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--;  hi>=0 ; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }
        public void union(int i, int j){
            int f1 = find(i);
            int f2 = find(j);
            if (f1!=f2){
                if (sizes[f1]>sizes[f2]){
                    sizes[f1]+=sizes[f2];
                    parent[f2] = f1;
                }else {
                    sizes[f2]+=sizes[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }
    }
}
