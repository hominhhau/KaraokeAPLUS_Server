package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "DonDatPhong")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "DonDatPhong.getalltbDonDatPhong", query = "SELECT d FROM DonDatPhong d"),
        @NamedQuery(name = "DonDatPhong.createDonDatPhong", query = "INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, phongHat, khachHang) VALUES (:maDonDatPhong, :ngayDat, :ngayNhan, :phongHat, :khachHang)"),
        @NamedQuery(name = "DonDatPhong.getDonDatPhongTheoMaKH", query = "SELECT d FROM DonDatPhong d WHERE d.khachHang.maKH = :maKH"),
        //       String sql = "SELECT * FROM DonDatPhong WHERE CAST(ngayNhan AS DATE) = ?";
        @NamedQuery(name = "DonDatPhong.getDonDatPhongTheoNgayNhanPhong", query = "SELECT d FROM DonDatPhong d WHERE CAST(d.ngayNhan AS DATE) = :ngayNhanPhong"),
        @NamedQuery(name = "DonDatPhong.timDonDatPhong", query = "SELECT d FROM DonDatPhong d WHERE d.maDonDatPhong LIKE :maDDP AND d.khachHang.maKH LIKE :maKH AND d.phongHat.maPhong LIKE :maPhong"),
        @NamedQuery(name = "DonDatPhong.deleteDonDatPhong", query = "DELETE FROM DonDatPhong d WHERE d.maDonDatPhong = :maDDP AND d.phongHat.maPhong = :maPhong")
})
public class DonDatPhong implements Serializable{
	@Id
	@Column(name = "maDonDatPhong")
	private String maDonDatPhong;
	@Column(name = "ngayDat")
	private LocalDateTime ngayDat;
	@Column(name = "ngayNhan")
	private LocalDateTime ngayNhan;

	// Mối quan hệ n - 1 với PhongHat
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maPhong")
	private PhongHat phongHat;

	// Mối quan hệ n - 1 với KhachHang
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;


    public DonDatPhong() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DonDatPhong(String maDonDatPhong, LocalDateTime ngayDat, LocalDateTime ngayNhan, PhongHat phongHat,
                       KhachHang khachHang) {
        super();
        this.maDonDatPhong = maDonDatPhong;
        this.ngayDat = ngayDat;
        this.ngayNhan = ngayNhan;
        this.phongHat = phongHat;
        this.khachHang = khachHang;
    }

    public DonDatPhong(String maDonDatPhong) {
        this.maDonDatPhong = maDonDatPhong;
    }

    public String getMaDonDatPhong() {
        return maDonDatPhong;
    }

    public void setMaDonDatPhong(String maDonDatPhong) {
        this.maDonDatPhong = maDonDatPhong;
    }

    public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) throws Exception {
        if (ngayDat.compareTo(LocalDateTime.now()) != 0 && ngayDat.isBefore(LocalDateTime.now())) {
            throw new Exception("Ngày đặt phải bằng hoặc sau ngày hiện tại!");
        } else {
            this.ngayDat = ngayDat;
        }
    }

    public LocalDateTime getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(LocalDateTime ngayNhan) throws Exception {
        if (ngayNhan != ngayDat) {
            throw new Exception("Ngày nhận bằng ngày đặt!");
        } else {
            this.ngayNhan = ngayNhan;
        }
    }

    public PhongHat getPhongHat() {
        return phongHat;
    }

    public void setPhongHat(PhongHat phongHat) {
        this.phongHat = phongHat;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.maDonDatPhong);
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
        final DonDatPhong other = (DonDatPhong) obj;
        return Objects.equals(this.maDonDatPhong, other.maDonDatPhong);
    }

    @Override
    public String toString() {
        return "DonDatPhong [maDonDatPhong=" + maDonDatPhong + ", ngayDat=" + ngayDat + ", ngayNhan=" + ngayNhan
                + ", phongHat=" + phongHat + ", khachHang=" + khachHang + "]";
    }

}
