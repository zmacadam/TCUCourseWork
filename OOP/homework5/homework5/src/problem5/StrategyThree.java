package problem5;

import java.util.List;

public class StrategyThree implements Discounter {
    @Override
    public String applyDiscount(List<CartItem> items) {
        Double cost = 0.0;
        for (CartItem item : items) {
            cost += item.getPrice() * item.getNumber();
        }
        return "Add a free Amazon Echo Dot in shopping cart\n$" + cost;
    }
}
