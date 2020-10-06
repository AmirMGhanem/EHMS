package DBH;

import Controller.TherapistPaneController;
import Model.Address;
import Model.Person;
import Model.Therapist;
import Util.DatabaseConnector;
import Util.JPQLHandler;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class personDAO {

    static Connection con = DatabaseConnector.getConnection();

    public int insertperson(Person person) throws SQLException {

        String sql = "insert into person(id,name,address,gender,birthdate,contactno) values(?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, person.getID());
        ps.setString(2, person.getName());
        ps.setInt(3, person.getAddress().getAddresscode());
        ps.setString(4, person.getGender());
        ps.setString(5, person.getDate().toString());
        ps.setString(6, person.getContactNo());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public void updatePerson(Therapist t) throws SQLException {

        String sql = "update person set name=? where addresscode=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, t.getName());
        ps.setString(2, t.getContactNo());
        ps.setString(3, t.getID());

        ps.executeQuery();
        ps.close();
    }

    public ArrayList<Person> selectAll() throws SQLException {
        ArrayList<Person> list = new ArrayList<Person>();

        String sql = "select * from address , person where address.addresscode = person.address";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Address address = new Address(rs.getInt("addresscode"), rs.getString("city"), rs.getString("street"), rs.getInt("housenum"));
            Person p = new Person(rs.getString("id"), rs.getString("name"), address, rs.getString("gender"), rs.getDate("birthdate"), rs.getString("contactno"));
            list.add(p);
        }

        System.out.println(list);

        ps.close();
        rs.close();

        return list;
    }
}
