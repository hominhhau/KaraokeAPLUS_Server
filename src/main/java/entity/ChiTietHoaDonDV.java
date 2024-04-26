package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "ChiTietHoaDonDV")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(
                name = "getAllChiTietHoaDonDV",
                query = "SELECT c FROM ChiTietHoaDonDV c"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonDVByMaHD",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.hoaDon.maHD = :maHD"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonDVByMaMH",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.matHang.maMH = :maMH"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonDVByMaPhong",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.phongHat.maPhong = :maPhong"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonDVByMaHDAndMaMH",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.hoaDon.maHD = :maHD AND c.matHang.maMH = :maMH"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonDVByMaHDAndMaPhong",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.hoaDon.maHD = :maHD AND c.phongHat.maPhong = :maPhong"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonDVByMaMHAndMaPhong",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.matHang.maMH = :maMH AND c.phongHat.maPhong = :maPhong"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonDVByMaHDAndMaMHAndMaPhong",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.hoaDon.maHD = :maHD AND c.matHang.maMH = :maMH AND c.phongHat.maPhong = :maPhong"
        ),
        @NamedQuery(name ="deleteChiTietHoaDonDV"
                ,query = "DELETE FROM ChiTietHoaDonDV c WHERE c.hoaDon.maHD = :maHD AND c.matHang.maMH = :maMH"
        ),
        //findChiTietHoaDonDV(String maHD, String maMH)
        @NamedQuery(
                name = "findChiTietHoaDonDV",
                query = "SELECT c FROM ChiTietHoaDonDV c WHERE c.hoaDon.maHD = :maHD AND c.matHang.maMH = :maMH"
        )

})

public class ChiTietHoaDonDV implements Serializable{
    @Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHD")
    private HoaDon hoaDon;
    @Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maMH")
    private MatHang matHang;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhong")
    private PhongHat phongHat;
    @Column(name = "soLuong")
    private int soLuong;
    @Column(name = "gia")
    private Double gia;



    public ChiTietHoaDonDV() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ChiTietHoaDonDV(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietHoaDonDV(HoaDon hoaDon, MatHang matHang, PhongHat phongHat, int soLuong, Double gia) {
        super();
        this.hoaDon = hoaDon;
        this.matHang = matHang;
        this.phongHat = phongHat;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public MatHang getMatHang() {
        return matHang;
    }

    public void setMatHang(MatHang matHang) {
        this.matHang = matHang;
    }

    public PhongHat getPhongHat() {
        return phongHat;
    }

    public void setPhongHat(PhongHat phongHat) {
        this.phongHat = phongHat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) throws Exception {
        if (soLuong <= 0) {
            throw new Exception("Số lượng không được <= 0!");
        }
        this.soLuong = soLuong;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) throws Exception {
        if (gia <= 0) {
            throw new Exception("Giá không được <= 0!");
        }
        this.gia = gia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.hoaDon);
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
        final ChiTietHoaDonDV other = (ChiTietHoaDonDV) obj;
        return Objects.equals(this.hoaDon, other.hoaDon);
    }

    public Double tinhTienDV() {
        return gia * soLuong;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonDV{" +
                "hoaDon=" + hoaDon +
                ", matHang=" + matHang +
                ", phongHat=" + phongHat +
                ", soLuong=" + soLuong +
                ", gia=" + gia +
                '}';
    }

}
