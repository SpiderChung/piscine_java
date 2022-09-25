

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class MyThread extends Thread{
    private int num;
    private Urls urls;

    public MyThread(int num, Urls urls) {
        this.num = num;
        this.urls = urls;
    }

    @Override
    public void run() {
        while (!urls.isAllDownloaded()) {
            String str = urls.getUrl();
            byte[] dataBuffer = new byte[1024];
            int byteRead, fileNumber;
            File file;

            if (str != null) {
                file = getFile(str);

                try (BufferedInputStream input = new BufferedInputStream(new URL(str).openStream());
                     FileOutputStream output = new FileOutputStream((file))) {
                    fileNumber = urls.getUrlsListIndex(str);
                    System.out.println("Thread-" + num + " start download file number " + fileNumber);

                    while ((byteRead = input.read(dataBuffer)) != -1) {
                        output.write(dataBuffer, 0, byteRead);
                    }
                    System.out.println("Thread-" + num + " finish download file number " + fileNumber);
                } catch (IOException e) {
                    System.err.println("There is no file with name " + e.getMessage());
                }
            }
        }
    }
    private File getFile(String str) {
        File file;
        String[] strings = str.split("/");

        file = new File(strings[strings.length - 1]);
        return file;
    }
}

