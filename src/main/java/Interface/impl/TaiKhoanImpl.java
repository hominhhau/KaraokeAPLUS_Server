package Interface.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import Interface.TaiKhoanDao;

import connectDB.DatabaseManager;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TaiKhoanImpl implements TaiKhoanDao {


    private EntityManager em;

    public TaiKhoanImpl() {
		DatabaseManager.getInstance().connectIfNeeded();
		em = DatabaseManager.getInstance().getEntityManager();
    }

	@Override
	public String hashPassword(String plainPassword) {
    	try {
    		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(plainPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
    	}catch(NoSuchAlgorithmException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    @Override
    public boolean authenticate(String maNV, String plainPassword) throws Exception {
    	EntityTransaction tx = em.getTransaction();
    	boolean isUser = false;
		try {
			tx.begin();
			TaiKhoan tk = em.find(TaiKhoan.class, maNV);
			if(tk != null) {
				String hashedPasswordFromDB = tk.getPassWord();
				String hashedPasswordInput = hashPassword(plainPassword);
				if(hashedPasswordInput != null && hashedPasswordInput.equals(hashedPasswordFromDB)) {
					isUser = true;
					System.out.println("User authenticated successfully");
				}else {
					System.out.println("Invalid password!");
				}
			}else {
				System.out.println("Invalid maNV!");
			}
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return isUser;
    }

    @Override
    public boolean taoTK(String maNV) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			TaiKhoan tk = new TaiKhoan();
			tk.setUsername(new NhanVien(maNV));
			tk.setPassWord(hashPassword("123A"));
			em.persist(tk);
			tx.commit();
			return true;
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}
    @Override
    public boolean nhoMK(String maNV, StringBuilder password) {
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			TaiKhoan tk = em.find(TaiKhoan.class, maNV);
			if(tk != null){
				password.append(tk.getPassWord());
				tx.commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();
		}

        return false;
    }
}
