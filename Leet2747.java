import java.util.*;

public class Leet2747 {
    public static void main(String[] args) {
        Leet2747 leet = new Leet2747();
        // int n = 3;
        // int x = 5;
        // int[][] logs = { { 1, 3 }, { 2, 6 }, { 1, 5 } };
        // int[] queries = { 10, 11 };
        int n = 3;
        int x = 8;
        int[][] logs = { { 1, 35 }, { 1, 32 }, { 1, 11 }, { 1, 39 }, { 2, 29 } };
        int[] queries = { 38, 30, 23, 33, 15, 31, 34, 22, 11, 14 };
        int[] outputArray = leet.countServers(n, logs, x, queries);
        System.out.print("[");
        for (int index : outputArray) {
            System.out.print(index + ",");
        }
        System.out.print("]");
        System.out.println();
    }

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int logsCount = logs.length;
        int queriesCount = queries.length;
        int[] outputArray = new int[queriesCount];
        int[][] qIndexArray = new int[queriesCount][2];

        for (int i = 0; i < queries.length; i++) {
            qIndexArray[i][0] = queries[i];
            qIndexArray[i][1] = i;
        }

        Arrays.sort(qIndexArray, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        Arrays.sort(logs, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        int startIndex = 0;
        int endIndex = 0;
        for (int index = 0; index < queriesCount; index++) {
            int queriesIndex = qIndexArray[index][1];
            int windowStart = qIndexArray[index][0] - x;
            int windowEnd = qIndexArray[index][0];

            while (endIndex < logsCount && logs[endIndex][1] <= windowEnd) {
                int currentServer = logs[endIndex][0];
                countMap.put(currentServer, countMap.getOrDefault(currentServer, 0) + 1);
                endIndex++;
            }

            while (startIndex < logsCount && logs[startIndex][1] < windowStart) {
                int currentServer = logs[startIndex][0];
                countMap.put(currentServer, countMap.getOrDefault(currentServer, 0) - 1);
                if (countMap.get(currentServer) == 0) {
                    countMap.remove(currentServer);
                }
                startIndex++;
            }
            outputArray[queriesIndex] = n - countMap.size();
        }
        return outputArray;
    }
}