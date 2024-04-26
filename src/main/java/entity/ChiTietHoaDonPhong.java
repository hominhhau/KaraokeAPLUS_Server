package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "ChiTietHoaDonPhong")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(
                name = "getAllChiTietHoaDonPhong",
                query = "SELECT c FROM ChiTietHoaDonPhong c"
        ),
        @NamedQuery(
                name = "getChiTietHoaDonPhongByMaHD",
                query = "SELECT c FROM ChiTietHoaDonPhong c WHERE c.hoaDon.maHD = :maHD AND c.phongHat.maPhong = :maPhong"
        ),
        //             PreparedStatement statement = con.prepareStatement("SELECT * FROM ChiTietHoaDonPhong WHERE maPhong = ? AND gioVao = gioRa"))
        @NamedQuery(
                name = "findHDByRoomID",
                query = "SELECT c FROM ChiTietHoaDonPhong c WHERE c.phongHat.maPhong = :maPhong AND c.gioRa = c.gioVao"
        ),
        @NamedQuery(
                name = "findHDByRoomIDPaid",
                query = "SELECT c FROM ChiTietHoaDonPhong c WHERE c.phongHat.maPhong = :maPhong AND c.gioRa IS NOT NULL"
        ),
        @NamedQuery(
                name = "updateCheckoutAndPrice",
                query = "UPDATE ChiTietHoaDonPhong c SET c.gioRa = :gioRa, c.gia = :gia WHERE c.hoaDon.maHD = :maHD AND c.phongHat.maPhong = :maPhong"
        ),
           @NamedQuery(
                    name = "changeRoom",
                    query = "UPDATE ChiTietHoaDonPhong c SET c.phongHat.maPhong = :newRoomID, c.ghiChu = :note WHERE c.hoaDon.maHD = :maHD AND c.phongHat.maPhong = :currentRoomID"
            ),
        @NamedQuery(
                name = "getAllByMaHD",
                query = "SELECT c FROM ChiTietHoaDonPhong c WHERE c.hoaDon.maHD = :maHD"
        )

})
public class ChiTietHoaDonPhong implements Serializable{
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHD")
	private HoaDon hoaDon;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maPhong")
	private PhongHat phongHat;

	@Column(name = "gia")
	private Double gia;
	@Column(name = "gioVao")
	private LocalDateTime gioVao;
	@Column(name = "gioRa")
	private LocalDateTime gioRa;
	@Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
	private String ghiChu;
    public ChiTietHoaDonPhong() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ChiTietHoaDonPhong(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietHoaDonPhong(HoaDon hoaDon, PhongHat phongHat, Double gia, LocalDateTime gioVao, LocalDateTime gioRa,
                              String ghiChu) {
        super();
        this.hoaDon = hoaDon;
        this.phongHat = phongHat;
        this.gia = gia;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
        this.ghiChu = ghiChu;
    }

    public ChiTietHoaDonPhong(HoaDon hoaDon, PhongHat phongHat, Double gia, LocalDateTime gioVao, String ghiChu) {
        this(hoaDon, phongHat, gia, gioVao, null, ghiChu);
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public PhongHat getPhongHat() {
        return phongHat;
    }

    public void setPhongHat(PhongHat phongHat) {
        this.phongHat = phongHat;
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

    public LocalDateTime getGioVao() {
        return gioVao;
    }

    public void setGioVao(LocalDateTime gioVao) throws Exception {
        if (gioVao.isAfter(gioRa)) {
            throw new Exception("Giờ vào trước giờ ra!");
        }

        this.gioVao = gioVao;
    }

    public LocalDateTime getGioRa() {
        return gioRa;
    }

    public void setGioRa(LocalDateTime gioRa) throws Exception {


        this.gioRa = gioRa;
    }

    public String getghiChu() {
        return ghiChu;
    }

    public void setghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.hoaDon);
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
        final ChiTietHoaDonPhong other = (ChiTietHoaDonPhong) obj;
        return Objects.equals(this.hoaDon, other.hoaDon);
    }

    public Double tinhTienPhong() {
        return gia * (gioRa.getHour() - gioVao.getHour());
    }

    public Double tinhTienGiam() {
        if (ghiChu.equals("VIP")) {
            return tinhTienPhong() * 0.02;
        } else if (ghiChu.equals("TH")) {
            return tinhTienPhong() * 0.01;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonPhong [hoaDon=" + hoaDon + ", phongHat=" + phongHat + ", gia=" + gia + ", ghiChu="
                + ghiChu + "]";
    }

}
