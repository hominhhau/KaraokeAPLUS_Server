package dao;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import entity.NhanVien;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author HO MINH HAU
 */
public class NhanVien_DAO {
    private TaiKhoan_DAO tk_dao;

    public NhanVien_DAO() {
        tk_dao = new TaiKhoan_DAO();
    }

    public ArrayList<NhanVien> getalltbNhanVien() {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT *FROM NhanVien";
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                Boolean gioiTinh = rs.getBoolean(3);
                String cccd = rs.getString(4);
                String sdt = rs.getString(5);
                String diaChi = rs.getString(6);
                String caLam = rs.getString(7);
                String loaiNV = rs.getString(8);
                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, cccd, sdt, diaChi, caLam, new LoaiNhanVien(loaiNV));
                dsNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNV;
    }

    public ArrayList<NhanVien> getNhanVienTheoMaNV(String id) {
        ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "Select * from NhanVien where maNV = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery();
            // Duyệt trên kết quả trả về
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                Boolean gioiTinh = rs.getBoolean(3);
                String cccd = rs.getString(4);
                String sdt = rs.getString(5);
                String diaChi = rs.getString(6);
                String caLam = rs.getString(7);
                String loaiNV = rs.getString(8);
                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, cccd, sdt, diaChi, caLam, new LoaiNhanVien(loaiNV));
                dsnv.add(nv);
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
        return dsnv;
    }

    public ArrayList<NhanVien> getNVTheoLoai(LoaiNhanVien lnv) {
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        PreparedStatement sta = null;

        try {
            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT *FROM NhanVien nv join LoaiNhanVien lnv on nv.maLoai = lnv.maLoai where lnv.maLoai=?";
            String sql = "SELECT *FROM NhanVien WHERE loaiNV=?";
            sta = con.prepareStatement(sql);
            sta.setString(1, lnv.getMaLoai());

            ResultSet rs = sta.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                Boolean gioiTinh = rs.getBoolean(3);
                String cccd = rs.getString(4);
                String sdt = rs.getString(5);
                String diaChi = rs.getString(6);
                String caLam = rs.getString(7);
                String loaiNV = rs.getString(8);

                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, cccd, sdt, diaChi, caLam, new LoaiNhanVien(loaiNV));
                ds.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

//     public ArrayList<NhanVien> getEmployeesByLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
//        ArrayList<NhanVien> employees = new ArrayList<>();
//
//        // SQL query to retrieve employees based on LoaiNhanVien
//        String sql = "SELECT * FROM nhanvien WHERE maLoai = ?";
// 
//        Connection con = ConnectDB.getInstance().getConnection();
//        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
//            preparedStatement.setString(1, loaiNhanVien.getMaLoai());
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    String maNV = resultSet.getString("maNV");
//                    String tenNV = resultSet.getString("tenNV");
//                    boolean gioiTinh = resultSet.getBoolean("gioiTinh");
//                    String CCCD = resultSet.getString("CCCD");
//                    String SDT = resultSet.getString("SDT");
//                    String diaChi = resultSet.getString("diaChi");
//                    String caLam = resultSet.getString("caLam");
//
//                    // Assuming LoaiNhanVien has a constructor that takes the type as a parameter
//                    NhanVien nhanVien = new NhanVien(maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNhanVien);
//
//                    employees.add(nhanVien);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception according to your application's needs
//        }
//
//        return employees;
//    }

    public boolean addStaff(NhanVien nv) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
//            stmt = con.prepareStatement("INSERT INTO NhanVien VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt = con.prepareStatement("INSERT INTO nhanvien (maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
//            System.out.println("Xong lần 1");
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getTenNV());
            stmt.setBoolean(3, nv.isGioiTinh());
            stmt.setString(4, nv.getCCCD());
            stmt.setString(5, nv.getSDT());
            stmt.setString(6, nv.getDiaChi());
            stmt.setString(7, nv.getCaLam());
            stmt.setString(8, nv.getLoaiNV().getMaLoai());
//            System.out.println("Xong lần 2");
            n = stmt.executeUpdate();

            if (tk_dao.taoTK(nv.getMaNV()) == false) {
                JOptionPane.showInputDialog(null, "Tạo Tài khoant thất bại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return n > 0;
    }

    public boolean editStaff(NhanVien nv) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update NhanVien set tenNV= ?, gioiTinh= ?, CCCD= ?, SDT = ?, diaChi= ?, caLam= ?, loaiNV= ? WHERE maNV = ?");
//            System.out.println("UP Xongggg");
            stmt.setString(1, nv.getTenNV());
            stmt.setBoolean(2, nv.isGioiTinh());
            stmt.setString(3, nv.getCCCD());
            stmt.setString(4, nv.getSDT());
            stmt.setString(5, nv.getDiaChi());
            stmt.setString(6, nv.getCaLam());
            stmt.setString(7, nv.getLoaiNV().getMaLoai());
            stmt.setString(8, nv.getMaNV());
            n = stmt.executeUpdate();
//            System.out.println("Xongggg");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public NhanVien findStaff(String maTim) {
        NhanVien nv = null;
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT *FROM NhanVien WHERE maNV= ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTim);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                Boolean gioiTinh = rs.getBoolean(3);
                String cccd = rs.getString(4);
                String sdt = rs.getString(5);
                String diaChi = rs.getString(6);
                String caLam = rs.getString(7);
                String loaiNV = rs.getString(8);

                nv = new NhanVien(maNV, tenNV, gioiTinh, cccd, sdt, diaChi, caLam, new LoaiNhanVien(loaiNV));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

}
