package problem3;

public class TestVendingMachine {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(2);
        vendingMachine.insertCoin();
        vendingMachine.pressButton();
        vendingMachine.insertCoin();
        vendingMachine.pressButton();

        vendingMachine.insertCoin();
        vendingMachine.insertCoin();
        vendingMachine.pressButton();

        vendingMachine.insertCoin();
        vendingMachine.insertCoin();
        vendingMachine.pressButton();

        vendingMachine.refill(5);
        vendingMachine.insertCoin();
        vendingMachine.insertCoin();
        vendingMachine.pressButton();

    }
}
