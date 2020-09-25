package Util;

import java.io.*;

public class CssFile {
    public void CreateFile(String url, String design) throws IOException {
        File userFile = new File(url);
        FileWriter fw = new FileWriter(url);

        try {
            userFile.createNewFile();
            fw.write(design);
            fw.close();
        } catch (IOException e) {
            System.out.println("UNEXPECTED ERROR");
        }
    }
}
