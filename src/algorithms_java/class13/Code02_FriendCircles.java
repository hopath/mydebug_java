package algorithms_java.class13;

import java.util.ArrayList;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_FriendCircles {

    public static int findCircleNum(int arr[][]) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            list.add(i);
        }

        Code01_UnionFind.UnionFind unionFind =
                new Code01_UnionFind.UnionFind<Integer>(list);

        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[i][j] == 1){
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.size();
    }
}
