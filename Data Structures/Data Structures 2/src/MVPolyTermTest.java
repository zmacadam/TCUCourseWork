
public class MVPolyTermTest {
	public static void main(String[] args0) {
		MVPolyTerm t1 = new MVPolyTerm(-2,4,6,1);
		MVPolyTerm t2 = new MVPolyTerm(-2,0,6,1);
		MVPolyTerm t3 = new MVPolyTerm(2,4,0,1);
		MVPolyTerm t4 = new MVPolyTerm(2,4,6,0);
		MVPolyTerm t5 = new MVPolyTerm(0,4,6,1);
		MVPolyTerm t6 = new MVPolyTerm(2,0,6,0);
		MVPolyTerm t7 = new MVPolyTerm(2,4,0,0);
		MVPolyTerm t8 = new MVPolyTerm(0,0,0,0);
		
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println(t3.toString());
		System.out.println(t4.toString());
		System.out.println(t5.toString());
		System.out.println(t6.toString());
		System.out.println(t7.toString());
		System.out.println(t8.toString());
	}
}
