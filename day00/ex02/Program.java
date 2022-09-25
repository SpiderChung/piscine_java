package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int num = 0;
        boolean isPrime = true;
        int src = scanner.nextInt();

        while (src != 42) {
            while (src >= 1) {
                num += src % 10;
                src /= 10;
            }
            for (long i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime == true) {
                count++;
            }
            isPrime = true;
            src = scanner.nextInt();
            num = 0;
        }
        System.out.println("Count of coffee - request - " + count);
        scanner.close();
        System.exit(0);
    }
}
