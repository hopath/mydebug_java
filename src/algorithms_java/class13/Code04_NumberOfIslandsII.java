package algorithms_java.class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_NumberOfIslandsII {
    //生成遍历
    public static List<Integer> numIslands20(int m, int n, int[][] positions) {
        char[][] board = new char[m][n];
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < positions.length; i++) {
            board[positions[i][1]][positions[i][2]] = '1';
            int numberOfIslands02 = Code03_NumberOfIslands.getNumberOfIslands02(board);
            res.add(numberOfIslands02);
        }

        return res;
    }

    //动态生成
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        ArrayList<Integer> res = new ArrayList<>();
        UnionFind01 unionFind = new UnionFind01(m, n);
        for (int[] p : positions) {
            res.add(unionFind.contact(p[1], p[2]));
        }
        return res;
    }

    static class UnionFind01 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sites;
        private int row;
        private int col;

        public UnionFind01(int m, int n) {
            row = m;
            col = n;
            int N = row * col;
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sites = 0;
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int k = 0;
            while (parent[i] != i) {
                help[k++] = i;
                i = parent[i];
            }

            for (k--; k >= 0; k--) {
                parent[help[k]] = i;
            }

            return i;
        }

        private void union(int r, int c, int x, int y) {
            if (x < 0 || x == row || y < 0 || y == col || r < 0 || r == row || c < 0 || c == col) {
                return;
            }
            int i1 = index(r, c);
            int i2 = index(x, y);

            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }

            int f1 = find(i1);
            int f2 = find(i2);

            if (f1 != f2) {
                int large = f1 > f2 ? f1 : f2;
                int small = large == f1 ? f2 : f1;
                parent[small] = large;
                size[large] = size[f1] + size[f2];
                sites--;
            }
        }

        private int contact(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sites++;
                union(r, c, r - 1, c);
                union(r, c, r + 1, c);
                union(r, c, r, c - 1);
                union(r, c, r, c + 1);
            }
            return sites;
        }
    }


    static class UnionFind02 {
        private HashMap<String, String> parentMap;
        private HashMap<String, Integer> sizeMap;
        private String[] help;
        private int sites;

        public UnionFind02() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            sites = 0;
        }


        private String find(String s) {
            int k = 0;
            while (parentMap.get(s) != s) {
                help[k++] = s;
                s = parentMap.get(s);
            }

            for (k--; k >= 0; k--) {
                parentMap.put(help[k], s);
            }

            return s;
        }

        private void union(int r, int c, int x, int y) {
            String s1 = indexString(r, c);
            String s2 = indexString(x, y);

            String f1 = find(s1);
            String f2 = find(s2);
            if (!parentMap.containsKey(f1) || !parentMap.containsKey(f2)) {
                return;
            }

            if (f1 != f2) {
                Integer size1 = sizeMap.get(f1);
                Integer size2 = sizeMap.get(f2);
                String large = size1 > size2 ? f1 : f2;
                String small = large == f1 ? f2 : f1;
                parentMap.put(small, large);
                sizeMap.put(large, size1 + size2);
                sites--;
            }
        }

        private String indexString(int r, int c) {
            return r + "_" + c;
        }

        private int contact(int r, int c) {
            String index = indexString(r, c);
            if (sizeMap.get(index) == 0) {
                parentMap.put(index, index);
                sizeMap.put(index, sizeMap.get(index) + 1);
                sites++;
                union(r, c, r - 1, c);
                union(r, c, r + 1, c);
                union(r, c, r, c - 1);
                union(r, c, r, c + 1);
            }
            return sites;
        }
    }
}
