package dao;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author HO MINH HAU
 */
public class HoaDon_DAO {

    public HoaDon_DAO() {

    }

    /**
     * Lấy tất cả hóa đơn
     *
     * @return dsHD
     */
    public ArrayList<HoaDon> getalltbHoaDon() {
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT *FROM HoaDon";
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString(1);
                java.sql.Date ngayLapHD_sql = rs.getDate(2);
                LocalDate ngayLapHD = ngayLapHD_sql.toLocalDate();
                String maKH = rs.getString(3);
                String maNV = rs.getString(4);
                String maKM = rs.getString(5);
                double tongTien = rs.getDouble(6);
                HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), new KhuyenMai(maKM), tongTien);
                dsHD.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    public boolean createHoaDon(HoaDon hd) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO HoaDon VALUES(?,?,?,?,?,?)");
            stmt.setString(1, hd.getMaHD());
            stmt.setDate(2, Date.valueOf(hd.getNgayLapHD()));
            stmt.setString(3, hd.getKhachHang().getMaKH());
            stmt.setString(4, hd.getNhanVien().getMaNV());
            stmt.setString(5, hd.getMaKM().getMaKM());
            stmt.setDouble(6, hd.getTongTien());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    /**
     * Hóa đơn theo mã hóa đơn
     *
     * @param maHD
     * @return hoadon
     */
    public HoaDon getHoaDonTheoMaHD(String maHD) {
        HoaDon hoaDon = null;

        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM HoaDon WHERE maHD = ?")) {
            stmt.setString(1, maHD);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String maHoaDon = rs.getString("maHD");
                    LocalDate ngayLapHD = rs.getDate("ngayLapHD").toLocalDate();
                    String maKH = rs.getString("maKH");
                    String maNV = rs.getString("maNV");
                    double tongTien = rs.getDouble("tongTien");
                    hoaDon = new HoaDon(maHoaDon, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), tongTien);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    // update tongtien cua hoa don bằng maHD
    public boolean updateTongTien(String maHD, Double tongTien, String maKM) {

        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE HoaDon SET tongTien = ?,maKM = ? WHERE maHD = ?");
            stmt.setDouble(1, tongTien);
            stmt.setString(2, maKM);
            stmt.setString(3, maHD);

            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static final String TABLE_NAME = "HoaDon";
    public static final String COLUMN_SO_LUONG = "SoLuong";
    public static final String COLUMN_SO_LUONG_HOA_DON = "SoLuongHoaDon";

    public int getSoLuongHoaDon() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT COUNT(*) AS SoLuongHoaDon FROM " + TABLE_NAME;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(COLUMN_SO_LUONG_HOA_DON);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tìm hóa đơn theo mã khách hàng getHoaDonTheoMaKH
    public ArrayList<HoaDon> getHoaDonTheoMaKH(String maKH) {

        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT *FROM HoaDon WHERE maKH = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                java.sql.Date ngayLapHD_sql = rs.getDate(2);
                LocalDate ngayLapHD = ngayLapHD_sql.toLocalDate();
                String maKH1 = rs.getString(3);
                String maNV = rs.getString(4);
                String maKM = rs.getString(5);
                double tongTien = rs.getDouble(6);
                HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH1), new NhanVien(maNV), new KhuyenMai(maKM), tongTien);
                dsHD.add(hd);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    /**
     * Số lượng hóa đơn theo ngày
     *
     * @param ngay
     * @return Số lượng hóa đơn theo ngày
     */
    public static final String COLUMN_SO_LUONG_HOA_DON_NGAY = "SoLuongHoaDonNgay";

    public int getSoLuongHoaDonNgay(Date ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT COUNT(*) AS SoLuongHoaDonNgay FROM [dbo].[HoaDon] WHERE (ngayLapHD) = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ngay.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_SO_LUONG_HOA_DON_NGAY);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Tổng tiền hóa đơn theo ngày
     *
     * @param ngay
     * @return Tổng tiền hóa đơn theo ngày
     */
    public static final String COLUMN_TONG_TIEN = "TongTien";

    public int getTongTienNgay(Date ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "  SELECT SUM([tongTien]) AS TongTien FROM [dbo].[HoaDon] WHERE (ngayLapHD) = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ngay.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_TONG_TIEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<HoaDon> getDoanhThuTungThangTrongNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            String sql = "	SELECT\n"
                    + "    MONTH(ngayLapHD) AS Thang,\n"
                    + "    SUM(tongTien) AS TongTien\n"
                    + "FROM HoaDon\n"
                    + "WHERE YEAR(ngayLapHD) = " + nam
                    + "GROUP BY MONTH(ngayLapHD)\n"
                    + "ORDER BY Thang ASC;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString(1);
                java.sql.Date ngayLapHD_sql = rs.getDate(2);
                LocalDate ngayLapHD = ngayLapHD_sql.toLocalDate();
                String maKH = rs.getString(3);
                String maNV = rs.getString(4);
                double tongTien = rs.getDouble(5);
                HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), tongTien);
                dsHD.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    /**
     * Tổng doanh thu của năm
     *
     * @param nam
     * @return Tổng doanh thu của năm (Không cần xuất)
     */
    public ArrayList<HoaDon> getDoanhThuNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            String sql = "SELECT\n"
                    + "  maHD,\n"
                    + "    ngayLapHD,\n"
                    + "    maKH,\n"
                    + "    maNV,\n"
                    + "    maKM,\n"
                    + "    tongTien\n"
                    + "\n"
                    + "FROM HoaDon\n"
                    + "WHERE YEAR(ngayLapHD) = " + nam;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString(1);
                java.sql.Date ngayLapHD_sql = rs.getDate(2);
                LocalDate ngayLapHD = ngayLapHD_sql.toLocalDate();
                String maKH = rs.getString(3);
                String maNV = rs.getString(4);
                String maKM = rs.getString(5);
                double tongTien = rs.getDouble(6);
                HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), new KhuyenMai(maKM), tongTien);
                dsHD.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    /**
     * Doanh thu theo ngày
     *
     * @param ngay
     * @return Doanh thu theo ngày, xuất hóa đơn theo ngày
     */
    public ArrayList<HoaDon> getDoanhThuNgay(Date ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            String sql = "SELECT maHD, ngayLapHD, maKH, maNV, maKM, tongTien\n"
                    + "FROM HoaDon\n"
                    + "WHERE (ngayLapHD) = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ngay.toString());
            ResultSet rs = stmt.executeQuery();

            // Iterate through the result set and create HoaDon objects for each invoice
            while (rs.next()) {
                String maHD = rs.getString(1);
                java.sql.Date ngayLapHD_sql = rs.getDate(2);
                LocalDate ngayLapHD = ngayLapHD_sql.toLocalDate();
                String maKH = rs.getString(3);
                String maNV = rs.getString(4);
                String maKM = rs.getString(5);
                double tongTien = rs.getDouble(6);
                HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), new KhuyenMai(maKM), tongTien);
                dsHD.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    /**
     * Doanh thu theo tháng
     *
     * @param nam
     * @return Doanh thu theo tháng, xuất hóa đơn theo tháng
     */
    public ArrayList<String[]> getDoanhThuTungThangNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<String[]> list = new ArrayList<>();
        try {
            String sql = "SELECT months.month AS Thang, COALESCE(SUM(HoaDon.tongTien), 0) AS TongTien\n"
                    + "	FROM (\n"
                    + "	SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL\n"
                    + "	SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL\n"
                    + "	SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12) AS months\n"
                    + "	LEFT JOIN HoaDon ON months.month = MONTH(HoaDon.ngayLapHD) AND YEAR(HoaDon.ngayLapHD) = " + nam + "	GROUP BY months.month;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] arr = new String[2];
                arr[0] = rs.getString("Thang");
                arr[1] = rs.getString("TongTien");
                list.add(arr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param thang
     * @param nam
     * @return Xuất danh sách hóa đơn, Doanh thu của tháng trên từng hóa đơn
     * (đang bị lỗi jdbc)
     */
    public ArrayList<HoaDon> getDoanhThuThang(String thang, String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            String sql = "SELECT maHD, ngayLapHD, maKH, maNV, maKM, tongTien  FROM HoaDon WHERE MONTH(ngayLapHD) = " + thang + " AND YEAR(ngayLapHD) = " + nam + " ;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                java.sql.Date ngayLapHD_sql = rs.getDate(2);
                LocalDate ngayLapHD = ngayLapHD_sql.toLocalDate();
                String maKH = rs.getString(3);
                String maNV = rs.getString(4);
                String maKM = rs.getString(5);
                double tongTien = rs.getDouble(6);
                HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), new KhuyenMai(maKM), tongTien);
                dsHD.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    /**
     * Tổng tiền hóa đơn theo tháng
     *
     * @param thang
     * @param nam
     * @return
     */
    public static final String COLUMN_TONG_TIEN_HOA_DON_THANG = "TongTienHoaDonThang";

    public int getTongTienThang(String thang, String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "  SELECT SUM([tongTien]) AS TongTienHoaDonThang FROM [dbo].[HoaDon] WHERE MONTH([ngayLapHD]) = " + thang + " AND YEAR([ngayLapHD]) =  " + nam;
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_TONG_TIEN_HOA_DON_THANG);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static final String COLUMN_SO_LUONG_HOA_DON_THANG = "SoLuongHoaDonThang";

    public int getSoLuongHoaDonThang(String thang, String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT COUNT(*) AS SoLuongHoaDonThang FROM [dbo].[HoaDon] WHERE MONTH([ngayLapHD]) = " + thang + " AND YEAR([ngayLapHD]) =  " + nam;
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_SO_LUONG_HOA_DON_THANG);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static final String COLUMN_SO_LUONG_HOA_DON_NAM = "SoLuongHoaDonNam";

    public int getSoLuongHoaDonNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT COUNT(*) AS SoLuongHoaDonNam FROM [dbo].[HoaDon] WHERE YEAR([ngayLapHD]) =  " + nam;
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_SO_LUONG_HOA_DON_NAM);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static final String COLUMN_TONG_TIEN_HOA_DON_NAM = "TongTienHoaDonNam";

    public int getTongTienNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "  SELECT SUM([tongTien]) AS TongTienHoaDonNam FROM [dbo].[HoaDon] WHERE YEAR([ngayLapHD]) =  " + nam;
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(COLUMN_TONG_TIEN_HOA_DON_NAM);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
