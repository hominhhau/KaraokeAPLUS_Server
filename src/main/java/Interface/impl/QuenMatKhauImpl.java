package Interface.impl;

import Interface.NhanVienDao;
import Interface.QuenMatKhauDao;
import connectDB.DatabaseManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class QuenMatKhauImpl implements QuenMatKhauDao {

    private EntityManager em;

    public QuenMatKhauImpl() {
        DatabaseManager.getInstance().connectIfNeeded();
        em = DatabaseManager.getInstance().getEntityManager();
    }


    @Override
    public boolean updatePasswordTaiKhoan(String soDienThoai, String matKhauMoi) {
        //"UPDATE TaiKhoan SET passWord = :passWord WHERE username IN(SELECT nv FROM NhanVien nv WHERE nv.SDT = :SDT)"
        EntityTransaction tx = em.getTransaction();
        try {
            //Kiem tra co giao dich nao dang thuc hien khong
//            if(!tx.isActive()){
//                tx.begin();
//            }
            em.createNamedQuery("TaiKhoan.updatePasswordTaiKhoan").
                    setParameter("passWord", hashPassword(matKhauMoi)).
                    setParameter("SDT", soDienThoai).
                    executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean SDT_tonTaiNV(String soDienThoai) {
        try {
            NhanVienDao nhanVienDao = new NhanVienImpl(); // hoặc khởi tạo từ constructor
            return nhanVienDao.SDT_tonTaiNV(soDienThoai);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String hashPassword(String plainPassword) {
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(plainPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        QuenMatKhauDao quenMatKhau = new QuenMatKhauImpl();
        //System.out.println(quenMatKhau.SDT_tonTaiNV("123456789"));
        System.out.println(quenMatKhau.updatePasswordTaiKhoan("0909444666", "1"));
    }
}
