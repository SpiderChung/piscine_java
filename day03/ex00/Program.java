

public class Program {
    private static String PARAM = "--count=";

    public static void main(String[] args) {
        Integer count = 0;

        if (args.length != 1 || !args[0].startsWith(PARAM)) {
            System.err.println("Specify a count argument using '" + PARAM + "'");
            System.exit(-1);
        }

        String inputCount = args[0].substring(PARAM.length());
        try {
            count = Integer.parseInt(inputCount);
            if (count < 1) {
                throw new NumberFormatException();
            }
            MyThread egg = new MyThread(count, "Egg");
            MyThread hen = new MyThread(count, "Hen");
            egg.start();
            hen.start();
            egg.join();
            hen.join();

            for (int i = 0; i < count; i++) {
                System.out.println("Human");
                if (i == 30) {
                    egg.interrupt();
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid count argument " + count);
            System.exit(-1);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
