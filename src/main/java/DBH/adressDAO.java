package DBH;

import Model.Address;
import Model.Therapist;
import Util.DatabaseConnector;
import Util.JPQLHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class adressDAO  {

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

    public void updateAddress(Therapist t) throws SQLException {

    }

}
