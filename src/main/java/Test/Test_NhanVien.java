package Test;

import Interface.NhanVienDao;
import Interface.TaiKhoanDao;
import Interface.impl.NhanVienImpl;
import Interface.impl.TaiKhoanImpl;
import entity.LoaiNhanVien;
import entity.NhanVien;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_NhanVien {
    private static NhanVienDao nhanVienDao;

    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        try{
//            tx.begin();
//            nhanVienDao = new NhanVienImpl();
//            ArrayList<NhanVien> dsNhanVien = nhanVienDao.getalltbNhanVien();
//            System.out.println("Danh sách nhân viên: " + dsNhanVien.size());
//            tx.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//            tx.rollback();
//        }finally {
//            em.close();
//            emf.close();
//        }

        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("Chọn chức năng:");
            System.out.println("1. TEST getalltbNhanVien");
            System.out.println("2. TEST getNhanVienTheoMaNV");
            System.out.println("3. TEST getNVTheoLoai");
            System.out.println("4. TEST addStaff");
            System.out.println("5. TEST editStaff");
            System.out.println("6. TEST findStaff");
            System.out.println("0. Thoát");

            int choose = sc.nextInt();
            sc.nextLine();

            switch (choose){
                case 1:
                    nhanVienDao = new NhanVienImpl();
                    System.out.println("----------TEST getalltbNhanVien----------");
                    ArrayList<NhanVien> dsNhanVien1 = nhanVienDao.getalltbNhanVien();
                    System.out.println("Danh sách nhân viên: ");
                    for (NhanVien nv : dsNhanVien1){
                        System.out.println(nv.getMaNV() + " - " + nv.getTenNV() + " - " + nv.isGioiTinh() + " - " + nv.getCCCD() + " - " + nv.getSDT() + " - " + nv.getDiaChi() + " - " + nv.getCaLam() + " - " + nv.getLoaiNV());
                    }
                    break;
                case 2:
                    nhanVienDao = new NhanVienImpl();
                    System.out.println("----------TEST getNhanVienTheoMaNV----------");
                    ArrayList<NhanVien> dsNhanVien2 = nhanVienDao.getNhanVienTheoMaNV("NV001");
                    System.out.println("Danh sách nhân viên theo mã nhân viên: ");
                    for (NhanVien nv : dsNhanVien2){
                        System.out.println(nv.getMaNV() + " - " + nv.getTenNV() + " - " + nv.isGioiTinh() + " - " + nv.getCCCD() + " - " + nv.getSDT() + " - " + nv.getDiaChi() + " - " + nv.getCaLam() + " - " + nv.getLoaiNV());
                    }
                    break;
                case 3:
                    nhanVienDao = new NhanVienImpl();
                    System.out.println("----------TEST getNVTheoLoai----------");
                    ArrayList<NhanVien> dsNhanVien3 = nhanVienDao.getNVTheoLoai("NVQL");
                    System.out.println("Danh sách nhân viên theo loại nhân viên: ");
                    for (NhanVien nv : dsNhanVien3){
                        System.out.println(nv.getMaNV() + " - " + nv.getTenNV() + " - " + nv.isGioiTinh() + " - " + nv.getCCCD() + " - " + nv.getSDT() + " - " + nv.getDiaChi() + " - " + nv.getCaLam() + " - " + nv.getLoaiNV());
                    }
                    break;
                case 4:
                    nhanVienDao = new NhanVienImpl();
                    System.out.println("----------TEST addStaff----------");
                    NhanVien nv = new NhanVien("NV0010", "Nguyễn Quỳnh My", true, "123456789012", "0123456789", "Hà Nội", "Ca 1", new LoaiNhanVien("NVQL"));
                    boolean result = nhanVienDao.addStaff(nv);
                    if (result){
                        TaiKhoanDao taiKhoanDao = new TaiKhoanImpl();
                        taiKhoanDao.taoTK(nv.getMaNV());
                        System.out.println("Thêm nhân viên thành công!");
                    }else {
                        System.out.println("Thêm nhân viên thất bại!");
                    }
                    break;

                case 5:
                    nhanVienDao = new NhanVienImpl();
                    System.out.println("----------TEST editStaff----------");
                    NhanVien nv1 = new NhanVien("NV007", "Nguyễn Quỳnh Anh", true, "123456789012", "0123456789", "Hà Nội", "Ca 2", new LoaiNhanVien("NVQL"));
                    boolean result1 = nhanVienDao.editStaff(nv1);
                    if (result1){
                        System.out.println("Sửa nhân viên thành công!");
                    }else {
                        System.out.println("Sửa nhân viên thất bại!");
                    }
                    break;
                case 6:
                    nhanVienDao = new NhanVienImpl();
                    System.out.println("----------TEST findStaff----------");
                    NhanVien nv2 = nhanVienDao.findStaff("NV007");
                    System.out.println("Nhân viên cần tìm: " + nv2.getMaNV() + " - " + nv2.getTenNV() + " - " + nv2.isGioiTinh() + " - " + nv2.getCCCD() + " - " + nv2.getSDT() + " - " + nv2.getDiaChi() + " - " + nv2.getCaLam() + " - " + nv2.getLoaiNV().getMaLoai());
                    break;

                case 0:
                    System.exit(0);
                    break;
        }
    }
}
}
