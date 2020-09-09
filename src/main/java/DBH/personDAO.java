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
import java.util.Date;
import java.util.List;

public class personDAO implements JPQLHandler {

    static Connection con = DatabaseConnector.getConnection();

    public int insertperson(Person person) throws SQLException {

        String sql = "insert into person(id,name,address,gender,birthdate,contactno) values(?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, person.getID());
        ps.setString(2, person.getName());
        ps.setInt(3, person.getAddress().getAddresscode());
        ps.setString(4,person.getGender());
        ps.setString(5,person.getDate().toString());
        ps.setString(6,person.getContactNo());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;

    }

    public void updatePerson(Therapist t) throws SQLException{

        String sql = "update person set name=? where addresscode=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1 , t.getName());
        ps.setString(2 , t.getContactNo());
        ps.setString(3 , t.getID());

        ps.executeQuery();
        ps.close();

    }

    @Override
    public void SelectQuery() {


    }

    @Override
    public void InsertQuery() {

    }

    @Override
    public void RemoveQuery() {

    }

    @Override
    public int CountQuery() {
        return 0;
    }

    @Override
    public List SelectAllQuery() {

        return null;
    }
}
