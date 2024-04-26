package dao;

import connectDB.ConnectDB;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.PhongHat;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DonDatPhong_DAO {
    public DonDatPhong_DAO() {

    }

    //get all
    public ArrayList<DonDatPhong> getalltbDonDatPhong() {
        ArrayList<DonDatPhong> dsDonDatPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonDatPhong";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDonDatPhong = rs.getString(1);
                LocalDateTime ngayDat = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime ngayNhan = rs.getTimestamp(3).toLocalDateTime();
                String maPhong = rs.getString(4);
                String maKH = rs.getString(5);
                DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDat, ngayNhan, new PhongHat(maPhong), new KhachHang(maKH));
                dsDonDatPhong.add(ddp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonDatPhong;
    }

    //create
    public boolean createDonDatPhong(DonDatPhong ddp) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("INSERT INTO DonDatPhong VALUES (?, ?, ?, ?, ? )");
            stmt.setString(1, ddp.getMaDonDatPhong());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(ddp.getNgayDat()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(ddp.getNgayNhan()));
            stmt.setString(4, ddp.getPhongHat().getMaPhong());
            stmt.setString(5, ddp.getKhachHang().getMaKH());
            n = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;

    }

    //getDonDatPhongTheoMaKH
    public ArrayList<DonDatPhong> getDonDatPhongTheoMaKH(String maKH) {
        ArrayList<DonDatPhong> dsDonDatPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonDatPhong WHERE maKH = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDonDatPhong = rs.getString(1);
                LocalDateTime ngayDat = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime ngayNhan = rs.getTimestamp(3).toLocalDateTime();
                String maPhong = rs.getString(4);
                String maKH1 = rs.getString(5);
                DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDat, ngayNhan, new PhongHat(maPhong), new KhachHang(maKH1));
                dsDonDatPhong.add(ddp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonDatPhong;
    }

    // lấy đơn đặt phòng theo ngày nhận phòng
    public ArrayList<DonDatPhong> getDonDatPhongTheoNgayNhanPhong(LocalDate ngayNhanPhong) {
        ArrayList<DonDatPhong> dsDonDatPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonDatPhong WHERE CAST(ngayNhan AS DATE) = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(ngayNhanPhong));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDonDatPhong = rs.getString(1);
                LocalDateTime ngayDat = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime ngayNhan = rs.getTimestamp(3).toLocalDateTime();
                String maPhong = rs.getString(4);
                String maKH = rs.getString(5);
                DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDat, ngayNhan, new PhongHat(maPhong), new KhachHang(maKH));
                dsDonDatPhong.add(ddp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonDatPhong;
    }

    // tìm theo maDDP , maKH , maPhong

    public ArrayList<DonDatPhong> timDonDatPhong(String maDDP, String maKH, String maPhong) {
        ArrayList<DonDatPhong> dsDonDatPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonDatPhong WHERE maDonDatPhong LIKE ? AND maKH LIKE ? AND maPhong LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + maDDP + "%");
            statement.setString(2, "%" + maKH + "%");
            statement.setString(3, "%" + maPhong + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDonDatPhong = rs.getString(1);
                LocalDateTime ngayDat = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime ngayNhan = rs.getTimestamp(3).toLocalDateTime();
                String maPhong1 = rs.getString(4);
                String maKH1 = rs.getString(5);
                DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDat, ngayNhan, new PhongHat(maPhong1), new KhachHang(maKH1));
                dsDonDatPhong.add(ddp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonDatPhong;

    }

    // delete
    public boolean deleteDonDatPhong(String maDDP, String maPhong) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("DELETE FROM DonDatPhong WHERE maDonDatPhong = ? AND maPhong = ? ");
            stmt.setString(1, maDDP);
            stmt.setString(2, maPhong);
            n = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }

}
