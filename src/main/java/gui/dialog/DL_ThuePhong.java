package gui.dialog;

import Interface.KhachHangDao;
import Interface.impl.KhachHangImpl;
import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 84343
 */
public class DL_ThuePhong extends javax.swing.JFrame {

    private KhachHangDao kh_dao;

    public DL_ThuePhong() {
        initComponents();
        setLocationRelativeTo(null);

    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlThongTinKH = new javax.swing.JPanel();
        lblThongTinKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        btnKiemTra = new gui.swing.RadiusButton();
        btnThuePhong = new gui.swing.RadiusButton();
        btnThoat = new gui.swing.RadiusButton();
        txtSDT = new gui.swing.CustomJTextField();
        txtTenKH = new gui.swing.CustomJTextField();
        lblThongBao = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlThongTinKH.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTinKH.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        btnThuePhong.setBackground(new java.awt.Color(41, 173, 86));
        btnThuePhong.setForeground(new java.awt.Color(255, 255, 255));
        btnThuePhong.setText("Thuê Phòng");
        btnThuePhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuePhongActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(205, 13, 13));
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
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

        javax.swing.GroupLayout pnlThongTinKHLayout = new javax.swing.GroupLayout(pnlThongTinKH);
        pnlThongTinKH.setLayout(pnlThongTinKHLayout);
        pnlThongTinKHLayout.setHorizontalGroup(
                pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                                                .addComponent(lblGioiTinh)
                                                                                .addGap(62, 62, 62)
                                                                                .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                                                .addGap(52, 52, 52)
                                                                                .addComponent(btnThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(29, 29, 29)
                                                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addGap(103, 103, 103)
                                                .addComponent(lblThongTinKH)))
                                .addContainerGap(34, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlThongTinKHLayout.setVerticalGroup(
                pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(lblThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40))
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

    private void btnThuePhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuePhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThuePhongActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void radNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radNuActionPerformed

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        String sdt = txtSDT.getText();
//        ConnectDB db = ConnectDB.getInstance();
//
//        try {
//            db.connect();
            kh_dao = new KhachHangImpl();
            ArrayList<KhachHang> kh = kh_dao.getKhachHangTheoSdtKH(sdt);
            if (!kh.isEmpty()) {
                lblThongBao.setText("Tìm thấy thông tin khách hàng");
                KhachHang khachHang = kh.get(0);
                // Set the customer's name
                txtTenKH.setText(khachHang.getTenKH());

                // Set the gender radio buttons based on khachHang.isGioitinh()
                if (khachHang.isGioitinh()) {
                    radNam.setSelected(true); // Set the "Nam" radio button as selected
                    radNu.setSelected(false); // Deselect the "Nữ" radio button
                } else {
                    radNam.setSelected(false); // Deselect the "Nam" radio button
                    radNu.setSelected(true); // Set the "Nữ" radio button as selected
                }
            } else {
                lblThongBao.setText("Nhập lại số điện thoại hoặc thêm khách hàng bên dưới");

                btnThuePhong.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maKH = phatSinhMaKH();
                        String tenKH = txtTenKH.getText();
                        System.out.println(tenKH);
                        String Sdt = txtSDT.getText();
                        Boolean gt = radNam.isSelected();
                        KhachHang addKh = new KhachHang(maKH, tenKH, Sdt, gt);
                        kh_dao.addCustomer(addKh);
                    }
                });
            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DL_ThuePhong.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnKiemTraActionPerformed

//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DL_ThuePhong().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnKiemTra;
    private gui.swing.RadiusButton btnThoat;
    private gui.swing.RadiusButton btnThuePhong;
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
