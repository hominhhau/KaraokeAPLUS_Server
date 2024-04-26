package dao;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.PhongHat;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author HO MINH HAU
 */
public class PhongHat_DAO {

    private ArrayList<PhongHat> ls;

    public PhongHat_DAO() {
        ls = new ArrayList<>();
    }

    public ArrayList<PhongHat> getAllPhongHat() {
        ArrayList<PhongHat> dsPH = new ArrayList<PhongHat>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhongHat";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String loaiPhong = rs.getString(3);
                String tinhTrang = rs.getString(4);
                PhongHat ph = new PhongHat(maPhong, tenPhong, new LoaiPhong(loaiPhong), tinhTrang);
                dsPH.add(ph);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPH;

    }

    public ArrayList<PhongHat> getPhongByTinhTrang(String tinhTrang) {
        ArrayList<PhongHat> dsPH = new ArrayList<PhongHat>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhongHat WHERE tinhTrangPhong = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, tinhTrang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String loaiPhong = rs.getString(3);
                String trangThai = rs.getString(4);

                PhongHat ph = new PhongHat(maPhong, tenPhong, new LoaiPhong(loaiPhong), tinhTrang);
                dsPH.add(ph);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPH;
    }

    public boolean updateTinhTrangPhong(String maPhong, String tinhTrangMoi) {
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE PhongHat SET tinhTrangPhong = ? WHERE maPhong = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, tinhTrangMoi);
            statement.setString(2, maPhong);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //find phong by maPhong
    public PhongHat getPhongHatByMaPhong(String maPhong) {
        PhongHat ph = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhongHat WHERE maPhong = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maPhong);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String tenPhong = rs.getString(2);
                String loaiPhong = rs.getString(3);
                String tinhTrang = rs.getString(4);
                ph = new PhongHat(maPhong, tenPhong, new LoaiPhong(loaiPhong), tinhTrang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ph;
    }

    public boolean editPhongHat(PhongHat ph) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update PhongHat set tenPhong=?, maLoaiPhong=?, tinhTrangPhong=? where maPhong=?");
            stmt.setString(1, ph.getTenPhong());
            stmt.setString(2, ph.getLoaiPhong().getMaLoaiPhong());
            stmt.setString(3, ph.getTinhTrangPhong());
            stmt.setString(4, ph.getMaPhong());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean DeletePhongHat(String maPH) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("delete PhongHat from PhongHat where maPhong=?");
            stmt.setString(1, maPH);
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<PhongHat> FindTheoMaLoai(String id) {
        ArrayList<PhongHat> dsPH = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT *FROM PhongHat WHERE maLoaiPhong = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String loaiPhong = rs.getString(3);
                String trangThai = rs.getString(4);
                PhongHat ph = new PhongHat(maPhong, tenPhong, new LoaiPhong(loaiPhong), trangThai);
                dsPH.add(ph);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPH;
    }

    public static final String TABLE_NAME = "PhongHat";
    public static final String COLUMN_TINH_TRANG_PHONG = "tinhTrangPhong";
    public static final String COLUMN_SO_PHONG_TRONG = "SoPhongTrong";
    public static final String COLUMN_SO_PHONG = "SoPhong";

    public int getSoPhongTrong() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT COUNT(*) AS SoPhongTrong FROM " + TABLE_NAME + " WHERE " + COLUMN_TINH_TRANG_PHONG + " = 'Trong'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(COLUMN_SO_PHONG_TRONG);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTongSoPhong() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT COUNT(*) AS SoPhong FROM " + TABLE_NAME + " ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(COLUMN_SO_PHONG);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addPhongHat(PhongHat ph) {
        ConnectDB db = ConnectDB.getInstance();
        Connection con = db.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("INSERT INTO PhongHat(maPhong, tenPhong, maLoaiPhong, tinhTrangPhong) VALUES (?, ?, ?, ?)");
            stmt.setString(1, ph.getMaPhong());
            stmt.setString(2, ph.getTenPhong());
            stmt.setString(3, ph.getLoaiPhong().getMaLoaiPhong());
            stmt.setString(4, ph.getTinhTrangPhong());
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
