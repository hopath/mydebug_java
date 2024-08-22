package algorithms_java.class14;


import java.util.*;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_TopologySortOrderDFS1 {


    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    static class Record {
        int value;
        DirectedGraphNode node;

        public Record(DirectedGraphNode node, int value) {
            this.node = node;
            this.value = value;
        }
    }

    private class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o2.value - o1.value;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> recordHashMap = new HashMap<>();

        for (DirectedGraphNode node : graph) {
            f(node, recordHashMap);
        }

        ArrayList<Record> records = new ArrayList<>();
        for (Record r : recordHashMap.values()) {
            records.add(r);
        }

        ArrayList<DirectedGraphNode> res = new ArrayList<>();

        records.sort(new MyComparator());

        for (Record cur : records) {
            res.add(cur.node);
        }

        return res;
    }

    public static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> recordHashMap) {
        if (recordHashMap.containsKey(node)) {
            return recordHashMap.get(node);
        }

        int num = 0;

        for (DirectedGraphNode next : node.neighbors) {
            num += f(next, recordHashMap).value;
        }

        Record ans = new Record(node, num + 1);
        recordHashMap.put(node, ans);

        return ans;
    }


    public static HashMap<DirectedGraphNode, Integer> record(DirectedGraphNode node, HashMap<DirectedGraphNode, Integer> res) {
        if (node.neighbors == null) {
            res.put(node, 1);
            return res;
        }

        int num = 0;
        for (DirectedGraphNode next : node.neighbors) {
            HashMap<DirectedGraphNode, Integer> nextMap = record(next, res);
            num = nextMap.get(next) + 1;
        }

        res.put(node, num);

        return res;
    }
}
