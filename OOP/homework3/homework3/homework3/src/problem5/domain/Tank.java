package problem5.domain;

public class Tank {
    private String model;
    private int speed;
    private Soldier driver;

    public Tank() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Soldier getDriver() {
        return driver;
    }

    public void setDriver(Soldier driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "model='" + model + '\'' +
                ", speed=" + speed +
                ", driver=" + driver +
                '}';
    }
}
