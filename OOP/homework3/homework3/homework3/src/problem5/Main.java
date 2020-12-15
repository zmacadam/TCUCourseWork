package problem5;

import org.dom4j.Document;
import problem5.factory.XmlBeanFactory;
import problem5.resource.ClasspathResource;
import problem5.resource.Resource;
import problem5.utils.DocumentReader;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        // Step 1: BeanDefinitions.xml is where we define all the beans
        String location = "BeanDefinitions.xml";

        // Step 2: encapsulate BeanDefinitions.xml as resource
        Resource resource = new ClasspathResource(location);

        // Step 3: read from BeanDefinitions.xml file and create instances using Java reflection
        InputStream resourceAsStream = resource.getResourceAsStream();
        Document document = DocumentReader.createDocument(resourceAsStream);
        XmlBeanFactory beanFactory = new XmlBeanFactory();
        beanFactory.createBeanInstances(document.getRootElement());
    }
}
