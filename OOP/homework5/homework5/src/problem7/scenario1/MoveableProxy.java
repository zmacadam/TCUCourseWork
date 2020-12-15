package problem7.scenario1;


public class MoveableProxy implements Movable {

    private static Movable tank;

    public MoveableProxy(Tank tank) {
        this.tank = tank;
    }
    @Override
    public void move() {
        long startTime = System.nanoTime();
        tank.move();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Move took " + duration/1000000 + " ms");
    }
}
