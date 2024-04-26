package Test;

import Interface.NhanVienDao;
import Interface.QuenMatKhauDao;
import Interface.impl.QuenMatKhauImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Test_QuenMatKhau {
    private static QuenMatKhauDao quenMatKhauDao;
    private static NhanVienDao nhanVienDao = new Interface.impl.NhanVienImpl();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
        EntityManager em = emf.createEntityManager();

                try{
            try {
//                quenMatKhauDao = new QuenMatKhauImpl();
//                if(nhanVienDao.SDT_tonTaiNV("0909666888")) {
//                    System.out.println("SDT co trong bang nhan vien");
//                } else {
//                    System.out.println("SDT khong co trong bang nhan vien");
//                }
                quenMatKhauDao = new QuenMatKhauImpl();
                quenMatKhauDao.updatePasswordTaiKhoan("0909666888", "123");
                System.out.println("Update password thanh cong");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
//        Scanner sc = new Scanner(System.in);
//        int choose = sc.nextInt();
//        while(true){
//            System.out.println("1. Test updatePasswordTaiKhoan");
//            System.out.println("2. Test SDT_tonTaiNV");
//            System.out.println("3. Exit");
//
//            switch (choose){
//                case 1:
//                    System.out.println("Test updatePasswordTaiKhoan");
//                    try {
//                        quenMatKhauDao = new QuenMatKhauImpl();
//                        quenMatKhauDao.updatePasswordTaiKhoan("0909666888", "123456");
//                        System.out.println("Update password thanh cong");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case 2:
//                    System.out.println("Test SDT_tonTaiNV");
//                    try {
//                        quenMatKhauDao = new QuenMatKhauImpl();
//                        if(nhanVienDao.SDT_tonTaiNV("0909666888")) {
//                            System.out.println("SDT co trong bang nhan vien");
//                        } else {
//                            System.out.println("SDT khong co trong bang nhan vien");
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case 3:
//                    System.exit(0);
//                    break;
//        }
//
//
//    }
}}


