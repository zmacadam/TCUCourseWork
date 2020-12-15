package problem6;

public class CSVTextFormatter implements Formatter {
    @Override
    public String formatText(String s) {
        return s.replace(".", ",");
    }
}
