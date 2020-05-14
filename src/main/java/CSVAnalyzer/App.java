package CSVAnalyzer;

public class App {
    public static void main(String[] args) {

        CSVAnalizerBoard.getInstance().constructMenuBar();
        CSVAnalizerBoard.getInstance().setVisible(true);
        CSVAnalizerBoard.getInstance().startUpdaterThread();
    }
}
