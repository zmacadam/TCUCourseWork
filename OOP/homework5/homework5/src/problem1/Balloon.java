package problem1;

public class Balloon extends FlowerBouquetDecorator {
    FlowerBouquet flowerBouquet;
    public Balloon(FlowerBouquet flowerBouquet) {
        this.flowerBouquet = flowerBouquet;
    }
    public String getDescription() {
        return flowerBouquet.getDescription()+" Balloon...";
    }
    public double cost() {
        return 7 + flowerBouquet.cost();
    }
}
