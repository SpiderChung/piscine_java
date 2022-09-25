package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.Args;
import edu.school21.printer.logic.Logic;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        try {
            Args jargs = new Args();
            JCommander jCommander = new JCommander(jargs);
            jCommander.parse(args);
            Logic logic = new Logic("/resources/it.bmp", jargs);
            logic.seeImage();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}