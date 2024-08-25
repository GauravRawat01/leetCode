import java.util.*;

public class FindLowestPriceV1 {
    public static void main(String[] args) {
        FindLowestPriceV1 flp = new FindLowestPriceV1();
        String[][] products = { { "10", "d0", "d1" }, { "15", "EMPTY", "EMPTY" }, { "20", "d1", "EMPTY" } };
        String[][] discounts = { { "d0", "1", "27" }, { "d1", "2", "5" } };
        long lowestPrice = flp.calculatePrice(products, discounts);
        System.out.println("Lowest Price: " + lowestPrice);
    }

    public int getN(String s) {
        int ans = 0;
        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            ans += (s.charAt(i) - '0') * Math.pow(10, cnt);
            cnt++;
        }
        return ans;
    }

    public long calculatePrice(String[][] p, String[][] d) {
        long ans = 0;
        Map<String, HashMap<String, Long>> mp = new HashMap<>();
        for (String[] a : d) {
            mp.computeIfAbsent(a[0], k -> new HashMap<>()).put(a[1], (long) getN(a[2]));
        }
        for (String[] product : p) {
            int price = getN(product[0]);
            System.out.println("Price: " + price);
            long minm = Integer.MAX_VALUE;
            for (int j = 1; j < product.length; j++) {
                String tag = product[j];
                if (tag.equals("EMPTY")) {
                    System.out.println("skipping");
                    continue;
                }
                for (String it : mp.getOrDefault(tag, new HashMap<>()).keySet()) {
                    String type = it;
                    long disc = (long) mp.get(tag).get(it);
                    System.out.println(type + "|" + disc);
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
            // if (minm != Long.MAX_VALUE) {
            // price = Math.min(price, (int) minm);
            // }
            price = Math.min(price, (int) minm);
            ans += price;
        }
        return ans;
    }
}
