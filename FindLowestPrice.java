import java.util.*;

public class FindLowestPrice {
    public static int getN(String s) {
        int ans = 0;
        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            ans += (s.charAt(i) - '0') * Math.pow(10, cnt);
            cnt++;
        }
        return ans;
    }

    public static long func(List<List<String>> p, List<List<String>> d) {
        long ans = 0;
        Map<String, List<Pair<String, Long>>> mp = new HashMap<>();
        for (List<String> a : d) {
            mp.computeIfAbsent(a.get(0), k -> new ArrayList<>()).add(new Pair<>(a.get(1), (long) getN(a.get(2))));
        }
        for (List<String> product : p) {
            int price = getN(product.get(0));
            long minm = Long.MAX_VALUE;
            for (int j = 1; j < product.size(); j++) {
                String tag = product.get(j);
                if (tag.equals("EMPTY"))
                    continue;
                for (Pair<String, Long> it : mp.getOrDefault(tag, new ArrayList<>())) {
                    String type = it.getKey();
                    long disc = it.getValue();
                    if (type.equals("0")) {
                        minm = Math.min(minm, disc);
                    } else if (type.equals("1")) {
                        long X = price * (100 - disc) / 100;
                        minm = Math.min(minm, X);
                    } else {
                        minm = Math.min(minm, price - disc);
                    }
                }
            }
            price = Math.min(price, (int) minm);
            ans += price;
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<String>> products = Arrays.asList(
                Arrays.asList("10", "d0", "d1"),
                Arrays.asList("15", "EMPTY", "EMPTY"),
                Arrays.asList("20", "d1", "EMPTY"));
        List<List<String>> discounts = Arrays.asList(
                Arrays.asList("d0", "1", "27"),
                Arrays.asList("d1", "2", "5"));
        long ans = func(products, discounts);
        System.out.print(ans + " ");
    }

    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
