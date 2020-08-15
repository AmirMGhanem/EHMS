package Model;

import java.util.Date;

public class Meeting {

    private int meetingID;
    private String type;
    private Date time;
    private Address address;

    public Meeting() {
    }

    public Meeting(int meetingID , String type, Date time, Address address) {
        this.meetingID = meetingID;
        this.type = type;
        this.time = time;
        this.address = address;
    }
    public int getMeetingID(){return meetingID;}

    public  void setMeetingID(int meetingID){this.meetingID = meetingID;}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "type='" + type + '\'' +
                ", time=" + time +
                ", address=" + address +
                '}';
    }
}
