package problem1;

public class PaperWrap extends FlowerBouquetDecorator {
    FlowerBouquet flowerBouquet;
    public PaperWrap(FlowerBouquet flowerBouquet) {
        this.flowerBouquet = flowerBouquet;
    }
    public String getDescription() {
        return flowerBouquet.getDescription()+" Paper wrap...";
    }
    public double cost() {
        return 5 + flowerBouquet.cost();
    }
}
