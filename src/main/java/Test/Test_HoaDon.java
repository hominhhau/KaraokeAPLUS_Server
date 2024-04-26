package Test;

import Interface.HoaDonDao;
import Interface.impl.HoaDonImpl;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Test_HoaDon {
    private static HoaDonDao hoaDonDao;

    public static void main(String[] args) {
        // TODO code application logic here
        /*
    ArrayList<HoaDon> getalltbHoaDon();
    boolean createHoaDon(HoaDon hd);
    HoaDon getHoaDonTheoMaHD(String maHD);
    boolean updateTongTien(String maHD, Double tongTien, String maKM);
    int getSoLuongHoaDon();
    ArrayList<HoaDon> getHoaDonTheoMaKH(String maKH);
    int getSoLuongHoaDonNgay(Date ngay);
    int getTongTienNgay(Date ngay);
    ArrayList<HoaDon> getDoanhThuTungThangTrongNam(String nam);
    ArrayList<HoaDon> getDoanhThuNam(String nam);
    ArrayList<HoaDon> getDoanhThuNgay(Date ngay);
    ArrayList<String[]> getDoanhThuTungThangNam(String nam);
    ArrayList<HoaDon> getDoanhThuThang(String thang, String nam);
    int getTongTienThang(String thang, String nam);
    int getSoLuongHoaDonThang(String thang, String nam);
    int getSoLuongHoaDonNam(String nam);
    int getTongTienNam(String nam);


         */
        hoaDonDao = new HoaDonImpl();
        // menu
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            System.out.println("1. Lấy danh sách hóa đơn");
            System.out.println("2. Thêm hóa đơn");
            System.out.println("3. Lấy hóa đơn theo mã hóa đơn");
            System.out.println("4. Cập nhật tổng tiền");
            System.out.println("5. Lấy số lượng hóa đơn");
            System.out.println("6. Lấy hóa đơn theo mã khách hàng");
            System.out.println("7. Lấy số lượng hóa đơn ngày");
            System.out.println("8. Lấy tổng tiền ngày");
            System.out.println("9. Lấy doanh thu từng tháng trong năm");
            System.out.println("10. Lấy doanh thu năm");
            System.out.println("11. Lấy doanh thu ngày");
            System.out.println("12. Lấy doanh thu từng tháng năm");
            System.out.println("13. Lấy doanh thu tháng");
            System.out.println("14. Lấy tổng tiền tháng");
            System.out.println("15. Lấy số lượng hóa đơn tháng");
            System.out.println("16. Lấy số lượng hóa đơn năm");
            System.out.println("17. Lấy tổng tiền năm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.println("Danh sách hóa đơn: ");
                    hoaDonDao.getalltbHoaDon().forEach(System.out::println);
                    break;
                case 2:
                    //HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), new KhuyenMai(null), tongTien);
                    HoaDon hd = new HoaDon("HD009", LocalDate.now(), new KhachHang("KH008"), new NhanVien("NV001"), null, 1000000);
                    System.out.println("Thêm hóa đơn: " + hd);
                    if (hoaDonDao.createHoaDon(hd)) {
                        System.out.println("Thêm hóa đơn thành công");
                    } else {
                        System.out.println("Thêm hóa đơn thất bại");
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã hóa đơn cần tìm: ");
                    String maHD = sc.nextLine();
                    System.out.println("Hóa đơn cần tìm: " + hoaDonDao.getHoaDonTheoMaHD(maHD));
                    break;
                case 4:
                    System.out.print("Nhập mã hóa đơn cần cập nhật: ");
                    String maHD1 = sc.nextLine();
                    System.out.print("Nhập tổng tiền mới: ");
                    Double tongTien = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Nhập mã khuyến mãi: ");
                    String maKM = sc.nextLine();
                    if (hoaDonDao.updateTongTien(maHD1, tongTien, maKM)) {
                        System.out.println("Cập nhật thành công");
                    } else {

                        System.out.println("Cập nhật thất bại");
                    }
                    break;
                case 5:
                    System.out.println("Số lượng hóa đơn: " + hoaDonDao.getSoLuongHoaDon());
                    break;
                case 6:
                    System.out.print("Nhập mã khách hàng cần tìm: ");
                    String maKH = sc.nextLine();
                    System.out.println("Danh sách hóa đơn của khách hàng: ");
                    hoaDonDao.getHoaDonTheoMaKH(maKH).forEach(System.out::println);
                    break;
                case 7:
                    System.out.print("Nhập ngày cần tìm: ");
                    String ngay = sc.nextLine();
                    // ép kiểu String sang Date
                    Date date = Date.valueOf(ngay);

                    System.out.println("Số lượng hóa đơn ngày: " + hoaDonDao.getSoLuongHoaDonNgay(date));
                    break;
                case 8:
                    System.out.print("Nhập ngày cần tìm: ");
                    String ngay1 = sc.nextLine();
                    Date date1 = Date.valueOf(ngay1);
                    System.out.println("Tổng tiền ngày: " + hoaDonDao.getTongTienNgay(date1));
                    break;
                case 9:
                    System.out.print("Nhập năm cần tìm: ");
                    String nam = sc.nextLine();
                    System.out.println("Doanh thu từng tháng trong năm: ");
                    hoaDonDao.getDoanhThuTungThangTrongNam(nam).forEach(System.out::println);
                    break;
                case 10:
                    System.out.print("Nhập năm cần tìm: ");
                    String nam1 = sc.nextLine();
                    System.out.println("Doanh thu năm: ");
                    hoaDonDao.getDoanhThuNam(nam1).forEach(System.out::println);
                    break;
                case 11:
                    System.out.print("Nhập ngày cần tìm: ");
                    String ngay2 = sc.nextLine();
                    System.out.println("Doanh thu ngày: ");
                    hoaDonDao.getDoanhThuNgay(java.sql.Date.valueOf(ngay2)).forEach(System.out::println);
                    break;
                case 12:
                    System.out.print("Nhập năm cần tìm: ");
                    String nam2 = sc.nextLine();
                    System.out.println("Doanh thu từng tháng năm: ");
                    ArrayList<String[]> list = hoaDonDao.getDoanhThuTungThangNam(nam2);
                    for (String[] strings : list) {
                        System.out.println("Tháng: " + strings[0] + " - Tổng tiền: " + strings[1]);
                    }
                    break;
                case 13:
                    System.out.print("Nhập tháng cần tìm: ");
                    String thang = sc.nextLine();
                    System.out.print("Nhập năm cần tìm: ");
                    String nam3 = sc.nextLine();
                    System.out.println("Doanh thu tháng: ");
                    hoaDonDao.getDoanhThuThang(thang, nam3).forEach(System.out::println);
                    break;
                case 14:
                    System.out.print("Nhập tháng cần tìm: ");
                    String thang1 = sc.nextLine();
                    System.out.print("Nhập năm cần tìm: ");
                    String nam4 = sc.nextLine();
                    System.out.println("Tổng tiền tháng: " + hoaDonDao.getTongTienThang(thang1, nam4));
                    break;
                case 15:
                    System.out.print("Nhập tháng cần tìm: ");
                    String thang2 = sc.nextLine();
                    System.out.print("Nhập năm cần tìm: ");
                    String nam5 = sc.nextLine();
                    System.out.println("Số lượng hóa đơn tháng: " + hoaDonDao.getSoLuongHoaDonThang(thang2, nam5));
                    break;
                case 16:
                    System.out.print("Nhập năm cần tìm: ");
                    String nam6 = sc.nextLine();
                    System.out.println("Số lượng hóa đơn năm: " + hoaDonDao.getSoLuongHoaDonNam(nam6));
                    break;
                case 17:
                    System.out.print("Nhập năm cần tìm: ");
                    String nam7 = sc.nextLine();
                    System.out.println("Tổng tiền năm: " + hoaDonDao.getTongTienNam(nam7));
                    break;

                case 0:
                    System.out.println("Thoát");
                    break;
                default:
                    System.out.println("Chức năng không tồn tại");
                    break;
            }
        } while (choose != 0);


    }

}
