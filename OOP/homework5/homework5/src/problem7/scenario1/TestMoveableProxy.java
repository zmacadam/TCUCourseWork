package problem7.scenario1;

public class TestMoveableProxy {
    public static void main(String[] args) {
        MoveableProxy moveableProxy = new MoveableProxy(new Tank());
        moveableProxy.move();
    }
}
