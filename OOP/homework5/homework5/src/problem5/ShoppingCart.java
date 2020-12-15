package problem5;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();
    private Discounter strategy;

    public ShoppingCart(List<CartItem> items) {
        this.items = items;
    }

    public void checkout() {
        String result = strategy.applyDiscount(items);
        System.out.println(result);
    }

    public void setStrategy(Discounter strategy) {
        this.strategy = strategy;
    }
}
