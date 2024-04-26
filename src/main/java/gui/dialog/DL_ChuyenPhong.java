/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui.dialog;

import Interface.ChiTietHoaDonDichVuDao;
import Interface.ChiTietHoaDonPhongDao;
import Interface.PhongHatDao;

import Interface.impl.ChiTietHoaDonDichVuImpl;
import Interface.impl.ChiTietHoaDonPhongImpl;
import Interface.impl.PhongHatImpl;
import connectDB.ConnectDB;
import dao.ChiTietHoaDonDichVu_DAO;
import dao.ChiTietHoaDonPhong_Dao;
import dao.PhongHat_DAO;
import entity.*;
import gui.form.Form_QuanLyDatPhong;
import gui.swing.notification.Notification;
import gui.swing.scrollbar.ScrollBarCustom;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HO MINH HAU
 */
public class DL_ChuyenPhong extends javax.swing.JDialog {

    private PhongHatDao ph_dao;
    private String loaiPhong;
    private Float gia;
    private String maPhong;

    private ChiTietHoaDonPhongDao cthdp_dao;
    private ChiTietHoaDonDichVuDao cthddv_dao;

    public DL_ChuyenPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        loadData();
        tblDSP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tblDSP.getSelectedRow();
                    if (selectedRow != -1) {
                        maPhong = (String) tblDSP.getValueAt(selectedRow, 0);
                        loaiPhong = (String) tblDSP.getValueAt(selectedRow, 2);
//                        System.out.println(maPhong);
//                        System.out.println(loaiPhong);
                    }
                }
            }
        });

    }

    public void loadData() {
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
            PhongHat ph = new PhongHat();
            ph_dao = new PhongHatImpl();
            ArrayList<PhongHat> phongTrongList = ph_dao.getPhongByTinhTrang("Trong");
            for (PhongHat phongHat : phongTrongList) {
                if (phongHat.getLoaiPhong().getMaLoaiPhong().equals("LP001")) {
                    loaiPhong = "VIP";
                    gia = 100000f;
                } else {
                    loaiPhong = "Thường";
                    gia = 60000f;
                }
                String[] rowData = {phongHat.getMaPhong(), phongHat.getTenPhong(), loaiPhong, gia.toString()};
                DefaultTableModel model = (DefaultTableModel) tblDSP.getModel();
                model.addRow(rowData);


            }
//        } catch (SQLException ex) {
//
//        }


    }

    // check txtLyDo không được rỗng và thông báo
    public boolean checkLyDo() {
        if (txtLyDo.getText().equals("")) {
            return false;
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnExit = new gui.swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSP = new javax.swing.JTable();
        btnXacNhan = new gui.swing.RadiusButton();
        jLabel2 = new javax.swing.JLabel();
        txtLyDo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 0));
        jLabel1.setText("Đổi Phòng ");

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

        tblDSP.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã", "Tên ", "Loại Phòng", "Giá "
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDSP);

        btnXacNhan.setBackground(new java.awt.Color(0, 204, 0));
        btnXacNhan.setText("Xác Nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Lý do chuyển phòng:");

        txtLyDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(144, 144, 144)
                                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(55, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 17, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtLyDo)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (checkLyDo()) {
//            ConnectDB db = ConnectDB.getInstance();
//            try {
//                db.connect();
                Form_QuanLyDatPhong frmPH = new Form_QuanLyDatPhong();
                cthdp_dao = new ChiTietHoaDonPhongImpl();
                ChiTietHoaDonPhong hd = cthdp_dao.finHDByRoomID(frmPH.getRoomSelected());

                // Lấy ra loại phòng từ maPhong
                ph_dao = new PhongHatImpl();
//                db.connect();
                PhongHat dataPH = ph_dao.getPhongHatByMaPhong(frmPH.getRoomSelected());
                String loaiPhong = dataPH.getLoaiPhong().getMaLoaiPhong();

                // Kiểm tra điều kiện chuyển phòng
                if (loaiPhong.equals("LP001") && tblDSP.getValueAt(tblDSP.getSelectedRow(), 2).equals("Thường")) {
                    Notification noti = new Notification(
                            (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                            Notification.Type.WARNING,
                            Notification.Location.TOP_RIGHT,
                            "Không thể chuyển từ phòng VIP sang phòng thường"
                    );
                    noti.showNotification();
                } else {
                    String maHD = hd.getHoaDon().getMaHD();

//                    db.connect();
                    cthdp_dao.doiPhong(maHD, frmPH.getRoomSelected(), maPhong, txtLyDo.getText());
                    cthddv_dao = new ChiTietHoaDonDichVuImpl();
//                    db.connect();
                    cthddv_dao.doiPhong(maHD, frmPH.getRoomSelected(), maPhong);

                    Form_QuanLyDatPhong updatePhong = new Form_QuanLyDatPhong();
//                    db.connect();
                    ph_dao.updateTinhTrangPhong(updatePhong.getRoomSelected(), "Trong");
//                    db.connect();
                    ph_dao.updateTinhTrangPhong(maPhong, "Dang su dung");
                    this.dispose();
                }

//            } catch (Exception e) {
//                // Xử lý exception
//            }

        } else {
            txtLyDo.requestFocus();
            Notification noti = new Notification(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                    Notification.Type.WARNING,
                    Notification.Location.TOP_RIGHT,
                    "Vui lòng nhập lý do chuyển phòng"
            );
            noti.showNotification();
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void txtLyDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoActionPerformed

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
            java.util.logging.Logger.getLogger(DL_ChuyenPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_ChuyenPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_ChuyenPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_ChuyenPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_ChuyenPhong dialog = new DL_ChuyenPhong(new javax.swing.JFrame(), true);
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
    private gui.swing.Button btnExit;
    private gui.swing.RadiusButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDSP;
    private javax.swing.JTextField txtLyDo;
    // End of variables declaration//GEN-END:variables
}
