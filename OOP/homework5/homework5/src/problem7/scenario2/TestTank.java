package problem7.scenario2;
// I have already imported Tank class from Tank.jar
import com.bing.tank.Tank;
import com.bing.tank.Movable;

import java.lang.reflect.InvocationTargetException;

public class TestTank {
    public static void main(String[] args) {
        // record move() execution time of a tank
        TimerProxyFactory proxyFactory = new TimerProxyFactory();

        Movable tank = (Movable) proxyFactory.createProxy(new Tank());
        tank.move();


        //EXTERNAL SOLUTION MADE USING JAVASSIST LIBRARY
        ExternalSolution externalSolution = new ExternalSolution();
        try {
            tank = (Tank) externalSolution.createObject();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        tank.move();
    }
}
