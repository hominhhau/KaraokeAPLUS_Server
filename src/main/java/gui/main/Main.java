package gui.main;

import gui.form.Form_Home;
import entity.NhanVien;
import gui.component.Header;
import gui.component.Menu;
import gui.form.*;
import net.miginfocom.swing.MigLayout;
import gui.event.EventMenuSelected;
import gui.form.Form_Login;

import gui.event.EventShowPopUpMenu;
import gui.swing.MenuItem;
import gui.swing.PopupMenu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Lớp Main đại diện cho giao diện chính của ứng dụng.
 *
 * @author HO MINH HAU
 */
public class Main extends javax.swing.JFrame {

	private MigLayout layout;
	private Menu menu;
	private Header header;
	private MainForm main;
	private Animator animator;

	public Main() {
		initComponents();
		init();
		// set icon cho frame
		this.setIconImage(new ImageIcon(getClass().getResource("/icon/microphone.png")).getImage());

	}

	/**
	 * Khởi tạo các thành phần giao diện và các sự kiện.
	 */
	private void init() {
		layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
		bg.setLayout(layout);
		menu = new Menu();
		header = new Header();
		main = new MainForm();
		main.showForm(new Form_Home());

		// Thêm sự kiện cho menu khi cần hiển thị menu con (pop-up menu).
		// Khi thu nhỏ menu thì gọi sự kiện này để hiển thị popupmenu
		menu.addEventShowPopUpMenu(new EventShowPopUpMenu() {
			@Override
			public void showPopUp(Component com) {
				MenuItem item = (MenuItem) com;
				PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(),
						item.getMenu().getSubMenu());
				int x = Main.this.getX() + 52;
				int y = Main.this.getY() + com.getY() + 86;
				popup.setLocation(x, y);
				popup.setVisible(true);
			}
		});
		NhanVien nhanVienDangNhap = Form_Login.getNhanVienDangNhap();
		if (nhanVienDangNhap != null) {
			if (nhanVienDangNhap.getLoaiNV().getMaLoai().equals("NVQL")) {
				phanQuyenQL();
			} else if (nhanVienDangNhap.getLoaiNV().getMaLoai().equals("NVTN")) {
				phanQuyenTN();
			}
		} else {
			System.out.println("Không có nhân viên đăng nhập");
			System.out.println("Test thử");
			phanQuyenQL();
		}

		// check data để phân quyền nhân viên
//       String  maNV ="NV002";
		bg.add(menu, "w 220!, spany2");
		bg.add(header, "h 40!, wrap");
		bg.add(main, "w 100% , h 100%");

		// Tạo hiệu ứng mở và đóng menu bằng cách thay đổi kích thước và hiển thị của
		// menu.
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				double width;
				if (menu.isShowMenu()) {
					width = 60 + (170 * (1f - fraction));
				} else {
					width = 60 + (170 * fraction);
				}
				layout.setComponentConstraints(menu, "w " + width + "!, spany2");
				menu.revalidate();
			}

			@Override
			public void end() {
				menu.setShowMenu(!menu.isShowMenu());
			}
		};

		animator = new Animator(500, target);
		animator.setResolution(0);
		animator.setDeceleration(0.5f);
		animator.setAcceleration(0.5f);

// Thêm sự kiện cho nút menu (hiển thị hoặc ẩn menu).
		header.addMenuEvent(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//                if (!animator.isRunning()) {
//                    if (menu.isShowMenu()) {
//                        header.btnMenu.setIcon(new ImageIcon(getClass().getResource("/icon/next.png")));
//                    } else {
//                        header.btnMenu.setIcon(new ImageIcon(getClass().getResource("/icon/left.png")));
//                    }
//                    animator.start();
//                }
////                menu.setEnbleMenu(false);
//                if (menu.isShowMenu()) {
//                    menu.hideallMenu();
//                }

				chuyenDoiMenu();
			}
		});

		// Thêm phím tắt đóng mở Menu (Ctrl + M)
		Action toggleMenuAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chuyenDoiMenu();
			}
		};

		InputMap inputMap = bg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK), "chuyenDoiMenu");
		bg.getActionMap().put("chuyenDoiMenu", toggleMenuAction);
	}

	private void chuyenDoiMenu() {
		if (!animator.isRunning()) {
			if (menu.isShowMenu()) {
				header.btnMenu.setIcon(new ImageIcon(getClass().getResource("/icon/next.png")));
			} else {
				header.btnMenu.setIcon(new ImageIcon(getClass().getResource("/icon/left.png")));
			}
			animator.start();
		}
//                menu.setEnbleMenu(false);
		if (menu.isShowMenu()) {
			menu.hideallMenu();
		}
	}

	private void phanQuyenQL() {
		menu.addEvent(new EventMenuSelected() {
			@Override
			public void menuSelect(int menuIndex, int subMenuIndex) {
//                System.out.println("Menu Index:" + menuIndex + "SubMenuIndex:" + subMenuIndex);
				// Xử lý khi một mục menu được chọn
				// Ví dụ: Hiển thị một form tương ứng với mục menu được chọn.
				if (menuIndex == 0) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_Home());
					}
				} else if (menuIndex == 1) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLyDatPhong());
					}
				} else if (menuIndex == 2) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLyPhongHat());
					}
				} else if (menuIndex == 3) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLyKhachHang());
					}
				} else if (menuIndex == 4) {
					if (subMenuIndex == -1 || subMenuIndex == 0) {
						main.showForm(new Form_MatHang());
					} else if (subMenuIndex == 1) {
						main.showForm(new Form_DichVu());
					}
				} else if (menuIndex == 5) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLiHoaDon());
					}
				} else if (menuIndex == 6) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLyNhanVien());
					}
				} else if (menuIndex == 7) {
					if (subMenuIndex == -1 || subMenuIndex == 0) {
						main.showForm(new Form_ThongKeMatHang());
					} else if (subMenuIndex == 1) {
						main.showForm(new Form_ThongKeDoanhThu());
					}
				} else if (menuIndex == 8) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_Setting());
					}
				}

			}
		});
		menu.initMenuItem();
	}

	private void phanQuyenTN() {
		menu.addEvent(new EventMenuSelected() {
			@Override
			public void menuSelect(int menuIndex, int subMenuIndex) {
//                System.out.println("Menu Index:" + menuIndex + "SubMenuIndex:" + subMenuIndex);
				// Xử lý khi một mục menu được chọn
				// Ví dụ: Hiển thị một form tương ứng với mục menu được chọn.
				if (menuIndex == 0) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_Home());
					}
				} else if (menuIndex == 1) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLyDatPhong());
					}
				} else if (menuIndex == 2) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLyPhongHat());
					}
				} else if (menuIndex == 3) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLyKhachHang());
					}
				} else if (menuIndex == 4) {
					if (subMenuIndex == -1 || subMenuIndex == 0) {
						main.showForm(new Form_MatHang());
					} else if (subMenuIndex == 1) {
						main.showForm(new Form_DichVu());
					}
				} else if (menuIndex == 5) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_QuanLiHoaDon());
					}
				} else if (menuIndex == 6) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_ThongKeDoanhThu());
					}
				} else if (menuIndex == 7) {
					if (subMenuIndex == -1) {
						main.showForm(new Form_Setting());
					}
				}
			}
		});
		menu.initMenuItemTN();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		bg = new javax.swing.JLayeredPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		bg.setOpaque(true);

		javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
		bg.setLayout(bgLayout);
		bgLayout.setHorizontalGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				1221, Short.MAX_VALUE));
		bgLayout.setVerticalGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 700,
				Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(bg));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(bg));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;

				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLayeredPane bg;
	// End of variables declaration//GEN-END:variables
}
