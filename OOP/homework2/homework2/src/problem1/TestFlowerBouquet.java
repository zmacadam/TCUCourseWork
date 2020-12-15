package problem1;

public class TestFlowerBouquet {

	public static void main(String[] args) {
		RoseBouquet roseBouquet1 = new RoseBouquet();
		roseBouquet1.addDecoration(5, "Paper wrap");
		roseBouquet1.addDecoration(6, "Card");
		System.out.println(roseBouquet1.getDescription() + "$" + roseBouquet1.getCost());

		RoseBouquet roseBouquet2 = new RoseBouquet();
		roseBouquet2.addDecoration(5, "Paper wrap");
		roseBouquet2.addDecoration(5, "Paper wrap");
		roseBouquet2.addDecoration(8, "Glitter");
		System.out.println(roseBouquet2.getDescription() + "$" + roseBouquet2.getCost());

		DaisyBouquet daisyBouqet = new DaisyBouquet();
		System.out.println(daisyBouqet.getDescription() + " $" + daisyBouqet.getCost());

		LilyBouquet lilyBouquet = new LilyBouquet();
		lilyBouquet.addDecoration(10, "Ornamental leaves");
		System.out.println(lilyBouquet.getDescription() + "$" + lilyBouquet.getCost());
	}

}
