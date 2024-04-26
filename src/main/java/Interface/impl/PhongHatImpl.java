package Interface.impl;

import java.util.ArrayList;

import Interface.PhongHatDao;
import connectDB.DatabaseManager;
import entity.PhongHat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class PhongHatImpl implements PhongHatDao {

    private EntityManager em;

    public PhongHatImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();


    }

    @Override
    public ArrayList<PhongHat> getAllPhongHat() {

        return (ArrayList<PhongHat>) em.createNamedQuery("PhongHat.getAllPhongHat").getResultList();
    }

    @Override
    public ArrayList<PhongHat> getPhongByTinhTrang(String tinhTrang) {
        return (ArrayList<PhongHat>) em.createNamedQuery("PhongHat.getPhongByTinhTrang")
                .setParameter("tinhTrang", tinhTrang).getResultList();
    }

    @Override
    public boolean updateTinhTrangPhong(String maPhong, String tinhTrangMoi) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            PhongHat ph = em.find(PhongHat.class, maPhong);
            ph.setTinhTrangPhong(tinhTrangMoi);
            em.merge(ph);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PhongHat getPhongHatByMaPhong(String maPhong) {
        return em.find(PhongHat.class, maPhong);
    }

    @Override
    public boolean editPhongHat(PhongHat ph) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(ph);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean DeletePhongHat(String maPH) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            PhongHat ph = em.find(PhongHat.class, maPH);
            em.remove(ph);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<PhongHat> FindTheoMaLoai(String id) {
        return (ArrayList<PhongHat>) em.createNamedQuery("PhongHat.FindTheoMaLoai").setParameter("id", id)
                .getResultList();
    }

    @Override
    public int getSoPhongTrong() {
        Long result = (Long) em.createNamedQuery("PhongHat.getSoPhongTrong").getSingleResult();
        return result.intValue();
    }

    @Override
    public int getTongSoPhong() {
        Long result = (Long) em.createNamedQuery("PhongHat.getTongSoPhong").getSingleResult();
        return result.intValue();
    }

    @Override
    public boolean addPhongHat(PhongHat ph) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ph);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        // TongSoPhong();
        PhongHatDao ph = new PhongHatImpl();
        System.out.println(ph.getTongSoPhong());
    }

}
