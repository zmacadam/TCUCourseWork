package problem4;

public class SubscriberService {
    public static void sendMessage(String bidderName, Product product) {
        String placedBy;
        if (bidderName == product.getLeader()) {
            placedBy = "you";
        } else {
            placedBy = product.getLeader();
        }
        System.out.println("Hello " + bidderName + "! New bid amount " + product.getBidAmount() + " has been placed on " + product.getDescription() + " by " + placedBy);
    }
}
