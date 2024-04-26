package dao;

import connectDB.ConnectDB;
import entity.MatHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author 84343
 */
public class MatHang_DAO {

    private ArrayList<MatHang> ls;

    public MatHang_DAO() {
        ls = new ArrayList<>();
    }

    public ArrayList<MatHang> getalltbMatHang() {
        ArrayList<MatHang> dsmh = new ArrayList<MatHang>();
        try {
            ConnectDB db = ConnectDB.getInstance();
            db.connect();
            Connection con = db.getConnection();
            String sql = "SELECT *FROM MatHang";
            Statement statement = con.createStatement();

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maMH = rs.getString(1);
                String tenMH = rs.getString(2);
                Double gia = rs.getDouble(3);
                Boolean trangThai = rs.getBoolean(4);
                MatHang mh = new MatHang(maMH, tenMH, gia, trangThai);
                dsmh.add(mh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsmh;

    }

    public int traVeViTri(MatHang mh) {
        return ls.indexOf(mh);
    }

    public ArrayList<MatHang> getMatHangTheoMaMH(String id) {
        ArrayList<MatHang> dsmh = new ArrayList<MatHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT *FROM MatHang WHERE maMH=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maMH = rs.getString(1);
                String tenMH = rs.getString(2);
                Double gia = rs.getDouble(3);
                Boolean trangThai = rs.getBoolean(4);
                MatHang mh = new MatHang(maMH, tenMH, gia, trangThai);
                dsmh.add(mh);
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
        return dsmh;
    }

    // Thêm mặt hàng
    public boolean addMatHang(MatHang mh) {
        ConnectDB db = ConnectDB.getInstance();
        Connection con = db.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("INSERT INTO MatHang(maMH, tenMH, Gia, trangThai) VALUES (?, ?, ?, ?)");
//               stmt = con.prepareStatement("INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES (?, ?, ?, ?)");
            stmt.setString(1, mh.getMaMH());
            stmt.setString(2, mh.getTenMH());
            stmt.setDouble(3, mh.getGia());
            stmt.setBoolean(4, mh.isTrangThai());
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

    // Xóa mặt hàng
    public boolean DeleteMatHang(String maMH) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("delete MatHang from MatHang where maMH=?");
            stmt.setString(1, maMH);
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    //    Tìm mặt hàng
    public MatHang findMatHang(String maTim) {
        MatHang mh = null;
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "Select *from MatHang where maMH=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTim);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maMH = rs.getString(1);
                String tenMH = rs.getString(2);
                Double gia = rs.getDouble(3);
                Boolean tinhTrang = rs.getBoolean(4);
                mh = new MatHang(maMH, tenMH, gia, tinhTrang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mh;
    }

    // Sửa mặt hàng
    public boolean editMatHang(MatHang mh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update MatHang set TenMH=?, Gia=?, trangThai=? where maMH=?");
            stmt.setString(1, mh.getTenMH());
            stmt.setDouble(2, mh.getGia());
            stmt.setBoolean(3, mh.isTrangThai());
            stmt.setString(4, mh.getMaMH());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static final String TABLE_NAME = "MatHang";
    public static final String COLUMN_MatHang = "SoMatHang";

    public int getTongSoMH() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT COUNT(*) AS SoMatHang FROM " + TABLE_NAME + " ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(COLUMN_MatHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param nam
     * @return Danh sách mặt hàng theo năm
     */
    public ArrayList<String[]> getMHNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<String[]> list = new ArrayList<>();
        try {
            String sql = "SELECT sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                    + "ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                    + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                    + "JOIN HoaDon o ON od.maHD = o.maHD\n"
                    + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                    + "WHERE YEAR(o.ngayLapHD) = ? "
                    + "GROUP BY sp.maMH, sp.tenMH\n"
                    + "ORDER BY sp.maMH ASC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nam);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] arr = new String[4];
                arr[0] = rs.getString("MaMH");
                arr[1] = rs.getString("TenMH");
                arr[2] = rs.getString("SoLuong");
                arr[3] = rs.getString("TongTien");

                list.add(arr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static final String COLUMN_TONG_TIEN_MH_NAM = "TongTatCaTien";

    /**
     * @param nam
     * @return Tổng tiền mặt hàng theo năm
     */
    public int getTongTienNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT SUM(TongTien) AS TongTatCaTien\n"
                    + "FROM\n"
                    + "(\n"
                    + "    SELECT ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                    + "    FROM [dbo].[ChiTietHoaDonDV] od\n"
                    + "    JOIN HoaDon o ON od.maHD = o.maHD\n"
                    + "    JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                    + "    WHERE YEAR(o.ngayLapHD) = ? \n"
                    + "    GROUP BY sp.maMH, sp.tenMH\n"
                    + ") AS Tong";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_TONG_TIEN_MH_NAM);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param thang
     * @param nam
     * @return Danh sách mặt hàng theo tháng
     */
    public ArrayList<String[]> getMHThang(String thang, String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<String[]> list = new ArrayList<>();
        try {
            String sql = "SELECT sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                    + "ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                    + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                    + "JOIN HoaDon o ON od.maHD = o.maHD\n"
                    + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                    + "WHERE MONTH(o.ngayLapHD) = ? AND YEAR(o.ngayLapHD) = ? "
                    + "GROUP BY sp.maMH, sp.tenMH\n"
                    + "ORDER BY sp.maMH ASC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, thang);
            stmt.setString(2, nam);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] arr = new String[4];
                arr[0] = rs.getString("MaMH");
                arr[1] = rs.getString("TenMH");
                arr[2] = rs.getString("SoLuong");
                arr[3] = rs.getString("TongTien");
                list.add(arr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static final String COLUMN_TONG_TIEN_MH_THANG = "TongTatCaTienThang";

    /**
     * @param thang
     * @param nam
     * @return Tổng tiền mặt hàng theo tháng
     */
    public int getTongTienThang(String thang, String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT SUM(TongTien) AS TongTatCaTienThang\n"
                    + "FROM\n"
                    + "(\n"
                    + "    SELECT ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                    + "    FROM [dbo].[ChiTietHoaDonDV] od\n"
                    + "    JOIN HoaDon o ON od.maHD = o.maHD\n"
                    + "    JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                    + "    WHERE MONTH(o.ngayLapHD) = ? AND YEAR(o.ngayLapHD) = ? \n"
                    + "    GROUP BY sp.maMH, sp.tenMH\n"
                    + ") AS Tong";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, thang);
            stmt.setString(2, nam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_TONG_TIEN_MH_THANG);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String[]> getMHNgay(Date ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<String[]> list = new ArrayList<>();
        try {
            String sql = "SELECT sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                    + "ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                    + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                    + "JOIN HoaDon o ON od.maHD = o.maHD\n"
                    + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                    + "WHERE (o.ngayLapHD) = ? "
                    + "GROUP BY sp.maMH, sp.tenMH\n"
                    + "ORDER BY sp.maMH ASC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ngay.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] arr = new String[4];
                arr[0] = rs.getString("MaMH");
                arr[1] = rs.getString("TenMH");
                arr[2] = rs.getString("SoLuong");
                arr[3] = rs.getString("TongTien");
                list.add(arr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static final String COLUMN_TONG_TIEN_MH_NGAY = "TongTatCaTienNgay";

    public int getTongTienNgay(Date ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT SUM(TongTien) AS TongTatCaTienNgay\n"
                    + "FROM\n"
                    + "(\n"
                    + "    SELECT ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                    + "    FROM [dbo].[ChiTietHoaDonDV] od\n"
                    + "    JOIN HoaDon o ON od.maHD = o.maHD\n"
                    + "    JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                    + "    WHERE (o.ngayLapHD) = ?  \n"
                    + "    GROUP BY sp.maMH, sp.tenMH\n"
                    + ") AS Tong";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ngay.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_TONG_TIEN_MH_NGAY);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
