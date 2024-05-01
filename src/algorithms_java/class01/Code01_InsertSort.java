package algorithms_java.class01;

import java.util.Arrays;

public class Code01_InsertSort {
	
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100; // 随机数组的长度0～100
		int maxValue = 100;// 值：-100～100
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			insertSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				// 打印arr1
				// 打印arr2
				succeed = false;
				for (int j = 0; j < arr.length; j++) {
					System.out.print(arr[j] + " ");
				}
				System.out.println();
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		insertSort(arr);
		printArray(arr);
	}
	
	public static void insertSort(int[] arr ){
		if(arr == null || arr.length < 2){
			return;
		}
		
		for(int i = 0; i < arr.length - 1; i++){
			for(int j = i + 1; j < arr.length; j++){
				if(arr[i] > arr[j]){
					swap(arr, i, j);
				}
			}
		}
	}
	
	public static void swap(int arr[], int i, int j){
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
	
	public static void printArray(int arr[]){
		for(int i = 0; i < arr.length; i++){
			System.out.printf("%d ", arr[i]);
		}
	}
	
	public static void comparator(int[] arr){
		Arrays.sort(arr);
	}
	
	public static int[] generateRandomArray(int maxSize, int maxValue){
		int[] arr = new int[(int)((maxSize + 1) * Math.random())];
		for(int i = 0; i < arr.length; i++){
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
	
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	
}
