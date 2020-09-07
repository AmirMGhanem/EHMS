package Model;

import java.util.Objects;

public class Address {
    private int addresscode;
    private String city;
    private String street;
    private int houseNum;


    public Address(String city) {
        setCity(city);
    }

    public Address(int addresscode,String city, String street, int houseNum) {
        setAddresscode(addresscode);
        setCity(city);
        setStreet(street);
        setHouseNum(houseNum);
    }





    public int getAddresscode() {
        return addresscode;
    }

    public void setAddresscode(int addresscode) {
        this.addresscode = addresscode;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNum);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addresscode=" + addresscode +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +

                '}';
    }
}
