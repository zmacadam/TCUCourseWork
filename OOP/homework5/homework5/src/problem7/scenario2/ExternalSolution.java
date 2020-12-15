package problem7.scenario2;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import com.bing.tank.Tank;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExternalSolution {

    public Object createObject() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(Tank.class);
        factory.setFilter(
                new MethodFilter() {
                    @Override
                    public boolean isHandled(Method method) {
                        return true;
                    }
                }
        );

        MethodHandler handler = new MethodHandler() {
            @Override
            public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
                long startTimestamp = System.currentTimeMillis();
                method1.invoke(o, objects);
                long endTimeStamp = System.currentTimeMillis();
                long responseTime = endTimeStamp - startTimestamp;
                System.out.println("Move took " + responseTime + " ms");
                return null;
            }
        };

        return factory.create(new Class<?>[0], new Object[0], handler);
    }
}
