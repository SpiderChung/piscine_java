
import java.io.IOException;

public class Program {
    private static String PARAM = "--threadsCount=";
    private static int threadsCount;
    private static int[] array;
    private static MyThread[] threads;
    private static Urls urls;

    public static void main(String[] args) {
        try {
            checkParams(args);
            fillData();
            runCalculate();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid argument");
        } catch (IOException | RuntimeException e) {
            System.err.println("Can't find a file " + e.getMessage());
        }
    }

    private static void checkParams(String[] args) {
        if (args.length != 1 || !args[0].startsWith(PARAM)) {
            System.err.println("Specify argument using '" + PARAM + "'");
            System.exit(-1);
        }
        threadsCount = Integer.parseInt(args[0].substring(PARAM.length()));
        if (threadsCount < 1) {
            System.err.println("Error: Illegal argument for threadsCount. It must be greater than 0");
            System.exit(-1);
        }
    }

    private static void fillData() throws IOException {
        urls = new Urls();
        threads = new MyThread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new MyThread(i + 1, urls);
        }
    }

    private static void runCalculate() {
        for (int i = 0; i < threadsCount; i++) {
            threads[i].start();
        }
    }
}
