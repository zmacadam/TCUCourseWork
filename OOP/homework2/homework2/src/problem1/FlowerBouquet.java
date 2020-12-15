package problem1;

public abstract class FlowerBouquet {
    String description;
    double cost;

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public void addDecoration (double cost, String type) {
        description += " " + type + " ... ";
        this.cost += cost;
    }
}
