package problem4;

public class Bidder {
    String bidderName;

    public Bidder(String bidderName) {
        this.bidderName = bidderName;
    }

    @Override
    public String toString() {
        return bidderName;
    }
}
