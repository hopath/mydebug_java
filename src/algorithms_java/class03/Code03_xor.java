package algorithms_java.class03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Code03_xor {

//	public static int[] getRandomArray(int k, int m, int num, int value, int r){
//		int arr[] = new int[k + m * num];
//
//		for(int i = 0; i < num; i++){
//			r += 13;
//			for(int j = 0; j < m; j++){
//				arr[i * m + j] = r;
//			}
//		}
//
//		int p = m* num;
//
//		for(int i = 0; i < k; i++){
//			arr[p] = value;
//			p++;
//		}
//
//		return arr;
//	}

    public static int[] getRandomArray(int range, int Maxkind, int k, int m){
        int knums = getRandomNum(range);
        //生成数的种类
        int nkind = (int)(Math.random() * Maxkind) + 2;
        //生成随机k的个数，k < m, k > 0
        k = (Math.random() < 0.5) ? k : (int)(Math.random() * (m - 1)) + 1;

        int[] arr = new int[k + (nkind - 1) * m];

        int i = 0;
        for(; i < k; i++){
            arr[i] = knums;
        }

        //种类减一
        nkind--;

        HashSet<Integer> hs = new HashSet<>();
        //确保mnums != knums
        hs.add(knums);
        for(; nkind > 0; nkind--){
            int mnums = 0;
            do{
                mnums = getRandomNum(range);
                //确保mnums之间互不相同
            }while(hs.contains(mnums));
            hs.add(mnums);
            for(int j = 0; j < m; j++){
                arr[i++] = mnums;
            }
        }

        //打乱数据排列
        for(int p = 0; p < arr.length; p++){
            int j = (int)Math.random() * arr.length;
            int tmp = arr[p];
            arr[p] = arr[j];
            arr[j] = tmp;
        }

        return arr;

    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int getRandomNum(int range){
        return (((int)(Math.random() * range) + 1) - ((int)(Math.random() * range) + 1));
    }
    public static void main(String args[]){
//		int arr[] = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 5, 1};
//
//		int res = f1(arr);
//
//		System.out.println(res);

//		System.out.println(f2(6));
//		int arr[] = {1, 2, 3, 3, 4, 4};
//		f3(arr);
//		int arr[] = { 3, 3, 4, 4 ,4, 3, 2, 2, 2, 5, 5, 5, 6, 6, 6, -2, -2};
//		f4(arr, 3);

//		boolean succeed = true;
//		for(int i = 0; i < 100000; i++){
//			int b[] = getRandomArray(i + 1, i + 2, i, 5, i);
//			int res01 = f4(b, i + 2);
//			int res02 = f5(b, i + 2, i + 1);
//			System.out.println(res01 + " " + res02);

//			if(res01 != res02){
//				succeed = false;
//				break;
//			}

//		}
//
//		if(succeed){
//			System.out.println("nice");
//		}else{
//			System.out.println("no");
//		}

        int times = 1000;
        boolean succeed = true;

        for(int i = 0; i < times; i++){
            int Maxkinds  = 1000;
            int range = 1000;
            int a = (int)(Math.random() * 10) + 1;
            int b = (int)(Math.random() * 10) + 1;

            int k = Math.min(a, b);
            int m = Math.max(a, b);

            if(k == m){
                m++;
            }
            int[] arr = getRandomArray(range, Maxkinds, k, m);
            int[] t1 = copyArray(arr);
            int[] t2 = copyArray(arr);
            int r1 = f4(t1, m, k);
            int r2 = f5(t2, m, k);
            for(int j = 0; j < arr.length; j++){
                System.out.print(arr[j] + " ");
            }
            System.out.println();
            System.out.println(r1);
            System.out.println(r2);
            if(r1 != r2){
                succeed = false;
                break;
            }
        }
        if(succeed == true){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }

    //get one difference in arr
    public static int f1(int[] arr){
        int tar = 0;

        for(int i = 0; i < arr.length; i++){
            tar = tar ^ arr[i];
        }

        return tar;
    }

    //get rightside one
    public static int f2(int v){
        return v & (~v + 1);
    }

    //print two difference in arr
    public static void f3(int[] arr){
        //get all xor
        int n = 0;
        for(int i = 0; i < arr.length; i++){
            n ^= arr[i];
        }

        //get right one
        int rightone = n & (-n);

        //get rightone != 0 xor
        int onexor = 0;
        for(int i = 0; i < arr.length; i++){
            if((rightone & arr[i]) != 0){
                onexor ^= arr[i];
            }
        }
        System.out.println(onexor + "\t" + (onexor ^ n));
    }

    //get difference in times, k < m and m > 1
    public static int f4(int[] arr, int m, int k){
        int t[] = new int[32];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < 32; j++){
                t[j] += (arr[i] >> j) & 1;
            }
        }

        int num = 0;
        int i = 0;

        for(; i < 32; i++){
            if(t[i] % m == 0){
                continue;
            }

            if(t[i] % m == k){
                num |= (1 << i);
            }else{
                return -1;
            }
        }

        int cnt = 0;
        if(num == 0){
            for(int p = 0; p < arr.length; p++){
                if(arr[p] == 0){
                    cnt++;
                }
            }
            if(cnt != k){
                return -1;
            }
        }
        return num;
    }

    public static int f5(int[] arr, int m, int k){
        HashMap<Integer, Integer>map = new HashMap<>();
        for(int num : arr){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else{
                map.put(num, 1);
            }
        }

        for(int num : map.keySet()){
            if(map.get(num) == k){
                return num;
            }
        }

        return -1;
    }
}

//

