package algorithms_java.class07;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_Heap {

    public static void heapSort(int[] arr){
        //建堆
        for(int i = 0; i < arr.length; i++){
            Code02_Heap.heepInsert(arr, i);
        }
        /*
            第一个元素和最后一个交换，然后将堆收缩一个在进行(此时的最大值已经在最右端了)
            heapify将剩下改变后的堆调好后(就是类似于pop的过程)再交换，
         */
        for(int i = 0; i < arr.length - 1; i++){
            int last = arr.length - 1 - i;
            swap(arr, 0, last);
            Code02_Heap.heapify(arr, 0, last);
        }
    }
    /*
        对于新插入的节点，先将其放在堆的末尾
        然后向上去和其的父节点比较，若大于父节点则
        交换，交换后再与其父节点比较直到比父节点小
        或者交换到根节点后跳出循环。
     */
    public static void heepInsert(int[] heep, int size){
        while(heep[size] > heep[(size - 1) / 2]){
            swap(heep, size, (size - 1) / 2);
            size = (size - 1) / 2;
        }
//        while((pos - 1) / 2 >= 0){
//            int p = (pos - 1) / 2;
//            if(heep[pos] > heep[p]){
//                swap(heep, pos, p);
//                pos = p;
//            }else {
//                break;
//            }
//        }
    }

    private static void heapify(int[] heep, int index, int size){
        int left = 2 * index + 1;
        while(left < size){
            //找最大的那个孩子，若是右孩子存在且大于左孩子则是右，否则是左
            int largest = left + 1 < size && heep[left + 1] > heep[left] ? left + 1 : left;
            //判断父节点与最大孩子节点的大小
            largest = heep[largest] > heep[index] ? largest : index;
            if(largest == index){
                break;
            }
            swap(heep, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }


    public static int heapTop(int[] heep, int size){
        int res = heep[0];
        swap(heep, 0, size- 1);
        size--;

        int index = 0;
        /*
            此处有两个边界，即左右子树都没或右子树有而左子树没有
            用左子树去卡位置，若左子树越界则右子树一定越界。则保证
            了有一个孩子和二个孩子的进入循环，没有孩子的不进入循环
            本质是确保我们指定的操作进行操作
         */
        while(2 * index + 1 < size){
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            /*
                注意和左右孩子的最大的那个交换，以形成稳定的大根堆结构
                在没有右孩子的情况下注意最大的就是左孩子
             */
            int mp = max(heep, left, right);
            if(heep[index] < heep[mp]){
                swap(heep, index, mp);
                index = mp;
            }else{
                //若是比自己的孩子都大，则一定比后面的大
                break;
            }
        }
        return res;
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    private static int max(int[] arr, int i, int j){
        //在没有右孩子的情况下注意最大的就是左孩子
        if(i == arr.length - 1){
            return i;
        }
        return arr[i] > arr[j] ? i : j;
    }
}
