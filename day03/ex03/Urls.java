

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Urls {
    private String URLPATH = "files_urls.txt";
    private boolean isAllDownloaded = false;
    private Map<Integer, String> urlsList = new TreeMap<>();
    private int key = 1;

    public Urls() throws IOException {
        List<String> list = Files.readAllLines(Paths.get(URLPATH));
        list = list.stream().map(r -> r = r.trim()).filter(r -> !r.isEmpty()).collect(Collectors.toList());

        if (list.size() == 0) {
            throw new RemoteException("There are no URL files");
        }

        for (String str: list) {
            String[] value = str.split("\\s+");
            urlsList.put(Integer.parseInt(value[0]), value[1]);
        }
    }

    public synchronized boolean isAllDownloaded() {
        return isAllDownloaded;
    }

    public int getUrlsListIndex(String str) {
        Set<Map.Entry<Integer, String>> set = urlsList.entrySet();

        for (Map.Entry entry : set) {
            if (entry.getValue().equals(str)) {
                return (int) entry.getKey();
            }
        }
        return -1;
    }

    public synchronized String getUrl() {
        if (!urlsList.containsKey(key)) {
            return null;
        }
        String str = urlsList.get(key++);

        if (!urlsList.containsKey(key)) {
            isAllDownloaded = true;
        }
        return str;
    }
}
