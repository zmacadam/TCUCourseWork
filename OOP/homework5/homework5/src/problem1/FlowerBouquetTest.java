package problem1;

import org.junit.Test;

public class FlowerBouquetTest {

    @Test
    public void testFlowerBouquet() {
        FlowerBouquet testBouquet1 = new RoseBouquet();
        testBouquet1 = new PaperWrap(testBouquet1);
        testBouquet1 = new Card(testBouquet1);
        System.out.println(testBouquet1.getDescription() + " $" + testBouquet1.cost());
        System.out.println("-----------------------------------");

        FlowerBouquet testBouquet2 = new RoseBouquet();
        testBouquet2 = new PaperWrap(testBouquet2);
        testBouquet2 = new PaperWrap(testBouquet2);
        testBouquet2 = new Glitter(testBouquet2);
        System.out.println(testBouquet2.getDescription() + " $" + testBouquet2.cost());
        System.out.println("-----------------------------------");

        FlowerBouquet testBouquet3 = new DaisyBouquet();
        System.out.println(testBouquet3.getDescription() + " $" + testBouquet3.cost());
    }
}
