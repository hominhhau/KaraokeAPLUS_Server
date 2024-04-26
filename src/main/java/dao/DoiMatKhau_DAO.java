package dao;

import connectDB.ConnectDB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

/**
 * @author 84934
 */
public class DoiMatKhau_DAO {
    public boolean doiMatKhau(String maNV, String matKhauMoi) {
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE TaiKhoan SET Password = ? WHERE maNV = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            // Băm mật khẩu mới trước khi lưu vào cơ sở dữ liệu (thay bằng hàm băm thực tế của bạn)
            String hashedMatKhauMoi = hashPassword(matKhauMoi);

            statement.setString(1, hashedMatKhauMoi);
            statement.setString(2, maNV);

            int rowsAffected = statement.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, có nghĩa là cập nhật thành công
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("DB related Error");
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String plainPassword) {
        try {
            // Sử dụng thuật toán SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Băm mật khẩu
            byte[] hashedBytes = messageDigest.digest(plainPassword.getBytes());

            // Chuyển đổi byte array thành chuỗi Base64
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

