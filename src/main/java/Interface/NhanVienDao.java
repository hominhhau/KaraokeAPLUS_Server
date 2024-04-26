package Interface;

import entity.NhanVien;

import java.util.ArrayList;

public interface NhanVienDao {
    ArrayList<NhanVien> getalltbNhanVien();
    ArrayList<NhanVien> getNhanVienTheoMaNV(String id);
    ArrayList<NhanVien> getNVTheoLoai(String lnv);
    boolean addStaff(NhanVien nv);
    boolean editStaff(NhanVien nv);
    NhanVien findStaff(String maTim);
    boolean SDT_tonTaiNV(String soDienThoai);

}
