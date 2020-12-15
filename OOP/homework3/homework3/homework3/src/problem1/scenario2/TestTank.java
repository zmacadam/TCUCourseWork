package problem1.scenario2;
// I have already imported Tank class from Tank.jar
import com.bing.tank.Tank;

public class TestTank {
    public static void main(String[] args) {
        // record move() execution time of a tank
        Tank tank = new Tank();
        long startTime = System.currentTimeMillis();
        tank.move();
        long endTime = System.currentTimeMillis();
        System.out.println("Move took " + (endTime - startTime) + " milliseconds");
    }
}
