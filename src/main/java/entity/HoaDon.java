package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "HoaDon")
@Inheritance(strategy = InheritanceType.JOINED)

@NamedQueries({
        @NamedQuery(name = "HoaDon.getalltbHoaDon", query = "SELECT hd FROM HoaDon hd"),
        @NamedQuery(name = "HoaDon.getHoaDonTheoMaHD", query = "SELECT hd FROM HoaDon hd WHERE hd.maHD = :maHD"),
        @NamedQuery(name = "HoaDon.updateTongTien", query = "UPDATE HoaDon hd SET hd.tongTien = :tongTien, hd.maKM = :maKM WHERE hd.maHD = :maHD"),
        @NamedQuery(name = "HoaDon.getSoLuongHoaDon", query = "SELECT COUNT(hd) FROM HoaDon hd"),
        @NamedQuery(name = "HoaDon.getHoaDonTheoMaKH", query = "SELECT hd FROM HoaDon hd WHERE hd.khachHang.maKH = :maKH"),
        @NamedQuery(name = "HoaDon.getSoLuongHoaDonNgay", query = "SELECT COUNT(hd) FROM HoaDon hd WHERE FUNCTION('YEAR', hd.ngayLapHD) = FUNCTION('YEAR', :ngay) AND FUNCTION('MONTH', hd.ngayLapHD) = FUNCTION('MONTH', :ngay) AND FUNCTION('DAY', hd.ngayLapHD) = FUNCTION('DAY', :ngay)"),
        @NamedQuery(name = "HoaDon.getTongTienNgay", query = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE FUNCTION('YEAR', hd.ngayLapHD) = FUNCTION('YEAR', :ngay) AND FUNCTION('MONTH', hd.ngayLapHD) = FUNCTION('MONTH', :ngay) AND FUNCTION('DAY', hd.ngayLapHD) = FUNCTION('DAY', :ngay)"),
        @NamedQuery(name = "HoaDon.getDoanhThuTungThangNam", query =
                "SELECT months.month AS Thang, COALESCE(SUM(h.tongTien), 0) AS TongTien " +
                "FROM (SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL " +
                "SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL " +
                "SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12) AS months " +
                "LEFT JOIN HoaDon h ON months.month = MONTH(h.ngayLapHD) AND YEAR(h.ngayLapHD) = :nam " +
                "GROUP BY months.month"),
        @NamedQuery(name = "HoaDon.getDoanhThuNam", query = "SELECT hd FROM HoaDon hd WHERE FUNCTION('YEAR', hd.ngayLapHD) = :nam"),
        @NamedQuery(name = "HoaDon.getDoanhThuNgay", query = "SELECT hd FROM HoaDon hd WHERE FUNCTION('YEAR', hd.ngayLapHD) = FUNCTION('YEAR', :ngay) AND FUNCTION('MONTH', hd.ngayLapHD) = FUNCTION('MONTH', :ngay) AND FUNCTION('DAY', hd.ngayLapHD) = FUNCTION('DAY', :ngay)"),
        @NamedQuery(name = "HoaDon.getDoanhThuTungThangTrongNam", query = "SELECT hd FROM HoaDon hd WHERE FUNCTION('YEAR', hd.ngayLapHD) = :nam"),
        @NamedQuery(name = "HoaDon.getDoanhThuThang", query = "SELECT hd FROM HoaDon hd WHERE FUNCTION('MONTH', hd.ngayLapHD) = :thang AND FUNCTION('YEAR', hd.ngayLapHD) = :nam"),
        @NamedQuery(name = "HoaDon.getTongTienThang", query = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE FUNCTION('MONTH', hd.ngayLapHD) = :thang AND FUNCTION('YEAR', hd.ngayLapHD) = :nam"),
        @NamedQuery(name = "HoaDon.getSoLuongHoaDonThang", query = "SELECT COUNT(hd) FROM HoaDon hd WHERE FUNCTION('MONTH', hd.ngayLapHD) = :thang AND FUNCTION('YEAR', hd.ngayLapHD) = :nam"),
        @NamedQuery(name = "HoaDon.getSoLuongHoaDonNam", query = "SELECT COUNT(hd) FROM HoaDon hd WHERE FUNCTION('YEAR', hd.ngayLapHD) = :nam"),
        @NamedQuery(name = "HoaDon.getTongTienNam", query = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE FUNCTION('YEAR', hd.ngayLapHD) = :nam"),

})
public class HoaDon implements  Serializable{
    @Id
    @Column(name = "maHD")
    private String maHD;
    @Column(name = "ngayLapHD")
    private LocalDate ngayLapHD;


    // Mối quan hệ n - 1 với KhachHang
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKH")
    private KhachHang khachHang;

    @Column(name = "tongTien")
    private double tongTien;

    // Mối quan hệ 1 - n với ChiTietHoaDonPhong
    @OneToMany(mappedBy = "hoaDon")
    private Set<ChiTietHoaDonPhong> dsChiTietHoaDonPhong;

    // Mối quan hệ 1 - n với ChiTietHoaDonDV
    @OneToMany(mappedBy = "hoaDon")
    private Set<ChiTietHoaDonDV> dsChiTietHoaDonDV;

    // Mối quan hệ 1 - n với khuyenMai
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKM")
    private KhuyenMai maKM;

    // Mối quan hệ 1- n với NhanVien
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNV")
    private NhanVien nhanVien;

    public HoaDon() {


    }


    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }


    public HoaDon(String maHD, LocalDate ngayLapHD, KhachHang khachHang, NhanVien nhanVien, KhuyenMai maKM, double tongTien) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.maKM = maKM;
        this.tongTien = tongTien;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.maHD);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHD, other.maHD);
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(LocalDate ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhuyenMai getMaKM() {
        return maKM;
    }

    public void setMaKM(KhuyenMai maKM) {
        this.maKM = maKM;
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    public HoaDon(String maHD, LocalDate ngayLapHD, KhachHang khachHang, NhanVien nhanVien, double tongTien) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", maKM=" + maKM + ", tongTien=" + tongTien + '}';
    }

}
