package Interface.impl;

import Interface.ChiTietHoaDonDichVuDao;
import connectDB.DatabaseManager;
import entity.ChiTietHoaDonDV;
import entity.HoaDon;
import entity.MatHang;
import entity.PhongHat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChiTietHoaDonDichVuImpl implements ChiTietHoaDonDichVuDao {

    private  EntityManager em;
    private  EntityManagerFactory emf;

    public ChiTietHoaDonDichVuImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }

    @Override
    public boolean createChiTietHoaDonDV(ChiTietHoaDonDV cthddv) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cthddv);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ChiTietHoaDonDV getChiTietHoaDonDVTheoMaHD(String id, String maPhong) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ChiTietHoaDonDV cthdp = em.createNamedQuery("getChiTietHoaDonDVByMaHDAndMaPhong", ChiTietHoaDonDV.class)
                    .setParameter("maHD", id).setParameter("maPhong", maPhong).getSingleResult();
            tx.commit();
            return cthdp;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public ArrayList<ChiTietHoaDonDV> getalltbChiTietHoaDonDV() {
        return (ArrayList<ChiTietHoaDonDV>) em.createNamedQuery("getAllChiTietHoaDonDV").getResultList();

    }

    @Override
    public ArrayList<ChiTietHoaDonDV> getalltbChiTietHoaDonDVTheoMaMH(String maMH) {
        return (ArrayList<ChiTietHoaDonDV>) em.createNamedQuery("getChiTietHoaDonDVByMaMH", ChiTietHoaDonDV.class)
                .setParameter("maMH", maMH).getResultList();

    }

    @Override
    public boolean updateChiTietHoaDonDV(ChiTietHoaDonDV cthddv) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cthddv);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteChiTietHoaDonDV(String maHD, String maMH) {
        // @NamedQuery(name ="deleteChiTietHoaDonDV"
        // ,query = "DELETE FROM ChiTietHoaDonDV c WHERE c.hoaDon.maHD = :maHD AND
        // c.matHang.maMH = :maMH"
        // )
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNamedQuery("deleteChiTietHoaDonDV").setParameter("maHD", maHD).setParameter("maMH", maMH)
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public ChiTietHoaDonDV findChiTietHoaDonDV(String maHD, String maMH) {
        EntityTransaction tx = em.getTransaction();
        ChiTietHoaDonDV cthdp = null;
        try {
            tx.begin();
            cthdp = em.createNamedQuery("findChiTietHoaDonDV", ChiTietHoaDonDV.class).setParameter("maHD", maHD).setParameter("maMH", maMH).getSingleResult();
            tx.commit();
            if (cthdp != null) {
                return cthdp;
            } else {
                return cthdp = null;
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        }

        return cthdp;
    }


    @Override
    public ChiTietHoaDonDV findChiTietHoaDonDVforThem(String maHD, String maMH, String maPhong) {
        ChiTietHoaDonDV cthdp = null;
        try {
            cthdp = em.createNamedQuery("getChiTietHoaDonDVByMaHDAndMaMHAndMaPhong", ChiTietHoaDonDV.class).setParameter("maHD", maHD).setParameter("maMH", maMH).setParameter("maPhong", maPhong).getSingleResult();
            if(cthdp != null) {
                return cthdp;
            }else{
                return cthdp = null;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return cthdp;
    }


    @Override
    public ArrayList<ChiTietHoaDonDV> getAllTheMaHDDVforRoomArray(String maHD, String maPhong) {
        return (ArrayList<ChiTietHoaDonDV>) em
                .createNamedQuery("getChiTietHoaDonDVByMaHDAndMaPhong", ChiTietHoaDonDV.class)
                .setParameter("maHD", maHD).setParameter("maPhong", maPhong).getResultList();
    }


    @Override
    public ArrayList<String[]> getTOPDVNam(String nam) {
        ArrayList<String[]> list = new ArrayList<>();
        List<Object[]> results = em.createNativeQuery("SELECT TOP 5 sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                        + "    ROUND((SUM(od.soLuong) * 100.0 / (SELECT SUM(SoLuong) FROM (SELECT TOP 5 SUM(od2.soLuong) AS SoLuong FROM [dbo].[ChiTietHoaDonDV] od2 JOIN HoaDon o2 ON od2.maHD = o2.maHD WHERE YEAR(o2.ngayLapHD) = ? GROUP BY od2.maMH ORDER BY SoLuong DESC) AS top5)) , 2) AS TyLe, \n"
                        + "    ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n" + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                        + "JOIN HoaDon o ON od.maHD = o.maHD\n" + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                        + "WHERE YEAR(o.ngayLapHD) = ? " + "GROUP BY sp.maMH, sp.tenMH\n" + "ORDER BY SUM(od.soLuong) DESC")
                .setParameter(1, nam).setParameter(2, nam).getResultList();

        for (Object[] result : results) {
            String[] arr = new String[5];
            arr[0] = (String) result[0]; // maMH
            arr[1] = (String) result[1]; // tenMH
            arr[2] = String.valueOf(result[2]); // SoLuong
            arr[3] = String.valueOf(result[3]); // TyLe
            arr[4] = String.valueOf(result[4]); // TongTien
            list.add(arr);
        }

        return list;
    }

    @Override
    public double getTongTienNam(String nam) {
        double tongTien = 0;

        List<Object> results = em
                .createNativeQuery("SELECT SUM(TongTien) AS TongTatCaTien\n" + "FROM\n" + "(\n"
                        + "    SELECT TOP 5 ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                        + "    FROM [dbo].[ChiTietHoaDonDV] od\n" + "    JOIN HoaDon o ON od.maHD = o.maHD\n"
                        + "    JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n" + "    WHERE YEAR(o.ngayLapHD) = ? \n"
                        + "    GROUP BY sp.maMH, sp.tenMH\n" + "    ORDER BY SUM(od.soLuong) DESC\n" + ") AS Top5;")
                .setParameter(1, nam).getResultList();

        for (Object result : results) {
            if (result != null) {
                tongTien = (Double) result;
            }
        }
        return tongTien;
    }

    @Override
    public ArrayList<String[]> getTOPDVThang(String thang, String nam) {
        ArrayList<String[]> list = new ArrayList<>();
        List<Object[]> results = em.createNativeQuery("SELECT TOP 5 sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                        + "    ROUND((SUM(od.soLuong) * 100.0 / (SELECT SUM(SoLuong) FROM (SELECT TOP 5 SUM(od2.soLuong) AS SoLuong FROM [dbo].[ChiTietHoaDonDV] od2 JOIN HoaDon o2 ON od2.maHD = o2.maHD WHERE MONTH(o2.ngayLapHD) = ? AND YEAR(o2.ngayLapHD) = ? GROUP BY od2.maMH ORDER BY SoLuong DESC) AS top5)) , 2) AS TyLe, \n"
                        + "    ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n" + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                        + "JOIN HoaDon o ON od.maHD = o.maHD\n" + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                        + "WHERE MONTH(o.ngayLapHD) = ? AND YEAR(o.ngayLapHD) = ? " + "GROUP BY sp.maMH, sp.tenMH\n"
                        + "ORDER BY SUM(od.soLuong) DESC").setParameter(1, thang).setParameter(2, nam).setParameter(3, thang)
                .setParameter(4, nam).getResultList();

        for (Object[] result : results) {
            String[] arr = new String[5];
            arr[0] = (String) result[0]; // maMH
            arr[1] = (String) result[1]; // tenMH
            arr[2] = String.valueOf(result[2]); // SoLuong
            arr[3] = String.valueOf(result[3]); // TyLe
            arr[4] = String.valueOf(result[4]); // TongTien
            list.add(arr);
        }

        return list;

    }

    @Override
    public double getTongTienThangTop(String thang, String nam) {
        double tongTien = 0;

        List<Object> results = em
                .createNativeQuery("SELECT SUM(TongTien) AS TongTatCaTien\n" + "FROM\n" + "(\n"
                        + "    SELECT TOP 5 ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                        + "    FROM [dbo].[ChiTietHoaDonDV] od\n" + "    JOIN HoaDon o ON od.maHD = o.maHD\n"
                        + "    JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n" + "    WHERE MONTH(o.ngayLapHD) = ? AND YEAR(o.ngayLapHD) = ? \n"
                        + "    GROUP BY sp.maMH, sp.tenMH\n" + "    ORDER BY SUM(od.soLuong) DESC\n" + ") AS Top5;")
                .setParameter(1, thang).setParameter(2, nam).getResultList();

        for (Object result : results) {
            if (result != null) {
                tongTien = (Double) result;
            }
        }
        return tongTien;
    }


    @Override
    public ArrayList<String[]> getTOPDVNgay(Date ngay) {
        ArrayList<String[]> list = new ArrayList<>();
        List<Object[]> results = em.createNativeQuery("SELECT TOP 5 sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                        + "    ROUND((SUM(od.soLuong) * 100.0 / (SELECT SUM(SoLuong) FROM (SELECT TOP 5 SUM(od2.soLuong) AS SoLuong FROM [dbo].[ChiTietHoaDonDV] od2 JOIN HoaDon o2 ON od2.maHD = o2.maHD WHERE (o2.ngayLapHD) = ? GROUP BY od2.maMH ORDER BY SoLuong DESC) AS top5)) , 2) AS TyLe, \n"
                        + "    ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n" + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                        + "JOIN HoaDon o ON od.maHD = o.maHD\n" + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                        + "WHERE (o.ngayLapHD) = ? " + "GROUP BY sp.maMH, sp.tenMH\n" + "ORDER BY SUM(od.soLuong) DESC")
                .setParameter(1, ngay).setParameter(2, ngay).getResultList();

        for (Object[] result : results) {
            String[] arr = new String[5];
            arr[0] = (String) result[0]; // maMH
            arr[1] = (String) result[1]; // tenMH
            arr[2] = String.valueOf(result[2]); // SoLuong
            arr[3] = String.valueOf(result[3]); // TyLe
            arr[4] = String.valueOf(result[4]); // TongTien
            list.add(arr);

        }
        return list;

    }

    @Override
    public double getTongTienNgayDV(Date ngay) {
        double tongTien = 0;

        List<Object> results = em
                .createNativeQuery("SELECT SUM(TongTien) AS TongTatCaTien\n" + "FROM\n" + "(\n"
                        + "    SELECT TOP 5 ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n"
                        + "    FROM [dbo].[ChiTietHoaDonDV] od\n" + "    JOIN HoaDon o ON od.maHD = o.maHD\n"
                        + "    JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n" + "    WHERE (o.ngayLapHD) = ? \n"
                        + "    GROUP BY sp.maMH, sp.tenMH\n" + "    ORDER BY SUM(od.soLuong) DESC\n" + ") AS Top5;")
                .setParameter(1, ngay).getResultList();

        for (Object result : results) {
            if (result != null) {
                tongTien = (Double) result;
            }
        }
        return tongTien;

    }


    @Override
    public ArrayList<String[]> getTKNam(String nam) {
        ArrayList<String[]> list = new ArrayList<>();
        List<Object[]> results = em.createNativeQuery("SELECT TOP 5 sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                        + "    ROUND((SUM(od.soLuong) * 100.0 / (SELECT SUM(SoLuong) FROM (SELECT TOP 5 SUM(od2.soLuong) AS SoLuong FROM [dbo].[ChiTietHoaDonDV] od2 JOIN HoaDon o2 ON od2.maHD = o2.maHD WHERE YEAR(o2.ngayLapHD) = ? GROUP BY od2.maMH ORDER BY SoLuong DESC) AS top5)) , 2) AS TyLe, \n"
                        + "    ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n" + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                        + "JOIN HoaDon o ON od.maHD = o.maHD\n" + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                        + "WHERE YEAR(o.ngayLapHD) = ? " + "GROUP BY sp.maMH, sp.tenMH\n" + "ORDER BY SUM(od.soLuong) DESC")
                .setParameter(1, nam).setParameter(2, nam).getResultList();

        for (Object[] result : results) {
            String[] arr = new String[5];
            arr[0] = (String) result[0]; // maMH
            arr[1] = (String) result[1]; // tenMH
            arr[2] = String.valueOf(result[2]); // SoLuong
            arr[3] = String.valueOf(result[3]); // TyLe
            arr[4] = String.valueOf(result[4]); // TongTien
            list.add(arr);
        }

        return list;

    }

    @Override
    public ArrayList<String[]> getTKThang(String thang, String nam) {
        ArrayList<String[]> list = new ArrayList<>();
        List<Object[]> results = em.createNativeQuery("SELECT TOP 5 sp.maMH, sp.tenMH, SUM(od.soLuong) AS SoLuong, \n"
                        + "    ROUND((SUM(od.soLuong) * 100.0 / (SELECT SUM(SoLuong) FROM (SELECT TOP 5 SUM(od2.soLuong) AS SoLuong FROM [dbo].[ChiTietHoaDonDV] od2 JOIN HoaDon o2 ON od2.maHD = o2.maHD WHERE MONTH(o2.ngayLapHD) = ? AND YEAR(o2.ngayLapHD) = ? GROUP BY od2.maMH ORDER BY SoLuong DESC) AS top5)) , 2) AS TyLe, \n"
                        + "    ROUND(SUM(od.soLuong * sp.gia), 2) AS TongTien\n" + "FROM [dbo].[ChiTietHoaDonDV] od\n"
                        + "JOIN HoaDon o ON od.maHD = o.maHD\n" + "JOIN [dbo].[MatHang] sp ON od.maMH = sp.maMH\n"
                        + "WHERE MONTH(o.ngayLapHD) = ? AND YEAR(o.ngayLapHD) = ? " + "GROUP BY sp.maMH, sp.tenMH\n"
                        + "ORDER BY SUM(od.soLuong) DESC").setParameter(1, thang).setParameter(2, nam).setParameter(3, thang)
                .setParameter(4, nam).getResultList();

        for (Object[] result : results) {
            String[] arr = new String[5];
            arr[0] = (String) result[0]; // maMH
            arr[1] = (String) result[1]; // tenMH
            arr[2] = String.valueOf(result[2]); // SoLuong
            arr[3] = String.valueOf(result[3]); // TyLe
            arr[4] = String.valueOf(result[4]); // TongTien
            list.add(arr);

        }
        return list;
    }

    @Override
    public boolean doiPhong(String maHD, String phongHT, String phongMoi) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery("UPDATE ChiTietHoaDonDV SET maPhong = ? WHERE maHD = ? AND maPhong = ?")
                    .setParameter(1, phongMoi).setParameter(2, maHD).setParameter(3, phongHT).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @SneakyThrows
    public static void main(String[] args) {
//        createChiTietHoaDonDV

        ChiTietHoaDonDichVuDao cthddvDao = new ChiTietHoaDonDichVuImpl();
//        getAllTheMaHDDVforRoomArray
        List<ChiTietHoaDonDV> list = cthddvDao.getAllTheMaHDDVforRoomArray("HD019", "P034");
        for (ChiTietHoaDonDV cthdv : list) {
            System.out.println(cthdv.getMatHang().getTenMH());
        }



//        ChiTietHoaDonDV cthddv = new ChiTietHoaDonDV();
//        String maHD = "HD001"; // Mã hóa đơn
//        String maMH = "MH005"; // Mã mặt hàng
//        String maPhong = "P001"; // Mã phòng
//        int soLuong = 2; // Số lượng
//        double gia = 10.5; // Giá
////         public ChiTietHoaDonDV(HoaDon hoaDon, MatHang matHang, PhongHat phongHat, int soLuong, Double gia)
//        cthddv.setHoaDon(new HoaDon(maHD));
//        cthddv.setMatHang(new MatHang(maMH));
//        cthddv.setPhongHat(new PhongHat(maPhong));
//        cthddv.setSoLuong(soLuong);
//            cthddv.setGia(gia);
//        System.out.println(cthddvDao.createChiTietHoaDonDV(cthddv));

//        updateChiTietHoaDonDV
//        câp nhật ChiTietHoaDonDichVuDao
//        ChiTietHoaDonDV cthddv = new ChiTietHoaDonDV();
//        String maHD = "HD001"; // Mã hóa đơn
//        String maMH = "MH005"; // Mã mặt hàng
//        String maPhong = "P001"; // Mã phòng
//        int soLuong = 2; // Số lượng
//        double gia = 10.5; // Giá
////         public ChiTietHoaDonDV(HoaDon hoaDon, MatHang matHang, PhongHat phongHat, int soLuong, Double gia)
//        cthddv.setHoaDon(new HoaDon(maHD));
//        cthddv.setMatHang(new MatHang(maMH));
//        cthddv.setPhongHat(new PhongHat(maPhong));
//        cthddv.setSoLuong(soLuong);
//        cthddv.setGia(gia);
//        System.out.println(cthddvDao.updateChiTietHoaDonDV(cthddv));

    }

}
