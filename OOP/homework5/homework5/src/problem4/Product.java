package problem4;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Product {
    private BigDecimal bidAmount;
    private String description;
    private String leader;
    private ArrayList<Bidder> watchers = new ArrayList<>();

    public Product(String description, BigDecimal bidAmount) {
        this.description = description;
        this.bidAmount = bidAmount;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public String getDescription() {
        return description;
    }

    public void bid(BigDecimal amount, String name) {
        this.bidAmount = amount;
        this.leader = name;
    }

    public void addWatcher(Bidder bidder) {
        watchers.add(bidder);
    }

    public ArrayList<Bidder> getWatchers() {
        return watchers;
    }

    public String getLeader() {
        return leader;
    }
}
