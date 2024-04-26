package Interface;

import entity.DonDatPhong;

import java.time.LocalDate;
import java.util.ArrayList;

public interface DonDatPhongDao {
    ArrayList<DonDatPhong> getalltbDonDatPhong();
    boolean createDonDatPhong(DonDatPhong ddp);
    ArrayList<DonDatPhong> getDonDatPhongTheoMaKH(String maKH);
    ArrayList<DonDatPhong> getDonDatPhongTheoNgayNhanPhong(LocalDate ngayNhanPhong);
    ArrayList<DonDatPhong> timDonDatPhong(String maDDP, String maKH, String maPhong);
    boolean deleteDonDatPhong(String maDDP, String maPhong);
}
