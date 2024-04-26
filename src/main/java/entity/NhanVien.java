package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import jakarta.persistence.*;

@Entity
@Table(name = "NhanVien")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = "NhanVien.getallNhanVien", query = "SELECT nv FROM NhanVien nv"),
		@NamedQuery(name = "NhanVien.getNhanVienTheoMaNV", query = "SELECT nv FROM NhanVien nv WHERE nv.maNV like :maNV"),
		@NamedQuery(name = "NhanVien.getNVTheoLoai", query = "SELECT nv FROM NhanVien nv WHERE nv.loaiNV.maLoai = :loaiNV"),
		//@NamedQuery(name = "NhanVien.addStaff", query = "INSERT INTO NhanVien(maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, maLoai) VALUES(:maNV, :tenNV, :gioiTinh, :CCCD, :SDT, :diaChi, :caLam, :maLoai)"),
		//@NamedQuery(name = "NhanVien.editStaff", query = "UPDATE NhanVien nv SET nv.tenNV = :tenNV, nv.gioiTinh = :gioiTinh, nv.CCCD = :CCCD, nv.SDT = :SDT, nv.diaChi = :diaChi, nv.caLam = :caLam, nv.loaiNV = :loaiNV WHERE nv.maNV = :maNV"),
		@NamedQuery(name = "NhanVien.findStaff", query = "SELECT nv FROM NhanVien nv WHERE nv.maNV like :maNV"),
		@NamedQuery(name = "NhanVien.SDT_tonTaiNV", query="SELECT COUNT(n) FROM NhanVien n WHERE n.SDT = :SDT")
})
public class NhanVien implements Serializable {
	@Id
	@Column(name = "maNV")
	private String maNV;
	@Column(name = "tenNV", columnDefinition = "nvarchar(255)")
	private String tenNV;
	@Column(name = "gioiTinh")
	private Boolean gioiTinh;
	@Column(name = "CCCD")
	private String CCCD;
	@Column(name = "SDT")
	private String SDT;
	@Column(name = "diaChi", columnDefinition = "nvarchar(255)")
	private String diaChi;
	@Column(name = "caLam")
	private String caLam;

	// Mối quan hệ n - 1 với LoaiNhanVien .
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maLoai")
	private LoaiNhanVien loaiNV;

	// Mối quan hệ 1 - n với HoaDon
	@OneToMany(mappedBy = "nhanVien")
	private Set<HoaDon> dsHoaDon;




	public NhanVien() {
	}

	public NhanVien(String maNV, String tenNV, Boolean gioiTinh, String cCCD, String sDT, String diaChi, String caLam,
			LoaiNhanVien loaiNV) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.gioiTinh = gioiTinh;
		CCCD = cCCD;
		SDT = sDT;
		this.diaChi = diaChi;
		this.caLam = caLam;
		this.loaiNV = loaiNV;
	}

	public NhanVien(String maNV) {
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) throws Exception {
		if (tenNV.trim().isEmpty()) {
			throw new Exception("Tên nhân viên không được rỗng!");
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		if (!pattern.matcher(tenNV).matches()) {
			throw new Exception("Tên nhân viên chỉ được chứa ký tự chữ");
		}
		// tự động viết hoa
		this.tenNV = tenNV.substring(0, 1).toUpperCase() + tenNV.substring(1);

	}

	public Boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) throws Exception {
		if (CCCD.trim().isEmpty()) {
			throw new Exception("CCCD không được rỗng!");
		}
		Pattern pattern = Pattern.compile("[0-9]{12}");
		if (!pattern.matcher(CCCD).matches()) {
			throw new Exception("CCCD gồm 12 số!");
		} else {
			this.CCCD = cCCD;
		}
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) throws Exception {
		if (SDT.trim().isEmpty()) {
			throw new Exception("SDT không được rỗng!");
		}
		Pattern pattern = Pattern.compile("[0-9]{10}");
		if (!pattern.matcher(SDT).matches()) {
			throw new Exception("SDT gồm 10 số!");
		} else {
			SDT = sDT;
		}
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) throws Exception {
		if (diaChi.trim().isEmpty()) {
			throw new Exception("Địa chỉ không được rỗng!");
		}
		this.diaChi = diaChi;
	}

	public String getCaLam() {
		return caLam;
	}

	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}

	public LoaiNhanVien getLoaiNV() {
		return loaiNV;
	}

	public void setLoaiNV(LoaiNhanVien loaiNV) {
		this.loaiNV = loaiNV;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", gioiTinh=" + gioiTinh + ", CCCD=" + CCCD + ", SDT="
				+ SDT + ", diaChi=" + diaChi + ", caLam=" + caLam + ", loaiNV=" + loaiNV + "]";
	}

	public Object[] getObject() {
		return new Object[] { maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV.getMaLoai() };
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + Objects.hashCode(this.maNV);
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
		final NhanVien other = (NhanVien) obj;
		return Objects.equals(this.maNV, other.maNV);
	}

}
