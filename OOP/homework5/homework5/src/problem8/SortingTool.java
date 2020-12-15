package problem8;

public class SortingTool {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        sorter.sortFile(args[0]); // args[0] stores the file path
    }
}