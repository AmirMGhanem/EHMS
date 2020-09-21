package Model;

import java.util.Date;




public class Meeting {
    private int num;
    private String name;
    private Address address;
    private Date date;
    private String Time;

    public Meeting(int num, String name, Date date) {
        setNum(num);
        setName(name);
        setDate(date);

    }

    public Meeting() {
    }

    public Meeting(String name, Address address, Date date) {
        setName(name);
        setDate(date);
        setAddress(address);


    }

    public Meeting(int num, String name, Address address, Date date) {
        setNum(num);
        setName(name);
        setDate(date);
        setAddress(address);


    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setTime(int hours,int minutes) {
       String str = hours+":"+minutes;
        Time = str;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", date=" + date +
                '}';
    }
}