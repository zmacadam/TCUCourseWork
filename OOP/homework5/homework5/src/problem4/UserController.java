package problem4;

import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController {

    private EventBus eventBus;

    public UserController() {
        eventBus = new EventBus();
    }

    public void setObservers(Object observer) {
        eventBus.register(observer);
    }

    public void subscribe(Product product, Bidder bidder) {
        product.addWatcher(bidder);
    }

    public void notify(Product product) {
        eventBus.post(product);
    }
}
