package problem4;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Product {
    private String name;
    private BigDecimal bidAmount;
    private ArrayList<Bidder> subscribers = new ArrayList<>();

    public Product(String name, BigDecimal bidAmount) {
        this.name = name;
        this.bidAmount = bidAmount;
    }

    public void receiveBid(BigDecimal bidAmount, String bidder) {
        if (bidAmount.compareTo(this.bidAmount) > 0) {
            this.bidAmount = bidAmount;
            System.out.println("-------------New bid placed-------------");
            String notification = "";
            for (int i = 0; i < subscribers.size(); i++) {
                Bidder cur = subscribers.get(i);
                notification += "Hello " + cur.toString() + "! New bid amount of " + bidAmount + " has been place on " + name + " by ";
                if (cur.toString().equals(bidder)) {
                    notification += "you\n";
                } else {
                    notification += bidder + "\n";
                }
            }
            System.out.print(notification);
        }
    }

    public void addSubscriber(Bidder bidder) {
        subscribers.add(bidder);
    }
}
