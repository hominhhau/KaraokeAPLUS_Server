package Interface.impl;

import Interface.LoaiNVDao;
import connectDB.DatabaseManager;
import entity.KhachHang;
import entity.LoaiNhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class LoaiNVImpl implements LoaiNVDao {

    private EntityManager em;
    public LoaiNVImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }

    @Override
    public ArrayList<LoaiNhanVien> getAllLoaiNhanVien() {
        return (ArrayList<LoaiNhanVien>) em.createNamedQuery("LoaiNhanVien.getAllLoaiNhanVien").getResultList();
    }
}
