
package gui.dialog;

import Interface.DonDatPhongDao;
import Interface.PhongHatDao;
import Interface.impl.DonDatPhongImpl;
import Interface.impl.PhongHatImpl;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import connectDB.ConnectDB;
import dao.DonDatPhong_DAO;
import dao.PhongHat_DAO;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.PhongHat;
import gui.form.Form_QuanLyDatPhong;
import gui.swing.scrollbar.ScrollBarCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * @author HO MINH HAU
 */
public class DL_DatPhongTruoc extends javax.swing.JDialog {

    private DonDatPhongDao ddp_dao;
    private PhongHatDao ph_dao;
    private String loaiPhong;
    private Float gia;
    private String maPhongDuocDatTruoc = "";

    public DL_DatPhongTruoc(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        createDatePicker();
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        ConnectDB db = ConnectDB.getInstance();

        try {
            db.connect();
        } catch (SQLException ex) {

        }
    }

    private void createDatePicker() {
        thoiGianBatDau.setBackground(Color.WHITE);
//        date
        thoiGianBatDau.getDatePicker().getSettings().setAllowEmptyDates(false);
        thoiGianBatDau.getDatePicker().setBackground(Color.WHITE);
        thoiGianBatDau.getDatePicker().getSettings().setAllowKeyboardEditing(false);
        LocalDate today = LocalDate.now();
        thoiGianBatDau.getDatePicker().getSettings().setDateRangeLimits(today, LocalDate.MAX);
        thoiGianBatDau.getDatePicker().getSettings().setLocale(new Locale("vi"));
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesCommonEra("yyyy-MM-dd");
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesBeforeCommonEra("uuuu-MM-dd");

//        time
        thoiGianBatDau.getTimePicker().getSettings().setAllowEmptyTimes(false);
        thoiGianBatDau.getTimePicker().setBackground(Color.WHITE);
        if (thoiGianBatDau.getDatePicker().getDate().equals(LocalDate.now())) {
            thoiGianBatDau.getTimePicker().setTimeToNow();
            thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(time,
                    LocalTime.now(),
                    LocalTime.MAX, true));

        }
        thoiGianBatDau.getTimePicker().getSettings().generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, null, null);
        thoiGianBatDau.getTimePicker().getSettings().use24HourClockFormat();
        thoiGianBatDau.getTimePicker().getSettings().setFormatForDisplayTime("HH:mm");
        thoiGianBatDau.getTimePicker().getSettings().setFormatForMenuTimes("HH:mm");

        thoiGianBatDau.getDatePicker().addDateChangeListener((var event) -> {
            if (thoiGianBatDau.getDatePicker().getDate().equals(LocalDate.now())) {
                thoiGianBatDau.getTimePicker().setTimeToNow();
                thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(time,
                        LocalTime.now(),
                        LocalTime.MAX, true));
            } else {
                thoiGianBatDau.getTimePicker().setTime(LocalTime.of(6, 0));
                thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(
                        time,
                        LocalTime.of(6, 0),
                        LocalTime.MAX, true));
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCover = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNgayNhanPhong = new javax.swing.JLabel();
        btnExit = new gui.swing.Button();
        thoiGianBatDau = new com.github.lgooddatepicker.components.DateTimePicker();
        btnKiemTra = new gui.swing.RadiusButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnDatPhong = new gui.swing.RadiusButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlCover.setBackground(new java.awt.Color(255, 255, 255));
        pnlCover.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Đặt Phòng");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));

        lblNgayNhanPhong.setText("Ngày đặt:  ");
        lblNgayNhanPhong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnExit.setText("  X  ");
        btnExit.setBackground(new java.awt.Color(255, 0, 51));
        btnExit.setBorder(null);
        btnExit.setEffectColor(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        thoiGianBatDau.setMinimumSize(new java.awt.Dimension(200, 15));

        btnKiemTra.setText("Kiểm Tra");
        btnKiemTra.setBackground(new java.awt.Color(0, 204, 204));
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã Phòng", "Tên Phòng", "Loại Phòng", "Giá Phòng"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnDatPhong.setText("Đặt phòng");
        btnDatPhong.setBackground(new java.awt.Color(0, 204, 102));
        btnDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCoverLayout = new javax.swing.GroupLayout(pnlCover);
        pnlCover.setLayout(pnlCoverLayout);
        pnlCoverLayout.setHorizontalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                .addContainerGap(236, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(219, 219, 219)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(lblNgayNhanPhong)
                                .addGap(18, 18, 18)
                                .addComponent(thoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        pnlCoverLayout.setVerticalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNgayNhanPhong)
                                        .addComponent(thoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed

        // Lấy danh sách phòng
        ph_dao = new PhongHatImpl();
        ArrayList<PhongHat> dsPH = ph_dao.getAllPhongHat();
        // Xác định loại phòng và giá
        for (PhongHat ph : dsPH) {
            setLoaiPhong(ph);
        }
        // Kiểm tra đơn đặt phòng và lưu trữ danh sách phòng cần loại bỏ
        ddp_dao = new DonDatPhongImpl();
        ArrayList<DonDatPhong> dsDDP = ddp_dao.getalltbDonDatPhong();
        String maPhongDuocDatTruoc = "";
        List<PhongHat> phongCanLoaiBo = new ArrayList<>();

        for (DonDatPhong ddp : dsDDP) {
            if (ddp.getNgayNhan().toLocalDate().equals(thoiGianBatDau.getDatePicker().getDate())) {
                int gioNhan = ddp.getNgayNhan().getHour();
                int gioDat = thoiGianBatDau.getTimePicker().getTime().getHour();

                if (!(gioDat - gioNhan >= 6 || gioDat - gioNhan <= -6)) {
                    maPhongDuocDatTruoc = ddp.getPhongHat().getMaPhong();

                    // Lưu trữ phòng cần loại bỏ
                    for (PhongHat ph : dsPH) {
                        if (ph.getMaPhong().equals(maPhongDuocDatTruoc)) {
                            phongCanLoaiBo.add(ph);
                        }
                    }
                }
            }
        }

        // Loại bỏ phòng đã đặt trước
        dsPH.removeAll(phongCanLoaiBo);

        // Hiển thị danh sách phòng
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (PhongHat ph : dsPH) {
            setLoaiPhong(ph);
            Object[] row = {ph.getMaPhong(), ph.getTenPhong(), loaiPhong, gia};
            model.addRow(row);
        }
    }//GEN-LAST:event_btnKiemTraActionPerformed

    private void btnDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongActionPerformed
        //lấy maKH
        DL_KiemTravsAddKHforDatPhong dataKH = new DL_KiemTravsAddKHforDatPhong((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        String maKH = dataKH.getMaKHPublic();

        ddp_dao = new DonDatPhongImpl();
        String maDDP = phatSinhMaDDP();
        LocalDateTime ngayDat = LocalDateTime.now();
        LocalDateTime ngayNhan = thoiGianBatDau.getDateTimePermissive();
        // lấy ra tất cả các dòng được chọn trong tbale
        int[] rows = jTable1.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            String maPhong = (String) jTable1.getValueAt(rows[i], 0);

            DonDatPhong ddp = new DonDatPhong(maDDP, ngayDat, ngayNhan, new PhongHat(maPhong), new KhachHang(maKH));

            ddp_dao.createDonDatPhong(ddp);
        }
        // reload lại ds phòng
        this.dispose();

    }//GEN-LAST:event_btnDatPhongActionPerformed

    private void setLoaiPhong(PhongHat ph) {
        if (ph.getLoaiPhong().getMaLoaiPhong().equals("LP001")) {
            loaiPhong = "VIP";
            gia = 100000f;
        } else {
            loaiPhong = "Thường";
            gia = 60000f;
        }
    }

    public String phatSinhMaDDP() {
        String maDDP = "";
        ddp_dao = new DonDatPhongImpl();
        ArrayList<DonDatPhong> dsDDP = ddp_dao.getalltbDonDatPhong();
        int max = 0;
        for (DonDatPhong ddp : dsDDP) {
            int soDuoi = laySoDuoi(ddp.getMaDonDatPhong());
            if (soDuoi > max) {
                max = soDuoi;
            }
        }
        max++;
        if (max < 10) {
            maDDP = "DDP00" + max;
        } else if (max < 100) {
            maDDP = "DDP0" + max;
        } else {
            maDDP = "DDP" + max;
        }
        return maDDP;
    }

    public int laySoDuoi(String str) {
        if (str == null) {
            return 0;
        } else {
            String numberStr = str.substring(3); // loại bỏ ký tự "KH"
            int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
            return number;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnDatPhong;
    private gui.swing.Button btnExit;
    private gui.swing.RadiusButton btnKiemTra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblNgayNhanPhong;
    private javax.swing.JPanel pnlCover;
    private com.github.lgooddatepicker.components.DateTimePicker thoiGianBatDau;
    // End of variables declaration//GEN-END:variables
}
