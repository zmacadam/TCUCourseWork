package problem5;

import java.util.List;

public class StrategyTwo implements Discounter {
    @Override
    public String applyDiscount(List<CartItem> items) {
        Double cost = 0.0;
        for (CartItem item : items) {
            cost += item.getPrice() * item.getNumber();
        }
        return "$" + (cost - 100);
    }
}
