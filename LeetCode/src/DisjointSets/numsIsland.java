package DisjointSets;

import java.awt.event.HierarchyBoundsAdapter;

public class numsIsland {
    public int numsIsland(char[][] board){
        int island = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1'){
                    island++;
                    infect(board,i,j);
                }
            }
        }
        return island;
    }

    private void infect(char[][] board, int i, int j) {
        if (i<0||i == board.length||j<0||j==board[0].length||board[i][j]!='1'){
            return;
        }
        board[i][j] = 2;
        infect(board,i-1,j);
        infect(board,i+1,j);
        infect(board,i,j-1);
        infect(board,i,j+1);
    }
    public int numsIsland2(char[][] board){
        UnionFind unionFind = new UnionFind(board.length);
        int island = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1'){
                    island++;
                    unionFind.union(i,j);
                }
            }
        }
        return island;
    }
    public class UnionFind{
        int[] parent;
        int[] sizes;
        int[] help;
        int sets;
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
            for (hi--;hi>=0;hi--){
                parent[help[hi]] = i;
            }
            return i;
        }
        public void union(int i, int j){
            int f1 = find(i);
            int f2 = find(j);
            if (f1!=f2){
                if (sizes[f1] > sizes[f2]){
                    sizes[f1] += sizes[f2];
                    parent[f2] = f1;
                }else {
                    sizes[f2] += sizes[f1];
                    parent[f1] = f2;
                }
            }
            sets--;
        }
    }
}














