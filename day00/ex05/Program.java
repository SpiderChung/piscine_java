package ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] students = new String[10];
        String[][] classes = new String[7][6];
        String[][][][] attendance = new String[10][31][10][1];
        String time;
        String day;
        String name = scanner.next();

        for (int i = 0; !name.equals(".") ; i++) {
            students[i] = name;
            name = scanner.next();
        }
        time = scanner.next();
        while (!time.equals(".")) {
            day = scanner.next();
            if (day.equals("MO")) {
                fillShedule(classes[0], time);
            } else if (day.equals("TU")) {
                fillShedule(classes[1], time);
            } else if (day.equals("WE")) {
                fillShedule(classes[2], time);
            } else if (day.equals("TH")) {
                fillShedule(classes[3], time);
            } else if (day.equals("FR")) {
                fillShedule(classes[4], time);
            } else if (day.equals("SA")) {
                fillShedule(classes[5], time);
            } else if (day.equals("SU")) {
                fillShedule(classes[6], time);
            }
            time = scanner.next();
        }
        name = scanner.next();
        while (!name.equals(".")) {
            int n, d, t;
            time = scanner.next();
            d = scanner.nextInt();
            String here = scanner.next();

            for (n = 0; n < students.length && !students[n].equals(name); n++);
            t = findDayandClassOfWeek(d, classes, time);
            attendance[n][d][t][0] = here;
            name = scanner.next();
        }
        for (int i = 1; i < 31; i++) {
            findTimeOfClassAndPrint(i, classes);
        }
        for (int i = 0; i < students.length && students[i] != null; i++) {
            System.out.printf("%10s", students[i]);
            drawExcel(attendance[i], classes, students[i].length());
        }


}
    public static void findTimeOfClassAndPrint(int i, String[][] classes) {
        if (i == 1) {
            System.out.print("          ");
        }
        int j = i % 7;

        for (int k = 0; k < 6 && classes[j][k] != null; k++) {
            System.out.print(classes[j][k] + ":00 ");

            if (j == 0) {
                System.out.print("MO  " + i + "|");
            } else if (j == 1) {
                System.out.print("TU  " + i + "|");
            } else if (j == 2) {
                System.out.print("WE  " + i + "|");
            } else if (j == 3) {
                System.out.print("TH  " + i + "|");
            } else if (j == 4) {
                System.out.print("FR  " + i + "|");
            } else if (j == 5) {
                System.out.print("SA  " + i + "|");
            } else if (j == 6) {
                System.out.print("SU  " + i + "|");
            }
        }
        if (i == 30) {
            System.out.println();
        }
    }

    public static int findDayandClassOfWeek(int d, String[][] classes, String time) {
        int week = d % 7;
        int i = 0;

        while (i < 10 && !classes[week][i].equals(time)) {
            i++;
        }
        if (i < 10) {
            return i;
        }
        return 0;
    }

    public static void fillShedule(String[] classes, String time) {
        int i = 0;

        while (i < classes.length && classes[i] != null) {
            i++;
        }
        if (i < 10) {
            classes[i] = time;
        }
    }

    public static void drawExcel(String[][][] array, String[][] classes, int len) {
        for (int i = 1; i < 31; i++) {
            for (int j = 0; j < 10 && classes[i % 7][j] != null; j++) {
                if (i > 9) {
                    System.out.print(" ");
                }
                if (array[i][j][0] != null && array[i][j][0].equals("HERE")) {
                    System.out.printf("%10d|", 1);
                } else if (array[i][j][0] != null && array[i][j][0].equals("NOT_HERE")) {
                    System.out.printf("%10d|", -1);
                } else {
                    System.out.printf("%10s|", " ");
                }
            }
        }
        System.out.println();
    }
}