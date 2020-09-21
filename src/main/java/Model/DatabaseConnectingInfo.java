package Model;



public class DatabaseConnectingInfo {
   private String url;
   private String user;
   private String Pass;


    public DatabaseConnectingInfo() {
    }

    public DatabaseConnectingInfo(String url, String user, String pass) {
      setUrl(url);
      setUser(user);
      setPass(pass);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
