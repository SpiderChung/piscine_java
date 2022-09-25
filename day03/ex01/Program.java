

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
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid count argument ");
            System.exit(-1);
        }
        Printer printer = new Printer();
        Thread egg = new Thread(new Egg(count, printer));
        Thread hen = new Thread(new Hen(count, printer));
        egg.start();
        hen.start();
    }
}
