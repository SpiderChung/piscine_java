

import java.util.Arrays;
import java.util.Random;

public class Program {
    private static String PARAM1 = "--arraySize=";
    private static String PARAM2 = "--threadsCount=";
    private static int arraySize, threadsCount;
    private static int[] array;
    private static MyThread[] threads;
    private static Printer printer;


    public static void main(String[] args) {
        try {
            checkParams(args);
            fillArray();
            runCalculate();
            System.out.println("Sum by threads: " + printer.getSumAll());
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid array and count argument");
            System.exit(-1);
        }

    }

    private static void checkParams(String[] args) {
        if (args.length != 2 || !args[0].startsWith(PARAM1) || !args[1].startsWith(PARAM2)) {
            System.err.println("Specify arguments using '" + PARAM1 + "' and '" + PARAM2 + "'");
            System.exit(-1);
        }
        arraySize = Integer.parseInt(args[0].substring(PARAM1.length()));
        threadsCount = Integer.parseInt(args[1].substring(PARAM2.length()));
        if (arraySize > 2_000_000 || threadsCount < 1 || threadsCount > arraySize) {
            System.err.println("Error: Illegal argument for arraySize or threadsCount");
            System.exit(-1);
        }
    }

    private static void fillArray() {
        int section, first;
        int last = 0;
        int n = 0;
        
        array = new int[arraySize];
        threads = new MyThread[threadsCount];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(2001) - 1000;
        }
        System.out.println("Sum: " + Arrays.stream(array).sum());
        printer = new Printer(array);
        section = arraySize / threadsCount;

        for (int i = 0; i < threadsCount - 1; i++, n++) {
            first = section * n;
            last = first + section - 1;
            threads[i] = new MyThread(printer, first, last, (i + 1));
        }

        if (arraySize % threadsCount != 0) {
            section = arraySize - (section * (threadsCount - 1));
        }

        if (threadsCount > 1) {
            threads[threads.length - 1] = new MyThread(printer, last + 1, last + section, threads.length);
        } else {
            threads[0] = new MyThread(printer, 0, arraySize - 1, 1);
        }
    }
    private static void runCalculate() {
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
                Thread.sleep(5);
            }
            for (int j = 0; j < threads.length; j++) {
                threads[j].join();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}