package Test;

import Interface.KhachHangDao;
import Interface.impl.KhachHangImpl;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_KhachHang {
    private static KhachHangDao khachHangDao;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

		Scanner sc = new Scanner(System.in);


		/**
		 * Case 9 bi bug chua fix duoc
		 */
				while (true) {
					System.out.println("Chọn chức năng:");
					System.out.println("1. TEST getalltbKhachHang");
					System.out.println("2. TEST getKhachHangTheoSdtKH");
					System.out.println("3. TEST getKhachHangTheoMaKH");
					System.out.println("4. TEST addCustomer");
					System.out.println("5. TEST DeleteCustomer");
					System.out.println("6. TEST findCustomer");
					System.out.println("7. TEST timKhachHangbySDT");
					System.out.println("8. TEST editCustomer");
					System.out.println("9. TEST getdataKH");
					System.out.println("10. TEST getSoLuongKhachHang");
					System.out.println("0. Thoát");

					int choose = sc.nextInt();
					sc.nextLine();

					switch (choose) {

						case 1:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST getalltbKhachHang----------");
							ArrayList<KhachHang> dsKhachHang1 = khachHangDao.getalltbKhachHang();
							System.out.println("Danh sách khách hàng: ");
							for (KhachHang kh : dsKhachHang1) {
								System.out.println(kh.getMaKH() + " - " + kh.getTenKH() + " - " + kh.getSdt() + " - " + kh.isGioitinh());
							}
							break;
						case 2:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST getKhachHangTheoSdtKH----------");
							ArrayList<KhachHang> dsKhachHang2 = khachHangDao.getKhachHangTheoSdtKH("0909888777");
							System.out.println("Danh sách khách hàng theo số điện thoại: " );
							for (KhachHang kh : dsKhachHang2) {
								System.out.println(kh.getMaKH() + " - " + kh.getTenKH() + " - " + kh.getSdt() + " - " + kh.isGioitinh());
							}
							break;
						case 3:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST getKhachHangTheoMaKH----------");
							ArrayList<KhachHang> dsKhachHang3 = khachHangDao.getKhachHangTheoMaKH("KH001");
							System.out.println("Danh sách khách hàng theo mã khách hàng: " );
							for (KhachHang kh : dsKhachHang3) {
								System.out.println(kh.getMaKH() + " - " + kh.getTenKH() + " - " + kh.getSdt() + " - " + kh.isGioitinh());
							}
							break;
						case 4:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST addCustomer----------");
							try {
								if(khachHangDao.getKhachHangTheoMaKH("KH003") != null){
									System.out.println("Khách hàng đã tồn tại!");
								}else {
									KhachHang kh = new KhachHang("KH003", "Nguyễn Văn C", "0909888777", true);
									khachHangDao.addCustomer(kh);
									System.out.println("Thêm khách hàng thành công!");
								}
							}catch(Exception e) {
								System.out.println("Lỗi! Không thêm được khách hàng: ");
							}
							break;
						case 5:

							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST DeleteCustomer----------");
							try {
								if(khachHangDao.getKhachHangTheoMaKH("KH003") == null){
									System.out.println("Khách hàng không tồn tại!");
								}else {
									khachHangDao.DeleteCustomer("KH003");
									System.out.println("Xóa khách hàng thành công!");
								}
							}catch(Exception e) {
								System.out.println("Lỗi! Không xóa được khách hàng: ");
							}
							break;
						case 6:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST findCustomer----------");
							KhachHang kh = khachHangDao.findCustomer("KH001");
							System.out.println("Tìm khách hàng theo mã khách hàng: ");
							System.out.println(kh.getMaKH() + " - " + kh.getTenKH() + " - " + kh.getSdt() + " - " + kh.isGioitinh());
							break;
						case 7:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST timKhachHangbySDT----------");
							KhachHang kh1 = khachHangDao.timKhachHangbySDT("0912345678");
							if(kh1 == null) {
								System.out.println("Khách hàng không tồn tại!");
							}else {
								System.out.println("Tìm khách hàng theo số điện thoại: ");
								System.out.println(kh1.getMaKH() + " - " + kh1.getTenKH() + " - " + kh1.getSdt() + " - " + kh1.isGioitinh());
							}

							break;
						case 8:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST editCustomer----------");
							try {
								KhachHang kh2 = khachHangDao.findCustomer("KH001");
								kh2.setTenKH("Nguyễn Văn A");
								kh2.setSdt("0909888777");
								kh2.setGioitinh(true);
								khachHangDao.editCustomer(kh2);
								System.out.println("Sửa thông tin khách hàng thành công!");
							}catch(Exception e) {
								System.out.println("Lỗi! Không sửa được thông tin khách hàng: ");
							}
							break;
						case 9:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST getdataKH----------");
							ArrayList<KhachHang> dsKhachHang4 = khachHangDao.getdataKH("KH001");
							System.out.println("Danh sách khách hàng: ");
							for (KhachHang kh3 : dsKhachHang4) {
								System.out.println(kh3.getMaKH() + " - " + kh3.getTenKH() + " - " + kh3.getSdt() + " - " + kh3.isGioitinh());
							}
							break;
						case 10:
							khachHangDao = new KhachHangImpl();
							System.out.println("----------TEST getSoLuongKhachHang----------");
							long slKhachHang = khachHangDao.getSoLuongKhachHang();
							System.out.println("Số lượng khách hàng: " + slKhachHang);
							break;
						case 0:
							System.out.println("Thoát!");
							System.exit(0);
							break;
					}

					try{
						tx.begin();
						khachHangDao = new KhachHangImpl();
						ArrayList<KhachHang> dsKhachHang = khachHangDao.getalltbKhachHang();
						System.out.println("Số lượng khách hàng: " + dsKhachHang.size());
					tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            em.close();
            emf.close();
        }

    }
}}


