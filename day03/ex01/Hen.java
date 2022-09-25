

public class Hen implements Runnable{
    private Integer count;
    private Printer printer;

    public Hen(Integer count, Printer printer) {
        this.count = count;
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                printer.printHen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
