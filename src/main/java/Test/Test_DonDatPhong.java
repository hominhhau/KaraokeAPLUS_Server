package Test;

import Interface.DonDatPhongDao;
import Interface.impl.DonDatPhongImpl;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.PhongHat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Test_DonDatPhong {

    public static void main(String[] args) {

        DonDatPhongDao donDatPhongDao = new DonDatPhongImpl();

        // menu
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            System.out.println("1. Xem danh sách đơn đặt phòng");
            System.out.println("2. Tạo đơn đặt phòng");
            System.out.println("3. Xem đơn đặt phòng theo mã khách hàng");
            System.out.println("4. Xem đơn đặt phòng theo ngày nhận phòng");
            System.out.println("5. Tìm đơn đặt phòng");
            System.out.println("6. Xóa đơn đặt phòng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    donDatPhongDao.getalltbDonDatPhong().forEach(System.out::println);
                    break;
                case 2:
                    //DonDatPhong(String maDonDatPhong, LocalDateTime ngayDat, LocalDateTime ngayNhan, PhongHat phongHat,
                    //                       KhachHang khachHang)
                    LocalDateTime ngayDat = LocalDateTime.now();
                    LocalDateTime ngayNhan = LocalDateTime.now();
                    DonDatPhong ddp = new DonDatPhong("DDP001", ngayDat, ngayNhan, new PhongHat("P001"), new KhachHang("KH001"));

                    donDatPhongDao.createDonDatPhong(ddp);
                    break;
                case 3:
                    System.out.print(" Nhập mã khách hàng: ");
                    String maKH1 = sc.nextLine();
                    donDatPhongDao.getDonDatPhongTheoMaKH(maKH1).forEach(System.out::println);
                    break;
                case 4:
                    break;
                case 5:
                    System.out.print("Nhập mã đơn đặt phòng: ");
                    String maDDP1 = sc.nextLine();
                    System.out.print("Nhập mã khách hàng: ");
                    String maKH2 = sc.nextLine();
                    System.out.print("Nhập mã phòng: ");
                    String maPhong1 = sc.nextLine();
                    donDatPhongDao.timDonDatPhong(maDDP1, maKH2, maPhong1).forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Nhập mã đơn đặt phòng: ");
                    String maDDP2 = sc.nextLine();
                    System.out.print("Nhập mã phòng: ");
                    String maPhong2 = sc.nextLine();
                    donDatPhongDao.deleteDonDatPhong(maDDP2, maPhong2);
                    break;
                case 0:
                    System.out.println("Thoát");
                    break;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại!");
                    break;

            }
        } while (choose != 0);

    }


}
