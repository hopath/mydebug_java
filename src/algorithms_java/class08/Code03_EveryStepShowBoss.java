package algorithms_java.class08;

/**
 * @author 张志伟
 * @version 1.0
 */


import java.util.*;

/**
 * 1. 为什么用两个堆？
 * 2. 两个堆是如何实现候奖区先来的进，得奖区先进的出的？
 */
public class Code03_EveryStepShowBoss {

    private class Node<T> {
        private T obj;
        private int nums;
        private int rank;

        public Node(T obj, int nums, int rank) {
            this.obj = obj;
            this.nums = nums;
            this.rank = rank;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(obj, node.obj);
        }

    }

    private class MyComparatorUpper implements Comparator<Node<String>> {

        @Override
        public int compare(Node<String> o1, Node<String> o2) {
            if (o1.nums != o2.nums) {
                return o2.nums - o1.nums;
            } else {
                return o2.rank - o1.rank;
            }
        }
    }

    private class MyComparatorLower implements Comparator<Node<String>> {

        @Override
        public int compare(Node<String> o1, Node<String> o2) {
            if (o1.nums != o2.nums) {
                return o2.nums - o1.nums;
            } else {
                return o1.rank - o2.rank;
            }
        }
    }

    public ArrayList<ArrayList<Node<String>>> getWinners(String arr[], boolean op[], int k) {
        ArrayList<Node<String>> nodes = new ArrayList<>();
        ArrayList<ArrayList<Node<String>>> resLists = new ArrayList<>();

        HashMap<Node<String>, Integer> indexMap = new HashMap<>();
        //候奖区
        Code02_HeapGreater<Node<String>> heapLow =
                new Code02_HeapGreater<>(nodes, indexMap, new MyComparatorLower());
        //得奖区
        Code02_HeapGreater<Node<String>> heapUp =
                new Code02_HeapGreater<>(nodes, indexMap, new MyComparatorUpper());

        for (int i = 0; i < arr.length; i++) {
            int num = -1;
            if (op[i]) {
                num = 1;
            }
            Node<String> node = new Node<>(arr[i], num, i);
            if (heapUp.size() < k) {
                if (heapUp.contains(node)) {
                    Integer integer = heapUp.getHashMap().get(nodes);
                    Node<String> stringNode = heapUp.getHeap().get(integer);
                    stringNode.nums += num;
                    if (stringNode.nums == 0) {
                        heapUp.remove(stringNode);
                    } else {
                        heapUp.getHashMap().put(stringNode, integer);
                        heapUp.resign(stringNode);
                    }
                } else {
                    heapUp.push(node);
                }
            } else {
                if (heapLow.contains(node)) {
                    Integer integer = heapLow.getHashMap().get(nodes);
                    Node<String> stringNode = heapLow.getHeap().get(integer);
                    stringNode.nums += num;
                    if (stringNode.nums == 0) {
                        heapLow.remove(stringNode);
                    } else {
                        heapLow.getHashMap().put(stringNode, integer);
                        heapLow.resign(stringNode);
                    }
                } else {
                    heapLow.push(node);
                }
                int index = heapUp.size() - 1 / 2;
                Node<String> peek = heapLow.peek();
                heapUp.push(peek);
                if (2 * index + 2 > heapUp.size()) {
                    if (heapUp.getCmp().compare(peek, heapUp.getHeap().get(heapUp.size() - 2)) < 0 &&
                            heapUp.getCmp().compare(peek, heapUp.getHeap().get(index)) < 0) {
                        peek.rank = i;
                        heapUp.swap(heapUp.size() - 1, heapUp.size() - 2);
                        Node<String> last = heapUp.getHeap().get(heapUp.size() - 1);
                        heapUp.remove(last);
                        Node<String> pop = heapLow.pop();
                        last.rank = i;
                        heapLow.push(last);
                    }else if(heapUp.getCmp().compare(peek, heapUp.getHeap().get(heapUp.size() - 2)) > 0 &&
                            heapUp.getCmp().compare(peek, heapUp.getHeap().get(index)) < 0){

                    }else{
                        Node<String> pop = heapLow.pop();
                        pop.rank = i;
                        heapUp.push(pop);
                        Node<String> last = heapUp.getHeap().get(heapUp.size() - 1);
                        heapUp.remove(last);
                        last.rank = i;
                        heapLow.push(last);
                    }
                }else{
                    if(heapUp.getCmp().compare(peek, heapUp.getHeap().get(index)) > 0){
                        peek.rank = i;
                        heapUp.push(peek);
                        Node<String> last = heapUp.getHeap().get(heapUp.size() - 1);
                        heapUp.remove(last);
                        last.rank = i;
                        heapLow.push(last);
                    }
                }
            }
            ArrayList<Node<String>> resNode = new ArrayList<>();
            if(heapUp.isEmpty() && heapUp.size() > k){
                for(int j = 0; j < k; j++){
                    resNode.add(heapUp.pop());
                }
            }
            if(heapUp.size() == 1){
                resNode.add(heapUp.pop());
            }
            resLists.add(resNode);
        }
        return resLists;
    }
}
