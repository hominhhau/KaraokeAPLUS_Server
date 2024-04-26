/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui.dialog;

import Interface.ChiTietHoaDonPhongDao;
import Interface.DonDatPhongDao;
import Interface.HoaDonDao;
import Interface.PhongHatDao;
import Interface.impl.ChiTietHoaDonPhongImpl;
import Interface.impl.DonDatPhongImpl;
import Interface.impl.HoaDonImpl;
import Interface.impl.PhongHatImpl;
import connectDB.ConnectDB;
import dao.ChiTietHoaDonPhong_Dao;
import dao.DonDatPhong_DAO;
import dao.HoaDon_DAO;
import dao.PhongHat_DAO;
import entity.*;
import gui.form.Form_Login;
import gui.form.Form_QuanLyDatPhong;
import gui.swing.scrollbar.ScrollBarCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;

/**
 * @author HO MINH HAU
 */
public class DL_ChonNhieuPhong extends javax.swing.JDialog {

    private DonDatPhongDao ddp_dao;
    private PhongHatDao ph_dao;
    private HoaDonDao hd_dao;
    private ChiTietHoaDonPhongDao cthdp_dao;
    private String loaiPhong;
    private Float gia;
    private String maPhongDuocDatTruoc = "";

    public DL_ChonNhieuPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        loadData();
        hd_dao = new HoaDonImpl();
        ConnectDB db = ConnectDB.getInstance();

        try {
            db.connect();
        } catch (SQLException ex) {

        }

    }

    private void setLoaiPhong(PhongHat ph) {
        if (ph.getLoaiPhong().getMaLoaiPhong().equals("LP001")) {
            loaiPhong = "VIP";
            gia = 100000f;
        } else {
            loaiPhong = "Thường";
            gia = 60000f;
        }
    }

    public int laySoDuoi(String str) {
        if (str == null) {
            return 0;
        } else {
            String numberStr = str.substring(2); // loại bỏ ký tự "KH"
            int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
            return number;
        }
    }

    public String phatSinhMaHD() {

        List<HoaDon> khs = hd_dao.getalltbHoaDon();
        String temp = null;
        for (HoaDon hd : khs) {
            temp = hd.getMaHD();
        }
        int count = laySoDuoi(temp);

        count++;

        String maVe = String.format("HD%03d", count);

        return maVe;
    }


    // lodData vô table
    public void loadData() {
        // Lấy danh sách phòng
        ph_dao = new PhongHatImpl();
        ArrayList<PhongHat> phongTrongList = ph_dao.getPhongByTinhTrang("Trong");
        // set loai phong vs gia


        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        ddp_dao = new DonDatPhongImpl();
        ArrayList<DonDatPhong> dsDDP = ddp_dao.getalltbDonDatPhong();
        String maPhongDuocDatTruoc = "";
        List<PhongHat> phongCanLoaiBo = new ArrayList<>();
        for (DonDatPhong ddp : dsDDP) {
            if (ddp.getNgayNhan().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                int gioNhan = ddp.getNgayNhan().getHour();
                int gioDat = LocalDateTime.now().getHour();
                if (!(gioDat - gioNhan >= 6 || gioDat - gioNhan <= -6)) {
                    maPhongDuocDatTruoc = ddp.getPhongHat().getMaPhong();

                    for (PhongHat phong : phongTrongList) {
                        if (phong.getMaPhong().equals(maPhongDuocDatTruoc)) {
                            phongCanLoaiBo.add(phong);
                        }
                    }
                }

            }
        }
        phongTrongList.removeAll(phongCanLoaiBo);
        for (PhongHat ph : phongTrongList) {
            setLoaiPhong(ph);
            dtm.addRow(new Object[]{
                    ph.getMaPhong(),
                    ph.getTenPhong(),
                    loaiPhong,
                    gia
            });
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnExit = new gui.swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnDatNhieuPhong = new gui.swing.RadiusButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));
        jLabel1.setText("Đặt nhiều phòng");

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

        btnDatNhieuPhong.setBackground(new java.awt.Color(0, 204, 102));
        btnDatNhieuPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnDatNhieuPhong.setText("Đặt phòng");
        btnDatNhieuPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatNhieuPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDatNhieuPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1))
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDatNhieuPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDatNhieuPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatNhieuPhongActionPerformed
        // lấy mã hóa đơn
        String maHD = phatSinhMaHD();

        DL_CheckKHforDatNhieuPhong dl = new DL_CheckKHforDatNhieuPhong(null, true);
        String makH = dl.getMaKHPublic();
        NhanVien nhanVienDangNhap = Form_Login.getNhanVienDangNhap();
        String maNV = "";
        if (nhanVienDangNhap != null) {
            maNV = nhanVienDangNhap.getMaNV();
        }else{
            maNV = "NV001";
        }

        double tongTien = 0;
        LocalDate ngayLapHD = LocalDate.now();
        String maKM = null;
        HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(makH), new NhanVien(maNV), null, tongTien);


        int[] selectedRows = jTable1.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng");
        } else {
            hd_dao = new HoaDonImpl();
            hd_dao.createHoaDon(hd);
            for (int i = 0; i < selectedRows.length; i++) {
                String maPhong = (String) jTable1.getValueAt(selectedRows[i], 0);
                double gia = 0;
                LocalDateTime gioVao = LocalDateTime.now();
                LocalDateTime gioRa = gioVao;
                String maGiamGia = null;
                ChiTietHoaDonPhong cthdp = new ChiTietHoaDonPhong(new HoaDon(maHD), new PhongHat(maPhong), gia, gioVao, gioRa, maGiamGia);
                 cthdp_dao = new ChiTietHoaDonPhongImpl();
                cthdp_dao.createChiTietHoaDonPhong(cthdp);
                ph_dao = new PhongHatImpl();
                ph_dao.updateTinhTrangPhong(maPhong, "Dang su dung");
            }
            //refresh room
            Form_QuanLyDatPhong form = new Form_QuanLyDatPhong();
            form.refreshRooms();
            this.dispose();
        }
    }//GEN-LAST:event_btnDatNhieuPhongActionPerformed

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
            java.util.logging.Logger.getLogger(DL_ChonNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_ChonNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_ChonNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_ChonNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_ChonNhieuPhong dialog = new DL_ChonNhieuPhong(new javax.swing.JFrame(), true);
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
    private gui.swing.RadiusButton btnDatNhieuPhong;
    private gui.swing.Button btnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
