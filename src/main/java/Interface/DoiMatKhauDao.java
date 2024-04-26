package Interface;

public interface DoiMatKhauDao  {
    boolean doiMatKhau(String maNV, String matKhauMoi);
    String hashPassword(String plainPassword);
}
