package Model;

import java.sql.Date;
import java.sql.Time;

public class Notification {
    private int Num;
    private Request request;
    private Patient patient;
    private Date date;
    public Notification() {
    }

    public Notification(int num, Request request, Patient patient,Date date) {
        setNum(num);
        setPatient(patient);
        setRequest(request);
        setDate(date);
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
