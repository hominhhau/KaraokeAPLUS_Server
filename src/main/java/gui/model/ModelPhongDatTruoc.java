package gui.model;

public class ModelPhongDatTruoc {

    @Override
    public String toString() {
        return "ModelPhongDatTruoc{"
                + "maPhong='" + maPhong + '\''
                + ", maKH='" + maKH + '\''
                + ", ngayDat='" + ngayDat + '\''
                + ", ngayNhan='" + ngayNhan + '\''
                + ", maDonDatPhong='" + maDonDatPhong + '\''
                + '}';
    }

    private String maPhong;
    private String maKH;
    private String ngayDat;
    private String ngayNhan;
    private String maDonDatPhong;

    public ModelPhongDatTruoc(String maPhong, String maKH, String ngayDat, String ngayNhan, String maDonDatPhong) {
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.ngayDat = ngayDat;
        this.ngayNhan = ngayNhan;
        this.maDonDatPhong = maDonDatPhong;
    }

    public ModelPhongDatTruoc() {
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(String ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public String getMaDonDatPhong() {
        return maDonDatPhong;
    }

    public void setMaDonDatPhong(String maDonDatPhong) {
        this.maDonDatPhong = maDonDatPhong;
    }

}
