package DBH;

import Controller.TherapistPaneController;
import Model.Address;
import Model.Patient;
import Model.Person;
import Model.Therapist;
import Util.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class therapistDAO {
    static Connection con = DatabaseConnector.getConnection();

    public int insertherapist(Therapist therapist) throws SQLException {

        String sql = "insert into therapist(id,dateworkstart) values(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, therapist.getID());
        ps.setString(2, therapist.getWorkDateStart().toString());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }


    public ObservableList<Therapist> selectTherapists() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();

        System.out.println(list);

        String sql = "select * from address , person , therapist where address.addresscode = person.address and person.id = therapist.id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Address address = new Address(rs.getInt("addresscode"), rs.getString("city"), rs.getString("street"), rs.getInt("housenum"));
            Therapist t = new Therapist(rs.getString("id"), rs.getString("name"), address, rs.getString("gender"), rs.getDate("birthdate"), rs.getString("contactno"), rs.getDate("dateworkstart"), null);
            list.add(t);
        }

        System.out.println(list);

        ps.close();
        rs.close();

        return list;
    }

    public ArrayList<Therapist> selectAll() throws SQLException {
        ArrayList<Therapist> list = new ArrayList<Therapist>();

        System.out.println(list);

        String sql = "select * from address , person , therapist where address.addresscode = person.address and person.id = therapist.id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Address address = new Address(rs.getInt("addresscode"), rs.getString("city"), rs.getString("street"), rs.getInt("housenum"));
            Therapist t = new Therapist(rs.getString("id"), rs.getString("name"), address, rs.getString("gender"), rs.getDate("birthdate"), rs.getString("contactno"), rs.getDate("dateworkstart"), null);
            list.add(t);
        }

        System.out.println(list);

        ps.close();
        rs.close();

        return list;
    }

    public void removeTherapistByID(String id , int addressCode) throws SQLException{
        String sql1 = "DELETE FROM therapist WHERE therapist.id=? ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1 , id);
        ps.executeUpdate();
        ps.close();

        String sql2 = "DELETE FROM person WHERE person.id=?";
        ps = con.prepareStatement(sql2);
        ps.setString(1 , id);
        ps.executeUpdate();
        ps.close();

        String sql3 = "DELETE FROM address where address.addresscode = ? ";
        ps = con.prepareStatement(sql3);
        ps.setInt(1 , addressCode);
        ps.executeUpdate();
        ps.close();
    }

    public void Updateherapist(Therapist t) throws SQLException {


        String sql1 = "update person SET name = ?, contactno=?";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1, t.getName());
        ps.setString(2,t.getContactNo());
        int rows = ps.executeUpdate();
        ps.close();


        String sql2 = "update address SET city = ? , street=? , housenum=?";
        ps = con.prepareStatement(sql2);

        ps.setString(1, t.getAddress().getCity());
        ps.setString(2, t.getAddress().getStreet());
        ps.setInt(3, t.getAddress().getHouseNum());

        rows = ps.executeUpdate();
        ps.close();


    }

    /*
    DELETE FROM therapist WHERE therapist.id=208913236 ;
DELETE FROM person WHERE person.id=208913236 ;
DELETE from address where address.addresscode = 1 ;
     */
}