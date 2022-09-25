package ex04;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String src = scanner.nextLine();
        int[] charCount = new int[65536];
        char [] resultList = new char[10];
        int [] countChrList = new int[10];
        char maxChar = ' ';
        int maxCount = 0;
        int ind = 0;

        char[] inputArray = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            charCount[inputArray[i]]++;
        }
        for (int j = 0; j < 10 ; j++) {
            for (int i = 0; i < 65536; i++) {
                if (charCount[i] > maxCount) {
                    maxCount = charCount[i];
                    maxChar = (char) i;
                    ind = i;
                }
            }
            countChrList[j] = charCount[ind];
            resultList[j] = maxChar;
            charCount[ind] = 0;
            maxChar = ' ';
            maxCount = 0;
        }
        if (charCount[0] > 999) {
            scanner.close();
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        drawHistogram(resultList, countChrList);
        scanner.close();
        System.out.println();
    }

    public static void drawHistogram(char[] resultList, int[] countChrList) {
        {
            System.out.println();
            for(int i = 0; i < 10; i++) {
                if(countChrList[i] * 10 / countChrList[0] == 10)
                    System.out.print(countChrList[i] + " ");
            }
            System.out.println();
            for (int i = 10; i > 0; i--) {
                for (int j = 0; j < 10; j++) {
                    if (countChrList[j] * 10 / countChrList[0] >= i)
                        System.out.print("# ");
                    if (countChrList[j] * 10 / countChrList[0] == i - 1) {
                        if (countChrList[j] != 0) {
                            System.out.print(countChrList[j] + " ");
                        }
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < 10; i++){
                System.out.print(resultList[i] + " ");
            }
        }
    }
}
