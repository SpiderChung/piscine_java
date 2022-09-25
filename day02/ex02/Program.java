

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private static final String PATH = "--current-folder=";
    private static File directory;

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith(PATH)) {
            System.out.println("Specify an absolute path using '--current-folder='");
            System.exit(-1);
        }

        String inputPath = args[0].substring(PATH.length());
        if (inputPath.isEmpty()) {
            System.out.println("no such file or directory: ");
        }
        directory = new File(inputPath);
        System.out.println(directory.getPath());
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        while (!inputLine.equals("exit")) {
            if (!inputLine.isEmpty()) {
                run(inputLine);
            }
            inputLine = scanner.nextLine();
        }
        scanner.close();
        System.exit(0);
    }

    private static void run(String inputLine) {
        String[] inputCommand = inputLine.split("\\s+");

        if (inputCommand[0].equals("ls")) {
            if (inputCommand.length == 1) {
                runLS();
            } else {
                System.out.println("ls: Too many arguments");
            }
        } else if (inputCommand[0].equals("cd")) {
            if (inputCommand.length == 1) {
                return;
            } else if (inputCommand.length == 2) {
                runCd(inputCommand);
            } else {
                System.out.println("cd: Too many arguments");
            }
        } else if (inputCommand[0].equals("mv")) {
                if (inputCommand.length == 3) {
                    runMv(inputCommand);
                } else {
                    System.out.println("mv: There should be two arguments here");
                }
        } else {
            System.out.println("Unknown command");
        }
    }

    private static void runLS() {
        File[] files = listFiles(directory);

        for (File file : files) {
            System.out.println(file.getName() + " " + (getSize(file) / 1000) + " KB");
        }
    }

    private static void runCd(String[] inputCommand) {
        String newPath = getPath(inputCommand[1]);
        File currentPath = new File(newPath);

        if (currentPath.isDirectory()) {
            directory = currentPath;
            System.out.println(currentPath.getPath());
        } else {
            System.out.println("cd: no such directory " + inputCommand[1]);
        }
    }

    private static void runMv(String[] inputCommand) {
        String pathFile = getPath(inputCommand[1]);
        String pathDir = getPath(inputCommand[2]);
        File currentPathFile = new File(pathFile);
        File currentPathDir = new File (pathDir);
        pathFile = currentPathFile.getPath();
        pathDir = currentPathDir.getPath();
        String pathTo = pathDir;

        if (currentPathDir.isDirectory()) {
            pathTo = pathDir + File.separator + currentPathFile.getName();
        }

        try {
            if (currentPathFile.exists()) {
                Files.move(Paths.get(pathFile), Paths.get(pathTo));
            } else {
                System.err.println("mv: File not found");
            }
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    private static File[] listFiles(File file) {
        File[] files = file.listFiles();
        if (files == null) {
            return new File[0];
        }
        return files;
    }

    private static long getSize(File file) {
        long size = 0;

        if (!file.isDirectory()) {
            return file.length();
        }

        for (File src : listFiles(file)) {
            size += getSize(src);
        }
        return size;
    }

    private static String getPath(String name) {
        if (name.startsWith("/")) {
            return name;
        } else if (name.startsWith("./")) {
            return directory + name.substring(1);
        } else if (name.startsWith("..")) {
            return directory.getParent() + name.replaceFirst(("\\.\\."), "");
        }
        return directory + "/" + name;
    }
}
