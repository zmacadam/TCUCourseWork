package problem3;

public class NoCandyState implements VendingMachineState {

    VendingMachine machine;
    public NoCandyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Sorry, we are out...");
    }

    @Override
    public void pressButton() {
        System.out.println("Sorry, we are out...");
    }
}
