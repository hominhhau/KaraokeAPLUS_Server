package gui.dialog;

import Interface.KhachHangDao;
import Interface.impl.KhachHangImpl;
import connectDB.ConnectDB;
import connectDB.DatabaseManager;
import dao.KhachHang_DAO;
import entity.KhachHang;

import java.util.ArrayList;

import gui.form.Form_QuanLyKhachHang;
import gui.swing.CustomJOptionPane;

import java.awt.Frame;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author 84343
 */
public class DL_ThongTinKhachHang extends javax.swing.JDialog {

    //private KhachHang_DAO kh_dao;
    private KhachHangDao kh_dao;
    private Form_QuanLyKhachHang qlkh;
    private DefaultTableModel dtmKhachHang;

    private Form_QuanLyKhachHang qlKH;
    private ButtonGroup group;

    public DL_ThongTinKhachHang() {
        initComponents();
        setLocationRelativeTo(null);
        //kh_dao = new KhachHang_DAO();
        group = new ButtonGroup();
        group.add(radNam);
        group.add(radNu);
        kh_dao  = new KhachHangImpl();
    }

//   public void DocDuLieu() {
//        java.util.List<KhachHang> list = kh_dao.getalltbKhachHang();
//        for (KhachHang kh : list) {
//            dtmKhachHang.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getSdt(), kh.isGioitinh() ? "Nam" : "Nữ"});
//        }
//    }

    public String phatSinhMaKH() {
        List<KhachHang> khs = kh_dao.getalltbKhachHang();
        String temp = null;

        for (KhachHang kh : khs) {
            temp = kh.getMaKH();
        }
        int count = laySoDuoi(temp);
        count++;

        String maKHang = String.format("KH%03d", count);
        return maKHang;
    }

    public int laySoDuoi(String str) {
        if (str == null) {
            return 0;
        } else {
            String numberStr = str.substring(2);//Loại bỏ kí tự "KH"
            int number = Integer.parseInt(numberStr);
            return number;
        }
    }

    public boolean validData() {
        String maKH = phatSinhMaKH();
        String tenKH = txtTenKH.getText().trim();
        String Sdt = txtSDT.getText().trim();
        Boolean gt = radNam.isSelected();

        /**
         * Tên không được rỗng
         */
        if (!(tenKH.length() > 0 && tenKH.matches("^.+$"))) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được rỗng !!!");
            return false;
        }

        /**
         * Số điện thoại gồm 10 số
         */
        if (!(Sdt.length() > 0 && Sdt.matches("^0\\d{9}$"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 chữ số và phải bắt đầu bằng số 0 !!!");
            return false;
        }

        /**
         * Phải chọn không được để trống
         */
        if (!(radNam.isSelected() || radNu.isSelected())) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính !!!");
            return false;
        }

        return true;
    }

    public KhachHang revertKhachHang() {
        String maKH = phatSinhMaKH();
        String tenKH = txtTenKH.getText().trim();
        String SDT = txtSDT.getText().trim();
        Boolean gioiTinh = radNam.isSelected();

        return new KhachHang(maKH, tenKH, SDT, gioiTinh);
    }

//   public void DocDuLieu() {
//        java.util.List<KhachHang> list = kh_dao.getalltbKhachHang();
//        for (KhachHang kh : list) {
//            dtmKhachHang.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getSdt(), kh.isGioitinh() ? "Nam" : "Nữ"});
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnXoaR = new gui.swing.RadiusButton();
        btnThem = new gui.swing.RadiusButton();
        lblThongTinKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        btnThoat = new gui.swing.RadiusButton();
        txtSDT = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnXoaR.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaR.setText("Xóa rỗng");
        btnXoaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaRActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(65, 194, 33));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblThongTinKH.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblThongTinKH.setForeground(new java.awt.Color(65, 194, 33));
        lblThongTinKH.setText("THÔNG TIN KHÁCH HÀNG");

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenKH.setText("Tên khách hàng:");

        lblSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSDT.setText("Số điện thoại:");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGioiTinh.setText("Giới tính:");

        radNam.setText("Nam");
        radNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNamActionPerformed(evt);
            }
        });

        radNu.setText("Nữ");
        radNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNuActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(217, Short.MAX_VALUE)
                                .addComponent(lblThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(202, 202, 202))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(90, 90, 90)
                                                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(124, 124, 124)
                                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(76, 76, 76)
                                                .addComponent(btnXoaR, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(200, 200, 200)
                                                .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(43, 43, 43)
                                                .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSDT)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTenKH)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblGioiTinh)
                                        .addComponent(radNam)
                                        .addComponent(radNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXoaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        DatabaseManager db = DatabaseManager.getInstance();
        db.connect();
        //ConnectDB db = ConnectDB.getInstance();
        try {
            db.connect();
//            String maKH = phatSinhMaKH();
//            String tenKH = txtTenKH.getText();
//            String Sdt = txtSDT.getText();
//            Boolean gt = radNam.isSelected();

            if (validData()) {
                KhachHang addKH = revertKhachHang();
                boolean isSuccess = kh_dao.addCustomer(addKH);
                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công !");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại !!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin !!!");
            }
//            KhachHang addKh = new KhachHang(maKH, tenKH, Sdt, gt);
//            boolean isSuccess = kh_dao.addCustomer(addKh);
//            if(isSuccess){
//                CustomJOptionPane.showMessageDialog("Thêm khách hàng thành công !");
//            }
//            else{
//                CustomJOptionPane.showMessageDialog("Thêm khách hàng thất bại !!!");
//            }
//            qlkh.DocDuLieu();
        } catch (Exception ex) {
            Logger.getLogger(DL_ThongTinKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemActionPerformed


    private void btnXoaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaRActionPerformed

        txtTenKH.setText("");
        txtSDT.setText("");
        radNu.setSelected(false);
        radNam.setSelected(false);
        txtTenKH.requestFocus();

    }//GEN-LAST:event_btnXoaRActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
        return;
    }//GEN-LAST:event_btnThoatActionPerformed

    private void radNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNamActionPerformed
        if (radNam.isSelected()) {
            radNu.setSelected(false);
        }
    }//GEN-LAST:event_radNamActionPerformed

    private void radNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNuActionPerformed
        if (radNu.isSelected()) {
            radNu.setSelected(false);
        }
    }//GEN-LAST:event_radNuActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new DL_ThongTinKhachHang().setVisible(true);
                DL_ThongTinKhachHang dialog = new DL_ThongTinKhachHang();
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
    private gui.swing.RadiusButton btnThem;
    private gui.swing.RadiusButton btnThoat;
    private gui.swing.RadiusButton btnXoaR;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThongTinKH;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
