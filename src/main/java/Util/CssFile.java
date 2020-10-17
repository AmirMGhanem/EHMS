package Util;

import java.io.*;

public class CssFile {

        public void CreateFile(String url, String design) {

        try {
            FileWriter fw = new FileWriter(url);
            fw.write(design);
            fw.close();
        } catch (IOException e) {
            System.out.println("UNEXPECTED ERROR");
        }
    }
}
