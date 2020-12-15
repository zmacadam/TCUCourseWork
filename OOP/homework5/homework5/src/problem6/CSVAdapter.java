package problem6;

import com.bing.CSVTextFormatter;

public class CSVAdapter implements Formatter {
    @Override
    public String formatText(String s) {
        return new CSVTextFormatter().formatCSVText(s);
    }
}