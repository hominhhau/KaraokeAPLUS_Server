package Test;

import Interface.TaiKhoanDao;
import Interface.impl.TaiKhoanImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test_TaiKhoan {
    private static TaiKhoanDao taiKhoanDao;

    public static void main(String[] args) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
		EntityManager em = emf.createEntityManager();
		//EntityTransaction tx = em.getTransaction();
		
		try {
//			tx.begin();
			int choosen = 1;

			switch (choosen) {
			case 1:
				//authenticate
				System.out.println("Test authenticate");
				try {
					taiKhoanDao = new TaiKhoanImpl();
					if(taiKhoanDao.authenticate("NV003", "123A")) {
	                    System.out.println("Dang nhap thanh cong");
					} else {
						System.out.println("Dang nhap that bai");
					}
					//System.out.println(taiKhoanDao.authenticate("NV001", "123A"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
            case 2:
				//taoTK
				System.out.println("Test taoTK");
				try {
					// @NamedQuery(name = "TaiKhoan.taoTK", query = "INSERT INTO TaiKhoan(passWord, username) VALUES(:passWord, :maNV)"),
					taiKhoanDao = new TaiKhoanImpl();
					if(taiKhoanDao.taoTK("NV009")){
						System.out.println("Tao tai khoan thanh cong");
					} else {
						System.out.println("Tao tai khoan that bai");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				//nhoMK
				System.out.println("Test nhoMK");
				try {
					taiKhoanDao = new TaiKhoanImpl();
					StringBuilder password = new StringBuilder();
					if(taiKhoanDao.nhoMK("NV001", password)) {
						System.out.println("Mat khau cua NV001 la: " + password);
					} else {
						System.out.println("Khong tim thay mat khau");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}



		} catch (Exception e) {
			//tx.rollback();
			e.printStackTrace();
		}finally {
            em.close();
            emf.close();
        }
    }
		
		
}
