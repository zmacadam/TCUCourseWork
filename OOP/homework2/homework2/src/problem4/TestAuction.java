package problem4;

import java.math.BigDecimal;

public class TestAuction {
    public static void main(String[] args) {
        Product iPhone = new Product("iPhone 12",new BigDecimal(999));
        Bidder sanchez = new Bidder("Sanchez");
        Bidder wei = new Bidder("Wei");
        Bidder scherger = new Bidder("Scherger");

        iPhone.addSubscriber(sanchez);
        iPhone.addSubscriber(wei);
        iPhone.addSubscriber(scherger);

        sanchez.bid(iPhone, new BigDecimal(1200));
        scherger.bid(iPhone, new BigDecimal(1250));
    }
}
