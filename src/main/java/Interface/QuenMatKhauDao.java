package Interface;

public interface QuenMatKhauDao {
    //boolean SDT_tonTaiNV(String soDienThoai);
    boolean updatePasswordTaiKhoan(String soDienThoai, String matKhauMoi);
    boolean SDT_tonTaiNV(String soDienThoai);
    String hashPassword(String plainPassword);
}
