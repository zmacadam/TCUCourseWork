package problem7.scenario1;

import java.util.Random;

/**
 * Problem: I want to record the moving time
 */
public class Tank implements Movable {
    @Override
    public void move() {
        System.out.println("Tank moving...");
        // code to invoke some fancy graphics interface, it may be time consuming
        try {
            Thread.sleep(new Random().nextInt(10000)); // simulating the executing of some time consuming graphics interface
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

