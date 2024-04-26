package Test;

import java.util.ArrayList;

import Interface.PhongHatDao;
import Interface.impl.PhongHatImpl;
import entity.LoaiPhong;
import entity.PhongHat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test_PhongHat {
	private static PhongHatDao phongHatDao;

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			phongHatDao = new PhongHatImpl(); // Instantiate PhongHatImpl and assign it to phongHatDao
			ArrayList<PhongHat> dsPhongHat = phongHatDao.getAllPhongHat();
			System.out.println("Số phòng hát: " + dsPhongHat.size());

			// 1. Thêm phòng hát mới vào danh sách phòng hát không được trùng mã phòng
			try {
				if (phongHatDao.getPhongHatByMaPhong("PH04") != null) {
					System.out.println("Mã phòng hát đã tồn tại");
				} else {
					phongHatDao.addPhongHat(new PhongHat("PH04", "Phòng 02", new LoaiPhong("LP002"), "Trống"));
					System.out.println("Thêm phòng hát thành công");
				}
			} catch (Exception e) {
				System.out.println("Thêm phòng hát thất bại");
			}
			// 2. Xóa phòng hát theo mã phòng
			try {
				if (phongHatDao.getPhongHatByMaPhong("PH03") == null) {
					System.out.println("Mã phòng hát không tồn tại");
				} else {
					phongHatDao.DeletePhongHat("PH03");
					System.out.println("Xóa phòng hát thành công");
				}
			} catch (Exception e) {
				System.out.println("Xóa phòng hát thất bại");
			}

			// 3. Lấy danh sách phòng hát
			ArrayList<PhongHat> dsPhongHat1 = phongHatDao.getAllPhongHat();
			System.out.println("Danh sách phòng hát: ");
			for (PhongHat ph : dsPhongHat1) {
				System.out.println(ph.getMaPhong() + " - " + ph.getTenPhong() + " - "
						+ ph.getLoaiPhong().getMaLoaiPhong() + " - " + ph.getTinhTrangPhong());
			}

			// 4. Lấy danh sách phòng hát trống
			try {
				ArrayList<PhongHat> dsPhongHat2 = phongHatDao.getPhongByTinhTrang("Trong");
				System.out.println("Danh sách phòng hát trống: ");
				for (PhongHat ph : dsPhongHat2) {
					System.out.println(ph.getMaPhong() + " - " + ph.getTenPhong() + " - "
							+ ph.getLoaiPhong().getMaLoaiPhong() + " - " + ph.getTinhTrangPhong());
				}
			} catch (Exception e) {
				System.out.println("Không có phòng hát nào trống");
			}

			// 5. Tìm phòng hát theo mã phòng
			try {
				PhongHat ph = phongHatDao.getPhongHatByMaPhong("PH01");
				System.out.println("Thông tin phòng hát: ");
				System.out.println(ph.getMaPhong() + " - " + ph.getTenPhong() + " - "
						+ ph.getLoaiPhong().getMaLoaiPhong() + " - " + ph.getTinhTrangPhong());
			} catch (Exception e) {
				System.out.println("Không tìm thấy phòng hát");
			}

			// 6. Sửa tình trạng phòng hát
			try {
				if (phongHatDao.updateTinhTrangPhong("PH02", "Dang bao tri")) {
					System.out.println("Sửa tình trạng phòng hát thành công");
					// Lấy danh sách phòng hát
					ArrayList<PhongHat> dsPhongHat3 = phongHatDao.getAllPhongHat();
					System.out.println("Danh sách phòng hát: ");
					for (PhongHat ph : dsPhongHat3) {
						System.out.println(ph.getMaPhong() + " - " + ph.getTenPhong() + " - "
								+ ph.getLoaiPhong().getMaLoaiPhong() + " - " + ph.getTinhTrangPhong());
					}
				} else {
					System.out.println("Sửa tình trạng phòng hát thất bại");
				}
			} catch (Exception e) {
				System.out.println("Sửa tình trạng phòng hát thất bại");
			}

			// 7. int getTongSoPhong() - Lấy tổng số phòng
			System.out.println("Tổng số phòng: " + phongHatDao.getTongSoPhong());

			// 8. int getSoPhongTrong() - Lấy số phòng trống
			System.out.println("Số phòng trống: " + phongHatDao.getSoPhongTrong());

			// 9. ArrayList<PhongHat> FindTheoMaLoai(String id) - Tìm phòng theo mã loại
			ArrayList<PhongHat> dsPhongHat4 = phongHatDao.FindTheoMaLoai("LP002");
			System.out.println("Danh sách phòng hát theo mã loại: ");
			for (PhongHat ph : dsPhongHat4) {
				System.out.println(ph.getMaPhong() + " - " + ph.getTenPhong() + " - "
						+ ph.getLoaiPhong().getMaLoaiPhong() + " - " + ph.getTinhTrangPhong());
			}

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