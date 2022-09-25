package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String src;
        Scanner scanner = new Scanner(System.in);
        long grades = 0;
        long pow;

        for (int week = 1; week < 19; week++) {
            src = scanner.next();
            if (src.equals("42")) {
                break;
            } else if (!src.equals("Week")) {
                scanner.close();
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            if (week != scanner.nextInt()) {
                scanner.close();
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            pow = 1;
            for (int i = 1; i < week; i++) {
                pow = pow * 10;
            }
            grades += pow * getMinOfWeek(scanner);
        }
        scanner.close();
        getPrints(grades);
    }

    public static int getMinOfWeek(Scanner scanner) {
        int min = 10;
        int count = 0;
        int temp;

        while (count < 5) {
            if (scanner.hasNextInt()) {
                temp = scanner.nextInt();
                if (temp > 9 || temp < 1) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                if (temp < min) {
                    min = temp;
                }
            } else {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            count++;
        }
        return (min);
    }

    public static void getPrints(long grades) {
        long grade = 1;
        for (int week = 1; grades > 1; week++) {
            System.out.print("Week " + week + " ");
            grade = grades % 10;
            grades /= 10;
            for (int j = 0; j < grade; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }
}

