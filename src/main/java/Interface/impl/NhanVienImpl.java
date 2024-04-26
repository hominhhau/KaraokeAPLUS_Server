package Interface.impl;

import Interface.NhanVienDao;
import Interface.TaiKhoanDao;
import connectDB.DatabaseManager;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.util.ArrayList;



public class NhanVienImpl implements NhanVienDao {

    private EntityManager em;


    public NhanVienImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }
    @Override
    public ArrayList<NhanVien> getalltbNhanVien() {
        return (ArrayList<NhanVien>) em.createNamedQuery("NhanVien.getallNhanVien").getResultList();
    }

    @Override
    public ArrayList<NhanVien> getNhanVienTheoMaNV(String id) {
        return (ArrayList<NhanVien>) em.createNamedQuery("NhanVien.getNhanVienTheoMaNV").setParameter("maNV", id).getResultList();
    }

    @Override
    public ArrayList<NhanVien> getNVTheoLoai(String lnv) {
        return (ArrayList<NhanVien>) em.createNamedQuery("NhanVien.getNVTheoLoai").setParameter("loaiNV", lnv).getResultList();
    }

    @Override
    public boolean addStaff(NhanVien nv) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(nv);
            tx.commit();
            return true;
        }catch(Exception e) {
            tx.rollback();
            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean editStaff(NhanVien nv) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(nv);
            tx.commit();
            return true;

        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }
        return false;
    }

    @Override
    public NhanVien findStaff(String maTim) {
        return (NhanVien) em.createNamedQuery("NhanVien.findStaff").setParameter("maNV", maTim).getSingleResult();
    }

    @Override
    public boolean SDT_tonTaiNV(String soDienThoai) {
        EntityTransaction tx = em.getTransaction();
        try{
            if(!tx.isActive()){
                tx.begin();
            }
//            NhanVien nv = em.find(NhanVien.class, soDienThoai);
//            if(nv != null){
//                return true;
//            }
            //"SELECT COUNT(n) FROM NhanVien n WHERE n.soDienThoai = :soDienThoai",
            long count = (long) em.createNamedQuery("NhanVien.SDT_tonTaiNV").setParameter("SDT", soDienThoai).getSingleResult();
            if(count > 0){
                return true;
            }
            tx.commit();

        }catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        }
        return false;
    }
}
