package Model;

import java.util.Objects;

public class UserInfo {
    private String username;
    private String password;
    private String patientID;

    public UserInfo() {
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo(String username, String password, String patientID) {
        this.username = username;
        this.password = password;
        this.patientID = patientID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }







//-------------Equals And Hash Code-------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(username, userInfo.username) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(patientID, userInfo.patientID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, patientID);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", patientID='" + patientID + '\'' +
                '}';
    }
}
