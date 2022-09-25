package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Logic {

    public static int[][] seeBMPImage(String bmpFile, char black, char white) throws IOException {
        int array[][] = null;
        try {
            BufferedImage image = ImageIO.read(Logic.class.getResource(bmpFile));

            array = new int[image.getWidth()][image.getHeight()];

            for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
                for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                    int color = image.getRGB(xPixel, yPixel);
                    if (color == Color.BLACK.getRGB()) {
                        array[xPixel][yPixel] = black;
                    } else if (color == Color.WHITE.getRGB()) {
                        array[xPixel][yPixel] = white;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println("File not found");
            System.exit(-1);
        }
        return array;
    }
}
