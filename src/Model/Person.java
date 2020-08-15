package Model;

import java.util.Date;
import java.util.Objects;

abstract class Person {
    private String ID;
    private String name;
    private Address address;
    private String gender;
    private Date date;



    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public Date getDate() {
        return date;
    }





//--------------Equals And Hash Code ---------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return ID == person.ID &&
                Objects.equals(name, person.name) &&
                Objects.equals(address, person.address) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(date, person.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, address, gender, date);
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", gender='" + gender + '\'' +
                ", date=" + date +
                '}';
    }
}
