package problem1;

public class Card extends FlowerBouquetDecorator {
    FlowerBouquet flowerBouquet;
    public Card(FlowerBouquet flowerBouquet) {
        this.flowerBouquet = flowerBouquet;
    }
    public String getDescription() {
        return flowerBouquet.getDescription()+" Card...";
    }
    public double cost() {
        return 6 + flowerBouquet.cost();
    }
}
