package problem5.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;

public class XmlBeanFactory {
    /**
     * This method loads the information from BeanDefinitions.xml and create an instance
     * for every <bean><bean/> using Java reflection APIs
     *
     * for your simplicity, I copy the entire XML here:
     * <beans>
     *     <bean id="harry" class="problem5.domain.Soldier">
     *         <property name="name" value="Harry Potter"></property>
     *         <property name="rank" value="Sergeant"></property>
     *         <property name="age" value="35"></property>
     *     </bean>
     *     <bean id="m1" class="problem5.domain.Tank">
     *         <property name="model" value="M1 Abrams"></property>
     *         <property name="speed" value="45"></property>
     *     </bean>
     * </beans>
     *
     * @param rootElement root element of BeanDefinitions.xml, which is <beans><beans/>
     */
    public void createBeanInstances(Element rootElement) {
        for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext();) {
            Element element = it.next();
            createBeanInstance(element);
        }
    }

    /**
     * This method creates an instance given a XML element
     *
     * @param beanElement i.e. <bean><bean/>
     */
    private void createBeanInstance(Element beanElement) {
        Object beanInstance = createObject(beanElement.attribute("class").getValue());
        for (int i = 0; i < beanElement.nodeCount(); i++) {
            Node node = beanElement.node(i);
            if (node instanceof Element) {
                setPropertyForBeanInstance(beanInstance, (Element) node);
            }
        }
         // print this newly created instance here, please uncomment below
        System.out.println(beanInstance.toString());
    }

    /**
     * This method uses reflection to create an instance of the given fully qualified class name
     *
     * @param beanClassName
     * @return
     */
    private Object createObject(String beanClassName) {
        try {
            Class beanName = Class.forName(beanClassName);
            Object bean = beanName.newInstance();
            return bean;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method set a value for the given field of the given beanInstance
     *
     * @param beanInstance
     * @param propertyElement XML element represents a field and its value, i.e. <property><property/>
     */
    private void setPropertyForBeanInstance(Object beanInstance, Element propertyElement) {
        try {
            Class clazz = beanInstance.getClass();
            Object name = propertyElement.attribute("name").getValue();
            String value = propertyElement.attribute("value").getValue();
            Field declaredField = clazz.getDeclaredField((String) name);
            declaredField.setAccessible(true);
            if (declaredField.getType().toString().equals("int")) {
                declaredField.set(beanInstance, Integer.parseInt(value));
            } else {
                declaredField.set(beanInstance, value);
            }
            declaredField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
