package algorithms_java.class15;


import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_ReserveStackUsingRecursive {

    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int result = removeLastToTop(stack);
        reverse(stack);
        stack.push(result);
    }

    //返回底部元素，且保存剩下顺序不变
    public int removeLastToTop(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }

        int last = removeLastToTop(stack);
        stack.push(result);
        return last;
    }
}
