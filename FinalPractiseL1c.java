import java.util.*;

public class FinalPractiseL1c {
    public static void main(String[] args) {
        FinalPractiseL1c flp = new FinalPractiseL1c();
        // String[][] products = { { "10", "d0", "d1" }, { "15", "EMPTY", "EMPTY" }, {
        // "20", "d1", "EMPTY" } };
        // String[][] discounts = { { "d0", "1", "27" }, { "d1", "2", "5" } };
        List<String> p1 = new ArrayList<String>();
        List<String> p2 = new ArrayList<String>();
        List<String> p3 = new ArrayList<String>();
        List<String> d1 = new ArrayList<String>();
        List<String> d2 = new ArrayList<String>();
        p1.add("10");
        p1.add("d0");
        p1.add("d1");
        p2.add("15");
        p2.add("EMPTY");
        p2.add("EMPTY");
        p3.add("20");
        p3.add("d1");
        p3.add("EMPTY");
        d1.add("d0");
        d1.add("1");
        d1.add("27");
        d2.add("d1");
        d2.add("2");
        d2.add("5");
        List<List<String>> productsList = new ArrayList<>();
        List<List<String>> discountsList = new ArrayList<>();
        productsList.add(p1);
        productsList.add(p2);
        productsList.add(p3);
        discountsList.add(d1);
        discountsList.add(d2);

        long lowestPrice = flp.calculatePrice(productsList, discountsList);
        System.out.println("Lowest Price: " + lowestPrice);
    }

    public int convertToInt(String numText) {
        int num = 0;
        int count = 0;
        for (int i = numText.length() - 1; i >= 0; i--) {
            num += (numText.charAt(i) - '0') * Math.pow(10, count);
            count++;
        }
        return num;
    }

    public long calculatePrice(List<List<String>> p, List<List<String>> d) {
        long lowestPrice = 0;
        Map<String, HashMap<String, Long>> discountMap = new HashMap<>();
        for (List<String> discount : d) {
            discountMap.computeIfAbsent(discount.get(0), k -> new HashMap<>()).put(discount.get(1),
                    (long) convertToInt(discount.get(2)));
        }
        for (List<String> product : p) {
            int productPrice = convertToInt(product.get(0));
            long minPrice = Integer.MAX_VALUE;
            for (int j = 1; j < product.size(); j++) {
                String discountId = product.get(j);
                if (discountId.equals("EMPTY")) {
                    continue;
                }
                for (String discountType : discountMap.getOrDefault(discountId, new HashMap<>()).keySet()) {
                    long discountValue = discountMap.get(discountId).get(discountType);
                    if (discountType.equals("0")) {
                        minPrice = Math.min(minPrice, discountValue);
                    } else if (discountType.equals("1")) {
                        double discountedPrice = productPrice * ((100 - (double) discountValue) / 100);
                        minPrice = Math.min(minPrice, (long) discountedPrice);
                    } else if (discountType.equals("2")) {
                        if (discountValue > productPrice) {
                            discountValue = productPrice;
                        }
                        minPrice = Math.min(minPrice, productPrice - discountValue);
                    }
                }
            }
            productPrice = Math.min(productPrice, (int) minPrice);
            lowestPrice += productPrice;
        }
        return lowestPrice;
    }

}
