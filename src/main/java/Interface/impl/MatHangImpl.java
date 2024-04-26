package Interface.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Interface.MatHangDao;
import connectDB.DatabaseManager;
import entity.MatHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MatHangImpl implements MatHangDao {

    private EntityManager em;

    public MatHangImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }

    @Override
    public ArrayList<MatHang> getalltbMatHang() {
        return (ArrayList<MatHang>) em.createNamedQuery("MatHang.getalltbMatHang").getResultList();
    }

    @Override
    public ArrayList<MatHang> getMatHangTheoMaMH(String id) {
        return (ArrayList<MatHang>) em.createNamedQuery("MatHang.getMatHangTheoMaMH").setParameter("maMH", id)
                .getResultList();
    }

    @Override
    public boolean addMatHang(MatHang mh) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(mh);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean DeleteMatHang(String maMH) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            MatHang mh = em.find(MatHang.class, maMH);
            em.remove(mh);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MatHang findMatHang(String maTim) {
        return em.find(MatHang.class, maTim);
    }

    @Override
    public boolean editMatHang(MatHang mh) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(mh);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getTongSoMH() {
        Long result = (Long) em.createNamedQuery("MatHang.getTongSoMH").getSingleResult();
        return result.intValue();
    }

    @Override
    public ArrayList<String[]> getMHNam(String nam) {
        List<Object[]> resultList = em.createNamedQuery("MatHang.getMHNam").setParameter("nam", nam).getResultList();

        ArrayList<String[]> mhNamList = new ArrayList<>();
        for (Object[] arr : resultList) {
            String[] mhNam = new String[arr.length];
            for (int i = 0; i < arr.length; i++) {
                mhNam[i] = String.valueOf(arr[i]);
            }
            mhNamList.add(mhNam);
        }

        return mhNamList;
    }

    @Override
    public Double getTongTienNam(String nam) {
        return (Double) em.createNamedQuery("MatHang.getTongTienNam").setParameter("nam", nam).getSingleResult();
    }

    @Override
    public ArrayList<String[]> getMHThang(String thang, String nam) {
        List<Object[]> resultList = em.createNamedQuery("MatHang.getMHThang").setParameter("thang", thang)
                .setParameter("nam", nam).getResultList();

        ArrayList<String[]> mhThangList = new ArrayList<>();
        for (Object[] arr : resultList) {
            String[] mhThang = new String[arr.length];
            for (int i = 0; i < arr.length; i++) {
                mhThang[i] = String.valueOf(arr[i]);
            }
            mhThangList.add(mhThang);
        }

        return mhThangList;
    }

    @Override
    public Double getTongTienThang(String thang, String nam) {
        return (Double) em.createNamedQuery("MatHang.getTongTienThang").setParameter("thang", thang)
                .setParameter("nam", nam).getSingleResult();
    }


    @Override
    public ArrayList<String[]> getMHNgay(Date ngay) {
        ArrayList<String[]> list = new ArrayList<>();
        // Chuyển đổi từ java.util.Date sang java.time.LocalDate
        LocalDate localDate = ngay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Truyền tham số kiểu java.time.LocalDate vào truy vấn
        List<Object[]> results = em.createNamedQuery("MatHang.getMHNgay").setParameter("ngay", localDate)
                .getResultList();
        results.forEach(result -> {
            String[] arr = new String[4];
            arr[0] = (String) result[0]; // maMH
            arr[1] = (String) result[1]; // tenMH
            arr[2] = String.valueOf(result[2]); // SoLuong
            arr[3] = String.valueOf(result[3]); // TongTien
            list.add(arr);
        });
        return list;
    }

    @Override
    public Double getTongTienMHNgay(Date date) {
        // Chuyển đổi từ java.util.Date sang java.time.LocalDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Truyền tham số kiểu java.time.LocalDate vào truy vấn
        double tongTien = 0;
        List<Object> results = em.createNamedQuery("MatHang.getTongTienNgay").setParameter("ngay", localDate)
                .getResultList();
        for (Object result : results) {
            if (result != null) {
                tongTien = Math.round((Double) result);
            }

        }
        return (double) tongTien;

    }

    public static void main(String[] args) throws ParseException {
        // mhNam
        MatHangDao mh = new MatHangImpl();

//        String date = "2023-10-30";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateString = dateFormat.parse(date);
//
//
//        System.out.println(mh.getTongTienMHNgay(dateString));
//        getTongSoMH

        System.out.println(mh.getTongSoMH());
    }
}
