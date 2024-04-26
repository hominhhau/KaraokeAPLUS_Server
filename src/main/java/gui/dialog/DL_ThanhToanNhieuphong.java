/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui.dialog;

import Interface.*;
import Interface.impl.*;
import dao.*;
import entity.*;
import gui.form.Form_QuanLyDatPhong;
import gui.swing.notification.Notification;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;

/**
 * @author HO MINH HAU
 */
public class DL_ThanhToanNhieuphong extends javax.swing.JDialog {

    private KhachHangDao kh_dao;
    private KhachHang khachHang;
    private PhongHatDao ph_dao;
    private HoaDonDao hd_dao;
    private ChiTietHoaDonPhongDao cthdp_dao;
    private ChiTietHoaDonDichVuDao cthddv_dao;
    private HoaDon hoaDon;
    private ChiTietHoaDonPhong cthdp;
    private ChiTietHoaDonDV cthddv;
    private MatHangDao mh_dao;
    private String loaiPhong;
    private float giaPhong;

    private static String maHDDSD;
    private DefaultTableModel model;


    public DL_ThanhToanNhieuphong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtSDTKH.addKeyListener(new java.awt.event.KeyAdapter() {
            // nếu nhấn enter thì sẽ kiểm tra sdt
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnTimHD.doClick();
                }
            }
        });
    }

    public String getMaHDDSD() {
        return maHDDSD;
    }

    public void setMaHDDSD(String maHDDSD) {
        this.maHDDSD = maHDDSD;
    }

    private ArrayList<String> maHDDSDList = new ArrayList<>();

    // Existing code...

    public ArrayList<String> getMaHDDSDList() {
        return maHDDSDList;
    }

    public void setMaHDDSDList(ArrayList<String> maHDDSDList) {
        this.maHDDSDList = maHDDSDList;
    }

    public Boolean validDateSDT() {
        String sdt = txtSDTKH.getText();
        if (sdt.length() != 10 || !sdt.matches("^0[0-9]{9}$")) {
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
        jLabel2 = new javax.swing.JLabel();
        txtSDTKH = new gui.swing.CustomJTextField();
        btnTimHD = new gui.swing.RadiusButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnThanhToanNhieu = new gui.swing.RadiusButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));
        jLabel1.setText("Thanh Toán Nhiều Phòng");

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

        jLabel2.setText("Nhập số điện thoại của khách hàng thuê nhiều phòng :");

        btnTimHD.setText("Tìm ");
        btnTimHD.setBackground(new java.awt.Color(0, 204, 204));
        btnTimHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHDActionPerformed(evt);
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

        btnThanhToanNhieu.setBackground(new java.awt.Color(0, 204, 153));
        btnThanhToanNhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToanNhieu.setText("Thanh Toán");
        btnThanhToanNhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanNhieuActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã DV", "Tên DV", "SL", "Thành Tiền"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel3.setText("Danh sách phòng ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel4.setText("Danh sách dịch vụ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThanhToanNhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2))
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(2, 2, 2)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThanhToanNhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
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

    private void btnThanhToanNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanNhieuActionPerformed
        // check nếu jtable1 khác rỗng
        if (jTable1.getRowCount() > 0) {
            Form_QuanLyDatPhong form_QuanLyDatPhong = new Form_QuanLyDatPhong();
            int yesNo = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thanh toán không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.YES_OPTION) {
                ph_dao = new PhongHatImpl();
                // lấy ds maPhong từ cột số 1
                ArrayList<String> dsMaPhong = new ArrayList<>();
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    dsMaPhong.add(jTable1.getValueAt(i, 0).toString());
                }
                // update tình trạng phòng
                for (String maPhong : dsMaPhong) {
                    ph_dao.updateTinhTrangPhong(maPhong, "Trong");
                }
                this.dispose();
                form_QuanLyDatPhong.openDL_ThanhToanNhieu();
            } else {
                Notification noti = new Notification(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        Notification.Type.WARNING,
                        Notification.Location.TOP_RIGHT,
                        "Xác nhận không thanh toán"
                );
                noti.showNotification();
                this.dispose();
            }
        } else {
            Notification noti = new Notification(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                    Notification.Type.WARNING,
                    Notification.Location.TOP_RIGHT,
                    "không có phòng cần thanh toán"
            );
            noti.showNotification();
        }


    }//GEN-LAST:event_btnThanhToanNhieuActionPerformed

    private void btnTimHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHDActionPerformed
        String sdt = txtSDTKH.getText();
        if (validDateSDT()) {
            kh_dao = new KhachHangImpl();
            ArrayList<KhachHang> kh = kh_dao.getKhachHangTheoSdtKH(sdt);
            if (!kh.isEmpty()) {
                khachHang = kh.get(0);
                hd_dao = new HoaDonImpl();
                ArrayList<HoaDon> hd = hd_dao.getHoaDonTheoMaKH(khachHang.getMaKH());
                // lấy ra mã hóa đơn mà trong chitiethoadon phòng có số hóa đoưn lớn hơn 1


                if (hd.size() > 0) {

                    // lấy ra hd có điều kiện là chưa thanh toán
                    for (HoaDon hoaDon : hd) {
                        if (hoaDon.getTongTien() == 0) {

//                            maHDDSD = hoaDon.getMaHD();
//                            // lưu lại bằng arraylist
//                            maHDDSDList.add(maHDDSD);

                        }
                    }
                    cthdp_dao = new ChiTietHoaDonPhongImpl();
                    for (HoaDon hoaDon : hd) {
                        ArrayList<ChiTietHoaDonPhong> dsCTHDP = cthdp_dao.getAllTheMaHDArray(hoaDon.getMaHD());
                        // chỉ lấy ra cthdp có nhiều hơn 1 phòng
                        ArrayList<ChiTietHoaDonPhong> dsCTHDP2 = new ArrayList<>();
                        for (ChiTietHoaDonPhong cthdp : dsCTHDP) {
                            if (dsCTHDP.size() > 1) {
                                dsCTHDP2.add(cthdp);
                            }
                        }


                        for (ChiTietHoaDonPhong cthdp : dsCTHDP2) {
                            if (cthdp.getGia() == 0) {
                                maHDDSD = hoaDon.getMaHD();
//                                // lưu lại bằng arraylist
//                                maHDDSDList.add(maHDDSD);
                                model = (DefaultTableModel) jTable1.getModel();
                              ph_dao = new PhongHatImpl();
                                PhongHat addph = ph_dao.getPhongHatByMaPhong(cthdp.getPhongHat().getMaPhong());
                                if (addph.getLoaiPhong().getMaLoaiPhong().equals("LP001")) {
                                    giaPhong = 100000f;
                                    loaiPhong = "Phòng VIP";
                                } else {
                                    giaPhong = 60000f;
                                    loaiPhong = "Phòng thường";
                                }
                                model.addRow(new Object[]{
                                        cthdp.getPhongHat().getMaPhong(),
                                        addph.getTenPhong(),
                                        loaiPhong,
                                        giaPhong
                                });

                                cthddv_dao = new ChiTietHoaDonDichVuImpl();
                                ArrayList<ChiTietHoaDonDV> dsCTHDDV = cthddv_dao.getAllTheMaHDDVforRoomArray(hoaDon.getMaHD(), cthdp.getPhongHat().getMaPhong());
                                for (ChiTietHoaDonDV cthddv : dsCTHDDV) {
                                    mh_dao = new MatHangImpl();
                                    MatHang addmh = mh_dao.findMatHang(cthddv.getMatHang().getMaMH());
                                    DefaultTableModel serviceModel = (DefaultTableModel) jTable2.getModel();
                                    serviceModel.addRow(new Object[]{
                                            cthddv.getMatHang().getMaMH(),
                                            addmh.getTenMH(),
                                            cthddv.getSoLuong(),
                                            cthddv.getGia()
                                    });
                                }
                            }
                        }
                    }
                }
            } else {
                Notification noti = new Notification(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        Notification.Type.WARNING,
                        Notification.Location.TOP_RIGHT,
                        "Khách hàng chưa thuê phòng nào"
                );
                noti.showNotification();
            }

        } else {
            Notification noti = new Notification(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                    Notification.Type.WARNING,
                    Notification.Location.TOP_RIGHT,
                    "Số điện thoại không hợp lệ"
            );
            noti.showNotification();
        }
    }//GEN-LAST:event_btnTimHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.Button btnExit;
    private gui.swing.RadiusButton btnThanhToanNhieu;
    private gui.swing.RadiusButton btnTimHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private gui.swing.CustomJTextField txtSDTKH;
    // End of variables declaration//GEN-END:variables
}
