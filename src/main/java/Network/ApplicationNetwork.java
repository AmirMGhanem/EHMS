package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ApplicationNetwork {
    static Socket s;
    static ServerSocket ss;
    static InputStreamReader isr;
    static BufferedReader br;
    static String message;
    static PrintWriter out;

   public static void main(String[] args){
       try{
           System.out.println("TESTINGGGG");
           ss= new ServerSocket(6666);

           System.out.println("Waiting for Connection From Client ");

           s=ss.accept(); //Establish connection
           System.out.println("Connected");
           DataInputStream dis=new DataInputStream(s.getInputStream());
           String  str=(String)dis.readUTF();
           System.out.println("message= "+str);
           ss.close();
           ss.close();
       }catch (Exception e){
           System.out.println(e.toString());
       }

   }

}
