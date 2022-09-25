

public class Egg implements Runnable {
    Integer count;
    Printer printer;

    public Egg(Integer count, Printer printer) {
        this.count = count;
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                printer.printEgg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
