package problem3;

public class NoCoinState implements VendingMachineState {

    VendingMachine machine;
    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        if (machine.getNumberOfBars() > 0) {
            System.out.println("One coin inserted...");
            machine.setState(machine.getOneCoinState());
        } else {
            System.out.println("Sorry, we are out...");
            machine.setState(machine.getNoCandyState());
        }
    }

    @Override
    public void pressButton() {
        if (machine.getNumberOfBars() > 0) {
            System.out.println("No coin inserted");
        } else {
            System.out.println("Sorry, we are out...");
            machine.setState(machine.getNoCandyState());
        }
    }
}
