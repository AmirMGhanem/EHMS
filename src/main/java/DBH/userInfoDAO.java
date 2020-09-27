package DBH;

import Model.Address;
import Model.UserInfo;
import Util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userInfoDAO {

    static Connection con = DatabaseConnector.getConnection();

    public int inserUser(UserInfo ui) throws SQLException {


        String sql = "insert into tblusers(username,password ) values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ui.getUsername());
        ps.setString(2, ui.getPassword());
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }


    public ArrayList<UserInfo> SelectAll() throws SQLException {
        ArrayList<UserInfo> users = new ArrayList<UserInfo>();
        String sql = "select * from tblusers";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
          UserInfo ui = new UserInfo(rs.getString("username"),rs.getString("password"),666); //encrypted -> decrypted
            users.add(ui);
        }

        ps.close();
        rs.close();
        return users;
    }


}
