package problem1;

public class Glitter extends FlowerBouquetDecorator {
    FlowerBouquet flowerBouquet;
    public Glitter(FlowerBouquet flowerBouquet) {
        this.flowerBouquet = flowerBouquet;
    }
    public String getDescription() {
        return flowerBouquet.getDescription()+" Glitter...";
    }
    public double cost() {
        return 8 + flowerBouquet.cost();
    }
}
