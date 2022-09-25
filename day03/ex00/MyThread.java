

public class MyThread extends Thread{
    Integer count;
    String text;

    public MyThread(Integer count, String text) {
        this.count = count;
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(this.text);
        }
    }
}
