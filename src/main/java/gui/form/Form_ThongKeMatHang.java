package gui.form;

import com.raven.chart.ModelChart;

import Interface.ChiTietHoaDonDichVuDao;
import Interface.HoaDonDao;
import Interface.MatHangDao;
import Interface.impl.ChiTietHoaDonDichVuImpl;
import Interface.impl.HoaDonImpl;
import Interface.impl.MatHangImpl;
import entity.ChiTietHoaDonDV;
import entity.HoaDon;
import gui.swing.scrollbar.ScrollBarCustom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javaswingdev.chart.ModelPieChart;
import javaswingdev.chart.PieChart;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * @author HO MINH HAU
 */
public class Form_ThongKeMatHang extends javax.swing.JPanel {

	private HoaDonDao hd_dao;
	private MatHangDao mh_dao;
	private DefaultTableModel dtmdv;
	private DefaultTableModel dtmmh;
	private final DecimalFormat df = new DecimalFormat("#,###");
	private final SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd");
	// private DefaultCategoryDataset dataset;
	private ChartPanel chartPanel;
	private List<ModelChart> data;
	private ChiTietHoaDonDichVuDao ctdv_dao;
	private DefaultPieDataset dataset;
	private PieDataset pieDataset;

	public Form_ThongKeMatHang() {
		initComponents();
		scr1.getViewport().setOpaque(false);
		scr1.setVerticalScrollBar(new ScrollBarCustom());
		scr2.getViewport().setOpaque(false);
		scr2.setVerticalScrollBar(new ScrollBarCustom());
		hd_dao = new HoaDonImpl();
		ctdv_dao = new ChiTietHoaDonDichVuImpl();
		mh_dao = new MatHangImpl();
		dtmdv = (DefaultTableModel) tblTKDV.getModel();
		dtmmh = (DefaultTableModel) tblTKMH.getModel();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date selectedDate = txtDate.getDate();
		String formattedDate = dateFormat.format(selectedDate);
		txtDate.setDateFormatString("yyyy-MM-dd");

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date chooser = txtDate.getDate();
		String corect = simple.format(chooser);
		java.util.Date utilDate = null;
		try {
			utilDate = dateFormat.parse(corect);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	}

	/**
	 * Xóa dữ liệu
	 */
	private void removeData() {
		DefaultTableModel dv = (DefaultTableModel) tblTKDV.getModel();
		DefaultTableModel mh = (DefaultTableModel) tblTKMH.getModel();
		dv.setRowCount(0);
		mh.setRowCount(0);
	}

	public void drawChart() {
		String thang = cmbTKTheo.getSelectedItem().toString();
		String nam = cmbTKChiTiet.getSelectedItem().toString();
		// Lấy giá trị từ txtDate (giả sử đây là một đối tượng DatePicker)
		Date selectedDate = txtDate.getDate();

		// Chuyển đổi từ java.util.Date sang java.time.LocalDate
		LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// Chuyển đổi từ java.time.LocalDate sang java.util.Date
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		ArrayList<String[]> data;

		if (thang.equals("0")) {
			// Vẽ biểu đồ cho cả năm
			data = ctdv_dao.getTOPDVNam(nam);
			pieChart1.clearData(); // Xóa dữ liệu biểu đồ cũ
			pnl.removeAll(); // Xóa thành phần hiện tại của pnl
			cmbTKTheo.setSelectedIndex(0); // Đặt lại giá trị của cmbTKTheo
		} else if (nam.equals("0")) {
			// Vẽ biểu đồ cho một tháng cụ thể trong năm
			data = ctdv_dao.getTOPDVThang(thang, nam);
			pieChart1.clearData(); // Xóa dữ liệu biểu đồ cũ
			pnl.removeAll(); // Xóa thành phần hiện tại của pnl
		} else {
			// Vẽ biểu đồ cho một ngày cụ thể trong tháng
			data = ctdv_dao.getTOPDVNgay(date);
			pieChart1.clearData(); // Xóa dữ liệu biểu đồ cũ
			pnl.removeAll(); // Xóa thành phần hiện tại của pnl
			cmbTKTheo.setSelectedIndex(0); // Đặt lại giá trị của cmbTKTheo
			cmbTKChiTiet.setSelectedIndex(0); // Đặt lại giá trị của cmbTKChiTiet
		}

		DefaultPieDataset dataset = new DefaultPieDataset();

		int[][] rgbValues = { { 41, 173, 86 }, { 205, 13, 13 }, { 255, 153, 153 }, { 166, 208, 238 }, { 189, 135, 245 }
				// Thêm các mã màu khác tại đây nếu cần
		};

		int numDataPoints = data.size();

		for (int i = 0; i < numDataPoints; i++) {
			String[] item = data.get(i);
			String tenMH = item[1];
			double tyle = Double.parseDouble(item[3]);
			int[] rgb = rgbValues[i % rgbValues.length];
			Color color = new Color(rgb[0], rgb[1], rgb[2]);

			dataset.setValue(tenMH, tyle);
			pieChart1.addData(new ModelPieChart(tenMH, tyle, color));
		}

		JFreeChart chart = ChartFactory.createPieChart("Biểu đồ", dataset, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));

		pnl.removeAll();
		pnl.add(chartPanel);
		pnl.revalidate();
		pnl.repaint();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		materialTabbed1 = new gui.swing.tabbed.MaterialTabbed();
		jPanel1 = new javax.swing.JPanel();
		pnlHeader1 = new javax.swing.JPanel();
		lblTKMH1 = new javax.swing.JLabel();
		cmbTKChiTiet = new javax.swing.JComboBox<>();
		jLabel8 = new javax.swing.JLabel();
		lblNgay = new javax.swing.JLabel();
		txtDate = new com.toedter.calendar.JDateChooser();
		jLabel3 = new javax.swing.JLabel();
		cmbTKTheo = new javax.swing.JComboBox<>();
		materialTabbed4 = new gui.swing.tabbed.MaterialTabbed();
		jPanel6 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		scr1 = new javax.swing.JScrollPane();
		tblTKMH = new javax.swing.JTable();
		lblTong = new javax.swing.JLabel();
		txtTong = new javax.swing.JTextField();
		materialTabbed5 = new gui.swing.tabbed.MaterialTabbed();
		jPanel8 = new javax.swing.JPanel();
		scr2 = new javax.swing.JScrollPane();
		tblTKDV = new javax.swing.JTable();
		jLabel2 = new javax.swing.JLabel();
		txtTienDV = new javax.swing.JTextField();
		pnlBieuDo = new javax.swing.JPanel();
		pnlChart = new javax.swing.JPanel();
		lblBieuDo = new javax.swing.JLabel();
		pnl = new javax.swing.JPanel();
		pieChart1 = new javaswingdev.chart.PieChart();

		jPanel1.setBackground(new java.awt.Color(235, 249, 249));

		pnlHeader1.setBackground(new java.awt.Color(255, 255, 255));
		pnlHeader1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		lblTKMH1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
		lblTKMH1.setForeground(new java.awt.Color(41, 173, 86));
		lblTKMH1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTKMH1.setText("THỐNG KÊ MẶT HÀNG");
		lblTKMH1.setFocusable(false);
		lblTKMH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		cmbTKChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		cmbTKChiTiet
				.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "2022", "2023", "2024", "2025" }));
		cmbTKChiTiet.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbTKChiTietActionPerformed(evt);
			}
		});

		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel8.setText("Năm:");

		lblNgay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblNgay.setText("Ngày");

		txtDate.setDate(new Date());
		txtDate.setDateFormatString("dd-MM-yyyy hh:mm");
		txtDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		txtDate.setOpaque(false);
		txtDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtDatePropertyChange(evt);
			}
		});

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel3.setText("Tháng");

		cmbTKTheo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		cmbTKTheo.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "0", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cmbTKTheo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbTKTheoActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlHeader1Layout = new javax.swing.GroupLayout(pnlHeader1);
		pnlHeader1.setLayout(pnlHeader1Layout);
		pnlHeader1Layout
				.setHorizontalGroup(
						pnlHeader1Layout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlHeader1Layout.createSequentialGroup().addGap(36, 36, 36)
										.addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 181,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(160, 160, 160)
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cmbTKTheo, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(172, 172, 172)
										.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cmbTKChiTiet, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(139, 139, 139))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnlHeader1Layout.createSequentialGroup()
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblTKMH1, javax.swing.GroupLayout.PREFERRED_SIZE, 273,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(359, 359, 359)));
		pnlHeader1Layout.setVerticalGroup(pnlHeader1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeader1Layout.createSequentialGroup()
						.addContainerGap().addComponent(lblTKMH1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
						.addGroup(
								pnlHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(pnlHeader1Layout.createSequentialGroup().addGroup(pnlHeader1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(cmbTKTheo, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(cmbTKChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel8)).addGap(3, 3, 3))
										.addGroup(pnlHeader1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(29, 29, 29)));

		materialTabbed4.setBackground(new java.awt.Color(235, 249, 249));

		jPanel6.setBackground(new java.awt.Color(235, 249, 249));

		jPanel7.setBackground(new java.awt.Color(255, 255, 255));

		tblTKMH.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "STT", "Mã mặt hàng", "Tên mặt hàng", "Số lượng bán ra", "Tổng trị giá" }));
		tblTKMH.setRowHeight(30);
		tblTKMH.setSelectionBackground(new java.awt.Color(0, 169, 183));
		scr1.setViewportView(tblTKMH);

		lblTong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblTong.setText("Tổng");

		txtTong.setEditable(false);

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel7Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 58,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 107,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(16, 16, 16))
				.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel7Layout.createSequentialGroup().addGap(5, 5, 5)
								.addComponent(scr1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
								.addContainerGap())));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
						.addContainerGap(201, Short.MAX_VALUE)
						.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(14, 14, 14))
				.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel7Layout.createSequentialGroup().addGap(4, 4, 4)
								.addComponent(scr1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
								.addGap(48, 48, 48))));

		jPanel8.setBackground(new java.awt.Color(255, 255, 255));

		tblTKDV.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "STT", "Mã dịch vụ", "Tên dịch vụ", "Số lượng tiêu thụ", "Chiểm (%)", "Tổng trị giá" }));
		tblTKDV.setRowHeight(30);
		tblTKDV.setSelectionBackground(new java.awt.Color(0, 169, 183));
		scr2.setViewportView(tblTKDV);

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel2.setText("Tổng");

		txtTienDV.setEditable(false);

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel8Layout.createSequentialGroup().addContainerGap()
						.addComponent(scr2, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel8Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(txtTienDV, javax.swing.GroupLayout.PREFERRED_SIZE, 112,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(17, 17, 17)));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scr2, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(txtTienDV, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(216, 216, 216)));

		materialTabbed5.addTab("Các loại dịch vụ bán chạy", jPanel8);

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup()
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(materialTabbed5, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(16, 16, 16)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(materialTabbed5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, Short.MAX_VALUE)
						.addGap(12, 12, 12)));

		materialTabbed4.addTab("Bảng thống kê", jPanel6);

		pnlChart.setBackground(new java.awt.Color(255, 255, 255));

		lblBieuDo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
		lblBieuDo.setText("BIỂU ĐỒ THỐNG KÊ CÁC DỊCH VỤ BÁN CHẠY");

		pnl.setBackground(new java.awt.Color(255, 255, 255));
		pnl.setPreferredSize(new java.awt.Dimension(1000, 481));

		pieChart1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

		javax.swing.GroupLayout pnlLayout = new javax.swing.GroupLayout(pnl);
		pnl.setLayout(pnlLayout);
		pnlLayout.setHorizontalGroup(pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlLayout.createSequentialGroup().addContainerGap()
						.addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlLayout.setVerticalGroup(pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlLayout
						.createSequentialGroup().addGap(14, 14, 14).addComponent(pieChart1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(34, Short.MAX_VALUE)));

		javax.swing.GroupLayout pnlChartLayout = new javax.swing.GroupLayout(pnlChart);
		pnlChart.setLayout(pnlChartLayout);
		pnlChartLayout
				.setHorizontalGroup(
						pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnlChartLayout.createSequentialGroup()
												.addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, 1012,
														Short.MAX_VALUE)
												.addContainerGap())
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnlChartLayout.createSequentialGroup().addGap(350, 350, 350)
												.addComponent(lblBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(280, 280, 280)));
		pnlChartLayout.setVerticalGroup(pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlChartLayout.createSequentialGroup().addGap(15, 15, 15)
						.addComponent(lblBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addContainerGap()));

		javax.swing.GroupLayout pnlBieuDoLayout = new javax.swing.GroupLayout(pnlBieuDo);
		pnlBieuDo.setLayout(pnlBieuDoLayout);
		pnlBieuDoLayout.setHorizontalGroup(
				pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pnlChart,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		pnlBieuDoLayout.setVerticalGroup(
				pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pnlChart,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		materialTabbed4.addTab("Biểu đồ", pnlBieuDo);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(materialTabbed4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
				.addComponent(pnlHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(pnlHeader1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(materialTabbed4, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Combobox Năm
	 *
	 * @param evt
	 */
	private void cmbTKChiTietActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmbTKChiTietActionPerformed
		String thang = cmbTKTheo.getSelectedItem().toString();
		String nam = cmbTKChiTiet.getSelectedItem().toString();
		
//		tháng không được chọn
		thang = thang.equals("0") ? "0" : thang;
		ArrayList<String[]> dsDVThang = ctdv_dao.getTOPDVThang(thang, nam);
		ArrayList<String[]> dsDVNam = ctdv_dao.getTOPDVNam(nam);
		removeData();

		// Load data lên table

		ChiTietHoaDonDichVuDao cthd = new ChiTietHoaDonDichVuImpl();
		int stt = 1;
		for (int i = 0; i < 5 && i < dsDVNam.size(); i++) {
			String[] arr = dsDVNam.get(i);
			String maDV = arr[0];
			String tenDV = arr[1];
			int soLuong = Integer.parseInt(arr[2]);
			Double tyLe = Double.parseDouble(arr[3]);
			double tongTien = Double.parseDouble(arr[4]);
			dtmdv.addRow(new Object[] { stt, maDV, tenDV, soLuong, tyLe, tongTien });
			stt++;
		}
		double tongTienDV = cthd.getTongTienNam(nam);
		txtTienDV.setText(String.valueOf(tongTienDV));

		ArrayList<String[]> dsMHNam = mh_dao.getMHNam(nam);
		for (int i = 0; i < dsMHNam.size(); i++) {
			String[] arr = dsMHNam.get(i);
			String maMH = arr[0];
			String tenMH = arr[1];
			int soLuong = Integer.parseInt(arr[2]);
			double tongTien = Double.parseDouble(arr[3]);
			dtmmh.addRow(new Object[] { i + 1, maMH, tenMH, soLuong, tongTien });

		}

		MatHangDao ctmh = new MatHangImpl();
		Double tongTienMH = ctmh.getTongTienNam(nam);
		if (tongTienMH != null) {
			txtTong.setText(String.valueOf(tongTienMH));
		} else {
			txtTong.setText("0.0"); // Hoặc giá trị mặc định khác khi không có dữ liệu
		}

		/*
		 * Vẽ biểu đồ
		 */
		dataset = new DefaultPieDataset();
		ArrayList<String[]> data;

		if (thang.equals("0")) {
			// Vẽ biểu đồ cho cả năm
			data = ctdv_dao.getTOPDVNam(nam);
			pieChart1.clearData(); // Xóa dữ liệu biểu đồ cũ
			pnl.removeAll(); // Xóa thành phần hiện tại của pnl
		} else {
			// Vẽ biểu đồ cho một tháng cụ thể trong năm
			data = ctdv_dao.getTOPDVThang(thang, nam);
			pieChart1.clearData(); // Xóa dữ liệu biểu đồ cũ
			pnl.removeAll(); // Xóa thành phần hiện tại của pnl
		}

		dataset.clear();

		// Mảng các màu sắc
		int[][] rgbValues = { { 41, 173, 86 }, { 205, 13, 13 }, { 255, 153, 153 }, { 166, 208, 238 }, { 189, 135, 245 }
				// Thêm các mã màu khác tại đây nếu cần
		};

		int numDataPoints = Math.min(5, data.size());

		// Xóa các mục cũ khỏi biểu đồ
		pieChart1.clearData();

		for (int i = 0; i < numDataPoints; i++) {
			String[] item = data.get(i);

			if (item.length > 5) {
				System.out.println("Không tạo");
				pnlChart.removeAll();
			}

			String tenMH = item[1];
			double tyle = Double.parseDouble(item[3]);
			int[] rgb = rgbValues[i % rgbValues.length];
			Color color = new Color(rgb[0], rgb[1], rgb[2]);

			dataset.setValue(tenMH, tyle);
			pieChart1.addData(new ModelPieChart(tenMH, tyle, color));
		}

		pnl.removeAll();
		pnl.add(pieChart1);
		pnl.repaint();
	}// GEN-LAST:event_cmbTKChiTietActionPerformed

	/**
	 * Ngày
	 *
	 * @param evt
	 */
	private void txtDatePropertyChange(java.beans.PropertyChangeEvent evt) {
		// Lấy giá trị từ txtDate (giả sử đây là một đối tượng DatePicker)
		Date selectedDate = txtDate.getDate();

		// Chuyển đổi từ java.util.Date sang java.time.LocalDate
		LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// Chuyển đổi từ java.time.LocalDate sang java.util.Date
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		// Gọi phương thức với tham số kiểu java.util.Date
		ArrayList<String[]> dsDVNgay = ctdv_dao.getTOPDVNgay(date);
		removeData();
		int stt = 1;
		int index = 1;

		for (String[] arr : dsDVNgay) {
			String maDV = arr[0];
			String tenDV = arr[1];
			int soLuong = Integer.parseInt(arr[2]);
			double tyLe = Double.parseDouble(arr[3]);
			double tongTien = Double.parseDouble(arr[4]);
			dtmdv.addRow(new Object[] { stt, maDV, tenDV, soLuong, tyLe, tongTien });
			stt++;
			if (stt > 5) {
				break;
			}
		}

		ChiTietHoaDonDichVuDao cthd = new ChiTietHoaDonDichVuImpl();
		double tongALLTien = cthd.getTongTienNgayDV(date);
		txtTienDV.setText(String.valueOf(tongALLTien));

		ArrayList<String[]> dsMHNgay = mh_dao.getMHNgay(date);
		for (String[] arr : dsMHNgay) {
			String maDV = arr[0];
			String tenDV = arr[1];
			int soLuong = Integer.parseInt(arr[2]);
			double tongTien = Double.parseDouble(arr[3]);
			dtmmh.addRow(new Object[] { index, maDV, tenDV, soLuong, tongTien });
			index++;
		}

		MatHangDao ctmh = new MatHangImpl();
		double tongTienMH = ctmh.getTongTienMHNgay(date);
		txtTong.setText(String.valueOf(tongTienMH));

	}

	// GEN-LAST:event_txtDatePropertyChange

	/**
	 * combobox tháng
	 *
	 * @param evt
	 */
	private void cmbTKTheoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmbTKTheoActionPerformed
		String thang = cmbTKTheo.getSelectedItem().toString();
		String nam = cmbTKChiTiet.getSelectedItem().toString();
		ArrayList<String[]> dsDVThang = ctdv_dao.getTOPDVThang(thang, nam);
		removeData();
		pieChart1.removeAll();

		ChiTietHoaDonDichVuDao cthd = new ChiTietHoaDonDichVuImpl();
		int stt = 1;
		for (int i = 0; i < 5 && i < dsDVThang.size(); i++) {
			String[] arr = dsDVThang.get(i);
			String maDV = arr[0];
			String tenDV = arr[1];
			int soLuong = Integer.parseInt(arr[2]);
			Double tyLe = Double.parseDouble(arr[3]);
			double tongTien = Double.parseDouble(arr[4]);
			dtmdv.addRow(new Object[] { stt, maDV, tenDV, soLuong, tyLe, tongTien });
			stt++;

		}
		double tongALLTien = cthd.getTongTienThangTop(thang, nam);
		txtTienDV.setText(String.valueOf(tongALLTien));

		int index = 1;
		ArrayList<String[]> dsMHThang = mh_dao.getMHThang(thang, nam);
		for (int i = 0; i < dsMHThang.size(); i++) {
			String[] arr = dsMHThang.get(i);
			String maDV = arr[0];
			String tenDV = arr[1];
			int soLuong = Integer.parseInt(arr[2]);
			double tongTien = Double.parseDouble(arr[3]);
			dtmmh.addRow(new Object[] { index, maDV, tenDV, soLuong, tongTien });
			index++;

			MatHangDao ctmh = new MatHangImpl();
			double tongTienMH = ctmh.getTongTienThang(thang, nam);
			txtTong.setText(String.valueOf(tongTienMH));
		}

		/*
		 * Vẽ biểu đồ
		 */
		dataset = new DefaultPieDataset();
		ArrayList<String[]> data;

		if (thang.equals("0")) {
			// Vẽ biểu đồ cho cả năm
			data = ctdv_dao.getTKNam(nam);
			pieChart1.clearData(); // Xóa dữ liệu biểu đồ cũ
			pnl.removeAll(); // Xóa thành phần hiện tại của pnl
		} else {
			// Vẽ biểu đồ cho một tháng cụ thể trong năm
			data = ctdv_dao.getTKThang(thang, nam);
			pieChart1.clearData(); // Xóa dữ liệu biểu đồ cũ
			pnl.removeAll(); // Xóa thành phần hiện tại của pnl
		}

		dataset.clear();

		// Mảng các màu sắc
		int[][] rgbValues = { { 41, 173, 86 }, { 205, 13, 13 }, { 255, 153, 153 }, { 166, 208, 238 }, { 189, 135, 245 }
				// Thêm các mã màu khác tại đây nếu cần
		};

		int numDataPoints = Math.min(5, data.size());

		// Xóa các mục cũ khỏi biểu đồ
		pieChart1.clearData();

		for (int i = 0; i < numDataPoints; i++) {
			String[] item = data.get(i);

			if (item.length > 5) {
				System.out.println("Không tạo");
				pnlChart.removeAll();
			}

			String tenMH = item[1];
			double tyle = Double.parseDouble(item[3]);
			int[] rgb = rgbValues[i % rgbValues.length];
			Color color = new Color(rgb[0], rgb[1], rgb[2]);

			dataset.setValue(tenMH, tyle);
			pieChart1.addData(new ModelPieChart(tenMH, tyle, color));
		}

		pnl.removeAll();
		pnl.add(pieChart1);
		pnl.repaint();
	}// GEN-LAST:event_cmbTKTheoActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox<Object> cmbTKChiTiet;
	private javax.swing.JComboBox<String> cmbTKTheo;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JLabel lblBieuDo;
	private javax.swing.JLabel lblNgay;
	private javax.swing.JLabel lblTKMH1;
	private javax.swing.JLabel lblTong;
	private gui.swing.tabbed.MaterialTabbed materialTabbed1;
	private gui.swing.tabbed.MaterialTabbed materialTabbed4;
	private gui.swing.tabbed.MaterialTabbed materialTabbed5;
	private javaswingdev.chart.PieChart pieChart1;
	private javax.swing.JPanel pnl;
	private javax.swing.JPanel pnlBieuDo;
	private javax.swing.JPanel pnlChart;
	private javax.swing.JPanel pnlHeader1;
	private javax.swing.JScrollPane scr1;
	private javax.swing.JScrollPane scr2;
	private javax.swing.JTable tblTKDV;
	private javax.swing.JTable tblTKMH;
	private com.toedter.calendar.JDateChooser txtDate;
	private javax.swing.JTextField txtTienDV;
	private javax.swing.JTextField txtTong;
	// End of variables declaration//GEN-END:variables
}
