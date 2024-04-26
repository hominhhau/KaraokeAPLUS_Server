package gui.component;

import entity.NhanVien;
import gui.dialog.DL_DoiMK;
import gui.form.Form_Login;
import gui.form.Form_Setting;
import gui.main.Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Đây là class Header, chứa nút btnMneu để thu nhỏ hay mở rộng menu và cách
 * thành phần khác như , dồng hồ , tên nhân viên,....
 *
 * @author HO MINH HAU
 */
public class Header extends javax.swing.JPanel {

    private boolean isMenuOpen = false; // Ban đầu, menu chưa mở.
    private JPopupMenu popupMenu;

    /**
     * Creates new form Header
     */
    public Header() {
        initComponents();
        setOpaque(false);
        lock();
//        setTenNhanVien();

        popupMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("Đổi mật khẩu");
        JMenuItem item2 = new JMenuItem("Đăng xuất");

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DL_DoiMK doi = new DL_DoiMK((java.awt.Frame) SwingUtilities.getWindowAncestor((Component) e.getSource()), true);
                doi.setLocationRelativeTo(Header.this);
                doi.setVisible(true);
            }

        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(Header.this, "Bạn có chắc chắc muốn đăng xuất ?",
                        "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Form_Login loginForm = new Form_Login();
                    loginForm.setVisible(true);

                    SwingUtilities.getWindowAncestor(Header.this).dispose();// đóng cửa sổ hiện tại lại (nếu có)
                }
            }

        });

        popupMenu.add(item1);
        popupMenu.add(item2);

        btnChiTietNhanVien.setComponentPopupMenu(popupMenu);

    }

//    public void setTenNhanVien() {
//        NhanVien nhanVienDangNhap = Form_Login.getNhanVienDangNhap();
//        String tenNV = nhanVienDangNhap.getTenNV();
//        btnChiTietNhanVien.setText(tenNV);
//    }

    /**
     * sự kiện cho nút menu
     *
     * @param evt
     */
    public void addMenuEvent(ActionListener evt) {
        btnMenu.addActionListener(evt);

    }

    public void lock() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentTime = sdf.format(new Date());
                txtLock.setText(currentTime);
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(
                0, 0, Color.decode("#00AFB9"), // Màu bắt đầu (#c471ed)
                getWidth(), 0, Color.WHITE // Màu kết thúc (trắng)
        );

        // Vẽ gradient
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new gui.swing.Button();
        buttonGradient1 = new gui.swing.ButtonGradient();
        btnMenu = new gui.swing.Button();
        btnChiTietNhanVien = new gui.swing.ButtonGradient();
        txtLock = new javax.swing.JLabel();

        button1.setText("button1");

        buttonGradient1.setText("buttonGradient1");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(910, 50));

        btnMenu.setBackground(new java.awt.Color(0, 173, 184));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left.png"))); // NOI18N
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnChiTietNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N
        btnChiTietNhanVien.setText("Hồ Minh Hậu");
        btnChiTietNhanVien.setToolTipText("Chi tiết nhân viên ");
        btnChiTietNhanVien.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        btnChiTietNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnChiTietNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietNhanVienActionPerformed(evt);
            }
        });

        txtLock.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtLock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLock.setText("20:00:00");
        txtLock.setAutoscrolls(true);
        txtLock.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(340, 340, 340)
                                .addComponent(txtLock, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                .addGap(315, 315, 315)
                                .addComponent(btnChiTietNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(txtLock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnChiTietNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnChiTietNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietNhanVienActionPerformed
        popupMenu.show(btnChiTietNhanVien, 0, btnChiTietNhanVien.getHeight());// Hiên thị popup
    }//GEN-LAST:event_btnChiTietNhanVienActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.ButtonGradient btnChiTietNhanVien;
    public gui.swing.Button btnMenu;
    private gui.swing.Button button1;
    private gui.swing.ButtonGradient buttonGradient1;
    private javax.swing.JLabel txtLock;
    // End of variables declaration//GEN-END:variables
}
