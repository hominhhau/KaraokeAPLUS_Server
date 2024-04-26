package entity;

import java.io.Serializable;
import java.util.Objects;

public class DichVu extends MatHang implements Serializable {

    private String maDV;
    private String tenDV;

    public DichVu(String maDV, String tenDV, String maMH, String tenMH, double gia, boolean trangThai) {
        super(maMH, tenMH, gia, trangThai);
        this.maDV = maDV;
        this.tenDV = tenDV;
    }

    public DichVu(String maDV, String tenDV, String maMH) {
        super(maMH);
        this.maDV = maDV;
        this.tenDV = tenDV;
    }

    public DichVu(String maDV, String tenDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
    }

    public DichVu() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DichVu(String maDV) {
        this.maDV = maDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) throws Exception {
        if (tenDV.trim().isEmpty()) {
            throw new Exception("Tên dịch vụ không được rỗng!");
        }
        // Kiểm tra ký tự đặc biệt
        String regex = "[\\W_]";
        if (tenDV.matches(regex)) {
            throw new Exception("Tên dịch vụ không chứa ký tự đặc biệt");
        }
        // Kiểm tra trùng
        if (tenDV.equals(this.tenDV)) {
            throw new Exception("Tên dịch vụ không được trùng");
        }
        this.tenDV = tenDV;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.maDV);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DichVu other = (DichVu) obj;
        return Objects.equals(this.maDV, other.maDV);
    }

    @Override
    public String toString() {
        return "DichVu [maDV=" + maDV + ", tenDV=" + tenDV + "]";
    }

}
