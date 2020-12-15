package problem3;

public class TwoCoinState implements VendingMachineState {

    VendingMachine machine;
    public TwoCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Two coins already inserted... press Buy button...");
    }

    @Override
    public void pressButton() {
        System.out.println("Buy button pressed... start dispensing");
        machine.dispense();
    }
}
