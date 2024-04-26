package Interface.impl;

import Interface.DoiMatKhauDao;
import connectDB.DatabaseManager;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DoiMatKhauImpl implements DoiMatKhauDao {

    private EntityManager em;

    public DoiMatKhauImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }
    @Override
    public boolean doiMatKhau(String maNV, String matKhauMoi) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();;
            //@NamedQuery(name = "TaiKhoan.doiMatKhau", query = "UPDATE TaiKhoan SET passWord = :passWord WHERE username = :maNV")
            TaiKhoan tk  = new TaiKhoan();
            tk.setUsername(new NhanVien(maNV));
            tk.setPassWord(hashPassword(matKhauMoi));
            em.merge(tk);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String hashPassword(String plainPassword) {
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
