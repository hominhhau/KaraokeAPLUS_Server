package Test;

import Interface.ChiTietHoaDonDichVuDao;
import Interface.ChiTietHoaDonPhongDao;
import Interface.impl.ChiTietHoaDonDichVuImpl;
import Interface.impl.ChiTietHoaDonPhongImpl;
import entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Test_ChiTietHoaDonDV {
    public static void main(String[] args) {
        ChiTietHoaDonDichVuDao chiTietHoaDonDichVuDao = new ChiTietHoaDonDichVuImpl();
        // menu
        int choose;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Thêm chi tiết hóa đơn dịch vụ");
            System.out.println("2. Lấy chi tiết hóa đơn dịch vụ theo mã hóa đơn và mã dịch vụ");
            System.out.println("3. Lấy danh sách chi tiết hóa đơn dịch vụ");
            System.out.println("4. Cập nhật số lượng");
            //getalltbChiTietHoaDonDVTheoMaMH
            System.out.println("5. Lấy danh sách chi tiết hóa đơn dịch vụ theo mã mặt hàng");
            //deleteChiTietHoaDonDV
            System.out.println("6. Xóa chi tiết hóa đơn dịch vụ");
            System.out.println("7. Tìm chi tiết hóa đơn dịch vụ for them");
            //getAllTheMaHDDVforRoomArray
            System.out.println("8. Lấy danh sách mã hóa đơn dịch vụ theo mã hóa đơn và mã phòng");
            System.out.println("9. Thống kê  top dịch vụ theo năm");
            System.out.println("10. Tong tien theo nam");
            System.out.println("11. Thống kê top dịch vụ theo tháng");
            System.out.println("12. Thống kê tổng tiền theo tháng");
            System.out.println("13. Thống kê top dịch vụ theo ngày");
            System.out.println("14. Thống kê tổng tiền theo ngày");
            System.out.println("15. Thống kê theo năm");
            System.out.println("16. Thống kê theo tháng");
            System.out.println("17. Đổi phòng");
            //findChiTietHoaDonDV
            System.out.println("18. Tìm chi tiết hóa đơn dịch vụ");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                   // ChiTietHoaDonDV(HoaDon hoaDon, MatHang matHang, PhongHat phongHat, int soLuong, Double gia)
                    ChiTietHoaDonDV hddv = new ChiTietHoaDonDV(new HoaDon("HD008"), new MatHang("MH001"), new PhongHat("P001"), 1, 1000.0);

                    System.out.println(chiTietHoaDonDichVuDao.createChiTietHoaDonDV(hddv));
                    break;


                case 2:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD = sc.nextLine();
                    System.out.print("Nhập mã phòng: ");
                    String maPhong = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getChiTietHoaDonDVTheoMaHD(maHD, maPhong));
                    break;
                case 3:
                    System.out.println(chiTietHoaDonDichVuDao.getalltbChiTietHoaDonDV());
                    break;
                case 4:
                    ChiTietHoaDonDV cthddv = new ChiTietHoaDonDV(new HoaDon("HD008"), new MatHang("MH001"), new PhongHat("P001"), 3, 1000.0);
                    System.out.println(chiTietHoaDonDichVuDao.updateChiTietHoaDonDV(cthddv));
                    break;
                case 5:
                    System.out.print("Nhập mã mặt hàng: ");
                    String maMH = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getalltbChiTietHoaDonDVTheoMaMH(maMH));
                    break;
                case 6:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD1 = sc.nextLine();
                    System.out.print("Nhập mã mặt hàng: ");
                    String maMH1 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.deleteChiTietHoaDonDV(maHD1, maMH1));
                    break;
                case 7:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD2 = sc.nextLine();
                    System.out.print("Nhập mã mặt hàng: ");
                    String maMH2 = sc.nextLine();
                    System.out.println("Nhap ma phong");
                    String maPhong2 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.findChiTietHoaDonDVforThem(maHD2, maMH2, maPhong2));
                    break;
                case 8:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD3 = sc.nextLine();
                    System.out.print("Nhập mã phòng: ");
                    String maPhong3 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getAllTheMaHDDVforRoomArray(maHD3, maPhong3));
                    break;
                case 9:
                    System.out.print("Nhập năm: ");
                    String nam = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getTOPDVNam(nam));
                    break;
                case 10:
                    System.out.print("Nhập năm: ");
                    String nam1 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getTongTienNam(nam1));
                    break;
                case 11:
                    System.out.print("Nhập tháng: ");
                    String thang = sc.nextLine();
                    System.out.print("Nhập năm: ");
                    String nam2 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getTOPDVThang(thang, nam2));
                    break;
                case 12:
                    System.out.print("Nhập tháng: ");
                    String thang1 = sc.nextLine();
                    System.out.print("Nhập năm: ");
                    String nam3 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getTongTienThangTop(thang1, nam3));
                    break;
                case 13:
                    // gán mẫu
                    Date  ngay = new Date();
                    ngay = new Date(2023, 10, 30);
                    System.out.println(chiTietHoaDonDichVuDao.getTOPDVNgay(ngay));
                    break;
                case 14:
                   Date ngay1 = new Date();
                    ngay1 = new Date(2023, 10, 30);
                    System.out.println(chiTietHoaDonDichVuDao.getTongTienNgayDV(ngay1));
                    break;
                case 15:
                    System.out.print("Nhập năm: ");
                    String nam4 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getTKNam(nam4));
                    break;
                case 16:
                    System.out.print("Nhập tháng: ");
                    String thang2 = sc.nextLine();
                    System.out.print("Nhập năm: ");
                    String nam5 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.getTKThang(thang2, nam5));
                    break;

                case 17:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD5 = sc.nextLine();
                    System.out.print("Nhập phòng hiện tại: ");
                    String phongHT = sc.nextLine();
                    System.out.print("Nhập phòng mới: ");
                    String phongMoi = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.doiPhong(maHD5, phongHT, phongMoi));
                    break;
                case 18:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD6 = sc.nextLine();
                    System.out.print("Nhập mã mặt hàng: ");
                    String maMH6 = sc.nextLine();
                    System.out.println(chiTietHoaDonDichVuDao.findChiTietHoaDonDV(maHD6, maMH6));
                    break;

            }

        } while (choose != 0);

    }
}
