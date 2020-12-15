package problem1;

public class OrnamentalLeaves extends FlowerBouquetDecorator {
    FlowerBouquet flowerBouquet;
    public OrnamentalLeaves(FlowerBouquet flowerBouquet) {
        this.flowerBouquet = flowerBouquet;
    }
    public String getDescription() {
        return flowerBouquet.getDescription()+" Ornamental leaves...";
    }
    public double cost() {
        return 10 + flowerBouquet.cost();
    }
}
