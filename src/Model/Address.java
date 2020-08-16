package Model;

import java.util.Objects;

public class Address {
    private String city;
    private String contactNo;
    private String street;
    private int houseNum;

    public Address(String city) {
        setCity(city);
    }

    public Address(String city, String street, int houseNum,String contactNum) {
        setCity(city);
        setStreet(street);
        setHouseNum(houseNum);
        setContactNo(contactNum);
    }




    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }





    //--------------------Equals And Hash Code----------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNum == address.houseNum &&
                Objects.equals(city, address.city) &&
                Objects.equals(contactNo, address.contactNo) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, contactNo, street, houseNum);
    }

    @Override
    public String toString() {
        return "address{" +
                "city='" + city + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                '}';
    }
}
