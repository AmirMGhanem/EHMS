package Model;
import java.util.Date;
import java.util.Objects;

public class Person {
    private String id;
    private String name;
    private Address address;
    private String gender;
    private Date date;
    private String contactNo;

    public Person() {
    }

    public Person(String id, String name, Address address, String gender, Date date, String contactNo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.date = date;
        this.contactNo = contactNo;
    }

    public void setID(String ID) { this.id = ID;}

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
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getID() {
        return id;
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
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(address, person.address) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(date, person.date) &&
                Objects.equals(contactNo, person.contactNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, gender, date, contactNo);
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", gender='" + gender + '\'' +
                ", date=" + date +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }



}
