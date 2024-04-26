package gui.form;

import Interface.NhanVienDao;
import Interface.TaiKhoanDao;
import Interface.impl.NhanVienImpl;
import Interface.impl.TaiKhoanImpl;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import gui.dialog.DL_QuenMatKhau;
import gui.main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import connectDB.DatabaseManager;

/**
 * @author 84934 NguyenThiQuynhGiang
 */
public class Form_Login extends javax.swing.JFrame {
    private NhanVienDao nv_dao;
    private TaiKhoanDao tk_dao;
    private static NhanVien nhanVienDangNhap;

    public static NhanVien getNhanVienDangNhap() {

        return nhanVienDangNhap;
    }

    public static void setNhanVienDangNhap(NhanVien nhanVien) {
        nhanVienDangNhap = nhanVien;
    }

    /**
     * Creates new form Form_Login
     */
    public Form_Login() {
        initComponents();
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/microphone.png")).getImage());
        txtTenDangNhap.setText("NV003");
        txtPassword.setText("1");


        txtPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnDangNhapActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Enter"));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlLeft = new gui.swing.CustomPanel();
        btnThoat = new gui.swing.RadiusButton();
        btnDangNhap = new gui.swing.RadiusButton();
        txtPassword = new gui.swing.CustomJPasswordField();
        txtTenDangNhap = new gui.swing.CustomJTextField();
        lblMatKhau = new javax.swing.JLabel();
        btnQuenMatKhau = new gui.swing.Button();
        lblTenDangNhap = new javax.swing.JLabel();
        lblDangNhap = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Karaoke APLUS");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Đăng nhập"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(973, 500));
        setResizable(false);

        pnlTong.setBackground(new java.awt.Color(115, 211, 221));
        pnlTong.setPreferredSize(new java.awt.Dimension(900, 520));

        pnlRight.setPreferredSize(new java.awt.Dimension(450, 260));

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
                pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlRightLayout.setVerticalGroup(
                pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_LoginBlue.gif"))); // NOI18N

        pnlLeft.setEnabled(false);
        pnlLeft.setOpaque(false);

        btnThoat.setBackground(new java.awt.Color(255, 51, 51));
        btnThoat.setText("Thoát");
        btnThoat.setFocusable(false);
        btnThoat.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnDangNhap.setBackground(new java.awt.Color(0, 167, 182));
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setFocusable(false);
        btnDangNhap.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        txtTenDangNhap.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });

        lblMatKhau.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblMatKhau.setText("Mật khẩu");

        btnQuenMatKhau.setBackground(new java.awt.Color(115, 211, 221));
        btnQuenMatKhau.setText("Quên Mật Khẩu");
        btnQuenMatKhau.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        btnQuenMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuenMatKhauActionPerformed(evt);
            }
        });

        lblTenDangNhap.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTenDangNhap.setText("Tên đăng nhập");

        lblDangNhap.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblDangNhap.setText("ĐĂNG NHẬP");

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
                pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLeftLayout.createSequentialGroup()
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlLeftLayout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(32, 32, 32)
                                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pnlLeftLayout.createSequentialGroup()
                                                .addGap(78, 78, 78)
                                                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(55, 55, 55)
                                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(23, Short.MAX_VALUE))
                        .addGroup(pnlLeftLayout.createSequentialGroup()
                                .addContainerGap(92, Short.MAX_VALUE)
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftLayout.createSequentialGroup()
                                                .addComponent(lblDangNhap)
                                                .addGap(88, 88, 88))
                                        .addComponent(btnQuenMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlLeftLayout.setVerticalGroup(
                pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLeftLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(lblDangNhap)
                                .addGap(48, 48, 48)
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTenDangNhap)
                                        .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMatKhau)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addComponent(btnQuenMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(112, 112, 112))
        );

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
                pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTongLayout.createSequentialGroup()
                                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(701, 701, 701))
        );
        pnlTongLayout.setVerticalGroup(
                pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                        .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuenMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuenMatKhauActionPerformed
        // TODO add your handling code here:
        DL_QuenMatKhau qMK = new DL_QuenMatKhau((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        qMK.setLocationRelativeTo(qMK);
        qMK.setVisible(true);
    }//GEN-LAST:event_btnQuenMatKhauActionPerformed

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        String userNameValue = txtTenDangNhap.getText();
        String passwordValue = String.valueOf(txtPassword.getPassword());
        if (!userNameValue.isEmpty() || !passwordValue.isEmpty()) {
            //DatabaseManager db = DatabaseManager.getInstance();
            DatabaseManager db = DatabaseManager.getInstance();
//            db.connect();
//            try {
//                db.connect();
//			} catch (Exception ex) {
//				Logger.getLogger(Form_Login.class.getName()).log(Level.SEVERE, null, ex);
//			}
            /**
             *  tk_dao = new TaiKhoan_DAO();
             *  nv_dao = new NhanVien_DAO();
             */

            tk_dao = new TaiKhoanImpl();
            nv_dao = new NhanVienImpl();

            try {
                // Kiểm tra thông tin đăng nhập sử dụng TaiKhoan_DAO
                if (tk_dao.authenticate(userNameValue, passwordValue)) {
                    // Lấy thông tin nhân viên từ database
                    ArrayList<NhanVien> nv = nv_dao.getNhanVienTheoMaNV(userNameValue);
                    if (!nv.isEmpty()) {
                        nhanVienDangNhap = nv.get(0); // Assuming there is only one NhanVien for the given username

                    }

                    // Lưu thông tin nhân viên vào biến toàn cục "nhanVienDangNhap"
                    setNhanVienDangNhap(nhanVienDangNhap);

                    // Hiển thị thông tin nhân viên lên Form_Main
                    // Xử lý khi đăng nhập thành cônn
                    // Đóng Form_Login
                    this.dispose(); // Đóng cửa sổ hiện tại (Form_Login)
                    // Mở Form_Main
                    new Main().setVisible(true);

                } else {
                    // Xử lý khi đăng nhập thất bại (ví dụ: thông báo lỗi)
                    JOptionPane.showMessageDialog(this, "Đăng nhập không thành công. Vui lòng kiểm tra tên đăng nhập và mật khẩu.");
                }
            } catch (Exception e) {
                // Xử lý lỗi khi kiểm traNVN thông tin đăng nhập
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi kiểm tra thông tin đăng nhập.");
            }
        }

    }

 
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Login().setVisible(true);
            }
        });
    }
    private gui.swing.RadiusButton btnDangNhap;
    private gui.swing.Button btnQuenMatKhau;
    private gui.swing.RadiusButton btnThoat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private gui.swing.CustomPanel pnlLeft;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlTong;
    private gui.swing.CustomJPasswordField txtPassword;
    private gui.swing.CustomJTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables


}
