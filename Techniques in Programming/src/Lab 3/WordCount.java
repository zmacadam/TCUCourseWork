import java.util.ArrayList;
import java.util.StringTokenizer;

public class WordCount {
	public static void main(String[] args) {
		String file = "COSC10403 Comer Payne Sanchez COSC20203 Rinewalt COSC30353 Payne";
		String course = "";
		String line = "";
		String professor = "";
		String answer = "";
		ArrayList<String> prof = new ArrayList<String>();
		StringTokenizer tok = new StringTokenizer(file);
		while (tok.hasMoreTokens()) {
			String w = tok.nextToken();
			if (w.startsWith("COSC"))
				course = w;
			else {
				professor = w;
				prof.add(professor);
				
			line = professor + " " +course;	
			if (prof.contains(w));
			else answer = line + "\n";
			
			if (prof.contains(w)) {
			}	
		
		
	}
	
		System.out.print(answer);
		}
}
}