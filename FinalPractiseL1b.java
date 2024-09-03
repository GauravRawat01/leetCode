import java.util.*;

public class FinalPractiseL1b {
    public static void main(String[] args) {
        FinalPractiseL1b leet = new FinalPractiseL1b();
        // int[] arrival = { 0, 1, 1, 2, 4 };
        List<Integer> arrivalList = new ArrayList<>();
        arrivalList.add(0);
        arrivalList.add(1);
        arrivalList.add(1);
        arrivalList.add(2);
        arrivalList.add(4);
        List<Integer> stateList = new ArrayList<>();
        stateList.add(0);
        stateList.add(1);
        stateList.add(0);
        stateList.add(0);
        stateList.add(1);
        // int[] state = { 0, 1, 0, 0, 1 };
        List<Integer> output = leet.timeTaken(arrivalList, stateList);
        System.out.print("[");
        for (int i : output) {
            System.out.print(i + ",");
        }
        System.out.println("]");
        System.out.println();
    }

    public List<Integer> timeTaken(List<Integer> arrival, List<Integer> state) {
        List<Integer> outputList = new ArrayList<>();
        Integer[] outputArray = new Integer[arrival.size()];
        List<Integer> entryQ = new ArrayList<>();
        List<Integer> exitQ = new ArrayList<>();
        for (int i = 0; i < arrival.size(); i++) {
            if (state.get(i) == 0) {
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
            if (entryIndex < entryQ.size() && exitIndex < exitQ.size() && arrival.get(entryQ.get(entryIndex)) <= time
                    && arrival.get(exitQ.get(exitIndex)) <= time) {
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
        // outputList = Arrays.stream(outputArray).boxed().toList();
        // outputList = Arrays.stream(outputArray).boxed().
        outputList = Arrays.asList(outputArray);
        return outputList;
    }

}
