package Network;

import DBH.notificationDAO;
import DBH.patientDAO;
import Model.Notification;
import Model.Patient;
import Model.Request;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public  class ApplicationNetwork extends Thread {

    DBH.patientDAO pDAO = new patientDAO();
    ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    DBH.notificationDAO nDAO = new notificationDAO();
    ArrayList<Notification> notificationArrayList = new ArrayList<Notification>();

    String msg;


    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(6666);
            while (true) {
                Socket s = ss.accept();
                System.out.println("A Request Has Been Arrived From Patient ->");
                DataInputStream input = new DataInputStream(s.getInputStream());
                msg = input.readUTF();
                MessageFetcher(msg);
                input.close();
                s.close();

            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void MessageFetcher(String msg) throws SQLException {
        //Fetching msg ->> id + request
        String[] splittedMsg = new String[2];
        splittedMsg = msg.split(" -> ");
        String id = splittedMsg[0];
        String message = splittedMsg[1];
        patientArrayList = pDAO.selectAll();
        Patient patient = null;
        for (Patient p : patientArrayList) {
            if (id.equals(p.getID()))
                patient = p;
        }
        if (patient != null) {
            //creating instance of object
            Request request = new Request();
            Notification notification = new Notification();
            notification.setDate(new Date());
            notification.setPatient(patient);
            switch (message) {
                case "water":
                    request.setType("Low Urgency");
                    request.setDescription("Patient-> " + notification.getPatient().getName() + " Needs Water");
                    break;
                case "meal":
                    request.setType("Medium Urgency");
                    request.setDescription("Patient-> " + notification.getPatient().getName() + " Needs Meal");
                    break;
                case "toilet":
                    request.setType("Medium Urgency");
                    request.setDescription("Patient-> " + notification.getPatient().getName() + " Needs Toilet");
                    break;
                case "emergency":
                    request.setType("Critical Urgency");
                    request.setDescription(" Patient-> " + notification.getPatient().getName() + " Needs YOU");
                    break;
            }
            notification.setRequest(request);
            nDAO.insertNotification(notification);

            System.out.println(notification.toString());
        }
    }
}
