package Model;

import java.util.Date;

public class Meeting {

    private String type;
    private Date time;

    public Meeting() {
    }

    public Meeting(String type, Date time, Address address) {
        this.type = type;
        this.time = time;
        this.address = address;
    }

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

    private Address address;

    @Override
    public String toString() {
        return "Meeting{" +
                "type='" + type + '\'' +
                ", time=" + time +
                ", address=" + address +
                '}';
    }
}
