package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int iter = 1;
        int num = scanner.nextInt();
        boolean isPrime = true;

        if (num <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        } else if (num == 2) {
            System.out.println("true" + " " + 1);
            System.exit(0);
        } else {
            for (long i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
                iter++;
            }
        }
        System.out.println(isPrime + " " + iter);
        scanner.close();
        System.exit(0);
    }
}