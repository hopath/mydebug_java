package algorithms_java.class14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_TopologySortOrderDFS2 {

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    static class Record {
        long value;
        DirectedGraphNode node;

        public Record(DirectedGraphNode node, long value) {
            this.node = node;
            this.value = value;
        }

    }


    public Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> recordMap) {
        if (recordMap.containsKey(node)) {
            return recordMap.get(node);
        }

        long depth = 0;

        for (DirectedGraphNode next : node.neighbors) {
            depth = Math.max(depth, f(next, recordMap).value);
        }

        Record record = new Record(node, depth + 1);
        recordMap.put(node, record);

        return record;
    }

}
