package Interface.impl;

import java.util.ArrayList;

import Interface.KhuyenMaiDao;
import connectDB.DatabaseManager;
import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class KhuyenMaiImpl implements KhuyenMaiDao {

private  EntityManager em;


	public KhuyenMaiImpl() {
		DatabaseManager.getInstance().connectIfNeeded();
		em = DatabaseManager.getInstance().getEntityManager();
	}

	@Override
	public ArrayList<KhuyenMai> getAllKhuyenMai() {
		return (ArrayList<KhuyenMai>) em.createNamedQuery("KhuyenMai.getAllKhuyenMai").getResultList();
	}

	@Override
	public boolean createKhuyenMai(KhuyenMai km) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(km);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean editKhuyenMai(KhuyenMai km) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(km);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteKhuyenMai(String maKM) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			KhuyenMai km = em.find(KhuyenMai.class, maKM);
			em.remove(km);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String[] getKhuyenMai() {
		ArrayList<KhuyenMai> list = getAllKhuyenMai();
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i).getMoTa();
		}
		return result;
//		return (String[]) em.createNamedQuery("KhuyenMai.getKhuyenMai").getSingleResult();
	}

	@Override
	public KhuyenMai getKhuyenMaiByMaKM(String maKM) {
		return em.find(KhuyenMai.class, maKM);
	}

}
