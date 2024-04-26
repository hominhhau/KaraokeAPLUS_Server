package Interface;

import java.util.ArrayList;

import entity.KhuyenMai;

public interface KhuyenMaiDao {

	public ArrayList<KhuyenMai> getAllKhuyenMai();

	public boolean createKhuyenMai(KhuyenMai km);

	public boolean editKhuyenMai(KhuyenMai km);

	public boolean DeleteKhuyenMai(String maKM);

	public String[] getKhuyenMai();
	
	public KhuyenMai getKhuyenMaiByMaKM(String maKM);
}
