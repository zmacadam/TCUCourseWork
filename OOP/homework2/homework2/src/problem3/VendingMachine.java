package problem3;

public class VendingMachine {
    private int numberOfBars = 2;
    private int numberOfCoins = 0;

    public VendingMachine(int numberOfBars) {
        this.numberOfBars = numberOfBars;
    }

    public void insertCoin() {
        if (numberOfBars == 0) {
            System.out.println("Sorry, we are out...");
            return;
        }
        numberOfCoins += 1;
        if (numberOfCoins == 1) {
            System.out.println("One coin inserted...");
        } else if (numberOfCoins == 2) {
            System.out.println("Two coins inserted... please press Buy button...");
        }
    }

    public void pressButton() {
        if (numberOfCoins == 2 && numberOfBars > 0) {
            System.out.println("Buy button pressed... start dispensing");
            numberOfCoins = 0;
            numberOfBars -= 1;
        } else if (numberOfCoins == 1){
            System.out.println("Please insert one more coin...");
        } else {
            System.out.println("Sorry, we are out...");
        }
    }

    public void refill(int i) {
        numberOfBars += i;
    }
}
