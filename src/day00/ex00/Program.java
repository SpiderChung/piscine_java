package day00.ex00;

public class Program {
    public static void main(String[] args) {
        int res;
        int src;

        res = 0;
        src = 479598;
        res += src % 10;
        src = src / 10;
        res += src % 10;
        src = src / 10;
        res += src % 10;
        src = src / 10;
        res += src % 10;
        src = src / 10;
        res += src % 10;
        src = src / 10;
        res += src % 10;
        System.out.println(res);
    }
}
