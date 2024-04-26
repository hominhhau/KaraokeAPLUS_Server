package Test;

import java.time.LocalDate;
import java.util.ArrayList;

import Interface.KhuyenMaiDao;
import Interface.PhongHatDao;
import Interface.impl.KhuyenMaiImpl;
import Interface.impl.PhongHatImpl;
import entity.KhuyenMai;
import entity.LoaiPhong;
import entity.PhongHat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test_KhuyenMai {
	private static KhuyenMaiDao khuyenMaiDao;

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
//
//			KhuyenMai
//			- ArrayList<KhuyenMai> getAllKhuyenMai() 
//			- boolean createKhuyenMai(KhuyenMai km) 
//			- boolean editKhuyenMai(KhuyenMai km)
//			- boolean DeleteKhuyenMai(String maKM)
//			- String[] getKhuyenMai()

//			1. Lấy danh sách khuyến mãi
			khuyenMaiDao = new KhuyenMaiImpl();
			ArrayList<KhuyenMai> dsKhuyenMai = khuyenMaiDao.getAllKhuyenMai();
			System.out.println("Số khuyến mãi: " + dsKhuyenMai.size());
			System.out.println("Danh sách khuyến mãi: ");
			for (KhuyenMai km : dsKhuyenMai) {
				System.out.println(km.getMaKM() + " - " + km.getMoTa() + " - " + km.getGioBatDau() + " - "
						+ km.getGioKetThuc() + " - " + km.getPhanTram());
			}

			// 2. Thêm khuyến mãi mới vào danh sách khuyến mãi không được trùng mã khuyến
			// mãi
			try {
				if (khuyenMaiDao.getKhuyenMaiByMaKM("KM07") != null) {
					System.out.println("Mã khuyến mãi đã tồn tại");
				} else {
					khuyenMaiDao.createKhuyenMai(
							new KhuyenMai("KM07", "Khuyến mãi 02", LocalDate.now(), LocalDate.now(), 0.1));
					System.out.println("Thêm khuyến mãi thành công");
					System.out.println("Danh sách khuyến mãi sau khi thêm: ");
					for (KhuyenMai km : dsKhuyenMai) {
						System.out.println(km.getMaKM() + " - " + km.getMoTa() + " - " + km.getGioBatDau() + " - "
								+ km.getGioKetThuc() + " - " + km.getPhanTram());
					}
				}
			} catch (Exception e) {
				System.out.println("Thêm khuyến mãi thất bại");
			}

			// 3. Sửa khuyến mãi theo mã khuyến mãi
			try {
				if (khuyenMaiDao.getKhuyenMaiByMaKM("KM07") == null) {
					System.out.println("Mã khuyến mãi không tồn tại");
				} else {
					khuyenMaiDao.editKhuyenMai(
							new KhuyenMai("KM07", "Khuyến mãi 03", LocalDate.now(), LocalDate.now(), 0.2));
					System.out.println("Sửa khuyến mãi thành công");
					System.out.println("Danh sách khuyến mãi sau khi sửa: ");
					for (KhuyenMai km : dsKhuyenMai) {
						System.out.println(km.getMaKM() + " - " + km.getMoTa() + " - " + km.getGioBatDau() + " - "
								+ km.getGioKetThuc() + " - " + km.getPhanTram());
					}
				}
			} catch (Exception e) {
				System.out.println("Sửa khuyến mãi thất bại");
			}

			// 4. Xóa khuyến mãi theo mã khuyến mãi
			try {
				if (khuyenMaiDao.getKhuyenMaiByMaKM("KM06") == null) {
					System.out.println("Mã khuyến mãi không tồn tại");
				} else {
					khuyenMaiDao.DeleteKhuyenMai("KM06");
					System.out.println("Xóa khuyến mãi thành công");
					System.out.println("Danh sách khuyến mãi sau khi xóa: ");
					ArrayList<KhuyenMai> dsKhuyenMai1 = khuyenMaiDao.getAllKhuyenMai();
					for (KhuyenMai km : dsKhuyenMai1) {
						System.out.println(km.getMaKM() + " - " + km.getMoTa() + " - " + km.getGioBatDau() + " - "
								+ km.getGioKetThuc() + " - " + km.getPhanTram());
					}
				}
			} catch (Exception e) {
				System.out.println("Xóa khuyến mãi thất bại");
			}
			
			// 5. Lấy danh sách mã khuyến mãi
//			String[] dsMaKM = khuyenMaiDao.getKhuyenMai();
//			System.out.println("Danh sách mã khuyến mãi: ");
//			for (String maKM : dsMaKM) {
//				System.out.println(maKM);
//			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
