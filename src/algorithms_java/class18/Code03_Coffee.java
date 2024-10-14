package algorithms_java.class18;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_Coffee {
    static class Machine {
        int workPoint;
        int workTime;

        public Machine(int workPoint, int workTime) {
            this.workPoint = workPoint;
            this.workTime = workTime;
        }
    }

    static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.workPoint + o1.workTime) - (o2.workPoint + o2.workTime);
        }
    }

    public static int minTime(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> machines = new PriorityQueue<>(new MachineComparator());
        //每个人喝完所需最短时间
        int[] drinks = new int[n];

        for (int i = 0; i < arr.length; i++) {
            machines.add(new Machine(0, arr[i]));
        }

        for (int i = 0; i < n; i++) {
            Machine poll = machines.poll();
            poll.workPoint += poll.workTime;
            drinks[i] = poll.workPoint;
            machines.add(poll);
        }

        return process(drinks, a, b, 0, 0);
    }

    public static int process(int[] drinks, int wash, int air, int index, int washLine){
        return 0;
    }
}
