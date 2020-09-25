package DBH;

import Model.Address;
import Model.Allergy;
import Model.Medicine;
import Model.Therapist;
import Util.DatabaseConnector;
import Util.JPQLHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public ArrayList<Address> SelectAll() throws SQLException {
        ArrayList<Address> list = new ArrayList<Address>();
        String sql = "select * from address";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Address a = new Address(rs.getInt("addresscode"),rs.getString("city"),rs.getString("street"),rs.getInt("housenum"));
            list.add(a);
        }

        ps.close();
        rs.close();
        return list;
    }


    public void updateAddress(Therapist t) throws SQLException {

    }

}
