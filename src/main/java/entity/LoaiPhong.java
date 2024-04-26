package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@Table(name = "LoaiPhong")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ // JPQL
		@NamedQuery(name = "LoaiPhong.getalltbLoaiPhong", query = "select c from LoaiPhong c"),
		@NamedQuery(name = "LoaiPhong.getLoaiPhongTheoMaLoai", query = "select c from LoaiPhong c where c.maLoaiPhong like :maLoaiPhong") })
public class LoaiPhong implements Serializable {
	@Id
	@Column(name = "maLoaiPhong")
	private String maLoaiPhong;
	@Column(name = "tenLoaiPhong", columnDefinition = "nvarchar(255)")
	private String tenLoaiPhong;
	@Column(name = "gia")
	private Double gia;

	// Mối quan hệ 1 - n với PhongHat
	@OneToMany(mappedBy = "loaiPhong")
	private Set<PhongHat> dsPhongHat;

	public LoaiPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, Double gia) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.gia = gia;
	}

	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}

	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) throws Exception {
		if (tenLoaiPhong.trim().isEmpty()) {
			throw new Exception("Tên nhân viên không được rỗng!");
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		if (!pattern.matcher(tenLoaiPhong).matches()) {
			throw new Exception("Tên phòng chỉ được chứa ký tự chữ");
		}
		// tự động viết hoa
		this.tenLoaiPhong = tenLoaiPhong.substring(0, 1).toUpperCase() + tenLoaiPhong.substring(1);

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
		return Objects.hash(gia, maLoaiPhong, tenLoaiPhong);
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
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(gia, other.gia) && Objects.equals(maLoaiPhong, other.maLoaiPhong)
				&& Objects.equals(tenLoaiPhong, other.tenLoaiPhong);
	}

	@Override
	public String toString() {
		return "LoaiPhong [maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", gia=" + gia + "]";
	}

}
