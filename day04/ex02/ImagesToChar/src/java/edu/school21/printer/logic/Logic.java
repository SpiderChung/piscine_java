package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Logic {
    private String white;
    private String black;
    private BufferedImage image;

    public Logic(String bmpFile, Args args) throws IOException {
        this.image = ImageIO.read(Logic.class.getResource(bmpFile));
        this.image = image;
        this.white = args.getWhite();
        this.black = args.getBlack();
    }

    public void seeImage() {
        ColoredPrinter coloredPrinter = new ColoredPrinter();

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight() ; yPixel++) {
                int color = image.getRGB(yPixel, xPixel);

                if (color == Color.BLACK.getRGB()) {
                    coloredPrinter.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                } else if (color == Color.WHITE.getRGB()) {
                    coloredPrinter.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                }
            }
            System.out.println();
        }
    }
}
