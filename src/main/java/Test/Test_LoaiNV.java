package Test;

import dao.LoaiNV_DAO;
import entity.LoaiNhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test_LoaiNV {
    private  LoaiNV_DAO loaiNV_dao;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        LoaiNV_DAO loaiNV_dao = new LoaiNV_DAO();
        System.out.println("----------TEST getAllLoaiNhanVien----------");
        for (LoaiNhanVien lnv : loaiNV_dao.getAllLoaiNhanVien()) {
            System.out.println(lnv.getMaLoai() + " - " + lnv.getTenLoai());
        }
    }
}
