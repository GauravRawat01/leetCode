import java.util.*;

public class FinalPractise1c {
    public static void main(String[] args) {
        FinalPractise1c flp = new FinalPractise1c();
        String[][] products = { { "10", "d0", "d1" }, { "15", "EMPTY", "EMPTY" }, { "20", "d1", "EMPTY" } };
        String[][] discounts = { { "d0", "1", "23" }, { "d1", "2", "1" } };
        long lowestPrice = flp.calculatePrice(products, discounts);
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

    public long calculatePrice(String[][] p, String[][] d) {
        long lowestPrice = 0;
        Map<String, Map<String, Long>> discountMap = new HashMap<>();
        for (String[] discount : d) {
            discountMap.computeIfAbsent(discount[0], k -> new HashMap<>()).put(discount[1],
                    (long) convertToInt(discount[2]));
        }
        for (String[] product : p) {
            int productPrice = convertToInt(product[0]);
            long minimumPrice = Integer.MAX_VALUE;
            for (int index = 0; index < product.length; index++) {
                String discountId = product[index];
                if (discountId.equals("EMPTY")) {
                    continue;
                }
                for (String discountType : discountMap.getOrDefault(discountId, new HashMap<>()).keySet()) {
                    long discountValue = discountMap.get(discountId).get(discountType);
                    if (discountType.equals("0")) {
                        minimumPrice = Math.min(minimumPrice, discountValue);
                    } else if (discountType.equals("1")) {
                        double discountedPrice = productPrice * ((100 - (double) discountValue) / 100);
                        System.out.println(discountedPrice);
                        minimumPrice = Math.min(minimumPrice, (long) discountedPrice);
                    } else if (discountType.equals("2")) {
                        if (discountValue > productPrice) {
                            discountValue = productPrice;
                        }
                        minimumPrice = Math.min(minimumPrice, productPrice - discountValue);
                    }
                }
            }
            productPrice = Math.min(productPrice, (int) minimumPrice);
            lowestPrice += productPrice;
        }

        return lowestPrice;
    }

}
