package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "KhuyenMai")
@Inheritance(strategy = InheritanceType.JOINED)

@NamedQueries({ // JPQL
		@NamedQuery(name = "KhuyenMai.getAllKhuyenMai", query = "select c from KhuyenMai c"),
		@NamedQuery(name = "KhuyenMai.editKhuyenMai", query = "update KhuyenMai c set c.moTa = :moTa, c.gioBatDau = :gioBatDau, c.gioKetThuc = :gioKetThuc, c.phanTram = :phanTram where c.maKM like :maKM"),
		@NamedQuery(name = "KhuyenMai.DeleteKhuyenMai", query = "delete from KhuyenMai c where c.maKM like :maKM"),
		@NamedQuery(name = "KhuyenMai.getKhuyenMai", query = "select c.maKM from KhuyenMai c"),
		@NamedQuery(name = "KhuyenMai.getKhuyenMaiByMaKM", query = "select c from KhuyenMai c where c.maKM like :maKM")})
public class KhuyenMai implements Serializable {
	@Id
	@Column(name = "maKM")
	private String maKM;
	@Column(name = "moTa", columnDefinition = "nvarchar(255)")
	private String moTa;
	@Column(name = "gioBatDau")
	private LocalDate gioBatDau;
	@Column(name = "gioKetThuc")
	private LocalDate gioKetThuc;
	@Column(name = "phanTram")
	private Double phanTram;

	// Mối quan hệ 1 - n với HoaDon
	@OneToMany(mappedBy = "maKM")
	private Set<HoaDon> dsHoaDon;

	public KhuyenMai(String maKM, String moTa, LocalDate gioBatDau, LocalDate gioKetThuc, Double phanTram) {
		this.maKM = maKM;
		this.moTa = moTa;
		this.gioBatDau = gioBatDau;
		this.gioKetThuc = gioKetThuc;
		this.phanTram = phanTram;
	}

	public KhuyenMai() {
	}

	public KhuyenMai(String maKM) {
		this.maKM = maKM;
	}

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public LocalDate getGioBatDau() {
		return gioBatDau;
	}

	public void setGioBatDau(LocalDate gioBatDau) {
		this.gioBatDau = gioBatDau;
	}

	public LocalDate getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(LocalDate gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public Double getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(Double phanTram) {
		this.phanTram = phanTram;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.maKM);
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
		final KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(this.maKM, other.maKM);
	}

}
