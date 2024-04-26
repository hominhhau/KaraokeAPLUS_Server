

package gui.dialog;

import Interface.ChiTietHoaDonPhongDao;
import Interface.HoaDonDao;
import Interface.KhachHangDao;
import Interface.PhongHatDao;
import Interface.impl.ChiTietHoaDonPhongImpl;
import Interface.impl.HoaDonImpl;
import Interface.impl.KhachHangImpl;
import Interface.impl.PhongHatImpl;
import connectDB.ConnectDB;
import dao.ChiTietHoaDonPhong_Dao;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.PhongHat_DAO;
import entity.ChiTietHoaDonPhong;
import entity.HoaDon;
import entity.PhongHat;
import gui.form.Form_QuanLyDatPhong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.*;

/**
 * @author HO MINH HAU
 */
public class DL_PhongDangSuDung extends javax.swing.JDialog {

    private ChiTietHoaDonPhongDao cthdp_dao;
    private HoaDonDao hd_dao;
    private KhachHangDao kh_dao;
    private static ChiTietHoaDonPhong ctp;
    private static HoaDon hd;
    private Timer timer;
    private LocalDateTime gioVao;
    private static String maHDDSD;
    private PhongHatDao ph_dao;
    private Float gia;
    private Float thanhTien;
    private String maHDThanhToan;

    /**
     * Creates new form DL_PhongDangSuDung
     */
    public DL_PhongDangSuDung(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        cthdp_dao = new ChiTietHoaDonPhongImpl();
        hd_dao = new HoaDonImpl();
        ph_dao = new PhongHatImpl();
        kh_dao = new KhachHangImpl();
        setLblChiTietPhongDSD();


    }

    public void setMaHDPDSD(String maHD) {
        maHDDSD = maHD;
    }

    public String getMaHDPDSD() {
        return maHDDSD;
    }

    public void setMaHDThanhToan(String maHD) {
        maHDThanhToan = maHD;
    }

    public String getMaHDThanhToan() {
        return maHDThanhToan;
    }

    public void setLblChiTietPhongDSD() {
        Form_QuanLyDatPhong ph = new Form_QuanLyDatPhong();
        txtMaPhong.setText(ph.getRoomSelected());
        txtTenPhong.setText(ph.getRoomSelectedName());
        cthdp_dao = new ChiTietHoaDonPhongImpl();
        ChiTietHoaDonPhong result = cthdp_dao.finHDByRoomID(ph.getRoomSelected());
        setMaHDPDSD(result.getHoaDon().getMaHD());
        // Lấy giờ vào từ kết quả
        gioVao = result.getGioVao();
        // Bắt đầu timer để cập nhật thời gian sử dụng
        startTimer();
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                updateUsageTime();
            }
        }, 0, 1000);
    }


    private void updateUsageTime() {
        // Lấy thời điểm hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        // Tính độ chênh lệch giữa thời điểm hiện tại và giờ vào
        Duration duration = Duration.between(gioVao, currentTime);

        // Lấy giờ và phút từ độ chênh lệch
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        // Hiển thị giờ sử dụng
        txtTime.setText(String.format("%d giờ %d phút", hours, minutes));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCover = new javax.swing.JPanel();
        lblMaPhong = new javax.swing.JLabel();
        lblChiTietPhongDSD = new javax.swing.JLabel();
        txtMaPhong = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JLabel();
        btnThanhToan = new gui.swing.RadiusButton();
        btnThemDV = new gui.swing.RadiusButton();
        btnChuyenPhong = new gui.swing.RadiusButton();
        btnExit = new gui.swing.Button();
        lblTimes = new javax.swing.JLabel();
        txtTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlCover.setBackground(new java.awt.Color(255, 255, 255));
        pnlCover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMaPhong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaPhong.setText("Mã phòng:");

        lblChiTietPhongDSD.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblChiTietPhongDSD.setForeground(new java.awt.Color(0, 153, 0));
        lblChiTietPhongDSD.setText("Chi Tiết Phòng Đang Sử Dụng ");

        txtMaPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaPhong.setText("P001");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên phòng:");

        txtTenPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenPhong.setText("PhongVip1");

        btnThanhToan.setBackground(new java.awt.Color(0, 204, 204));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnThemDV.setBackground(new java.awt.Color(0, 204, 51));
        btnThemDV.setText("Thêm Dịch Vụ");
        btnThemDV.setToolTipText("Xem dịch vụ đã thêm");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        btnChuyenPhong.setBackground(new java.awt.Color(0, 204, 255));
        btnChuyenPhong.setText("Chuyển Phòng");
        btnChuyenPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenPhongActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 0, 51));
        btnExit.setBorder(null);
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("  X  ");
        btnExit.setEffectColor(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTimes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTimes.setText("Thời gian đã sử dụng:");

        txtTime.setText("Hồ Minh Hậu");

        javax.swing.GroupLayout pnlCoverLayout = new javax.swing.GroupLayout(pnlCover);
        pnlCover.setLayout(pnlCoverLayout);
        pnlCoverLayout.setHorizontalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                .addContainerGap(343, Short.MAX_VALUE)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(lblChiTietPhongDSD)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(btnChuyenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(41, 41, 41)
                                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addComponent(lblTimes)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(txtTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addComponent(lblMaPhong)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(43, 43, 43)
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))))
                                .addGap(18, 18, 18))
        );
        pnlCoverLayout.setVerticalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(lblChiTietPhongDSD)
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(txtTenPhong)
                                                        .addComponent(txtMaPhong)
                                                        .addComponent(lblMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblTimes)
                                                        .addComponent(txtTime))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnChuyenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(12, Short.MAX_VALUE))
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

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thanh toán không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            timer.cancel();
            timer.purge();
            this.dispose();
            Form_QuanLyDatPhong updatePhong = new Form_QuanLyDatPhong();
            ph_dao.updateTinhTrangPhong(updatePhong.getRoomSelected(), "Trong");
            updatePhong.openDL_ThanhToan();


        }

        this.dispose();

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        this.dispose();
        Form_QuanLyDatPhong frm = new Form_QuanLyDatPhong();
        frm.openDL_ThemDV();
    }//GEN-LAST:event_btnThemDVActionPerformed

    private void btnChuyenPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenPhongActionPerformed
        this.dispose();
        DL_ChuyenPhong chuyenPhong = new DL_ChuyenPhong((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        chuyenPhong.setLocationRelativeTo(this);
        chuyenPhong.setVisible(true);
    }//GEN-LAST:event_btnChuyenPhongActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DL_PhongDangSuDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_PhongDangSuDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_PhongDangSuDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_PhongDangSuDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_PhongDangSuDung dialog = new DL_PhongDangSuDung(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnChuyenPhong;
    private gui.swing.Button btnExit;
    private gui.swing.RadiusButton btnThanhToan;
    private gui.swing.RadiusButton btnThemDV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblChiTietPhongDSD;
    private javax.swing.JLabel lblMaPhong;
    private javax.swing.JLabel lblTimes;
    private javax.swing.JPanel pnlCover;
    private javax.swing.JLabel txtMaPhong;
    private javax.swing.JLabel txtTenPhong;
    private javax.swing.JLabel txtTime;
    // End of variables declaration//GEN-END:variables
}
