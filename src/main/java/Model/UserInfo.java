package Model;

import java.util.Objects;

public class UserInfo {
    private String username;
    private String password;
    private DatabaseConnectingInfo databaseConnectingInfo;

    public UserInfo() {
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo(String username, String password, String patientID) {
        this.username = username;
        this.password = password;

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

    public DatabaseConnectingInfo getDatabaseConnectingInfo() {
        return databaseConnectingInfo;
    }

    public void setDatabaseConnectingInfo(DatabaseConnectingInfo databaseConnectingInfo) {
        this.databaseConnectingInfo = databaseConnectingInfo;
    }

//-------------Equals And Hash Code-------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(username, userInfo.username) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(databaseConnectingInfo, userInfo.databaseConnectingInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, databaseConnectingInfo);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", databaseConnectingInfo=" + databaseConnectingInfo +
                '}';
    }
}
