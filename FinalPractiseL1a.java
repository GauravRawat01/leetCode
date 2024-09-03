import java.util.*;

public class FinalPractiseL1a {
    public static void main(String[] args) {
        FinalPractiseL1a leet = new FinalPractiseL1a();
        int n = 3;
        int x = 5;
        // int[][] logs = { { 1, 3 }, { 2, 6 }, { 1, 5 } };
        // int[] queries = { 10, 11 };
        List<Integer> queriesList = new ArrayList<Integer>();
        queriesList.add(10);
        queriesList.add(11);
        List<Integer> logsA = new ArrayList<>();
        List<Integer> logsB = new ArrayList<>();
        List<Integer> logsC = new ArrayList<>();
        logsA.add(1);
        logsA.add(3);
        logsB.add(2);
        logsB.add(6);
        logsC.add(1);
        logsC.add(5);
        List<List<Integer>> logsList = new ArrayList<>();
        logsList.add(logsA);
        logsList.add(logsB);
        logsList.add(logsC);
        // int n = 3;
        // int x = 8;
        // int[][] logs = { { 1, 35 }, { 1, 32 }, { 1, 11 }, { 1, 39 }, { 2, 29 } };
        // int[] queries = { 38, 30, 23, 33, 15, 31, 34, 22, 11, 14 };
        // int[] outputArray = leet.countServers(n, logs, x, queries);
        List<Integer> outputArray = leet.countServers(n, logsList, x, queriesList);
        System.out.print("[");
        for (int index : outputArray) {
            System.out.print(index + ",");
        }
        System.out.print("]");
        System.out.println();
    }

    public class SortedQuery {
        int idx;
        int val;

        public SortedQuery(int index, int value) {
            idx = index;
            val = value;
        }
    }

    public List<Integer> countServers(int n, List<List<Integer>> logs, int x, List<Integer> queries) {
        List<Integer> outputList = new ArrayList<Integer>();
        Integer[] outputArray = new Integer[queries.size()];
        // int[] outputArray = new int[queries.size()];
        SortedQuery[] sq = new SortedQuery[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            sq[i] = new SortedQuery(i, queries.get(i));
        }
        Arrays.sort(sq, (a, b) -> a.val - b.val);
        Collections.sort(logs, (a, b) -> {
            if (a.get(1) != b.get(1)) {
                return a.get(1) - b.get(1);
            }
            return a.get(0) - b.get(0);
        });
        int startIndex = 0;
        int endIndex = 0;
        int count = 0;
        int[] countMap = new int[n + 1];
        for (int index = 0; index < queries.size(); index++) {
            while (endIndex < logs.size() && logs.get(endIndex).get(1) <= sq[index].val) {
                countMap[logs.get(endIndex).get(0)]++;
                if (countMap[logs.get(endIndex).get(0)] == 1)
                    count++;
                endIndex++;
            }
            while (startIndex < logs.size() && logs.get(startIndex).get(1) < sq[index].val - x) {
                countMap[logs.get(startIndex).get(0)]--;
                if (countMap[logs.get(startIndex).get(0)] == 0)
                    count--;
                startIndex++;
            }
            outputArray[sq[index].idx] = n - count;
        }
        // outputList = Arrays.stream(outputArray).boxed().toList();
        outputList = Arrays.asList(outputArray);
        return outputList;
    }

}
