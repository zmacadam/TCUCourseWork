package problem5;

import java.util.Arrays;
import java.util.List;

public class TestShoppingCart {
    public static void main(String[] args) {
        List<CartItem> list = Arrays.asList(
                new CartItem("iPhone 10", 1, 40),
                new CartItem("Vizo TV", 2, 500),
                new CartItem("Tesla", 1, 50)
        );
        ShoppingCart shoppingCart = new ShoppingCart(list);
        // write your code here for strategy 1
        shoppingCart.setStrategy(1);

        // end of your code
        shoppingCart.checkout();

        // write your code here for strategy 2
        shoppingCart.setStrategy(2);

        // end of your code
        shoppingCart.checkout();

        // write your code here for strategy 3
        shoppingCart.setStrategy(3);

        // end of your code
        shoppingCart.checkout();
    }
}
