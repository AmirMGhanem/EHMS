package DBH;


import Model.*;
import Util.DatabaseConnector;
import Util.JPQLHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class meetingDAO {

    static Connection con = DatabaseConnector.getConnection();

    ArrayList<Address> addressArrayList = new ArrayList<Address>();

    DBH.adressDAO aDAO = new adressDAO();


    public int insertMeeting(Meeting m) throws SQLException {

        String sql = "insert into meeting(name, addresscode ,date,time) values(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, m.getName());
        ps.setInt(2, m.getAddress().getAddresscode());
        ps.setDate(3, new java.sql.Date(m.getDate().getTime()));
        ps.setString(4, m.getTime());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }


    public void removeMeeting(Meeting m) throws SQLException {
        String sql1 = "DELETE FROM meeting WHERE meeting.num=? ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setInt(1, m.getNum());
        ps.executeUpdate();
        ps.close();


        String sql2 = "DELETE FROM address WHERE address.addresscode=?";
         ps = con.prepareStatement(sql2);
        ps.setInt(1, m.getAddress().getAddresscode());
        ps.executeUpdate();
        ps.close();




    }


    public ArrayList<Meeting> selectAll() throws SQLException {

        ArrayList<Meeting> list = new ArrayList<Meeting>();
        String sql = "select * from meeting";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        addressArrayList = aDAO.SelectAll();


        while (rs.next()) {
            Meeting m = new Meeting(rs.getInt("num"), rs.getString("name"), rs.getDate("date"));
            m.setTime(rs.getString("time"));
            for (Address a : addressArrayList) {
                if (rs.getInt("addresscode") == a.getAddresscode())
                    m.setAddress(a);
            }
            list.add(m);
        }
        ps.close();
        rs.close();
        return list;
    }

    public int getCount() throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from meeting";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }


    public Meeting selectLastRow() throws SQLException {
        Meeting m = null;
        String sql = "SELECT * FROM  meeting ORDER BY num DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        addressArrayList = aDAO.SelectAll();

        if (rs.next()) {
            m = new Meeting(rs.getInt("num"), rs.getString("name"), rs.getDate("date"));
            m.setTime(rs.getString("time"));
            for (Address a : addressArrayList) {
                if (rs.getInt("addresscode") == a.getAddresscode())
                    m.setAddress(a);
            }


        }
        return m;

    }


    public void removeMeetingByNum(int num) throws SQLException {
        String sql1 = "DELETE FROM meeting WHERE meal.num=? ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setInt(1, num);
        ps.executeUpdate();
        ps.close();
    }


}
