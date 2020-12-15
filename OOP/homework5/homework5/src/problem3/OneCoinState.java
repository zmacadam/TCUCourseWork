package problem3;

public class OneCoinState implements VendingMachineState {

    VendingMachine machine;
    public OneCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Two coins inserted... please press Buy button...");
        machine.setState(machine.getTwoCoinState());
    }

    @Override
    public void pressButton() {
        System.out.println("Please insert one more coin...");
    }
}
