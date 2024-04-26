package gui.form;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import Interface.HoaDonDao;
import Interface.KhachHangDao;
import Interface.KhuyenMaiDao;
import Interface.PhongHatDao;
import Interface.impl.HoaDonImpl;
import Interface.impl.KhachHangImpl;
import Interface.impl.KhuyenMaiImpl;
import Interface.impl.PhongHatImpl;

/**
 * @author 84343
 */
public class Form_Home extends javax.swing.JPanel {

	public Form_Home() {
		initComponents();
		KhachHangDao KH = new KhachHangImpl();
		long tongKH = KH.getSoLuongKhachHang();
		lblKH.setText(String.valueOf(tongKH));

		HoaDonDao HD = new HoaDonImpl();
		int tongSLHD = HD.getSoLuongHoaDon();
		lblHD.setText(String.valueOf(tongSLHD));

		PhongHatDao ph = new PhongHatImpl();
		int phongTrong = ph.getSoPhongTrong();
		lblPH.setText(String.valueOf(phongTrong));

		KhuyenMaiDao KM = new KhuyenMaiImpl();
		String[] km = KM.getKhuyenMai();
		String kmText = String.join(",     ", km);
		txtKM.setText(kmText);
		animateText();
		System.out.println("Form_Home");

	}

	public void animateText() {
		Timer t = new Timer(200, new ActionListener() {
			String text = txtKM.getText() + "      ";

			public void actionPerformed(ActionEvent e) {
				text = text.substring(1, text.length()) + text.charAt(0);
				txtKM.setText(text);
			}
		});

		t.start();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		pnlChinh = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		pnlCustomer = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		lblKH = new javax.swing.JLabel();
		pnlRoom = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		lblPH = new javax.swing.JLabel();
		pnlIcome = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		lblHD = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		txtKM = new javax.swing.JLabel();

		pnlChinh.setBackground(new java.awt.Color(235, 249, 249));

		jLabel2.setBackground(new java.awt.Color(255, 255, 255));
		jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(0, 0, 102));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("WELCOME TO");
		jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		pnlCustomer.setBackground(new java.awt.Color(41, 173, 86));
		pnlCustomer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel3.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/teamwork.png"))); // NOI18N
		jLabel3.setText("Customer");

		lblKH.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
		lblKH.setForeground(new java.awt.Color(255, 255, 255));
		lblKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblKH.setText("1");
		lblKH.setBorder(
				javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 255, 255), java.awt.Color.white));

		javax.swing.GroupLayout pnlCustomerLayout = new javax.swing.GroupLayout(pnlCustomer);
		pnlCustomer.setLayout(pnlCustomerLayout);
		pnlCustomerLayout.setHorizontalGroup(pnlCustomerLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlCustomerLayout.createSequentialGroup().addGroup(pnlCustomerLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlCustomerLayout.createSequentialGroup().addContainerGap().addComponent(jLabel3))
						.addGroup(pnlCustomerLayout.createSequentialGroup().addGap(64, 64, 64).addComponent(lblKH,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)))
						.addGap(74, 74, 74)));
		pnlCustomerLayout
				.setVerticalGroup(pnlCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlCustomerLayout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(12, 12, 12).addComponent(lblKH, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(36, Short.MAX_VALUE)));

		pnlRoom.setBackground(new java.awt.Color(255, 153, 153));
		pnlRoom.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel7.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(255, 255, 255));
		jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/room.png"))); // NOI18N
		jLabel7.setText("empty room");
		jLabel7.setMaximumSize(new java.awt.Dimension(550, 500));
		jLabel7.setMinimumSize(new java.awt.Dimension(550, 500));

		lblPH.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
		lblPH.setForeground(new java.awt.Color(204, 255, 255));
		lblPH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblPH.setText("1");
		lblPH.setBorder(
				javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 142, 0), java.awt.Color.yellow));

		javax.swing.GroupLayout pnlRoomLayout = new javax.swing.GroupLayout(pnlRoom);
		pnlRoom.setLayout(pnlRoomLayout);
		pnlRoomLayout.setHorizontalGroup(pnlRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlRoomLayout.createSequentialGroup()
						.addGroup(pnlRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlRoomLayout.createSequentialGroup().addContainerGap().addComponent(jLabel7,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnlRoomLayout.createSequentialGroup().addGap(62, 62, 62).addComponent(lblPH,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
						.addGap(71, 71, 71)));
		pnlRoomLayout.setVerticalGroup(pnlRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlRoomLayout.createSequentialGroup()
						.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblPH, javax.swing.GroupLayout.PREFERRED_SIZE, 83,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 41, Short.MAX_VALUE)));

		pnlIcome.setBackground(new java.awt.Color(205, 13, 13));
		pnlIcome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel5.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(255, 255, 255));
		jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Tin.png"))); // NOI18N
		jLabel5.setText("Bill");
		jLabel5.setMaximumSize(new java.awt.Dimension(550, 500));
		jLabel5.setMinimumSize(new java.awt.Dimension(550, 500));

		lblHD.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
		lblHD.setForeground(new java.awt.Color(255, 198, 53));
		lblHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblHD.setText("4");
		lblHD.setBorder(
				javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 142, 0), java.awt.Color.white));

		javax.swing.GroupLayout pnlIcomeLayout = new javax.swing.GroupLayout(pnlIcome);
		pnlIcome.setLayout(pnlIcomeLayout);
		pnlIcomeLayout.setHorizontalGroup(pnlIcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlIcomeLayout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						pnlIcomeLayout.createSequentialGroup().addGap(67, 67, 67)
								.addComponent(lblHD, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(70, 70, 70)));
		pnlIcomeLayout.setVerticalGroup(pnlIcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlIcomeLayout.createSequentialGroup()
						.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblHD, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 36, Short.MAX_VALUE)));

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/TC.png"))); // NOI18N

		jLabel4.setFont(new java.awt.Font("Sitka Text", 3, 14)); // NOI18N
		jLabel4.setForeground(new java.awt.Color(255, 153, 0));
		jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Tin.png"))); // NOI18N
		jLabel4.setText("Khuyến mãi ");

		txtKM.setFont(new java.awt.Font("Sitka Text", 3, 14)); // NOI18N
		txtKM.setForeground(new java.awt.Color(255, 51, 51));
		txtKM.setText("jLabel6");

		javax.swing.GroupLayout pnlChinhLayout = new javax.swing.GroupLayout(pnlChinh);
		pnlChinh.setLayout(pnlChinhLayout);
		pnlChinhLayout
				.setHorizontalGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlChinhLayout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnlChinhLayout.createSequentialGroup().addGap(50, 50, 50)
										.addComponent(pnlCustomer, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(87, 87, 87)
										.addComponent(pnlIcome, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(96, 96, 96)
										.addComponent(pnlRoom, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(98, 98, 98))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChinhLayout.createSequentialGroup()
								.addGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(pnlChinhLayout.createSequentialGroup().addGap(291, 291, 291)
												.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(24, 24, 24)
												.addComponent(txtKM, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(pnlChinhLayout.createSequentialGroup().addGap(245, 245, 245)
												.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGap(276, 276, 276)));
		pnlChinhLayout.setVerticalGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlChinhLayout.createSequentialGroup().addGap(72, 72, 72)
						.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(30, 30, 30)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4).addComponent(txtKM, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(32, 32, 32)
						.addGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(pnlRoom, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlCustomer, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlIcome, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(203, 203, 203)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(0, 0, 0).addComponent(pnlChinh,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				pnlChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel lblHD;
	private javax.swing.JLabel lblKH;
	private javax.swing.JLabel lblPH;
	private javax.swing.JPanel pnlChinh;
	private javax.swing.JPanel pnlCustomer;
	private javax.swing.JPanel pnlIcome;
	private javax.swing.JPanel pnlRoom;
	private javax.swing.JLabel txtKM;
	// End of variables declaration//GEN-END:variables
}
