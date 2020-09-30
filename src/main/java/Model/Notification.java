package Model;

import java.util.Date;


public class Notification {
    private int Num;
    private Request request;
    private Patient patient;
    private Date date;
    private String isTreated;
    private Therapist therapist;




    public Notification() {

    }

    public Notification(int num, Request request, Patient patient,Date date,String isTreated) {
        setNum(num);
        setPatient(patient);
        setRequest(request);
        setDate(date);
        setIsTreated(isTreated);
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

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public String getIsTreated() {
        return isTreated;
    }

    public void setIsTreated(String isTreated) {
        this.isTreated = isTreated;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "Num=" + Num +
                ", request=" + request +
                ", patient=" + patient +
                ", date=" + date +
                '}';
    }
}
