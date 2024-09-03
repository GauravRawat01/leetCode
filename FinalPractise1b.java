import java.util.*;

public class FinalPractise1b {

    public static void main(String[] args) {
        FinalPractise1b leet = new FinalPractise1b();
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
        List<Integer> entryQ = new ArrayList<Integer>();
        List<Integer> exitQ = new ArrayList<Integer>();
        for (int i = 0; i < arrival.length; i++) {
            if (state[i] == 0) {
                entryQ.add(i);
            } else {
                exitQ.add(i);
            }
        }
        int entryIndex = 0;
        int exitIndex = 0;
        int time = 0;
        int direction = -1;
        while (entryIndex < entryQ.size() || exitIndex < exitQ.size()) {
            if (entryIndex < entryQ.size() && exitIndex < exitQ.size() && arrival[entryQ.get(entryIndex)] <= time
                    && arrival[exitQ.get(exitIndex)] <= time) {
                if (direction == 0) {
                    outputArray[entryQ.get(entryIndex)] = time;
                    entryIndex++;
                    direction = 0;
                } else {
                    outputArray[exitQ.get(exitIndex)] = time;
                    exitIndex++;
                    direction = 1;
                }
            } else if (entryIndex < entryQ.size() && entryIndex <= time) {
                outputArray[entryQ.get(entryIndex)] = time;
                entryIndex++;
                direction = 0;
            } else if (exitIndex < exitQ.size() && exitIndex <= time) {
                outputArray[exitQ.get(exitIndex)] = time;
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
