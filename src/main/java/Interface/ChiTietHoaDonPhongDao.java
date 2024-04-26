package Interface;

import entity.ChiTietHoaDonPhong;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ChiTietHoaDonPhongDao {
    ArrayList<ChiTietHoaDonPhong> getalltbChiTietHoaDonPhong();
    ChiTietHoaDonPhong getChiTietHoaDonPhongTheoMaHD(String id, String maPhong);
    boolean createChiTietHoaDonPhong(ChiTietHoaDonPhong cthdp);
    ChiTietHoaDonPhong finHDByRoomID(String roomID);
    ChiTietHoaDonPhong finHDByRoomIDDaTT(String roomID);
    boolean updateGioRaVsGia(String maHD, LocalDateTime gioRa, Float gia, String maPhong);
    Boolean doiPhong(String maHD, String maPhongHienTai, String maPhongMoi, String ghiChu);

    public void createChiTietHoaDonPhongList(List<ChiTietHoaDonPhong> cthdpList);

    ArrayList<ChiTietHoaDonPhong> getAllTheMaHDArray(String maHD6);
}
