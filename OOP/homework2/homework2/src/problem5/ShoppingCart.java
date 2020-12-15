package problem5;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();
    private int activeStrategy = 0;

    public ShoppingCart(List<CartItem> items) {
        this.items = items;
    }

    public void checkout() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice() * items.get(i).getNumber();
        }
        switch (activeStrategy) {
            case 1:
                System.out.println("$" + total * 0.9);
                return;
            case 2:
                if (total >= 500) {
                    total -= 100;
                    System.out.println("$" + total);
                } else {
                    System.out.println("$" + total);
                }
                return;
            case 3:
                if (total >= 300) {
                    System.out.println("Add a free Amazon Echo dot in shopping card\n" + "$" + total);
                } else {
                    System.out.println("$" + total);
                }
                return;
            default:
                System.out.println("$" + total);
                return;
        }
    }

    public void setStrategy(int strategy) {
        activeStrategy = strategy;
    }
}
