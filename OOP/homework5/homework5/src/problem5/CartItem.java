package problem5;

public class CartItem {
    private String name;
    private int number;
    private double price;

    public CartItem(String name, int number, double price) {
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }
}
