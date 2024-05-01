package algorithms_java.class02;

public class Code02_BinarySerch {
	
	public static void main(String args[]){
//		int arr[] = { 3, 4, 4, 7, 8, 9, 13};
//		int value = 6;
//		
//		int leftPos = leftMax(arr, value);
//		int rightPos = rightMax(arr, value);
//		
//		System.out.println(leftPos);
//		System.out.println(rightPos);
		int arr[] = { 7, 4, 5, 4, 2, 9};
		int partPos = getLessIndex(arr);
		System.out.println(partPos);
		
	}
	
	public static int leftMax(int[] arr, int value){
		int left = 0;
		int right = arr.length - 1;
		
		int leftPos = -1;
		
		while(left <= right){
			
			int mid = left + ((right - left) >> 1);
			
			if(arr[mid] >= value){
				leftPos = mid;
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
		
		return leftPos;
	}
	
	
	public static int rightMax(int arr[], int value){
		int left = 0;
		int right = arr.length - 1;
		
		int rightPos = -1;
		
		while(left <= right){
			
			int mid = left + ((right - left) >> 1);
			
			if(arr[mid] <= value){
				rightPos = mid;
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
		
		return rightPos;
	}
	
	//int arr[] = { 7, 4, 5, 4, 2, 9};
	public static int partMin(int[] arr){
		if(arr == null || arr.length < 3){
			return -1;
		}
		
		int left = 0;
		int right = arr.length - 1;
		
		if(arr[0] < arr[1]){
			return 0;
		}else if(arr[right - 1] > arr[right]){
			return right;
		}
		
		int mid = right - 1;
		
		System.out.println(mid);
		
		while(mid - 1 >= 0 && mid + 1 <= arr.length){
			mid = left + ((right - left) >> 1);
			System.out.println(mid);
			if(arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]){
				return mid;
			}else if(arr[mid] > arr[mid - 1]){
				right = mid;
				System.out.println("right = " +  mid);
			}else if(arr[mid] > arr[mid + 1] && arr[mid] < arr[mid - 1]){
				left = mid;
				System.out.println("left = " +  mid);
			}
		}
		
		return -1;
	}
	
	//int arr[] = { 7, 4, 5, 4, 2, 9};
	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			System.out.println(mid);
			if (arr[mid] > arr[mid - 1]) {
				right = mid - 1;
				System.out.println("right = " + right);
			} else if (arr[mid] > arr[mid + 1]) {
				left = mid + 1;
				System.out.println("left" + mid);
			} else {
				return mid;
			}
		}
		return left;
	}

}
