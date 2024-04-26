/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui.dialog;

import Interface.*;
import Interface.impl.KhachHangImpl;
import Interface.impl.PhongHatImpl;
import dao.*;
import entity.*;
import gui.form.Form_Login;
import gui.form.Form_QuanLyDatPhong;
import gui.swing.notification.Notification;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HO MINH HAU
 */
public class DL_CheckKHforDatNhieuPhong extends javax.swing.JDialog {

    private KhachHangDao kh_dao;
    private PhongHatDao ph_dao;
    private HoaDonDao hd_dao;
    private ChiTietHoaDonPhongDao cthdp_dao;
    private ChiTietHoaDonDichVuDao cthddv_dao;
    private static String makhAuto;
    private static String mahdAuto;
    private KhachHang khachHang;

    public DL_CheckKHforDatNhieuPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            // nếu nhấn enter thì sẽ kiểm tra sdt
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnKiemTra.doClick();
                }
            }
        });
    }

    public static void setMaKHPublic(String makh) {
        makhAuto = makh;
    }

    public static String getMaKHPublic() {
        return makhAuto;
    }

    public static void setMaHDPublic(String mahd) {
        mahdAuto = mahd;
    }

    public static String getMaHDPublic() {
        return mahdAuto;
    }

    //    public static String setDataPhongDat(String maPhong, String maHD){
//
//    }
    public String phatSinhMaKH() {

        List<KhachHang> khs = kh_dao.getalltbKhachHang();
        String temp = null;
        for (KhachHang kh : khs) {
            temp = kh.getMaKH();
        }
        int count = laySoDuoi(temp);

        count++;

        String maVe = String.format("KH%03d", count);

        return maVe;
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

    public int laySoDuoiHD(String str) {
        if (str == null) {
            return 0;
        } else {
            String numberStr = str.substring(2); // loại bỏ ký tự "HD"
            int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
            return number;
        }

    }

    public Boolean validDateSDT() {
        String sdt = txtSDT.getText();
        if (sdt.length() != 10 || !sdt.matches("^0[0-9]{9}$")) {
            return false;
        }
        return true;
    }

    public Boolean validDateTenKH() {
        String tenKH = txtTenKH.getText();
        if (!(tenKH.length() > 0 && tenKH.length() < 50)) {
            return false;
        }
        return true;
    }

    public HoaDon revertHoaDonFromTextfields() {
        NhanVien nhanVienDangNhap = Form_Login.getNhanVienDangNhap();
        String maHD = phatSinhMaHD();
        setMaHDPublic(maHD); // đừng xóa dòng này . công dụng lấy ra được maHD mới phát sinh
        LocalDate ngayLapHD = LocalDate.now();
        String maKH = getMaKHPublic();
        String maNV = nhanVienDangNhap.getMaNV();
        double tongTien = 0;
        HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), tongTien);
        return hd;

    }

    public ChiTietHoaDonPhong revertChiTietHoaDonPhongText() {
        String maHD = getMaHDPublic();
        Form_QuanLyDatPhong ph = new Form_QuanLyDatPhong();
        ph_dao = new PhongHatImpl();
        String maPhong = ph.getRoomSelected();
        double gia = 0;
        LocalDateTime gioVao = LocalDateTime.now();
        LocalDateTime gioRa = gioVao;
        String maGiamGia = null;

        ChiTietHoaDonPhong cthdp = new ChiTietHoaDonPhong(new HoaDon(maHD), new PhongHat(maPhong), gia, gioVao, gioRa, maGiamGia);
        return cthdp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlThongTinKH = new javax.swing.JPanel();
        lblThongTinKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        btnKiemTra = new gui.swing.RadiusButton();
        btnThueNhieuPhong = new gui.swing.RadiusButton();
        txtSDT = new gui.swing.CustomJTextField();
        txtTenKH = new gui.swing.CustomJTextField();
        lblThongBao = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        btnExit = new gui.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlThongTinKH.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTinKH.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlThongTinKH.setForeground(new java.awt.Color(204, 204, 204));

        lblThongTinKH.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblThongTinKH.setForeground(new java.awt.Color(41, 173, 86));
        lblThongTinKH.setText("THÔNG TIN KHÁCH HÀNG");

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenKH.setText("Tên khách hàng:");

        lblSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSDT.setText("Số điện thoại:");

        btnKiemTra.setBackground(new java.awt.Color(166, 208, 238));
        btnKiemTra.setText("Kiểm tra");
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        btnThueNhieuPhong.setBackground(new java.awt.Color(41, 173, 86));
        btnThueNhieuPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnThueNhieuPhong.setText("Thuê nhiều phòng");
        btnThueNhieuPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThueNhieuPhongActionPerformed(evt);
            }
        });

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblThongBao.setText("không tìm thấy thông tin khách hàng ");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGioiTinh.setText("Giới Tính:");

        radNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(radNam);
        radNam.setText("Nam");

        radNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(radNu);
        radNu.setText("Nữ");
        radNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNuActionPerformed(evt);
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

        javax.swing.GroupLayout pnlThongTinKHLayout = new javax.swing.GroupLayout(pnlThongTinKH);
        pnlThongTinKH.setLayout(pnlThongTinKHLayout);
        pnlThongTinKHLayout.setHorizontalGroup(
                pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblTenKH)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblGioiTinh)
                                                .addGap(62, 62, 62)
                                                .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(36, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblThongBao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblThongTinKH)
                                                .addGap(71, 71, 71)
                                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThueNhieuPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
        );
        pnlThongTinKHLayout.setVerticalGroup(
                pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(lblThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(29, 29, 29)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSDT)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(lblThongBao)
                                .addGap(18, 18, 18)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTenKH)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblGioiTinh)
                                        .addComponent(radNam)
                                        .addComponent(radNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThueNhieuPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlThongTinKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        String sdt = txtSDT.getText();

        if (validDateSDT()) {
            kh_dao = new KhachHangImpl();
            ArrayList<KhachHang> kh = kh_dao.getKhachHangTheoSdtKH(sdt);

            if (!kh.isEmpty()) {
                lblThongBao.setText("Tìm thấy thông tin khách hàng");
                khachHang = kh.get(0);

                // Set the customer's information
                setMaKHPublic(khachHang.getMaKH());
                txtTenKH.setText(khachHang.getTenKH());

                // Set the gender radio buttons based on khachHang.isGioitinh()
                radNam.setSelected(khachHang.isGioitinh());
                radNu.setSelected(!khachHang.isGioitinh());
            } else {
                lblThongBao.setText("Nhập lại số điện thoại hoặc thêm khách hàng bên dưới");

//                btnThuePhong.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                    }
//                });
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
    }//GEN-LAST:event_btnKiemTraActionPerformed

    private void btnThueNhieuPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThueNhieuPhongActionPerformed
        Form_QuanLyDatPhong ph = new Form_QuanLyDatPhong();
        if (khachHang != null) {

            ph.openDL_DatNhieuPhong();
            this.dispose();
        } else if (validDateTenKH()) {
            kh_dao = new KhachHangImpl();
            String maKH = phatSinhMaKH();
            setMaKHPublic(maKH);
            String tenKH = txtTenKH.getText();
            String sdt = txtSDT.getText();
            Boolean gioiTinh = radNam.isSelected();
            KhachHang addKh = new KhachHang(maKH, tenKH, sdt, gioiTinh);
            if (kh_dao.addCustomer(addKh)) {
                Notification noti = new Notification(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        Notification.Type.SUCCESS,
                        Notification.Location.TOP_RIGHT,
                        "Thuê phòng thành công"
                );
                noti.showNotification();

            } else {
                Notification noti = new Notification(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        Notification.Type.WARNING,
                        Notification.Location.TOP_RIGHT,
                        "Thêm khách hàng không thành công"
                );
                noti.showNotification();
            }


            this.dispose();
            ph.openDL_DatNhieuPhong();
        } else {

            Notification noti = new Notification(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                    Notification.Type.WARNING,
                    Notification.Location.TOP_RIGHT,
                    "Tên khách hàng không hợp lệ"
            );
            noti.showNotification();
        }

    }//GEN-LAST:event_btnThueNhieuPhongActionPerformed

    private void radNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radNuActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(DL_CheckKHforDatNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_CheckKHforDatNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_CheckKHforDatNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_CheckKHforDatNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_CheckKHforDatNhieuPhong dialog = new DL_CheckKHforDatNhieuPhong(new javax.swing.JFrame(), true);
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
    private gui.swing.RadiusButton btnKiemTra;
    private gui.swing.RadiusButton btnThueNhieuPhong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblThongTinKH;
    private javax.swing.JPanel pnlThongTinKH;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private gui.swing.CustomJTextField txtSDT;
    private gui.swing.CustomJTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
