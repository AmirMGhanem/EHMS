package Network;

import DBH.patientDAO;
import Model.Notification;
import Model.Patient;
import Model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class ApplicationNetwork {

    private ServerSocket serverSocket;
    private Socket acceptSocket;
    private PrintStream output;
    private BufferedReader input;
    private Scanner scan = new Scanner(System.in);

    DBH.patientDAO pDAO = new patientDAO();
    ArrayList<Patient> patientArrayList = new ArrayList<Patient>();

    public static void main(String[] args) {
        ApplicationNetwork server = new ApplicationNetwork();
        server.run();
    }


    public void run() {
        try {
            serverSocket = new ServerSocket(6666);
            acceptSocket = serverSocket.accept();
            output = new PrintStream(acceptSocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(
                    acceptSocket.getInputStream()));
            System.out.println("Connection Established");
            String patientid = input.readLine();
            System.out.println("Patient id ->" + patientid);
            while (acceptSocket.isConnected()) {
                String message = input.readLine();
                System.out.println("Button clicked ->" + message);
                MessageFetcher(patientid, message);

            }
        } catch (IOException | SQLException e) {
        }
    }

    public void MessageFetcher(String id, String message) throws SQLException {
        Request request = new Request();
        Notification notification = new Notification();
        patientArrayList = pDAO.selectAll();
        notification.setDate(new Date());
        for (Patient p : patientArrayList) {
            if (id.equals(p.getID()))
                notification.setPatient(p);
        }
        switch (message) {
            case "water":
                request.setType("Low Urgency");
                request.setDescription("Patient-> " + notification.getPatient().getName() + " Needs Water");
                break;
            case "meal":
                request.setType("Medium Urgency");
                request.setDescription("Patient-> " + notification.getPatient().getName() + " Needs A Meal");
                break;
            case "toilet":
                request.setType("Medium Urgency");
                request.setDescription("Patient-> " + notification.getPatient().getName() + " Needs Toilet");
                break;
            case "emergency":
                request.setType("Critical Urgency");
                request.setDescription(" Patient-> " + notification.getPatient().getName() + " Need YOU");
                break;

        }
        notification.setRequest(request);
        System.out.println(notification.toString());


    }

}
