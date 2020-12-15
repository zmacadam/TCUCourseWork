package problem5.resource;

import java.io.InputStream;

public class ClasspathResource implements Resource {
    private String location;

    public InputStream getResourceAsStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }

    public ClasspathResource(String location) {
        this.location = location;
    }
}
