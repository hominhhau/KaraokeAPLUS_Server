package Interface;

import entity.ChiTietHoaDonDV;

import java.util.ArrayList;
import java.util.Date;

public interface ChiTietHoaDonDichVuDao {
    boolean createChiTietHoaDonDV(ChiTietHoaDonDV cthddv);
    ChiTietHoaDonDV getChiTietHoaDonDVTheoMaHD(String id, String maPhong);
    ArrayList<ChiTietHoaDonDV> getalltbChiTietHoaDonDV();
    ArrayList<ChiTietHoaDonDV> getalltbChiTietHoaDonDVTheoMaMH(String maMH);
    boolean updateChiTietHoaDonDV(ChiTietHoaDonDV cthddv);
    boolean deleteChiTietHoaDonDV(String maHD, String maMH);
    ChiTietHoaDonDV findChiTietHoaDonDV(String maHD, String maMH);
    public  ChiTietHoaDonDV findChiTietHoaDonDVforThem(String maHD, String maMH, String maPhong);
    public ArrayList<ChiTietHoaDonDV> getAllTheMaHDDVforRoomArray(String maHD, String maPhong);
    public  ArrayList<String[]> getTOPDVNam(String nam);
    public  double getTongTienNam(String nam);
    public ArrayList<String[]> getTOPDVThang(String thang, String nam);
    public  double getTongTienThangTop(String thang, String nam);
    public  ArrayList<String[]> getTOPDVNgay(Date ngay);
    public  double getTongTienNgayDV(Date ngay);
    public   ArrayList<String[]> getTKNam(String nam);
    public  ArrayList<String[]> getTKThang(String thang, String nam);
    public  boolean doiPhong(String maHD, String phongHT, String phongMoi);

}
