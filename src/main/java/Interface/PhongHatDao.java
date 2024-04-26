package Interface;

import java.util.ArrayList;

import entity.PhongHat;

public interface PhongHatDao {
	public static final ArrayList<PhongHat> ls = new ArrayList<PhongHat>();

	
	public ArrayList<PhongHat> getAllPhongHat();

	public ArrayList<PhongHat> getPhongByTinhTrang(String tinhTrang);

	public boolean updateTinhTrangPhong(String maPhong, String tinhTrangMoi);

	public PhongHat getPhongHatByMaPhong(String maPhong);

	public boolean editPhongHat(PhongHat ph);

	public boolean DeletePhongHat(String maPH);

	public ArrayList<PhongHat> FindTheoMaLoai(String id);

	public int getSoPhongTrong();

	public int getTongSoPhong();

	public boolean addPhongHat(PhongHat ph);

}
