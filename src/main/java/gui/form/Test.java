package gui.form;

import Interface.TaiKhoanDao;
import Interface.impl.TaiKhoanImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test {
	private static TaiKhoanDao taiKhoanDao;

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			tx.commit();

		} finally {
			em.close();
			emf.close();
		}
	}

}
