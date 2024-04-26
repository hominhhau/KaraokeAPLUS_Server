package Test;

import Interface.ChiTietHoaDonPhongDao;
import Interface.HoaDonDao;
import Interface.impl.ChiTietHoaDonPhongImpl;
import entity.ChiTietHoaDonPhong;
import entity.HoaDon;
import entity.PhongHat;

import java.util.Scanner;

public class Test_ChiTietHoaDonPhong {
    private static ChiTietHoaDonPhongDao chiTietHoaDonPhongDao;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        chiTietHoaDonPhongDao = new ChiTietHoaDonPhongImpl();
        // menu
        int choose;

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Lấy danh sách chi tiết hóa đơn phòng");
            System.out.println("2. Lấy chi tiết hóa đơn phòng theo mã hóa đơn và mã phòng");
            System.out.println("3. Thêm chi tiết hóa đơn phòng");
            System.out.println("4. Tìm hóa đơn theo mã phòng");
            System.out.println("5. Tìm hóa đơn theo mã phòng đã thanh toán");
            System.out.println("6. Cập nhật giờ ra và giá");
            System.out.println("7. Chuyển phòng");
            System.out.println("8. Lấy danh sách chi tiết hóa đơn phòng theo mã hóa đơn");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.println(chiTietHoaDonPhongDao.getalltbChiTietHoaDonPhong());
                    break;
                case 2:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD = sc.nextLine();
                    System.out.print("Nhập mã phòng: ");
                    String maPhong = sc.nextLine();
                    System.out.println(chiTietHoaDonPhongDao.getChiTietHoaDonPhongTheoMaHD(maHD, maPhong));

                    break;
                case 3:
                    ChiTietHoaDonPhong cthdp = new ChiTietHoaDonPhong(new HoaDon("HD001"), new PhongHat("PH001"), 1000.0, null, null, "Ghi chú");
                    System.out.println(chiTietHoaDonPhongDao.createChiTietHoaDonPhong(cthdp));


                    break;
                case 4:
                    System.out.print("Nhập mã phòng: ");
                    String maPhong2 = sc.nextLine();
                    System.out.println(chiTietHoaDonPhongDao.finHDByRoomID(maPhong2));
                    break;
                case 5:
                    System.out.print("Nhập mã phòng: ");
                    String maPhong3 = sc.nextLine();
                    System.out.println(chiTietHoaDonPhongDao.finHDByRoomIDDaTT(maPhong3));
                    break;
                case 6:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD4 = sc.nextLine();
                    System.out.print("Nhập giờ ra: ");
                    String gioRa = sc.nextLine();
                    System.out.print("Nhập giá: ");
                    Float gia1 = sc.nextFloat();
                    System.out.print("Nhập mã phòng: ");
                    String maPhong4 = sc.nextLine();
                    System.out.println(chiTietHoaDonPhongDao.updateGioRaVsGia(maHD4, null, gia1, maPhong4));
                    break;
                case 7:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD5 = sc.nextLine();
                    System.out.print("Nhập mã phòng hiện tại: ");
                    String maPhong5 = sc.nextLine();
                    System.out.print("Nhập mã phòng mới: ");
                    String maPhong6 = sc.nextLine();
                    System.out.print("Nhập ghi chú: ");
                    String ghiChu1 = sc.nextLine();
                    System.out.println(chiTietHoaDonPhongDao.doiPhong(maHD5, maPhong5, maPhong6, ghiChu1));
                    break;
                case 8:
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD6 = sc.nextLine();
                    System.out.println(chiTietHoaDonPhongDao.getAllTheMaHDArray(maHD6));
                    break;
                case 0:
                    System.out.println("Thoát");
                    break;

            }
        } while (choose != 0);


    }

}
