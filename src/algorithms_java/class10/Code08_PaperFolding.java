package algorithms_java.class10;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code08_PaperFolding {
    public static void main(String[] args) {
        printFold(4);
    }
    public static void printFold(int N){
        process(1, N, true);
    }

    public static void process(int i , int N, boolean down){
        if(i > N){
            return;
        }
        process(i + 1, N, true);
        System.out.print(down? "凹 " : "凸 ");
        process(i + 1, N, false);
    }
}
