package algorithms_java.class08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_HeapGreater<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> hashMap;
    private int heapSize = 0;
    private Comparator<? super T> cmp;

    public Code02_HeapGreater(ArrayList<T> heap, HashMap<T, Integer> hashMap, Comparator<? super T> cmp) {
        this.heap = heap;
        this.hashMap = hashMap;
        this.cmp = cmp;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean contains(T obj) {
        return hashMap.containsKey(obj);
    }

    public int size(){
        return heapSize;
    }
    public T peek() {
        return heap.get(0);
    }



    public void push(T obj) {
        heap.add(obj);
        hashMap.put(obj, heapSize);
        heapInsert(heapSize++);
    } 


    public T pop(){
        T t = heap.get(0);
        swap(0, heapSize - 1);
        hashMap.remove(t);
        heap.remove(--heapSize);
        heapify(0);
        return t;
    }

    //对改变值的Node重新调整位置
    public void resign(T obj){
        int i = hashMap.get(obj);
        heapInsert(i);
        heapify(i);
    }


    //删除指定Node
    public void remove(T obj){
        int i = hashMap.get(obj);
        swap(i, heapSize - 1);
        T t = heap.get(i);
        heap.remove(--heapSize);
        hashMap.remove(obj);
        //若删除的是最后一个对象则不用再resign
        if(obj != t){
            resign(t);
        }
    }

    //向下调整
    private void heapify(int i){
        int left = 2 * i + 1;
        while(left < heapSize - 1){
            int max = left + 1 < heapSize - 1 && cmp.compare(heap.get(left + 1) ,heap.get(left)) < 0 ? left + 1 : left;
            int largeInx = cmp.compare(heap.get(max), heap.get(i)) < 0 ? max : i;
            if(largeInx == i){
                return;
            }
            swap(i, largeInx);
            i = largeInx;
            left = 2 * i + 1;
        }
    }

    //向上调整
    private void heapInsert(int i) {
        while (cmp.compare(heap.get(i), heap.get((i - 1) / 2)) < 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public void swap(int i, int j) {
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        heap.set(i, t2);
        heap.set(j, t1);
        hashMap.put(t1, j);
        hashMap.put(t2, i);
    }

    public ArrayList<T> getHeap() {
        return heap;
    }

    public void setHeap(ArrayList<T> heap) {
        this.heap = heap;
    }

    public HashMap<T, Integer> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<T, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    public Comparator<? super T> getCmp() {
        return cmp;
    }

    public void setCmp(Comparator<? super T> cmp) {
        this.cmp = cmp;
    }
}
