package algorithms_java.class05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code07_QueueByStack {
    private Queue<Integer> queue;
    private Queue<Integer> help;

    public Code07_QueueByStack() {
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(int x) {
        queue.offer(x);
    }

    public int pop() throws Exception {
        if(queue.isEmpty()){
            throw new Exception("栈已经空了...");
        }

        while(queue.size() > 1){
            help.offer(queue.poll());
        }
        int ans = queue.poll();
        Queue<Integer> tmp = queue;
        queue = help;
        help = tmp;

        return ans;
    }

    public int top() throws Exception {
        if(queue.isEmpty()){
            throw new Exception("栈已经空了...");
        }
        while(queue.size() > 1){
            help.offer(queue.poll());
        }
        int ans = queue.poll();
        help.offer(ans);

        Queue<Integer> tmp = queue;
        queue = help;
        help = tmp;

        return ans;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
