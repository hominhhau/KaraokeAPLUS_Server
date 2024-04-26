package Interface;


import java.util.ArrayList;
import java.util.Date;

import entity.MatHang;

public interface MatHangDao {
    public ArrayList<MatHang> getalltbMatHang();

    public ArrayList<MatHang> getMatHangTheoMaMH(String id);

    public boolean addMatHang(MatHang mh);

    public boolean DeleteMatHang(String maMH);

    public MatHang findMatHang(String maTim);

    public boolean editMatHang(MatHang mh);

    public int getTongSoMH();

    public ArrayList<String[]> getMHNam(String nam);

    public Double getTongTienNam(String nam);

    public ArrayList<String[]> getMHThang(String thang, String nam);

    public Double getTongTienThang(String thang, String nam);

    public ArrayList<String[]> getMHNgay(Date ngay);

    public Double getTongTienMHNgay(Date date);
}


