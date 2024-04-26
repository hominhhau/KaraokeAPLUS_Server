package Interface.impl;

import java.util.ArrayList;
import Interface.LoaiPhongDao;
import connectDB.DatabaseManager;
import entity.LoaiPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class LoaiPhongImpl implements LoaiPhongDao {


	private EntityManager em;

	public LoaiPhongImpl() {
		DatabaseManager.getInstance().connectIfNeeded();
		em = DatabaseManager.getInstance().getEntityManager();

	}

	@Override
	public ArrayList<LoaiPhong> getalltbLoaiPhong() {
		return (ArrayList<LoaiPhong>) em.createNamedQuery("LoaiPhong.getalltbLoaiPhong").getResultList();
	}

	@Override
	public ArrayList<LoaiPhong> getLoaiPhongTheoMaLoai(String id) {
		return (ArrayList<LoaiPhong>) em.createNamedQuery("LoaiPhong.getLoaiPhongTheoMaLoai")
				.setParameter("maLoaiPhong", id).getResultList();
	}

}
