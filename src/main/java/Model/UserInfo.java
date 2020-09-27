package Model;

import java.util.Objects;

public class UserInfo {
    private String username;
    private String password;


    public UserInfo(String username,String password,int key) {  // Selecting from DB ----  String -> object
    setUsername(DecryptUser(username,key));
    setPassword(DecryptPassword(password,key));
    }

    public UserInfo(String username, String password) {
        setUsername(EncryptUser(username));
        setPassword(EncryptPassword(password));
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
       this.password =password;
    }

    //--------------------------------------------------- Cryptograph ->  (encryption / decryption)  ---------------------------------------------

    private String EncryptUser(String username) {
        int key = 666;
        char[] encryptcharArray = username.toCharArray();
        for (int i = 0; i < encryptcharArray.length; i++)
            encryptcharArray[i] += key;
        String encryptedstr = "";
        for (int i = 0; i < encryptcharArray.length; i++)
            encryptedstr += encryptcharArray[i];

        return encryptedstr;
    }

    private String DecryptUser(String encryptedusername, int key) {

        char[] DecryptCharArray = encryptedusername.toCharArray();
        for (int i = 0; i < DecryptCharArray.length; i++)
            DecryptCharArray[i] -= key;
        String Decryptedstr = "";
        for (int i = 0; i < DecryptCharArray.length; i++)
            Decryptedstr += DecryptCharArray[i];
        return Decryptedstr;
    }


    private String EncryptPassword(String password) {
        int key = 666;
        char[] encryptcharArray = password.toCharArray();
        for (int i = 0; i < encryptcharArray.length; i++)
            encryptcharArray[i] += key;
        String encryptedstr = "";
        for (int i = 0; i < encryptcharArray.length; i++)
            encryptedstr += encryptcharArray[i];
        return encryptedstr;
    }

    private String DecryptPassword(String encryptedpassword, int key) {

        char[] DecryptCharArray = encryptedpassword.toCharArray();
        for (int i = 0; i < DecryptCharArray.length; i++)
            DecryptCharArray[i] -= key;
        String Decryptedstr = "";
        for (int i = 0; i < DecryptCharArray.length; i++)
            Decryptedstr += DecryptCharArray[i];
        return Decryptedstr;
    }


//--------------------------------------------------------------Equals And Hash Code----------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(username, userInfo.username) &&
                Objects.equals(password, userInfo.password);

    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
