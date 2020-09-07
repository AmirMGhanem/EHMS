package DBH;

import Model.Address;
import Util.DatabaseConnector;
import Util.JPQLHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class adressDAO implements JPQLHandler {

    static Connection con = DatabaseConnector.getConnection();

    public int insertAddress(Address address) throws SQLException {

        String sql = "insert into address(addresscode,city ,street,housenum) values(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, address.getAddresscode());
        ps.setString(2, address.getCity());
        ps.setString(3, address.getStreet());
        ps.setInt(4,address.getHouseNum());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;

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
