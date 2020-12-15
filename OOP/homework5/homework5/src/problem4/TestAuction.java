package problem4;

import com.google.common.eventbus.EventBus;

import java.math.BigDecimal;

public class TestAuction {


    public static void main(String[] args) {
        UserController uc = new UserController();
        uc.setObservers(new SubscriberObserver());
        Product iphone = new Product("iPhone 12", new BigDecimal(999));
        Bidder sanchez = new Bidder("Sanchez");
        uc.subscribe(iphone, sanchez);
        Bidder wei = new Bidder("Wei");
        uc.subscribe(iphone, wei);
        Bidder scherger = new Bidder("Scherger");
        uc.subscribe(iphone, scherger);

        iphone.bid(new BigDecimal(1200), sanchez.toString());
        uc.notify(iphone);

        iphone.bid(new BigDecimal(1250), scherger.toString());
        uc.notify(iphone);
    }
}
