/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * @author 84343
 */
@Entity
@Table(name = "MatHang")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "MatHang.getalltbMatHang", query = "SELECT mh FROM MatHang mh"),
		@NamedQuery(name = "MatHang.getMatHangTheoMaMH", query = "SELECT mh FROM MatHang mh WHERE mh.maMH = :maMH"),
		@NamedQuery(name = "MatHang.addMatHang", query = "INSERT INTO MatHang (maMH, tenMH, gia, trangThai) VALUES (:maMH, :tenMH, :gia, :trangThai)"),
		@NamedQuery(name = "MatHang.DeleteMatHang", query = "DELETE FROM MatHang mh WHERE mh.maMH = :maMH"),
		@NamedQuery(name = "MatHang.findMatHang", query = "SELECT mh FROM MatHang mh WHERE mh.maMH = :maTim"),
		@NamedQuery(name = "MatHang.editMatHang", query = "UPDATE MatHang mh SET mh.tenMH = :tenMH, mh.gia = :gia, mh.trangThai = :trangThai WHERE mh.maMH = :maMH"),
		@NamedQuery(name = "MatHang.getTongSoMH", query = "SELECT COUNT(mh) FROM MatHang mh"),

		@NamedQuery(name = "MatHang.getMHNam", query = "SELECT mh.maMH, mh.tenMH, SUM(ct.soLuong) AS SoLuong, ROUND(SUM(ct.soLuong * mh.gia), 2) AS TongTien FROM ChiTietHoaDonDV ct JOIN ct.hoaDon o JOIN ct.matHang mh WHERE YEAR(o.ngayLapHD) = :nam GROUP BY mh.maMH, mh.tenMH ORDER BY mh.maMH ASC"),

		@NamedQuery(name = "MatHang.getTongTienNam", query = "SELECT SUM(TongTien) FROM (SELECT ROUND(SUM(ct.soLuong * mh.gia), 2) AS TongTien FROM ChiTietHoaDonDV ct JOIN ct.hoaDon o JOIN ct.matHang mh WHERE YEAR(o.ngayLapHD) = :nam GROUP BY mh.maMH, mh.tenMH) AS Tong"),

		@NamedQuery(name = "MatHang.getMHThang", query = "SELECT mh.maMH, mh.tenMH, SUM(ct.soLuong) AS SoLuong, ROUND(SUM(ct.soLuong * mh.gia), 2) AS TongTien FROM ChiTietHoaDonDV ct JOIN ct.hoaDon o JOIN ct.matHang mh WHERE MONTH(o.ngayLapHD) = :thang AND YEAR(o.ngayLapHD) = :nam GROUP BY mh.maMH, mh.tenMH ORDER BY mh.maMH ASC"),
		
		@NamedQuery(name = "MatHang.getTongTienThang", query = "SELECT SUM(TongTien) AS TongTatCaTien FROM (SELECT ROUND(SUM(ct.soLuong * mh.gia), 2) AS TongTien FROM ChiTietHoaDonDV ct JOIN ct.hoaDon o JOIN ct.matHang mh WHERE MONTH(o.ngayLapHD) = :thang AND YEAR(o.ngayLapHD) = :nam GROUP BY mh.maMH, mh.tenMH) AS Tong"),

		@NamedQuery(name = "MatHang.getMHNgay", query = "SELECT mh.maMH, mh.tenMH,  SUM(ct.soLuong) AS SoLuong, ROUND(SUM(ct.soLuong * mh.gia), 2) AS TongTien FROM ChiTietHoaDonDV ct JOIN ct.hoaDon o JOIN ct.matHang mh WHERE o.ngayLapHD = :ngay GROUP BY mh.maMH, mh.tenMH ORDER BY mh.maMH ASC"),

		@NamedQuery(name = "MatHang.getTongTienNgay", query = "SELECT SUM(TongTien) AS TongTatCaTien FROM (SELECT ROUND(SUM(ct.soLuong * mh.gia), 2) AS TongTien FROM ChiTietHoaDonDV ct JOIN ct.hoaDon o JOIN ct.matHang mh WHERE o.ngayLapHD = :ngay GROUP BY mh.maMH, mh.tenMH) AS Tong") })
public class MatHang implements Serializable {
//
	@Id
	@Column(name = "maMH")
	private String maMH;
	@Column(name = "tenMH", columnDefinition = "nvarchar(255)")
	private String tenMH;
	@Column(name = "Gia")
	private double gia;
	@Column(name = "trangThai")
	private boolean trangThai;

	// Mối quan hệ 1 - n với ChiTietHoaDonDV
	@OneToMany(mappedBy = "matHang")
	private Set<ChiTietHoaDonDV> dsChiTietHoaDonDV;

	public MatHang(String tenMH, double gia, boolean trangThai) {
		this.tenMH = tenMH;
		this.gia = gia;
		this.trangThai = trangThai;
	}

	public MatHang(String maMH, String tenMH, double gia, boolean trangThai) {
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.gia = gia;
		this.trangThai = trangThai;
	}

	public MatHang(String maMH) {
		this.maMH = maMH;
	}

	public MatHang() {
	}

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.maMH);
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
		final MatHang other = (MatHang) obj;
		return Objects.equals(this.maMH, other.maMH);
	}

	@Override
	public String toString() {
		return "MatHang{" + "maMH=" + maMH + ", tenMH=" + tenMH + ", gia=" + gia + ", trangThai=" + trangThai + '}';
	}

}
