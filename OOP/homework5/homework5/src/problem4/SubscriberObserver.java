package problem4;

import com.google.common.eventbus.Subscribe;

import java.util.HashMap;

public class SubscriberObserver {

    private SubscriberService subscriberService;

    @Subscribe
    public void handleBidSuccess(Product product) {
        System.out.println("-----------------New bid placed----------------");
        for (Bidder bidder : product.getWatchers()) {
            SubscriberService.sendMessage(bidder.toString(), product);
        }
    }
}
