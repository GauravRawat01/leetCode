import java.util.*;

public class Leet2534 {

    public static void main(String[] args) {
        Leet2534 leet = new Leet2534();
        int[] arrival = { 0, 1, 1, 2, 4 };
        int[] state = { 0, 1, 0, 0, 1 };
        int[] output = leet.timeTaken(arrival, state);
        System.out.print("[");
        for (int i : output) {
            System.out.print(i + ",");
        }
        System.out.println("]");
        System.out.println();
    }

    public int[] timeTaken(int[] arrival, int[] state) {
        int[] outputArray = new int[arrival.length];
        List<Integer> enter = new ArrayList<Integer>();
        List<Integer> exit = new ArrayList<Integer>();

        for (int i = 0; i < arrival.length; i++) {
            if (state[i] == 0) {
                enter.add(i);
            } else {
                exit.add(i);
            }
        }

        for (int i : enter) {
            System.out.print(i + ",");
        }
        System.out.println();
        for (int i : exit) {
            System.out.print(i + ",");
        }
        System.out.println();

        int enterIndex = 0;
        int exitIndex = 0;
        int direction = -1;
        int time = 0;

        while (enterIndex < enter.size() || exitIndex < exit.size()) {
            if (enterIndex < enter.size() && exitIndex < exit.size() && arrival[enter.get(enterIndex)] <= time
                    && arrival[exit.get(exitIndex)] <= time) {
                if (direction == 0) {
                    outputArray[enter.get(enterIndex)] = time;
                    enterIndex++;
                    direction = 0;
                } else {
                    outputArray[exit.get(exitIndex)] = time;
                    exitIndex++;
                    direction = 1;
                }
            } else if (enterIndex < enter.size() && enterIndex <= time) {
                outputArray[enter.get(enterIndex)] = time;
                enterIndex++;
                direction = 0;
            } else if (exitIndex < exit.size() && exitIndex <= time) {
                outputArray[exit.get(exitIndex)] = time;
                exitIndex++;
                direction = 1;
            } else {
                direction = -1;
            }
            time++;
        }

        return outputArray;
    }

}
