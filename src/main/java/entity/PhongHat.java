package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PhongHat")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
//	PhongHat.getAllPhongHat hiển thị danh sách phòng hát gồm maPhong, tenPhong, new LoaiPhong(loaiPhong), tinhTrang
		@NamedQuery(name = "PhongHat.getAllPhongHat", query = "select c from PhongHat c"),
		@NamedQuery(name = "PhongHat.getPhongByTinhTrang", query = "select c from PhongHat c where c.tinhTrangPhong like :tinhTrang"),
		@NamedQuery(name = "PhongHat.updateTinhTrangPhong", query = "update PhongHat c set c.tinhTrangPhong = :tinhTrangMoi where c.maPhong like :maPhong"),
		@NamedQuery(name = "PhongHat.getPhongHatByMaPhong", query = "select c from PhongHat c where c.maPhong like :maPhong"),
		@NamedQuery(name = "PhongHat.editPhongHat", query = "update PhongHat c set c.tenPhong = :tenPhong, c.loaiPhong = :loaiPhong, c.tinhTrangPhong = :tinhTrangPhong where c.maPhong like :maPhong"),
		@NamedQuery(name = "PhongHat.DeletePhongHat", query = "delete from PhongHat c where c.maPhong like :maPH"),
		@NamedQuery(name = "PhongHat.FindTheoMaLoai", query = "select c from PhongHat c where c.loaiPhong.maLoaiPhong like :id"),
		@NamedQuery(name = "PhongHat.getSoPhongTrong", query = "select count(c) from PhongHat c where c.tinhTrangPhong like 'Trong'"),
		@NamedQuery(name = "PhongHat.getTongSoPhong", query = "select count(c) from PhongHat c"),
		@NamedQuery(name = "PhongHat.addPhongHat", query = "insert into PhongHat (maPhong, tenPhong, loaiPhong, tinhTrangPhong) values (:maPhong, :tenPhong, :loaiPhong, :tinhTrangPhong)") })

public class PhongHat implements Serializable{
	@Id
	private String maPhong;
	@Column(name = "tenPhong", columnDefinition = "nvarchar(255)")
	private String tenPhong;
	@Column(name = "tinhTrangPhong")
	private String tinhTrangPhong;

// Mối quan hệ n - 1 với LoaiPhong
	@ManyToOne
	@JoinColumn(name = "maLoaiPhong")
	private LoaiPhong loaiPhong;

	// Mối quan hệ 1 - n với DonDatPhong
	@OneToMany(mappedBy = "phongHat", fetch = FetchType.EAGER)
	private Set<DonDatPhong> dsDonDatPhong;

	// Mối quan hệ 1 - n với ChiTietHoaDonDV
	@OneToMany(mappedBy = "phongHat", fetch = FetchType.EAGER)
	private Set<ChiTietHoaDonDV> dsChiTietHoaDonDV;

	public PhongHat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhongHat(String maPhong) {
		this.maPhong = maPhong;
	}

	public PhongHat(String maPhong, String tenPhong, LoaiPhong loaiPhong, String tinhTrangPhong) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.loaiPhong = loaiPhong;
		this.tinhTrangPhong = tinhTrangPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) throws Exception {
		if (tenPhong.trim().isEmpty()) {
			throw new Exception("Tên phòng không được rỗng!");
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		if (!pattern.matcher(tenPhong).matches()) {
			throw new Exception("Tên phòng chỉ được chứa ký tự chữ");
		}
		// tự động viết hoa
		this.tenPhong = tenPhong.substring(0, 1).toUpperCase() + tenPhong.substring(1);

	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public String getTinhTrangPhong() {
		return tinhTrangPhong;
	}

	public void setTinhTrangPhong(String tinhTrangPhong) throws Exception {
		if (tinhTrangPhong.trim().isEmpty()) {
			throw new Exception("Tình trạng phòng không được rỗng!");
		}
		this.tinhTrangPhong = tinhTrangPhong;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongHat other = (PhongHat) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	@Override
	public String toString() {
		return "PhongHat [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", loaiPhong=" + loaiPhong
				+ ", tinhTrangPhong=" + tinhTrangPhong + "]";
	}

}
