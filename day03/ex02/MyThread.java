

public class MyThread extends Thread{
    private int num;
    private int first;
    private int last;
    private Printer printer;

    public MyThread(Printer printer, int first, int last, int num) {
        this.printer = printer;
        this.first = first;
        this.last = last;
        this.num = num;

    }

    @Override
    public void run() {
        printer.printer(first, last, num);
    }
}
