package server;

import Interface.*;
import Interface.impl.*;
import entity.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Server {
    private static final int SERVER_PORT = 7900;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started on port " + SERVER_PORT);

            while (true) {
                Socket client = server.accept();

                System.out.println("Client connected");
                System.out.println("Client IP: " + client.getInetAddress().getHostName());
                System.out.println("Client Port: " + client.getPort());
                Thread t = new Thread(new ClientHandler(client));
                t.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private LoaiPhongDao loaiPhongDao;
    private PhongHatDao phongHatDao;
    private MatHangDao matHangDao;
    private KhuyenMaiDao khuyenMaiDao;
    private HoaDonDao hoaDonDao;
    private ChiTietHoaDonPhongDao cthdpDao;
    private DonDatPhongDao donDatPhongDao;
    private KhachHangDao khachHangDao;
    private ChiTietHoaDonDichVuDao cthdDVDao;
    private LoaiNVDao loaiNVDao;
    private NhanVienDao nhanVienDao;
    private QuenMatKhauDao quenMatKhauDao;
    private TaiKhoanDao taiKhoanDao;
    private DoiMatKhauDao doiMatKhauDao;

    public ClientHandler(Socket client) {
        super();
        this.socket = client;
        this.loaiPhongDao = new LoaiPhongImpl();
        this.phongHatDao = new PhongHatImpl();
        this.matHangDao = new MatHangImpl();
        this.khuyenMaiDao = new KhuyenMaiImpl();
        this.hoaDonDao = new HoaDonImpl();
        this.cthdpDao = new ChiTietHoaDonPhongImpl();
        this.donDatPhongDao = new DonDatPhongImpl();
        this.khachHangDao = new KhachHangImpl();
        this.cthdDVDao = new ChiTietHoaDonDichVuImpl();
        this.loaiNVDao = new LoaiNVImpl();
        this.nhanVienDao = new NhanVienImpl();
        this.quenMatKhauDao = new QuenMatKhauImpl();
        this.taiKhoanDao = new TaiKhoanImpl();
        this.doiMatKhauDao = new DoiMatKhauImpl();


    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {
            while (true) {
//                String request = (String) ois.readObject();
                String request = null;
                try {
                    request = (String) ois.readObject();
                } catch (ClassNotFoundException e) {
                    // Handle the case where the class of the serialized object is not found
                    // Handle the case where the class of the serialized object is not found
                    e.printStackTrace(); // or log the error
                } catch (IOException e) {
                    // Handle IO-related errors
                    e.printStackTrace(); // or log the error
                }
               if(request == null){
                   break;

               }
                switch (request) {


                    case "createKhuyenMai":
                        KhuyenMai km1 = (KhuyenMai) ois.readObject();
                        boolean result = khuyenMaiDao.createKhuyenMai(km1);
                        oos.writeBoolean(result);
                        break;
//                    editKhuyenMai
                    case "editKhuyenMai":
                        KhuyenMai km2 = (KhuyenMai) ois.readObject();
                        boolean result1 = khuyenMaiDao.editKhuyenMai(km2);
                        oos.writeBoolean(result1);
                        break;
//                    DeleteKhuyenMai
                    case "DeleteKhuyenMai":
                        String maKM1 = (String) ois.readObject();
                        boolean result2 = khuyenMaiDao.DeleteKhuyenMai(maKM1);
                        oos.writeBoolean(result2);
                        break;

                    case "getSoLuongKhachHang":
                        long soLuongKH = khachHangDao.getSoLuongKhachHang();
                        oos.writeLong(soLuongKH);
                        System.out.println("Server: " + soLuongKH);
                        oos.flush();
                        break;
//
//                    case "getalltbKhachHang":
//                        ArrayList<KhachHang> khachHangs2 = khachHangDao.getalltbKhachHang();
//                        oos.writeObject(khachHangs2);
//                        break;
////
                    case "createChiTietHoaDonPhong":
                        ChiTietHoaDonPhong cthdp = (ChiTietHoaDonPhong) ois.readObject();
                        boolean resultCT = cthdpDao.createChiTietHoaDonPhong(cthdp);
                        oos.writeBoolean(resultCT);
                        oos.flush(); // Gửi kết quả về client
                        break;
//                    case "updateGioRaVsGia":
//                        //(String maHD, LocalDateTime gioRa, Float gia, String maPhong)
//                        String maHD5 = (String) ois.readObject();
//                        LocalDateTime gioRa = (LocalDateTime) ois.readObject();
//                        Float gia = (Float) ois.readObject();
//                        String maPhong5 = (String) ois.readObject();
//                        boolean resultU = cthdpDao.updateGioRaVsGia(maHD5, gioRa, gia, maPhong5);
//
//                        oos.writeBoolean(resultU);
//
//                        if (resultU) {
//                            System.out.println("Server: " + "Cập nhật giờ ra và giá thành công");
//
//                        }
//                        break;
//
//                    case "getChiTietHoaDonDVTheoMaHD1":
//                        String maHD6 = (String) ois.readObject();
//                        ArrayList<ChiTietHoaDonPhong> cthdp3 = cthdpDao.getAllTheMaHDArray(maHD6);
//                        oos.writeObject(cthdp3);
//                        oos.flush();
//                        break;
//                    addMatHang
                    case "addMatHang":
                        MatHang mh = (MatHang) ois.readObject();
                        boolean result9 = matHangDao.addMatHang(mh);
                        oos.writeBoolean(result9);
                        break;
//                    DeleteMatHang
                    case "DeleteMatHang":
                        String maMH = (String) ois.readObject();
                        boolean result10 = matHangDao.DeleteMatHang(maMH);
                        oos.writeBoolean(result10);
                        break;
//                    editMatHang
                    case "editMatHang":
                        MatHang mhT = (MatHang) ois.readObject();
                        boolean result11 = matHangDao.editMatHang(mhT);
                        oos.writeBoolean(result11);
                        break;

                    case "getMHThang":
                        String thang = (String) ois.readObject();
                        String nam = (String) ois.readObject();
                        ArrayList<String[]> matHangs2 = matHangDao.getMHThang(thang, nam);
                        oos.writeObject(matHangs2);
                        break;

//                    getMHNam
                    case "getMHNam":
                        String namss = (String) ois.readObject();
                        ArrayList<String[]> matHangs3 = matHangDao.getMHNam(namss);
                        oos.writeObject(matHangs3);
                        break;
                    // Trong phương thức xử lý yêu cầu từ máy khách
                    case "getTongSoMH":
                        int tongSoMH = matHangDao.getTongSoMH();
                        oos.writeInt(tongSoMH);
                        oos.flush();
                        break;
//                    case "findMatHang":
//                        String maTimMH = (String) ois.readObject();
//                        MatHang mh1 = matHangDao.findMatHang(maTimMH);
//                        oos.writeObject(mh1);
//                        break;

                    case "getalltbMatHang":
                        ArrayList<MatHang> matHangs = matHangDao.getalltbMatHang();
                        oos.writeObject(matHangs);
                        break;
                    /**
                     * Loaiphong
                     */


                    case "getMHNgay":
                        Date date2 = (Date) ois.readObject();
                        ArrayList<String[]> matHangs1 = matHangDao.getMHNgay(date2);
                        oos.writeObject(matHangs1);
                        break;

                    case "getTOPDVNgay":
                        Date date3 = (Date) ois.readObject();
                        ArrayList<String[]> dvNgay = cthdDVDao.getTOPDVNgay(date3);
                        oos.writeObject(dvNgay);
                        break;

                    //TongTienNgayDV
                    case "getTongTienNgayDV":
                        Date date4 = (Date) ois.readObject();
                        double tongTienNgayDV = cthdDVDao.getTongTienNgayDV(date4);
                        oos.writeDouble(tongTienNgayDV);
                        break;

                    case "getTOPDVNam":
                        String nams = (String) ois.readObject();
                        ArrayList<String[]> dvnam = cthdDVDao.getTOPDVNam(nams);
                        oos.writeObject(dvnam);
                        break;
                    case "getTOPDVThang":
                        String thangss = (String) ois.readObject();
                        String namss1 = (String) ois.readObject();
                        ArrayList<String[]> dvthang = cthdDVDao.getTOPDVThang(thangss, namss1);
                        oos.writeObject(dvthang);
                        break;
//                    getTongTienMHNgay
                    case "getTongTienMHNgay":
                        Date date5 = (Date) ois.readObject();
                        double tongTienMHNgay = matHangDao.getTongTienMHNgay(date5);
                        oos.writeDouble(tongTienMHNgay);
                        break;
                    case "getTongTienThangTop":
                        String thangss1 = (String) ois.readObject();
                        String namss2 = (String) ois.readObject();
                        double tongTienThangMH = cthdDVDao.getTongTienThangTop(thangss1, namss2);
                        oos.writeDouble(tongTienThangMH);
                        break;

                    /**
                     *
                     *
                     * Hoa Don
                     */


//                    case "finHDByRoomID":
//                        String roomID = (String) ois.readObject();
//                        PhongHat ph1 = phongHatDao.getPhongHatByMaPhong(roomID);
//                        oos.writeObject(ph1);
//                        oos.flush();
//                        System.out.println("Server: " + ph1.toString());
//                        break;
//                    case "getalltbHoaDon":
//                        ArrayList<HoaDon> hoaDons = hoaDonDao.getalltbHoaDon();
//                        oos.writeObject(hoaDons);
//                        break;
//                    case "createHoaDon":
//                        HoaDon hd = (HoaDon) ois.readObject();
//                        boolean result12 = hoaDonDao.createHoaDon(hd);
//                        oos.writeBoolean(result12);
//                        oos.flush();
//                        break;
                    //                    getSoLuongHoaDon
                    case "getSoLuongHoaDon":
                        int soLuongHD = hoaDonDao.getSoLuongHoaDon();
                        oos.writeInt(soLuongHD);
                        oos.flush(); // Đảm bảo gửi đi
                        break;
//                    case "getHoaDonTheoMaHD":
//                        String maHD1 = (String) ois.readObject();
//                        HoaDon hd1 = hoaDonDao.getHoaDonTheoMaHD(maHD1);
//                        oos.writeObject(hd1);
//                        break;
////                    updateTongTien
//                    case "updateTongTien":
//                        String maHD2 = (String) ois.readObject();
//                        Double tongTien = (Double) ois.readObject();
//                        String maKMs = (String) ois.readObject();
//                        boolean result15 = hoaDonDao.updateTongTien(maHD2, tongTien, maKMs);
//                        oos.writeBoolean(result15);
//                        break;
//                    getHoaDonTheoMaKH
                    case "getHoaDonTheoMaKH":
                        String maKH2 = (String) ois.readObject();
                        ArrayList<HoaDon> hoaDons1 = hoaDonDao.getHoaDonTheoMaKH(maKH2);
                        oos.writeObject(hoaDons1);
                        break;


//                    getDoanhThuTungThangTrongNam
                    case "getDoanhThuTungThangTrongNam":
                        String nams1 = (String) ois.readObject();
                        ArrayList<HoaDon> hoaDons2 = hoaDonDao.getDoanhThuTungThangTrongNam(nams1);
                        oos.writeObject(hoaDons2);
                        break;
//                    getDoanhThuNam
                    case "getDoanhThuNam":
                        String nam1 = (String) ois.readObject();
                        ArrayList<HoaDon> hoaDons3 = hoaDonDao.getDoanhThuNam(nam1);
                        oos.writeObject(hoaDons3);
                        break;


                    case "getDoanhThuNgay":
                        Date ngay = (Date) ois.readObject();
                        ArrayList<HoaDon> hoaDons4 = hoaDonDao.getDoanhThuNgay(ngay);
                        oos.writeObject(hoaDons4);
                        break;


//                    getDoanhThuTungThangNam
                    case "getDoanhThuTungThangNam":
                        String nam2 = (String) ois.readObject();
                        ArrayList<String[]> hoaDons5 = hoaDonDao.getDoanhThuTungThangNam(nam2);
                        oos.writeObject(hoaDons5);
                        break;
//                    getDoanhThuThang
                    case "getDoanhThuThang":
                        String thangs = (String) ois.readObject();
                        String nam3 = (String) ois.readObject();
                        ArrayList<HoaDon> hoaDons6 = hoaDonDao.getDoanhThuThang(thangs, nam3);
                        oos.writeObject(hoaDons6);
                        break;
//                    getTongTienThang
                    case "getTongTienThang":
                        String thang1 = (String) ois.readObject();
                        String nam4 = (String) ois.readObject();
                        double tongTienThang = hoaDonDao.getTongTienThang(thang1, nam4);
                        oos.writeDouble(tongTienThang);
                        break;
//                    getSoLuongHoaDonThang
                    case "getSoLuongHoaDonNgay":
                        Date date = (Date) ois.readObject();
                        int soLuongHDNgay = hoaDonDao.getSoLuongHoaDonNgay(date);
                        oos.writeInt(soLuongHDNgay);
                        oos.flush();
                        break;
                    case "getSoLuongHoaDonThang":
                        String thang2 = (String) ois.readObject();
                        String nam5 = (String) ois.readObject();
                        int soLuongHDThang = hoaDonDao.getSoLuongHoaDonThang(thang2, nam5);
                        oos.writeInt(soLuongHDThang);
                        oos.flush();
                        break;
                    case "getSoLuongHoaDonNam":
                        String nam6 = (String) ois.readObject();
                        int soLuongHDNam = hoaDonDao.getSoLuongHoaDonNam(nam6);
                        oos.writeInt(soLuongHDNam);
                        oos.flush(); // Đảm bảo gửi đi
                        break;
//                    getTongTienNam
                    case "getTongTienNam":
                        String nam7 = (String) ois.readObject();
                        double tongTienNam = hoaDonDao.getTongTienNam(nam7);
                        oos.writeDouble(tongTienNam);
                        break;
                    /**
                     *
                     * PhongHat
                     */

//                    addPhongHat
                    case "addPhongHat":
                        PhongHat ph = (PhongHat) ois.readObject();
                        boolean result16 = phongHatDao.addPhongHat(ph);
                        oos.writeBoolean(result16);
                        break;
//                    editPhongHat
                    case "editPhongHat":
                        PhongHat ph2 = (PhongHat) ois.readObject();
                        boolean result13 = phongHatDao.editPhongHat(ph2);
                        oos.writeBoolean(result13);
                        break;

                    case "DeletePhongHat":
                        String maPH = (String) ois.readObject();
                        boolean result14 = phongHatDao.DeletePhongHat(maPH);
                        oos.writeBoolean(result14);
                        break;
                    //
                    case "getSoPhongTrong":
                        long soPhongTrong = phongHatDao.getSoPhongTrong();
                        oos.writeLong(soPhongTrong);
                        System.out.println("Server: " + soPhongTrong);
                        oos.flush();
                        break;
//
//                    case "getAllPhongHat":
//                        ArrayList<PhongHat> phongHats = phongHatDao.getAllPhongHat();
//                        oos.writeObject(phongHats);
//                        break;
//                    case "getPhongByTinhTrang":
//                        String tinhTrang = (String) ois.readObject();
//                        ArrayList<PhongHat> phongHatsByTinhTrang = phongHatDao.getPhongByTinhTrang(tinhTrang);
//                        oos.writeObject(phongHatsByTinhTrang);
//                        System.out.println("Server: " + phongHatsByTinhTrang.toString());
//                        break;

//                    case "getPhongHatByMaPhong":
//                        String maPhong1 = (String) ois.readObject();
//                        PhongHat ph11 = phongHatDao.getPhongHatByMaPhong(maPhong1);
//                        oos.writeObject(ph11);
//                        System.out.println("Server: " + ph11.toString());
//                        break;
                    //doiPhong
//                    case "doiPhong":
//                        //String maHD, String maPhongHienTai, String maPhongMoi, String ghiChu
//                        String maHD = (String) ois.readObject();
//                        String maPhongHienTai = (String) ois.readObject();
//                        String maPhongMoi = (String) ois.readObject();
//                        String ghiChu = (String) ois.readObject();
//                        boolean resultss = cthdpDao.doiPhong(maHD, maPhongHienTai, maPhongMoi, ghiChu);
//                        oos.writeBoolean(resultss);
//                        break;
////                    //updateTinhTrangPhong//updateTinhTrangPhong(String maPhong, String tinhTrangMoi)
////                    case "updateTinhTrangPhong":
////                        String maPhong2 = (String) ois.readObject();
////                        String tinhTrangMoi = (String) ois.readObject();
////                        boolean results = phongHatDao.updateTinhTrangPhong(maPhong2, tinhTrangMoi);
////                        oos.writeBoolean(results);
////                        break;

//                    getTongSoPhong
                    case "getTongSoPhong":
                        long tong = phongHatDao.getTongSoPhong();
                        oos.writeLong(tong);
                        oos.flush();
                        break;

//
                    case "getTongTienNgay":
                        Date ngay1 = (Date) ois.readObject();
                        double tongTienNgay = hoaDonDao.getTongTienNgay(ngay1);
                        oos.writeDouble(tongTienNgay);
                        break;



                    /**
                     * ======= NHAN VIEN =======
                     */
                    case "getalltbNhanVien":
                        ArrayList<NhanVien> nhanViens = nhanVienDao.getalltbNhanVien();
                        oos.writeObject(nhanViens);
                        System.out.println("Server: " + nhanViens.toString());
                        oos.flush();
                        break;
                    case "getNhanVienTheoMaNV":
                        ArrayList<NhanVien> nhanViens1 = nhanVienDao.getNhanVienTheoMaNV((String) ois.readObject());
                        oos.writeObject(nhanViens1);
                        break;
                    case "getNVTheoLoai":
                        ArrayList<NhanVien> nhanViens2 = nhanVienDao.getNVTheoLoai((String) ois.readObject());
                        oos.writeObject(nhanViens2);
                        break;
                    case "addStaff":
                        NhanVien nv = (NhanVien) ois.readObject();
                        boolean resultadd = nhanVienDao.addStaff(nv);
                        oos.writeBoolean(resultadd);
                        oos.flush();
                        break;

                    case "findStaff":
                        String maTimNV = (String) ois.readObject();
                        NhanVien nv1 = nhanVienDao.findStaff(maTimNV);
                        oos.writeObject(nv1);
                        oos.flush();
                        break;
                    case "editStaff":
                        NhanVien nv2 = (NhanVien) ois.readObject();
                        boolean resultedit = nhanVienDao.editStaff(nv2);
                        oos.writeBoolean(resultedit);
                        oos.flush();
                        break;
                    case "SDT_tonTaiNV":
                        String soDienThoai = (String) ois.readObject();
                        boolean result17 = quenMatKhauDao.SDT_tonTaiNV(soDienThoai);
                        oos.writeBoolean(result17);
                        System.out.println("Server: " + result17);
                        oos.flush();
                        break;

/**
 * ======= TAI KHOAN =======
 */
                    case "authenticate":
                        String maNV = (String) ois.readObject();
                        String matKhau = (String) ois.readObject();
                        boolean result18 = taiKhoanDao.authenticate(maNV, matKhau);
                        System.out.println("Server authenticate: " + result18);
                        oos.writeBoolean(result18);
                        oos.flush();
                        break;
                    case "taoTK":
                        String maNV1 = (String) ois.readObject();
                        boolean result19 = taiKhoanDao.taoTK(maNV1);
                        System.out.println("Server: " + result19);
                        oos.writeBoolean(result19);
                        oos.flush();
                        break;
                    case "nhoMK":
                        String maNV2 = (String) ois.readObject();
                        StringBuilder password1 = new StringBuilder();
                        boolean result20 = taiKhoanDao.nhoMK(maNV2, password1);
                        System.out.println("Server: " + result20);
                        oos.writeBoolean(result20);
                        oos.writeObject(password1.toString());
                        oos.flush();
                        break;
                    case "doiMatKhau":
                        String maNV3 = (String) ois.readObject();
                        String matKhauMoi = (String) ois.readObject();
                        boolean result21 = doiMatKhauDao.doiMatKhau(maNV3, matKhauMoi);
                        System.out.println("Server DMK : " + result21);
                        oos.writeBoolean(result21);
                        oos.flush();
                        break;
                    case "updatePasswordTaiKhoan":
                        String soDienThoai1 = (String) ois.readObject();
                        String matKhauMoi1 = (String) ois.readObject();
                        boolean result22 = quenMatKhauDao.updatePasswordTaiKhoan(soDienThoai1, matKhauMoi1);
                        oos.writeBoolean(result22);
                        System.out.println("Server QMK : " + result22);
                        oos.flush();
                        break;

                    /**
                     * ======= LOAI NHAN VIEN =======
                     */
                    case "getAllLoaiNhanVien":
                        ArrayList<LoaiNhanVien> loaiNhanViens = loaiNVDao.getAllLoaiNhanVien();
                        oos.writeObject(loaiNhanViens);
                        oos.flush();
                        break;

//                    createChiTietHoaDonPhongList
                    case "createChiTietHoaDonPhongList":
                        ArrayList<ChiTietHoaDonPhong> cthdpList = (ArrayList<ChiTietHoaDonPhong>) ois.readObject();
                        cthdpDao.createChiTietHoaDonPhongList(cthdpList);
                        break;









































                    case "getAllPhongHat":
                        ArrayList<PhongHat> phongHats = phongHatDao.getAllPhongHat();
                        oos.writeObject(phongHats);
                        oos.flush();
                        break;
                    case "getPhongByTinhTrang":
                        String tinhTrang = (String) ois.readObject();
                        ArrayList<PhongHat> phongHatsByTinhTrang = phongHatDao.getPhongByTinhTrang(tinhTrang);
                        oos.writeObject(phongHatsByTinhTrang);
                        oos.flush();
                        break;
//
                    case "getPhongHatByMaPhong":
                        String maPhong1 = (String) ois.readObject();
                        PhongHat ph2a = phongHatDao.getPhongHatByMaPhong(maPhong1);
                        oos.writeObject(ph2a);
                        System.out.println("Server: " + ph2a.toString());
                        break;
                    //doiPhong
                    case "doiPhongforPhong":
                        //String maHD, String maPhongHienTai, String maPhongMoi, String ghiChu
                        String maHD1s = (String) ois.readObject();
                        String maPhongHienTai1 = (String) ois.readObject();
                        String maPhongMoi1 = (String) ois.readObject();
                        String ghiChu1 = (String) ois.readObject();
                        boolean result1s = cthdpDao.doiPhong(maHD1s, maPhongHienTai1, maPhongMoi1, ghiChu1);
                        oos.writeBoolean(result1s);
                        oos.flush();
                        break;
                    // "doiPhongforDV":
                    case "doiPhongforDV":
                        //String maHD, String maPhongHienTai, String maPhongMoi, String ghiChu
                        String maHD1a = (String) ois.readObject();
                        String maPhongHienTai1a = (String) ois.readObject();
                        String maPhongMoi1a = (String) ois.readObject();
                        boolean result2a = cthdDVDao.doiPhong(maHD1a, maPhongHienTai1a, maPhongMoi1a);
                        oos.writeBoolean(result2a);
                        oos.flush();
                        break;
                    //updateTinhTrangPhong//updateTinhTrangPhong(String maPhong, String tinhTrangMoi)
                    case "updateTinhTrangPhong":
                        String maPhong2 = (String) ois.readObject();
                        String tinhTrangMoi = (String) ois.readObject();
                        boolean results = phongHatDao.updateTinhTrangPhong(maPhong2, tinhTrangMoi);
                        oos.writeBoolean(results);
                        oos.flush();
                        break;


                    case "getKhachHangTheoSdtKH":
                        String sdt = (String) ois.readObject();
                        ArrayList<KhachHang> khachHangs = khachHangDao.getKhachHangTheoSdtKH(sdt);
                        oos.writeObject(khachHangs);
                        oos.flush();
                        break;
                    case "getDonDatPhongTheoMaKH":
                        String maKH = (String) ois.readObject();
                        ArrayList<DonDatPhong> donDatPhongs = donDatPhongDao.getDonDatPhongTheoMaKH(maKH);
                        oos.writeObject(donDatPhongs);
                        break;
                    case "getDonDatPhongTheoNgayNhanPhong":
                        LocalDate ngayNhanPhong = (LocalDate) ois.readObject();
                        ArrayList<DonDatPhong> donDatPhongs1 = donDatPhongDao.getDonDatPhongTheoNgayNhanPhong(ngayNhanPhong);
                        oos.writeObject(donDatPhongs1);
                        break;
                    case "createDonDatPhong":
                        DonDatPhong ddp = (DonDatPhong) ois.readObject();
                        boolean result3 = donDatPhongDao.createDonDatPhong(ddp);
                        oos.writeObject(result3);
                        oos.flush();
                        break;
                    case "timDonDatPhong":
                        String maDDP = (String) ois.readObject();
                        String maKH1 = (String) ois.readObject();
                        String maPhong6 = (String) ois.readObject();
                        ArrayList<DonDatPhong> donDatPhongs2 = donDatPhongDao.timDonDatPhong(maDDP, maKH1, maPhong6);
                        oos.writeObject(donDatPhongs2);
                        break;
                    case "deleteDonDatPhong":
                        String maDDP1 = (String) ois.readObject();
                        String maPhong4 = (String) ois.readObject();
                        boolean result4 = donDatPhongDao.deleteDonDatPhong(maDDP1, maPhong4);
                        oos.writeObject(result4);
                        break;

                    case "getdataKH":
                        String id = (String) ois.readObject();
                        ArrayList<KhachHang> khachHangs1 = khachHangDao.getdataKH(id);
                        oos.writeObject(khachHangs1);
                        break;


                    case "finHDByRoomID":
                        String roomID = (String) ois.readObject();
                        ChiTietHoaDonPhong cthdp1 = cthdpDao.finHDByRoomID(roomID);
                        oos.writeObject(cthdp1);
//                        oos.flush();
//                        System.out.println("Server: " + cthdp1.toString());
                        break;

                    case "findMatHang":
                        String maTimMH = (String) ois.readObject();
                        MatHang mh1 = matHangDao.findMatHang(maTimMH);
                        oos.writeObject(mh1);
                        break;

//                    getalltbDonDatPhong
                    case "getalltbDonDatPhong":
                        ArrayList<DonDatPhong> donDatPhongs3 = donDatPhongDao.getalltbDonDatPhong();
                        oos.writeObject(donDatPhongs3);
                        break;


//                    getAllKhuyenMai
                    case "getAllKhuyenMai":
                        ArrayList<KhuyenMai> khuyenMais = khuyenMaiDao.getAllKhuyenMai();
                        oos.writeObject(khuyenMais);
                        break;
//                    getKhuyenMai
                    case "getKhuyenMai":
                        String[] khuyenMais1 = khuyenMaiDao.getKhuyenMai();
                        oos.writeObject(khuyenMais1);
                        break;
//                    getKhuyenMaiByMaKM
                    case "getKhuyenMaiByMaKM":
                        String maKM = (String) ois.readObject();
                        KhuyenMai km = khuyenMaiDao.getKhuyenMaiByMaKM(maKM);
                        oos.writeObject(km);
                        break;
//                    getalltbKhachHang
                    case "getalltbKhachHang":
                        ArrayList<KhachHang> khachHanga = khachHangDao.getalltbKhachHang();
                        oos.writeObject(khachHanga);
                        break;
//                    getKhachHangTheoMaKH

                    case "getKhachHangTheoMaKH":
                        ArrayList<KhachHang> khachHangs3 = khachHangDao.getKhachHangTheoMaKH((String) ois.readObject());
                        oos.writeObject(khachHangs3);
                        break;
//                    addCustomer
                    case "addCustomer":
                        KhachHang kh = (KhachHang) ois.readObject();
                        boolean result5 = khachHangDao.addCustomer(kh);
                        oos.writeBoolean(result5);
                        oos.flush();
                        break;
//                    DeleteCustomer
                    case "DeleteCustomer":
                        String maKH11 = (String) ois.readObject();
                        boolean result6 = khachHangDao.DeleteCustomer(maKH11);
                        oos.writeBoolean(result6);
                        break;
//                    findCustomer
                    case "findCustomer":
                        String maTim = (String) ois.readObject();
                        KhachHang kh1 = khachHangDao.findCustomer(maTim);
                        oos.writeObject(kh1);
                        break;
//                    timKhachHangbySDT

                    case "timKhachHangbySDT":
                        String sdt1 = (String) ois.readObject();
                        KhachHang kh2 = khachHangDao.timKhachHangbySDT(sdt1);
                        oos.writeObject(kh2);
                        break;
//                    editCustomer
                    case "editCustomer":
                        KhachHang kh3 = (KhachHang) ois.readObject();
                        boolean result7 = khachHangDao.editCustomer(kh3);
                        oos.writeBoolean(result7);
                        break;
                    case "getalltbChiTietHoaDonPhong":
                        ArrayList<ChiTietHoaDonPhong> cthdp2 = cthdpDao.getalltbChiTietHoaDonPhong();
                        oos.writeObject(cthdp2);
                        oos.flush();
                        break;
//getChiTietHoaDonDVTheoMaHD
                    case "getChiTietHoaDonDVTheoMaHD":
                        String maHD6R = (String) ois.readObject();
                        String maPhong = (String) ois.readObject();
                        ChiTietHoaDonDV cthdp3R = cthdDVDao.getChiTietHoaDonDVTheoMaHD(maHD6R, maPhong);
                        oos.writeObject(cthdp3R);
                        oos.flush();
                        break;
//                    createChiTietHoaDonPhong

//                    case "createChiTietHoaDonPhong":
//                        ChiTietHoaDonPhong cthdp4 = (ChiTietHoaDonPhong) ois.readObject();
//                        boolean result8 = cthdpDao.createChiTietHoaDonPhong(cthdp4);
//                        oos.writeBoolean(result8);
//                        break;
                    case "updateGioRaVsGia":
                        //(String maHD, LocalDateTime gioRa, Float gia, String maPhong)
                        String maHD52 = (String) ois.readObject();
                        LocalDateTime gioRa2 = (LocalDateTime) ois.readObject();
                        Float gia2 = (Float) ois.readObject();
                        String maPhong52 = (String) ois.readObject();
                        boolean resultU2 = cthdpDao.updateGioRaVsGia(maHD52, gioRa2, gia2, maPhong52);

                        oos.writeBoolean(resultU2);

                        oos.flush();


                        if (resultU2) {
                            System.out.println("Server: " + "Cập nhật giờ ra và giá thành công");

                        }


//                    getalltbLoaiPhong
                    case "getalltbLoaiPhong":
                        ArrayList<LoaiPhong> loaiPhongss = loaiPhongDao.getalltbLoaiPhong();
                        oos.writeObject(loaiPhongss);
                        oos.flush();
                        break;
//                    getLoaiPhongTheoMaLoai
                    case "getLoaiPhongTheoMaLoai":
                        String idf = (String) ois.readObject();
                        ArrayList<LoaiPhong> loaiPhongsf = loaiPhongDao.getLoaiPhongTheoMaLoai(idf);
                        oos.writeObject(loaiPhongsf);
                        break;
//                    getalltbHoaDon
                    case "getalltbHoaDon":
                        ArrayList<HoaDon> hoaDonss = hoaDonDao.getalltbHoaDon();
                        oos.writeObject(hoaDonss);
                        oos.flush();
                        break;

//                    createHoaDon
                    case "createHoaDon":
                        HoaDon hde = (HoaDon) ois.readObject();
                        boolean result1e = hoaDonDao.createHoaDon(hde);
                        oos.writeBoolean(result1e);
                        oos.flush();
                        break;

//                    editPhongHat


                    //findChiTietHoaDonDV
                    case "findChiTietHoaDonDV":
                        String maHD7 = (String) ois.readObject();
                        String maKH7 = (String) ois.readObject();
                        ChiTietHoaDonDV cthdDV = cthdDVDao.findChiTietHoaDonDV(maHD7, maKH7);
                        oos.writeObject(cthdDV);
                        break;

                    //updateChiTietHoaDonDV
                    case "updateChiTietHoaDonDV":
                        ChiTietHoaDonDV cthdDV1 = (ChiTietHoaDonDV) ois.readObject();
                        boolean result1c = cthdDVDao.updateChiTietHoaDonDV(cthdDV1);
                        oos.writeObject(result1c);
                        oos.flush();
                        break;

                    //createChiTietHoaDonDV
                    case "createChiTietHoaDonDV":
                        ChiTietHoaDonDV cthdDV2 = (ChiTietHoaDonDV) ois.readObject();
                        boolean result1d = cthdDVDao.createChiTietHoaDonDV(cthdDV2);
                        oos.writeObject(result1d);
                        oos.flush();
                        break;

                    //deleteChiTietHoaDonDV
                    case "deleteChiTietHoaDonDV":
                        String maHD8 = (String) ois.readObject();
                        String maKH8 = (String) ois.readObject();
                        boolean result1a = cthdDVDao.deleteChiTietHoaDonDV(maHD8, maKH8);
                        oos.writeBoolean(result1a);
                        break;

                    //getalltbChiTietHoaDonDV
                    case "getalltbChiTietHoaDonDV":
                        ArrayList<ChiTietHoaDonDV> cthdDVs = cthdDVDao.getalltbChiTietHoaDonDV();
                        oos.writeObject(cthdDVs);
                        break;

                    //getAllTheMaHDDVforRoomArray
                    case "getAllTheMaHDDVforRoomArray":

                        String maHD9 = (String) ois.readObject();
                        String maPhong3 = (String) ois.readObject();
                        ArrayList<ChiTietHoaDonDV> ds = cthdDVDao.getAllTheMaHDDVforRoomArray(maHD9, maPhong3);
                        oos.writeObject(ds);
                        oos.flush();
                        break;

                    //findChiTietHoaDonDVforThem
                    case "findChiTietHoaDonDVforThem":
                        //cthddv_dao.findChiTietHoaDonDVforThem(maHD, data.getMaMH(), frmPH.getRoomSelected())
                        String maHD10 = (String) ois.readObject();
                        String maMH1 = (String) ois.readObject();
                        String maPhong7 = (String) ois.readObject();
                        ChiTietHoaDonDV cthdDV3 = cthdDVDao.findChiTietHoaDonDVforThem(maHD10, maMH1, maPhong7);
                        oos.writeObject(cthdDV3);
                        oos.flush();
                        break;
                    //getHoaDonTheoMaHD
                    case "getHoaDonTheoMaHD":
                        String maHD11 = (String) ois.readObject();
                        System.out.println("mahd thanh toan"+maHD11);
                        HoaDon hd1a = hoaDonDao.getHoaDonTheoMaHD(maHD11);
                        oos.writeObject(hd1a);
                        break;
                    //updateTongTien
                    case "updateTongTien":
                        String maHD12 = (String) ois.readObject();
                        Double tongTiena = (Double) ois.readObject();
                        String maKMa = (String) ois.readObject();
                        boolean resulta = hoaDonDao.updateTongTien(maHD12, tongTiena, maKMa);
                        oos.writeBoolean(resulta);
                        break;
                    //getChiTietHoaDonPhongTheoMaHD
                    case "getChiTietHoaDonPhongTheoMaHD":
                        String id8 = (String) ois.readObject();
                        String maPhong8 = (String) ois.readObject();
                        ChiTietHoaDonPhong cthdp5 = cthdpDao.getChiTietHoaDonPhongTheoMaHD(id8, maPhong8);
                        oos.writeObject(cthdp5);
//                        oos.flush();
//                        System.out.println("Server:getChiTietHoaDonPhongTheoMaHD "+id8 +maPhong8 + cthdp5.toString());
                        break;

//                    getAllTheMaHDArray
                    case "getAllTheMaHDArray":
                        String maHD13 = (String) ois.readObject();
                        ArrayList<ChiTietHoaDonPhong> ds1 = cthdpDao.getAllTheMaHDArray(maHD13);
                        oos.writeObject(ds1);
                        oos.flush();
                        break;


                }

            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
