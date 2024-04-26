package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import jakarta.persistence.*;

@Entity
@Table(name = "KhachHang")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "KhachHang.getalltbKhachHang", query = "select k from KhachHang k"),
		@NamedQuery(name = "KhachHang.getKhachHangTheoMaKH", query = "select k from KhachHang k where k.maKH like :maKH"),
		@NamedQuery(name = "KhachHang.getKhachHangTheoSdtKH", query = "select k from KhachHang k where k.sdt like :sdt"),
		@NamedQuery(name = "KhachHang.addCustomer", query = "insert into KhachHang(maKH, tenKH, sdt, gioiTinh) values(:maKH, :tenKH, :sdt, :gioiTinh)"),
		@NamedQuery(name = "KhachHang.DeleteCustomer", query = "delete from KhachHang k where k.maKH like :maKH"),
		@NamedQuery(name = "KhachHang.findCustomer", query = "select k from KhachHang k where k.maKH like :maKH"),
		@NamedQuery(name = "KhachHang.timKhachHangbySDT", query = "select k from KhachHang k where k.sdt like :sdt"),
		@NamedQuery(name = "KhachHang.editCustomer", query = "update KhachHang k set k.tenKH = :tenKH, k.sdt = :sdt, k.gioiTinh = :gioiTinh where k.maKH like :maKH"),
		@NamedQuery(name = "KhachHang.getdataKH", query = "select count(k) from KhachHang k"),
		@NamedQuery(name = "KhachHang.getSoLuongKhachHang", query = "select count(k) from KhachHang k") })
public class KhachHang implements Serializable {
	@Id
	@Column(name = "maKH")
	private String maKH;
	@Column(name = "tenKH", columnDefinition = "nvarchar(255)")
	private String tenKH;
	@Column(name = "sdt")
	private String sdt;
	@Column(name = "gioiTinh")
	private boolean gioiTinh;

	// Mối quan hệ 1 - n với DonDatPhong
	@OneToMany(mappedBy = "khachHang")
	private Set<DonDatPhong> dsDonDatPhong;

//	 Mối quan hệ 1 - n với HoaDon
	@OneToMany(mappedBy = "khachHang")
	private Set<HoaDon> dsHoaDon;

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String tenKH, String sdt, boolean gioitinh) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.gioiTinh = gioitinh;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) throws Exception {
		if (tenKH.trim().isEmpty()) {
			throw new Exception("Tên khách hàng không được rỗng!");
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		if (!pattern.matcher(tenKH).matches()) {
			throw new Exception("Tên khách hàng chỉ được chứa ký tự chữ");
		}
		// tự động viết hoa
		this.tenKH = tenKH.substring(0, 1).toUpperCase() + tenKH.substring(1);

	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) throws Exception {
		if (sdt.trim().equals("")) {
			throw new Exception("SDT không được rỗng!");
		}
		Pattern pattern = Pattern.compile("[0-9]{10}");
		if (!pattern.matcher(sdt).matches()) {
			throw new Exception("SDT gồm 10 số!");
		} else {
			this.sdt = sdt;
		}
	}

	public boolean isGioitinh() {
		return gioiTinh;
	}

	public void setGioitinh(boolean gioitinh) {

		this.gioiTinh = true;
	}

	public Object[] getObject() {
		return new Object[] { maKH, tenKH, sdt, gioiTinh };
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(this.maKH);
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
		final KhachHang other = (KhachHang) obj;
		return Objects.equals(this.maKH, other.maKH);
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", sdt=" + sdt + ", gioitinh=" + gioiTinh + "]";
	}

}
