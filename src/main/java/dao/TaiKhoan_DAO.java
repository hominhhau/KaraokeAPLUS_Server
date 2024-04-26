package dao;

import connectDB.ConnectDB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 * @author HO MINH HAU
 */
public class TaiKhoan_DAO {

    public TaiKhoan_DAO() {
    }
//    public boolean authenticate(String maNV, String Password)
//            throws Exception {
//        boolean isUser = false;
//        try {
//
//            Connection con = ConnectDB.getConnection();
//            String sql = "select  *from TaiKhoan where maNV = ? and Password = ?";
//            PreparedStatement statement = con.prepareStatement(sql);
//
//            statement.setString(1, maNV);
//            statement.setString(2, Password);
//            ResultSet result = statement.executeQuery();
//            if (result.next()) {
//                isUser = true;
//                System.out.println("User authenticated successfully");
//            } else {
//                System.out.println("Invalid maNV or password!");
//            }
//        } catch (Exception e) {
//            System.out.println("DB related Error");
//            e.printStackTrace();
//        }
//        return isUser;
//    }

    // Hàm băm mật khẩu sử dụng SHA-256
    private String hashPassword(String plainPassword) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(plainPassword.getBytes());
//            byte[] hashedBytes = md.digest();
//
//            // Chuyển đổi byte array sang dạng hex string
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashedBytes) {
//                sb.append(String.format("%02x", b));
//            }
//
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            // Xử lý exception nếu thuật toán không được hỗ trợ
//            e.printStackTrace();
//            return null;
//        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(plainPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean authenticate(String maNV, String plainPassword) throws Exception {
        boolean isUser = false;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE maNV = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, maNV);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String hashedPasswordFromDB = result.getString(2);
                String hashedPasswordInput = hashPassword(plainPassword);
                if (hashedPasswordInput != null && hashedPasswordInput.equals(hashedPasswordFromDB)) {
                    isUser = true;
                    System.out.println("User authenticated successfully");
                } else {
                    System.out.println("Invalid password!");
                }
            } else {
                System.out.println("Invalid maNV!");
            }
        } catch (Exception e) {
            System.out.println("DB related Error");
            e.printStackTrace();
        }
        return isUser;
    }

    public boolean taoTK(String maNV) {
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement statement = con.prepareStatement("INSERT into TaiKhoan VALUES(?,?)");
            statement.setString(1, maNV);

            String hashedDefaultPassword = hashPassword("123A");
            statement.setString(2, hashedDefaultPassword);

            n = statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;

    }

    //    public void nhoMK(String maNV, StringBuilder passwordRef) {
//        Connection con = ConnectDB.getConnection();
//        try {
//            PreparedStatement statement = con.prepareStatement("SELECT Password FROM TaiKhoan WHERE maNV = ?");
//            statement.setString(1, maNV);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String pass = resultSet.getString("Password");
//                passwordRef.append(pass);
//            } else {
//                System.out.println("Không tồn tại");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public boolean nhoMK(String maNV, StringBuilder password) {
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
//            System.out.println("Không ");
            PreparedStatement statement = con.prepareStatement("SELECT Password FROM TaiKhoan WHERE maNV = ?");
            statement.setString(1, maNV);
            ResultSet resultSet = statement.executeQuery();
//            System.out.println(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
