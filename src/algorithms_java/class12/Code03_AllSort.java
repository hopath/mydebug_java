package algorithms_java.class12;

import java.util.ArrayList;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_AllSort {

    public static void main(String[] args) {
        int times = 0;
        Integer[] integers = {1, 2, 3, 4, 5};
        ArrayList<ArrayList<Integer>> arrayLists = allSort(integers);
        for (ArrayList arrayList : arrayLists) {
            for(int i = 0; i < arrayList.size(); i++){
                System.out.print(arrayList.get(i)+ " ");
            }
            times++;
            System.out.println();
        }


        System.out.println(times);

    }
    public static ArrayList<ArrayList<Integer>> allSort(Integer[] arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        if (arr.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        for (int i = 0; i < arr.length; i++) {
            Integer last = arr[arr.length - 1 - i];
            Integer[] befores = removeIndexInteger(arr, arr.length - 1 - i);
            ArrayList<ArrayList<Integer>> before = allSort(befores);

            for (ArrayList arrayList : before) {
                arrayList.add(last);
                ans.add(arrayList);
            }
        }
        return ans;
    }


    public static Integer[] removeIndexInteger(Integer[] arr, int i){
        Integer[]  res = new Integer[arr.length - 1];
        int j = 0;
        for(int k = 0; k < arr.length; k++){
            if(k != i){
                res[j] = arr[k];
                j++;
            }
        }
        return res;
    }

}
