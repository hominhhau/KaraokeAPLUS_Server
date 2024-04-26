package Interface.impl;

import Interface.HoaDonDao;
import connectDB.DatabaseManager;
import entity.HoaDon;
import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonImpl implements HoaDonDao {

    private EntityManager em;

    public HoaDonImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }

    @Override
    public ArrayList<HoaDon> getalltbHoaDon() {
        return (ArrayList<HoaDon>) em.createNamedQuery("HoaDon.getalltbHoaDon").getResultList();
    }

    @Override
    public boolean createHoaDon(HoaDon hd) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(hd);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;

    }



    @Override
    public HoaDon getHoaDonTheoMaHD(String maHD) {
        return em.find(HoaDon.class, maHD);

    }

    @Override
    public boolean updateTongTien(String maHD, Double tongTien, String maKM) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            HoaDon hd = em.find(HoaDon.class, maHD);
            hd.setTongTien(tongTien);
//			KhuyenMai km = new KhuyenMai(maKM);
//			hd.setMaKM(km);
            em.merge(hd);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getSoLuongHoaDon() {
        return ((Long) em.createNamedQuery("HoaDon.getSoLuongHoaDon").getSingleResult()).intValue();

    }


    @Override
    public ArrayList<HoaDon> getHoaDonTheoMaKH(String maKH) {
        return (ArrayList<HoaDon>) em.createNamedQuery("HoaDon.getHoaDonTheoMaKH").setParameter("maKH", maKH)
                .getResultList();

    }


    @Override
    public ArrayList<HoaDon> getDoanhThuTungThangTrongNam(String nam) {
        return (ArrayList<HoaDon>) em.createNamedQuery("HoaDon.getDoanhThuTungThangTrongNam").setParameter("nam", nam)
                .getResultList();

    }

    @Override
    public ArrayList<HoaDon> getDoanhThuNam(String nam) {
        return (ArrayList<HoaDon>) em.createNamedQuery("HoaDon.getDoanhThuNam").setParameter("nam", nam)
                .getResultList();

    }

    @Override
    public ArrayList<String[]> getDoanhThuTungThangNam(String nam) {
        Query query = em.createNamedQuery("HoaDon.getDoanhThuTungThangNam");
        query.setParameter("nam", nam);

        List<Object[]> results = query.getResultList();
        ArrayList<String[]> doanhThuTungThang = new ArrayList<>();

        for (Object[] result : results) {
            String thang = String.valueOf(result[0]);
            String tongTien = String.valueOf(result[1]);
            String[] doanhThu = {thang, tongTien};
            doanhThuTungThang.add(doanhThu);
        }

        return doanhThuTungThang;

    }

    @Override
    public ArrayList<HoaDon> getDoanhThuThang(String thang, String nam) {
        return (ArrayList<HoaDon>) em.createNamedQuery("HoaDon.getDoanhThuThang").setParameter("thang", thang)
                .setParameter("nam", nam).getResultList();

    }

    @Override
    public double getTongTienThang(String thang, String nam) {
        return ((Double) em.createNamedQuery("HoaDon.getTongTienThang").setParameter("thang", thang)
                .setParameter("nam", nam).getSingleResult()).intValue();

    }

    @Override
    public int getSoLuongHoaDonThang(String thang, String nam) {
        return ((Long) em.createNamedQuery("HoaDon.getSoLuongHoaDonThang").setParameter("thang", thang)
                .setParameter("nam", nam).getSingleResult()).intValue();

    }

    @Override
    public int getSoLuongHoaDonNam(String nam) {
        return ((Long) em.createNamedQuery("HoaDon.getSoLuongHoaDonNam").setParameter("nam", nam).getSingleResult())
                .intValue();

    }

    @Override
    public double getTongTienNam(String nam) {
        Double result = (Double) em.createNamedQuery("HoaDon.getTongTienNam").setParameter("nam", nam)
                .getSingleResult();

        if (result != null) {
            return result.intValue();
        }
        return 0;

    }

    @Override
    public int getSoLuongHoaDonNgay(Date ngay) {
        return ((Long) em.createNamedQuery("HoaDon.getSoLuongHoaDonNgay").setParameter("ngay", ngay).getSingleResult())
                .intValue();
    }

    @Override
    public Double getTongTienNgay(Date ngay) {
        // Chuyển đổi từ java.util.Date sang java.time.LocalDate
        LocalDate localDate = ngay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        double tongTien = 0;
        try {
            tongTien = (Double) em.createNamedQuery("HoaDon.getTongTienNgay").setParameter("ngay", localDate)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongTien;
    }

    @Override
    public ArrayList<HoaDon> getDoanhThuNgay(Date ngay) {
        // Chuyển đổi từ java.util.Date sang java.time.LocalDate
        LocalDate localDate = ngay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Truyền tham số kiểu java.time.LocalDate vào truy vấn
        return (ArrayList<HoaDon>) em.createNamedQuery("HoaDon.getDoanhThuNgay").setParameter("ngay", localDate)
                .getResultList();
    }

    public static void main(String[] args) throws ParseException {
        // test doanh thu theo nam

        HoaDonDao hoaDonImpl = new HoaDonImpl();

        int soLuong = hoaDonImpl.getSoLuongHoaDonNam("2023");
        if (soLuong != 0) {
            System.out.println("So luong hoa don: " + soLuong);
        } else {
            System.out.println("Khong co hoa don nao");
        }
    }
}
