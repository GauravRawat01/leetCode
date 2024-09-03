import java.util.*;

public class FinalPractise1a {

    public static void main(String[] args) {
        FinalPractise1a leet = new FinalPractise1a();
        int n = 3;
        int x = 5;
        int[][] logs = { { 1, 3 }, { 2, 6 }, { 1, 5 } };
        int[] queries = { 10, 11 };
        int[] outputArray = leet.countServers(n, logs, x, queries);
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

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int[] outputArray = new int[queries.length];
        SortedQuery[] sq = new SortedQuery[queries.length];
        for (int i = 0; i < queries.length; i++) {
            sq[i] = new SortedQuery(i, queries[i]);
        }
        Arrays.sort(sq, (a, b) -> a.val - b.val);
        Arrays.sort(logs, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int startIndex = 0;
        int endIndex = 0;
        int count = 0;
        int[] countMap = new int[n + 1];
        for (int index = 0; index < queries.length; index++) {
            while (endIndex < logs.length && logs[endIndex][1] <= sq[index].val) {
                countMap[logs[endIndex][0]]++;
                if (countMap[logs[endIndex][0]] == 1)
                    count++;
                endIndex++;
            }
            while (startIndex < logs.length && logs[startIndex][1] < sq[index].val - x) {
                countMap[logs[startIndex][0]]--;
                if (countMap[logs[startIndex][0]] == 0)
                    count--;
                startIndex++;
            }
            outputArray[sq[index].idx] = n - count;
        }

        return outputArray;
    }

}
