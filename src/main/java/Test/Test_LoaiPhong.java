package Test;

import java.util.ArrayList;

import Interface.LoaiPhongDao;
import Interface.impl.LoaiPhongImpl;
import entity.LoaiPhong;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test_LoaiPhong {
	private static LoaiPhongDao loaiPhongDao;

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
//			LoaiPhong 
//			- ArrayList<LoaiPhong> getalltbLoaiPhong()
//			- ArrayList<LoaiPhong> getLoaiPhongTheoMaLoai(String id)
//			1. Lấy danh sách loại phòng
			loaiPhongDao = new LoaiPhongImpl();
			ArrayList<LoaiPhong> dsLoaiPhong = loaiPhongDao.getalltbLoaiPhong();
			System.out.println("Số loại phòng: " + dsLoaiPhong.size());
			for (LoaiPhong lp : dsLoaiPhong) {
				System.out.println(lp.getMaLoaiPhong() + " - " + lp.getTenLoaiPhong() + " - " + lp.getGia());
			}

			// 2. Lấy loại phòng theo mã loại
			ArrayList<LoaiPhong> dsLoaiPhong1 = loaiPhongDao.getLoaiPhongTheoMaLoai("LP001");
			System.out.println("Loại phòng theo mã: ");
			for (LoaiPhong lp : dsLoaiPhong1) {
				System.out.println(lp.getMaLoaiPhong() + " - " + lp.getTenLoaiPhong() + " - " + lp.getGia());
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