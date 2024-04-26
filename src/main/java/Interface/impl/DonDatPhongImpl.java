package Interface.impl;

import Interface.DonDatPhongDao;
import connectDB.DatabaseManager;
import entity.DonDatPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;

public class DonDatPhongImpl implements DonDatPhongDao {

    private EntityManager em;

    public DonDatPhongImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();

    }

    @Override
    public ArrayList<DonDatPhong> getalltbDonDatPhong() {
        return (ArrayList<DonDatPhong>) em.createNamedQuery("DonDatPhong.getalltbDonDatPhong").getResultList();


    }

    @Override
    public boolean createDonDatPhong(DonDatPhong ddp) {
     EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ddp);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<DonDatPhong> getDonDatPhongTheoMaKH(String maKH) {
        return (ArrayList<DonDatPhong>) em.createNamedQuery("DonDatPhong.getDonDatPhongTheoMaKH").setParameter("maKH", maKH).getResultList();

    }

    @Override
    public ArrayList<DonDatPhong> getDonDatPhongTheoNgayNhanPhong(LocalDate ngayNhanPhong) {
      //       String sql = "SELECT * FROM DonDatPhong WHERE CAST(ngayNhan AS DATE) = ?";
        return (ArrayList<DonDatPhong>) em.createNamedQuery("DonDatPhong.getDonDatPhongTheoNgayNhanPhong").setParameter("ngayNhanPhong", ngayNhanPhong).getResultList();


    }

    @Override
    public ArrayList<DonDatPhong> timDonDatPhong(String maDDP, String maKH, String maPhong) {
        return (ArrayList<DonDatPhong>) em.createNamedQuery("DonDatPhong.timDonDatPhong").setParameter("maDDP", maDDP).setParameter("maKH", maKH).setParameter("maPhong", maPhong).getResultList();

    }

    @Override
    public boolean deleteDonDatPhong(String maDDP, String maPhong) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DonDatPhong ddp = em.find(DonDatPhong.class, maDDP);
            em.remove(ddp);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
