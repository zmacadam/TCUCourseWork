package problem6;

public class CSVWrapper implements Formatter {
    @Override
    public String formatText(String s) {
        return new CSVTextFormatter().formatText(s);
    }
}
