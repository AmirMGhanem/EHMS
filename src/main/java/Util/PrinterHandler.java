package Util;

import java.io.*;

public class PrinterHandler {

    public void Print(String Content, String printerID) throws IOException, InterruptedException {
        byte[] ContentToBytes = Content.getBytes();
        ByteArrayInputStream fis = new ByteArrayInputStream(ContentToBytes);
        String finalLine ;

        System.out.println("printerID"+printerID);
        String command = "lp -d " + printerID;

        Process child = Runtime.getRuntime().exec(command);
        OutputStream childOut = child.getOutputStream();

        byte[] buffer = new byte[100000000];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) > 0)
            childOut.write(buffer, 0, bytesRead);

        childOut.close();
        int exitVal = child.waitFor();
        InputStream childIn = child.getInputStream();
        BufferedReader is = new BufferedReader(new InputStreamReader(childIn));
        String line;
        boolean retval;
        while ((line = is.readLine()) != null)
            finalLine = line;
        childIn.close();
        if (exitVal == 0)
            retval = true;
    }
}