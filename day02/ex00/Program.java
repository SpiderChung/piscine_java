

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final String FILE_PATH = "signature.txt";
    private static final char[] HEX = "0123456789ABCDEF".toCharArray();
    private static final String RESULT = "result.txt";

    public static void main(String[] args) {
        Map<String, String> signature = new HashMap<>();

        try (FileInputStream inputFile = new FileInputStream(FILE_PATH);
             Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String address = scanner.nextLine();
                String[] addressArray = address.split(",");
                signature.put(addressArray[0], addressArray[1].replaceAll("\\s+", ""));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        Scanner scanner = new Scanner(System.in);
        String inputFile = scanner.nextLine();

        while (!inputFile.equals("42")) {
            try (FileInputStream line = new FileInputStream(inputFile)) {
                byte[] bytes = new byte[8];
                line.read(bytes, 0, 8);
                checkSignature(signature, bytesToHex(bytes));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            inputFile = scanner.nextLine();
        }

    }

    private static void checkSignature(Map<String, String> map, String signature) {
        try (FileOutputStream out = new FileOutputStream(RESULT, true)) {
            for (Map.Entry<String, String> sign : map.entrySet()) {
                if (signature.contains(sign.getValue())) {
                    out.write(sign.getKey().getBytes());
                    out.write('\n');
                    System.out.println("PROCESSED");
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hex = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hex[j * 2] = HEX[v >>> 4];
            hex[j * 2 + 1] = HEX[v & 0x0F];
        }
        return new String(hex);
    }
}
