package algorithms_java.class05;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_Stack_GetMin {
   private Stack<Integer> stackData;
   private Stack<Integer> stackMin;

   public Code05_Stack_GetMin(){
       stackData = new Stack<>();
       stackMin = new Stack<>();
   }

   @Test
   public void testCode05() throws Exception {
       Code05_Stack_GetMin stack_getMin = new Code05_Stack_GetMin();
       int random;
       for (int i = 0; i < 100; i++) {
           random = (int)(Math.random() * 15) + 1;
           stack_getMin.push(random);
           System.out.println(stack_getMin.getMin() + "$" + stack_getMin.pop() + " ");
       }

   }

   public void push(Integer value){
       //两个栈是同步增减的
       if(stackMin.empty()){
           stackMin.push(value);
       }else {
           if(stackMin.peek() > value){
               stackMin.push(value);
           }else{
               stackMin.push(stackMin.peek());
           }
       }
       stackData.push(value);
   }

   public Integer pop() throws Exception {
       if(stackData.empty()){
           throw new Exception("栈已经空了...");
       }
       stackMin.pop();

       return stackData.pop();
   }

   public Integer getMin() throws Exception {
       if(stackMin.empty()){
           throw new Exception("栈已经空了...");
       }

       return stackMin.peek();
   }
}
