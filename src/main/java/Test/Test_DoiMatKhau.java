package Test;

import Interface.DoiMatKhauDao;
import Interface.impl.DoiMatKhauImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test_DoiMatKhau {
    private static DoiMatKhauDao doiMatKhauDao;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        doiMatKhauDao = new DoiMatKhauImpl();
        System.out.println(doiMatKhauDao.doiMatKhau("NV001", "12346789@Aa"));



    }

}
