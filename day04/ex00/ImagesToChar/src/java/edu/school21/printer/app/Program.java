package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Invalid arguments");
            System.exit(-1);
        }

        char white = args[0].charAt(0);
        char black = args[1].charAt(0);
        String path = args[2];

        int[][] array = Logic.seeBMPImage(path, black, white);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print((char)array[j][i]);
            }
            System.out.println();
        }
    }
}
