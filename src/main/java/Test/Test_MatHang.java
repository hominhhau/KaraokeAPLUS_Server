package Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import Interface.MatHangDao;
import Interface.impl.KhuyenMaiImpl;
import Interface.impl.MatHangImpl;
import entity.KhuyenMai;
import entity.MatHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test_MatHang {
	private static MatHangDao matHangDao;

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaraokeAplus_BTL MSSQL");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			// 1. Lấy danh sách mặt hàng
			matHangDao = new MatHangImpl();
			ArrayList<MatHang> dsMatHang = matHangDao.getalltbMatHang();
			System.out.println("Số mặt hàng: " + dsMatHang.size());
			System.out.println("Danh sách mặt hàng: ");
			for (MatHang mh : dsMatHang) {
				System.out
						.println(mh.getMaMH() + " - " + mh.getTenMH() + " - " + mh.getGia() + " - " + mh.isTrangThai());
			}
//
//			// 2. Thêm mặt hàng mới vào danh sách mặt hàng không được trùng mã mặt hàng
//			try {
//				MatHang mh = new MatHang("MH0061", "Máy lạnh", 2000000, true);
//				if (matHangDao.findMatHang("MH0061") == null) {
//					matHangDao.addMatHang(mh);
//					System.out.println("Thêm mặt hàng thành công");
//					System.out.println("Danh sách mặt hàng sau khi thêm: ");
//					ArrayList<MatHang> dsMatHang1 = matHangDao.getalltbMatHang();
//					for (MatHang mh1 : dsMatHang1) {
//						System.out.println(mh1.getMaMH() + " - " + mh1.getTenMH() + " - " + mh1.getGia() + " - "
//								+ mh1.isTrangThai());
//					}
//				} else {
//					System.out.println("Mã mặt hàng đã tồn tại");
//				}
//			} catch (Exception e) {
//				System.out.println("Thêm mặt hàng thất bại");
//			}
//
//			// 3. Xóa mặt hàng theo mã mặt hàng
//			try {
//				if (matHangDao.findMatHang("MH005") != null) {
//					matHangDao.DeleteMatHang("MH005");
//					System.out.println("Xóa mặt hàng thành công");
//					System.out.println("Danh sách mặt hàng sau khi xóa: ");
//					ArrayList<MatHang> dsMatHang2 = matHangDao.getalltbMatHang();
//					for (MatHang mh : dsMatHang2) {
//						System.out.println(
//								mh.getMaMH() + " - " + mh.getTenMH() + " - " + mh.getGia() + " - " + mh.isTrangThai());
//					}
//				} else {
//					System.out.println("Mã mặt hàng không tồn tại");
//				}
//			} catch (Exception e) {
//				System.out.println("Xóa mặt hàng thất bại");
//			}
//
//			// 4. Tìm mặt hàng theo mã mặt hàng
//			try {
//				MatHang mh = matHangDao.findMatHang("MH007");
//				System.out.println("Thông tin mặt hàng: ");
//				System.out
//						.println(mh.getMaMH() + " - " + mh.getTenMH() + " - " + mh.getGia() + " - " + mh.isTrangThai());
//			} catch (Exception e) {
//				System.out.println("Không tìm thấy mặt hàng");
//			}
//
//			// 5. Tông số mặt hàng
//			int tongSoMH = matHangDao.getTongSoMH();
//			System.out.println("Tổng số mặt hàng: " + tongSoMH);

			// 6. Lấy danh sách mặt hàng theo năm
//			 stmt.setString(1, nam);
//	            ResultSet rs = stmt.executeQuery();
//	            while (rs.next()) {
//	                String[] arr = new String[4];
//	                arr[0] = rs.getString("MaMH");
//	                arr[1] = rs.getString("TenMH");
//	                arr[2] = rs.getString("SoLuong");
//	                arr[3] = rs.getString("TongTien");
//
//	                list.add(arr);
//	            }
//			ArrayList<String[]> list = new ArrayList<>();
//
//			list = matHangDao.getMHNam("2023");
//			System.out.println("Danh sách mặt hàng theo năm: ");
//			while (list.size() > 0) {
//				String[] arr = new String[4];
//				arr[0] = list.get(0)[0];
//				arr[1] = list.get(0)[1];
//				arr[2] = list.get(0)[2];
//				arr[3] = list.get(0)[3];
//				System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2] + " - " + arr[3]);
//				list.add(arr);
//				System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2] + " - " + arr[3]);
//			}

			// 7. Tổng tiền mặt hàng theo năm
			Double tongTienNam = matHangDao.getTongTienNam("2023");
			System.out.println("Tổng tiền mặt hàng theo năm: " + tongTienNam);

//			// 8. Lấy danh sách mặt hàng theo tháng
//			ArrayList<String[]> dsMatHangThang = matHangDao.getMHThang("10", "2023");
//			System.out.println("Danh sách mặt hàng theo tháng: ");
//			for (String[] mh : dsMatHangThang) {
//				System.out.println(mh[0] + " - " + mh[1] + " - " + mh[2]);
//			}
//
			// 9. Tổng tiền mặt hàng theo tháng
			Double tongTienThang = matHangDao.getTongTienThang("10", "2023");
			System.out.println("Tổng tiền mặt hàng theo tháng: " + tongTienThang);

//			// 10. Lấy danh sách mặt hàng theo ngày
//			String dateString = "2023-10-31";
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//			try {
//				Date date = (Date) dateFormat.parse(dateString);
//				// Sử dụng giá trị Date trong truy vấn của bạn
//				ArrayList<String[]> dsMatHangNgay = matHangDao.getMHNgay(date);
//				System.out.println("Danh sách mặt hàng theo ngày: ");
//				for (String[] mh : dsMatHangNgay) {
//					System.out.println(mh[0] + " - " + mh[1] + " - " + mh[2]);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//

			// 11. Tổng tiền mặt hàng theo ngày
//			LocalDate date = LocalDate.parse("2023-10-31");
//			Double tongTienNgay = matHangDao.getTongTienNgay(date);
//			System.out.println("Tổng tiền mặt hàng theo ngày: " + tongTienNgay);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}

		ArrayList<String[]> list = matHangDao.getMHNam("2023");
		System.out.println("Danh sách mặt hàng theo năm: ");
		for (String[] arr : list) {
			System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2] + " - " + arr[3]);
		}
		
	}
}
