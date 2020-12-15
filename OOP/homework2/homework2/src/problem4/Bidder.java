package problem4;

import java.math.BigDecimal;

public class Bidder {
    String bidderName;

    public Bidder(String bidderName) {
        this.bidderName = bidderName;
    }

    @Override
    public String toString() {
        return bidderName;
    }

    public void bid(Product product, BigDecimal bidAmount) {
        product.receiveBid(bidAmount, bidderName);
    }
}
