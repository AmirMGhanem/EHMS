package DBH;

import Model.Address;
import Model.Therapist;
import Model.UserInfo;
import Model.WorkSchedule;
import Util.DatabaseConnector;
import org.hibernate.jdbc.Work;

import java.sql.*;
import java.util.ArrayList;

public class workScheduleDAO {

    static Connection con = DatabaseConnector.getConnection();
    ArrayList<Therapist> therapistArrayList = new ArrayList<Therapist>();
    DBH.therapistDAO tdao = new therapistDAO();

    public void insertWorkSched(String id, ArrayList<WorkSchedule> workScheduleArrayList) throws SQLException {

        for (WorkSchedule ws : workScheduleArrayList) {
            String sql = "insert into workschedule(therapistid,day,shift ) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, ws.getDay());
            ps.setInt(3, ws.getShift());
            int rows = ps.executeUpdate();
            ps.close();
        }
    }


    public int insertSpecificDayWorkSched(String id, WorkSchedule workSchedule) throws SQLException {

        String sql = "insert into workschedule(therapistid,day,shift ) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, workSchedule.getDay());
        ps.setInt(3, workSchedule.getShift());
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

    public ArrayList<WorkSchedule> SelectAll() throws SQLException {
        ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();
        String sql = "select * from address , person , therapist , workschedule where address.addresscode = person.address and person.id = therapist.id and workschedule.therapistid = person.id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        while (rs.next()) {
            Address address = new Address(rs.getInt("addresscode"), rs.getString("city"), rs.getString("street"), rs.getInt("housenum"));
            Therapist t = new Therapist(rs.getString("id"), rs.getString("name"), address, rs.getString("gender"), rs.getDate("birthdate"), rs.getString("contactno"), rs.getDate("dateworkstart"), null);
            WorkSchedule ws = new WorkSchedule(t, rs.getString("day"), rs.getInt("shift"));
            workScheduleArrayList.add(ws);
        }


        ps.close();
        rs.close();
        return workScheduleArrayList;
    }

    public ArrayList<WorkSchedule> SelectAllWS() throws SQLException {
        ArrayList<Therapist> therapistArrayList = new ArrayList<Therapist>();
        DBH.therapistDAO tdao = new therapistDAO();
        ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();
        String sql = "select * from workschedule";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        therapistArrayList = tdao.selectAll();
        while (rs.next()) {
            WorkSchedule ws = new WorkSchedule(rs.getString("day"), rs.getInt("shift"));
            for (Therapist t : therapistArrayList)
                if (t.getID().equals(rs.getString("therapistid")))
                    ws.setTherapist(t);
            workScheduleArrayList.add(ws);
        }
        ps.close();
        rs.close();
        return workScheduleArrayList;
    }


    public ArrayList<WorkSchedule> SelectAllByID(String therapistid) throws SQLException {

        ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();
        String sql = "select * from workschedule";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            if (therapistid.equals(rs.getString("therapistid"))) {
                WorkSchedule ws = new WorkSchedule(rs.getString("day"), rs.getInt("shift"));
                workScheduleArrayList.add(ws);
            }
        }

        ps.close();
        rs.close();
        return workScheduleArrayList;
    }

    public void updateWorkSched(String id, WorkSchedule ws) throws SQLException {
        String sql = "update workschedule set shift=? where therapistid=? and day=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ws.getShift());
        ps.setString(2, id);
        ps.setString(3, ws.getDay());
        ps.executeUpdate();
        ps.close();
    }

    public void removeWorkSceduleByTherapist(Therapist t) throws SQLException {
        String sql1 = "DELETE FROM workschedule WHERE therapistid=? ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1, t.getID());
        ps.executeUpdate();
        ps.close();
    }

    public void removeWorkSceduleByDay(String day) throws SQLException {
        String sql1 = "DELETE FROM workschedule WHERE day=? ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1, day);
        ps.executeUpdate();
        ps.close();
    }

    public void removeWorkSceduleByShift(String day, int shift) throws SQLException {
        String sql1 = "DELETE FROM workschedule WHERE day=? and shift=? ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1, day);
        ps.setInt(2, shift);
        ps.executeUpdate();
        ps.close();
    }

    public void removeAll() throws SQLException {
        String sql1 = "DELETE FROM workschedule ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.executeUpdate();
        ps.close();
    }
}