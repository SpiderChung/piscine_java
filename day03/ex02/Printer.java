

public class Printer {
    private int[] array;
    private int sumAll;

    public Printer(int[] array) {
        this.array = array;
    }

    public synchronized void printer(int first, int last, int num) {
        int sumThread = 0;

        for (int i = first; i <= last; i++) {
            sumThread += array[i];
        }
        System.out.println("Thread " + num + ": from " + first + " to " + last + " sum is " + sumThread);
            sumAll += sumThread;
    }

    public int getSumAll() {
        return sumAll;
    }
}
