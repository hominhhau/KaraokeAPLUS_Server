package Interface;

import java.util.ArrayList;

import entity.LoaiPhong;

public interface LoaiPhongDao {
	public ArrayList<LoaiPhong> getalltbLoaiPhong();

	public ArrayList<LoaiPhong> getLoaiPhongTheoMaLoai(String id);
	
}
