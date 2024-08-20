package algorithms_java.class14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

    public static HashMap<DirectedGraphNode, Integer> record(DirectedGraphNode node, HashMap<DirectedGraphNode, Integer> res) {
        if (node.neighbors == null) {
            res.put(node, 1);
            return res;
        }

        int num = 0;
        for (DirectedGraphNode next : node.neighbors) {
            HashMap<DirectedGraphNode, Integer> nextMap = record(next, res);
            //把已经算出的数量拷贝到上一层Hash表中
            Set<DirectedGraphNode> directedGraphNodes = nextMap.keySet();
            for(DirectedGraphNode cur : directedGraphNodes){
                res.put(cur, nextMap.get(cur));
            }

            num = nextMap.get(next) + 1;
        }



        res.put(node, num);

        return res;
    }
}
