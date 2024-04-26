package Interface.impl;

import Interface.ChiTietHoaDonPhongDao;
import Interface.PhongHatDao;
import connectDB.DatabaseManager;
import entity.ChiTietHoaDonPhong;
import entity.HoaDon;
import entity.PhongHat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonPhongImpl implements ChiTietHoaDonPhongDao {

    private EntityManager em;

    public ChiTietHoaDonPhongImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }


    @Override
    public ArrayList<ChiTietHoaDonPhong> getalltbChiTietHoaDonPhong() {
        return (ArrayList<ChiTietHoaDonPhong>) em.createNamedQuery("getAllChiTietHoaDonPhong").getResultList();
    }

    @Override
    public ChiTietHoaDonPhong getChiTietHoaDonPhongTheoMaHD(String id, String maPhong) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ChiTietHoaDonPhong cthdp = em.createNamedQuery("getChiTietHoaDonPhongByMaHD", ChiTietHoaDonPhong.class).setParameter("maHD", id).setParameter("maPhong", maPhong).getSingleResult();
            tx.commit();
            return cthdp;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        }
        return null;
    }



    @Override
    public boolean createChiTietHoaDonPhong(ChiTietHoaDonPhong cthdp) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cthdp);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ChiTietHoaDonPhong finHDByRoomID(String roomID) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ChiTietHoaDonPhong cthdp = em.createNamedQuery("findHDByRoomID", ChiTietHoaDonPhong.class).setParameter("maPhong", roomID).getSingleResult();
            tx.commit();
            return cthdp;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ChiTietHoaDonPhong finHDByRoomIDDaTT(String roomID) {
//        return em.find(ChiTietHoaDonPhong.class, roomID);
        return null;
    }

    @Override
    public boolean updateGioRaVsGia(String maHD, LocalDateTime gioRa, Float gia, String maPhong) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNamedQuery("updateCheckoutAndPrice").setParameter("maHD", maHD).setParameter("gioRa", gioRa).setParameter("gia", gia).setParameter("maPhong", maPhong).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean doiPhong(String maHD, String maPhongHienTai, String maPhongMoi, String ghiChu) {
       //  @NamedQuery(
        //                    name = "changeRoom",
        //                    query = "UPDATE ChiTietHoaDonPhong c SET c.phongHat.maPhong = :newRoomID, c.ghiChu = :note WHERE c.hoaDon.maHD = :maHD AND c.phongHat.maPhong = :currentRoomID"
        //            ),
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNamedQuery("changeRoom").setParameter("newRoomID", maPhongMoi).setParameter("note", ghiChu).setParameter("maHD", maHD).setParameter("currentRoomID", maPhongHienTai).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void createChiTietHoaDonPhongList(List<ChiTietHoaDonPhong> cthdpList) {
        for(ChiTietHoaDonPhong cthdp : cthdpList) {
            createChiTietHoaDonPhong(cthdp);
        }
    }

    @Override
    public ArrayList<ChiTietHoaDonPhong> getAllTheMaHDArray(String maHD) {
        return (ArrayList<ChiTietHoaDonPhong>) em.createNamedQuery("getAllByMaHD").setParameter("maHD", maHD).getResultList();
    }

    public static void main(String[] args) {
        ChiTietHoaDonPhongDao chiTietHoaDonPhongDao = new ChiTietHoaDonPhongImpl();
//  //findHDByRoomID
        ArrayList<ChiTietHoaDonPhong> list = chiTietHoaDonPhongDao.getAllTheMaHDArray("HD020");
        for (ChiTietHoaDonPhong cthdp : list) {
            System.out.println(cthdp.getPhongHat().getMaPhong());
        }


    }
}
