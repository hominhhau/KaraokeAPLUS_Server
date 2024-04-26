package Interface.impl;

import Interface.KhachHangDao;
import connectDB.DatabaseManager;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class KhachHangImpl implements KhachHangDao {

	private EntityManager em;

	public KhachHangImpl() {
		DatabaseManager.getInstance().connectIfNeeded();
		em = DatabaseManager.getInstance().getEntityManager();
	}

	@Override
	public ArrayList<KhachHang> getalltbKhachHang() {
		return (ArrayList<KhachHang>) em.createNamedQuery("KhachHang.getalltbKhachHang").getResultList();
	}

	@Override
	public ArrayList<KhachHang> getKhachHangTheoMaKH(String id) {
		return (ArrayList<KhachHang>) em.createNamedQuery("KhachHang.getKhachHangTheoMaKH").setParameter("maKH", id)
				.getResultList();
	}

	@Override
	public ArrayList<KhachHang> getKhachHangTheoSdtKH(String sdt) {
		return (ArrayList<KhachHang>) em.createNamedQuery("KhachHang.getKhachHangTheoSdtKH").setParameter("sdt", sdt)
				.getResultList();
	}

	@Override
	public boolean addCustomer(KhachHang kh) {
		EntityTransaction tx = em.getTransaction();
		try {
			if (!tx.isActive()) {
				tx.begin();
			}
			em.persist(kh);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return false;
	}

	@Override
	public boolean DeleteCustomer(String maKH) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			KhachHang kh = em.find(KhachHang.class, maKH);
			em.remove(kh);
			tx.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	@Override
	public KhachHang findCustomer(String maTim) {
		return (KhachHang) em.createNamedQuery("KhachHang.findCustomer").setParameter("maKH", maTim).getSingleResult();
	}

	@Override
	public KhachHang timKhachHangbySDT(String sdt) {
		return (KhachHang) em.createNamedQuery("KhachHang.timKhachHangbySDT").setParameter("sdt", sdt)
				.getSingleResult();
	}

	@Override
	public boolean editCustomer(KhachHang kh) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(kh);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	@Override
	public ArrayList<KhachHang> getdataKH(String id) {
		return (ArrayList<KhachHang>) em.createNamedQuery("KhachHang.getdataKH").setParameter("maKH", id)
				.getSingleResult();
	}

	@Override


	public long getSoLuongKhachHang() {
		return (long) em.createNamedQuery("KhachHang.getSoLuongKhachHang").getSingleResult();


	}



}
