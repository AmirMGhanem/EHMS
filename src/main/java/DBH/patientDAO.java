package DBH;

import Model.Address;
import Model.Patient;
import Model.Therapist;
import Util.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class patientDAO {
    static Connection con = DatabaseConnector.getConnection();

    public int insertPatient(Patient p) throws SQLException {

        String sql = "insert into patient(id) values(?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, p.getID());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public ObservableList<Patient> selectPatients() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();

        System.out.println(list);

        String sql = "select * from address , person , patient where address.addresscode = person.address and person.id = patient.id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        while (rs.next()) {
            Address address = new Address(rs.getInt("addresscode"), rs.getString("city"), rs.getString("street"), rs.getInt("housenum"));
            Patient p = new Patient(rs.getString("id"), rs.getString("name"), address, rs.getString("gender"), rs.getDate("birthdate"), rs.getString("contactno"),null,null,null,null);
            list.add(p);
        }

        System.out.println(list);

        ps.close();
        rs.close();

        return list;
    }

    public void removePatientByID(String id, int addressCode) throws SQLException {
        String sql1 = "DELETE FROM patient WHERE patient.id=? ";
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

    public int getCount() throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from patient";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }


    public ArrayList<String> PatientPDF() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select * from address , person , patient where address.addresscode = person.address and person.id = patient.id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String str="";
        while (rs.next()) {
            Address address = new Address(rs.getInt("addresscode"), rs.getString("city"), rs.getString("street"), rs.getInt("housenum"));
            Patient p = new Patient(rs.getString("id"), rs.getString("name"), address, rs.getString("gender"), rs.getDate("birthdate"), rs.getString("contactno"),null,null,null,null);
            str=p.getID()+"  "+p.getName()+"  "+p.getAddress().getCity()+"|"+p.getAddress().getStreet()+"|"+p.getAddress().getHouseNum()+"  "+p.getGender()+"  "+p.getDate()+"  "+p.getContactNo();
            list.add(str);
        }

        ps.close();
        rs.close();

        return list;
    }

    public ArrayList<Patient> selectAll() throws SQLException {
        ArrayList<Patient> list = new ArrayList<Patient>();

        String sql = "select * from address , person , patient where address.addresscode = person.address and person.id = patient.id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Address address = new Address(rs.getInt("addresscode"), rs.getString("city"), rs.getString("street"), rs.getInt("housenum"));
            Patient p = new Patient(rs.getString("id"), rs.getString("name"), address, rs.getString("gender"), rs.getDate("birthdate"), rs.getString("contactno"),null,null,null,null);
            list.add(p);
        }


        ps.close();
        rs.close();

        return list;
    }

    public void UpdatePatient(Patient p) throws SQLException {


        String sql1 = "update person SET name = ?, contactno=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1, p.getName());
        ps.setString(2,p.getContactNo());
        ps.setString(3,p.getID());
        int rows = ps.executeUpdate();
        ps.close();


        String sql2 = "update address SET city = ? , street=? , housenum=?";
        ps = con.prepareStatement(sql2);

        ps.setString(1, p.getAddress().getCity());
        ps.setString(2, p.getAddress().getStreet());
        ps.setInt(3, p.getAddress().getHouseNum());

        rows = ps.executeUpdate();
        ps.close();


    }

}