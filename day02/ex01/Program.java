

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid argument");
            System.exit(-1);
        }

        String dataFileA = getFileInfo(args[0]);
        String dataFileB = getFileInfo(args[1]);
        if (dataFileA.isEmpty() && dataFileB.isEmpty()) {
            System.out.println("Similarity = 0");
            System.exit(0);
        }

        ArrayList<String> arrayDataFileA = new ArrayList<>(Arrays.asList(dataFileA.split("\\s+")));
        ArrayList<String> arrayDataFileB = new ArrayList<>(Arrays.asList(dataFileB.split("\\s+")));
        Map<String, Integer> frequencyA = getCountWords(arrayDataFileA);
        Map<String, Integer> frequencyB = getCountWords(arrayDataFileB);
        HashSet<String> dictionary = new HashSet<>();
        dictionary.addAll(frequencyA.keySet());
        dictionary.addAll(frequencyB.keySet());
        createDictionary(dictionary);
        String similarity = createVector(dictionary, frequencyA, frequencyB);
        System.out.println("Similarity = " + similarity.substring(0, similarity.length() - 1));
    }

    private static String getFileInfo(String filename) {
        StringBuilder str = new StringBuilder();
        String line;

        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                str.append(line);
            }
        } catch (Exception e) {
            System.out.println("File not found");
            System.exit(-1);
        }
        return str.toString();
    }

    private static Map<String, Integer> getCountWords(ArrayList<String> list) {
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : list) {
            Integer freqInt = Collections.frequency(list, word);
            frequency.put(word, freqInt);
        }
        return frequency;
    }

    private static void createDictionary(HashSet<String> dictionary) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("dictionary.txt")) {
            for (String word : dictionary) {
                fileOutputStream.write(word.getBytes());
                fileOutputStream.write('\n');
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
            System.exit(-1);
        }
    }

    private static String createVector(HashSet<String> dictionary, Map<String, Integer> freqA, Map<String, Integer> freqB) {
        ArrayList<String> arrayList = new ArrayList<>(dictionary);
        Integer[] vectorA = new Integer[arrayList.size()];
        Integer[] vectorB = new Integer[arrayList.size()];
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (freqA.get(arrayList.get(i)) == null) {
                vectorA[i] = 0;
            } else {
                vectorA[i] = freqA.get(arrayList.get(i));
            }
            if (freqB.get(arrayList.get(i)) == null) {
                vectorB[i] = 0;
            } else {
                vectorB[i] = freqB.get(arrayList.get(i));
            }
        }
        double res = createResult(vectorA, vectorB, arrayList);
        return String.format("%.3f", res);
    }

    private static double createResult(Integer[] vectorA, Integer[] vectorB, ArrayList<String> arrayList) {
        Integer numerator = 0;
        double denominator = 0;
        double denominatorA = 0;
        double denominatorB = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            numerator += vectorA[i] * vectorB[i];
        }
        for (int i = 0; i < arrayList.size(); i++) {
            denominatorA += vectorA[i] * vectorA[i];
        }
        for (int i = 0; i < arrayList.size(); i++) {
            denominatorB += vectorB[i] * vectorB[i];
        }
        denominator = Math.sqrt(denominatorA) * Math.sqrt(denominatorB);
        return numerator / denominator;
    }

}
