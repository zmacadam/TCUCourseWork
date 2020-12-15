package problem6;

public class NewLineFormatter implements Formatter {
	@Override
	public String formatText(String s) {
		s = s.replace(".", "\n");
		return s;
	}
}
