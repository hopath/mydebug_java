package algorithms_java.class05;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */

/**
 * 1. 看示例代码
 * 2. 用测试用例
 * 3. 找leetcode
 */
public class Code06_StackByQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public Code06_StackByQueue(){
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    @Test
    public void testCode06() throws Exception {
        Code06_StackByQueue code06_stackByQueue = new Code06_StackByQueue();
        int random;
        for (int i = 0; i < 10; i++) {
            random = (int)(Math.random() * 15) + 1;
            System.out.print(random + " ");
            code06_stackByQueue.push(random);
        }
        System.out.println();
        for(int i = 0; i < 10; i++){
            System.out.print(code06_stackByQueue.pop() +  " ");
        }
    }
    private void pushToPop(){
        if(stackPop.empty()){
            while(!(stackPush.empty())){
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void push(Integer value){
        stackPush.push(value);
    }

    public Integer pop() throws Exception {
        if(stackPush.empty() && stackPop.empty()){
            throw new Exception("队列空了...");
        }
        pushToPop();
        return stackPop.pop();
    }
}
