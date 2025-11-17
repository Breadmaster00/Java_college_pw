import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        String[] text = {
                "Цена: 1 299,00 рублей (скидка 15% - 1 104,15 рублей)",
                "Цена: 31 000 рублей (скидка 15% - 1890 рублей)",
                "Цена: 13 000 рублей (скидка 30% - 9 200 рублей)",
                "Цена: 6 758 рублей",
                "Цена: 2 399,00 рублей (скидка 42% - 1 391,42 рублей)"
        };

        List<String> nonconsistent_prices = new ArrayList<String>();

        Pattern price_regex = Pattern.compile("(\\d+.)+\\b(?!%)");
        Pattern discount_regex = Pattern.compile("\\d+(?=%)");

        for (String raw : text) {
            Matcher price_matcher = price_regex.matcher(raw);
            Matcher discount_matcher = discount_regex.matcher(raw);

            float original_price = 0, final_price, discount_amount;
            float claimed_discount = 0;
            int computed_discount;
            boolean consistent;

            if (price_matcher.find()) {
                original_price = Float.parseFloat(price_matcher.group().replace(" ", "").replace(",", "."));
            }
            if (price_matcher.find()) {
                final_price = Float.parseFloat(price_matcher.group().replace(" ", "").replace(",", "."));
            } else {
                final_price = original_price;
            }

            if (discount_matcher.find()) {
                claimed_discount = Integer.parseInt(discount_matcher.group());
            }

            discount_amount = original_price - final_price;
            computed_discount = Math.round((discount_amount * 100) / original_price);
            consistent = computed_discount == claimed_discount;

            if (!consistent) {
                raw += " -> computed discount " + computed_discount + " -> consistent = " + consistent;
                nonconsistent_prices.add(raw);
            }

        }

        for (String price : nonconsistent_prices) {
            System.out.println(price);
        }
    }
}