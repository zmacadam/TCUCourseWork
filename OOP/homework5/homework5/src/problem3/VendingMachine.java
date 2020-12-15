package problem3;

public class VendingMachine {
    VendingMachineState noCoinState;
    VendingMachineState noCandyState;
    VendingMachineState oneCoinState;
    VendingMachineState twoCoinState;
    VendingMachineState state;
    private int numberOfBars = 2;

    public VendingMachine(int numberOfBars) {
        this.numberOfBars = numberOfBars;
        noCoinState = new NoCoinState(this);
        noCandyState = new NoCandyState(this);
        oneCoinState = new OneCoinState(this);
        twoCoinState = new TwoCoinState(this);
        state = noCoinState;
    }

    public int getNumberOfBars() {
        return numberOfBars;
    }

    public void refill(int count) {
        this.numberOfBars += count;
        this.state = noCoinState;
    }

    public void dispense() {
        numberOfBars--;
        this.state = noCoinState;
    }

    public void pressButton() {
        state.pressButton();
    }

    public void insertCoin() {
        state.insertCoin();
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public VendingMachineState getNoCoinState() {
        return noCoinState;
    }

    public VendingMachineState getNoCandyState() {
        return noCandyState;
    }

    public VendingMachineState getOneCoinState() {
        return oneCoinState;
    }

    public VendingMachineState getTwoCoinState() {
        return twoCoinState;
    }

}
