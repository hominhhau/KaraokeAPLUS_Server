package gui;

import connectDB.DatabaseManager;
import gui.form.Form_Home;
import gui.form.Form_Login;
import gui.form.Form_MatHang;
import gui.form.Form_QuanLyPhongHat;
import gui.form.ProcessesLoading;
import gui.main.Main;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author HO MINH HAU
 */
public class Application {
	public static void main(String[] args) {
		// Khởi tạo và kết nối đến cơ sở dữ liệu
		DatabaseManager databaseManager = DatabaseManager.getInstance();
		databaseManager.connect();


//			Nếu kết nối được thì hiển thị form đăng nhập
//		if (databaseManager.isConnected()) {
//			Form_Login formLogin = new Form_Login();
//			formLogin.setVisible(true);
//		} else {
//			JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//		}
//	}
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// Nếu kết nối được thì hiển thị MainFrame
//		if (databaseManager.isConnected()) {
////			Hiển thị giao diện MainFrame  trang chủ
//			Main main = new Main();
//			main.setVisible(true);
//
////			new Main().setVisible(true);
////			Form_Home formHome = new Form_Home();
////			formHome.setVisible(true);
//			// 1. OK QuanLyPhongHat
////			Form_QuanLyPhongHat formQuanLyPhongHat = new Form_QuanLyPhongHat();
////			formQuanLyPhongHat.setVisible(true);
//
//			// 2. OK MatHang
////			Form_MatHang formMatHang = new Form_MatHang();
////			formMatHang.setVisible(true);
//
//		} else {
//			JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ProcessesLoading processesLoading = new ProcessesLoading();
				Form_Login formLogin = new Form_Login();
				// Hiển thị giao diện "ProcessesLoading"
				processesLoading.setVisible(true);
				// Kết nối đến cơ sở dữ liệu
				try {
					DatabaseManager db = DatabaseManager.getInstance();
					if (db.isConnected()) {
						System.out.println("Connection Success with database KaraokeAPLUS");
						// Đợi 3 giây trước khi ẩn "ProcessesLoading" và hiển thị "Form_Login"
						Timer timer = new Timer(3000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// Ẩn giao diện "ProcessesLoading"
								processesLoading.setVisible(false);
								// Hiển thị giao diện "Form_Login"
								formLogin.setVisible(true);
							}
						});
						timer.setRepeats(false); // Đảm bảo chạy một lần duy nhất
						timer.start();
					} else {
						System.out.println("Connection failed!");
					}
				} catch (Exception e) {


					Timer timer = new Timer(50000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Ẩn giao diện "ProcessesLoading" chờ hơn 5 phút thì thoát app
							processesLoading.setVisible(false);
						}
					});
					timer.setRepeats(false);
					timer.start();
					System.out.println("Connection failed! <<check databasename, user, password>>" + e.getMessage());
				}
			}
		});
	}
}
