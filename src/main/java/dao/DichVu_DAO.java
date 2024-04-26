package dao;

import connectDB.ConnectDB;
import entity.DichVu;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 84343
 */
public class DichVu_DAO {

    private Statement statement;

    public DichVu_DAO() {

    }

    public ArrayList<DichVu> getalltbDichVu() {
        ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
        try {
            ConnectDB db = ConnectDB.getInstance();
            db.connect();
            Connection con = db.getConnection();
            String sql = "SELECT *FROM DichVu";
            statement = con.createStatement();

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maDV = rs.getString(1);
                String tenDV = rs.getString(2);
                String maMH = rs.getString(3);
//                String tenMH = rs.getString(4);
//                Double gia = rs.getDouble(4);
                DichVu dv = new DichVu(maDV, tenDV, maMH);

                dsDV.add(dv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsDV;

    }

    public ArrayList<DichVu> getDichVuTheoMaDV(String id) {
        ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT *FROM DichVu WHERE maDV=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maDV = rs.getString(1);
                String tenDV = rs.getString(2);
                String maMH = rs.getString(3);
                DichVu dv = new DichVu(maDV, tenDV, maMH);
                dsDV.add(dv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsDV;
    }

    // Thêm dịch vụ
    public boolean addDichVu(DichVu dv) {
        ConnectDB db = ConnectDB.getInstance();
        Connection con = db.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("insert into DichVu(maDV, tenDV) values (?, ?)");
            stmt.setString(2, dv.getTenDV());
            stmt.setString(1, dv.getMaDV());
//            stmt.setString(3, dv.getMaMH());

            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }

    // Xóa dịch vụ
    public boolean DeleteDichVu(String maDV) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("Delete DichVu from DichVu where maDV =?");
            stmt.setString(1, maDV);
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    //    Tìm dịch vụ
    public DichVu findDichVu(String maTim) {
        DichVu dv = null;
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "Select *from DichVu where maDV=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTim);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maDV = rs.getString(1);
                String tenDV = rs.getString(2);
                String maMH = rs.getString(3);
                dv = new DichVu(maDV, tenDV, maMH);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dv;
    }

    // Sửa dịch vụ
    public boolean editDichVu(DichVu dv) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update " + "DichVu set TenDV=? where maDV=?");
            stmt.setString(1, dv.getTenDV());
            stmt.setString(2, dv.getMaDV());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }

}
