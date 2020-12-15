import java.text.*;
public class Formating {
	public static void main(String[] args) {
	int x = 10000;
	int y = -20000;
	double pi = Math.PI;

	System.out.printf("%d%8d+%3d\n", x, x, x);
	System.out.printf("%d %8d +   %3d\n", x, x, x);
	System.out.printf("%d %-8d +   %3d\n", x, x, x);
	System.out.printf("%,8d\n", y);
	System.out.printf("%,(8d\n", y);
	System.out.printf("%(,8d\n", y);
	System.out.printf("%8d   %8d\n", x, y);
	System.out.printf("%2$8d   %1$8d\n", x, y);
	System.out.printf("%f\n", pi);
	System.out.printf("%.3f\n", pi);
	System.out.printf("%.2f\n", pi);
	System.out.printf("%5.2f\n", pi);
	System.out.printf("%5.12f\n", pi);
	System.out.printf("%5.2f\n", pi*1e6);
	System.out.printf("%5.2e\n", pi*1e6);
	System.out.printf("%15.2e\n", pi*1e6);

	NumberFormat nf = NumberFormat.getInstance();
	System.out.println(nf.format(x));
	System.out.println(nf.format(pi));
	nf.setMaximumFractionDigits(2);
	System.out.println(nf.format(pi));

	NumberFormat cf = NumberFormat.getCurrencyInstance();
	System.out.println(cf.format(pi));
	
	DecimalFormat df = new DecimalFormat();
	System.out.println(df.format(pi));
	System.out.println(df.toPattern());
	double z = 324652345234.1;
	System.out.println(df.format(z));
	
	DecimalFormat df2 = new DecimalFormat("#,##00.00#");
	System.out.println(df2.format(z));
	System.out.println(df2.format(pi));


	};
}