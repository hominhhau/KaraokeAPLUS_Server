package gui.dialog;

import Interface.LoaiNVDao;
import Interface.LoaiPhongDao;
import Interface.NhanVienDao;
import Interface.TaiKhoanDao;
import Interface.impl.LoaiNVImpl;
import Interface.impl.NhanVienImpl;
import Interface.impl.TaiKhoanImpl;
import connectDB.ConnectDB;
import connectDB.DatabaseManager;
import dao.LoaiNV_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.LoaiNhanVien;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.form.Form_QuanLyNhanVien;
import gui.swing.CustomJOptionPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * @author 84343
 */
public class DL_ThemNV extends javax.swing.JFrame {
    //private NhanVien_DAO nv_dao;
    private NhanVienDao nv_dao;
    //private LoaiNV_DAO loainv_dao;
    private LoaiNVDao loainv_dao;
    private Form_QuanLyNhanVien qlnv;
    private ButtonGroup group;
    private TaiKhoanDao taikhoan_dao;

    public DL_ThemNV() {
        initComponents();
        setLocationRelativeTo(null);
        //nv_dao = new NhanVien_DAO();
        nv_dao = new NhanVienImpl();
        loadLoaiNhanVien();
        group = new ButtonGroup();
        group.add(radNam);
        group.add(radNu);
    }

    /**
     * Load data lên combobox
     *
     * @return
     */
    private void loadLoaiNhanVien() {
        //LoaiNV_DAO loainv_dao = new LoaiNV_DAO();
        LoaiNVDao loainv_dao = new LoaiNVImpl();

        List<LoaiNhanVien> loaiNhanViens = loainv_dao.getAllLoaiNhanVien();
        DefaultComboBoxModel<LoaiNhanVien> dataModel = new DefaultComboBoxModel<>(loaiNhanViens.toArray(new LoaiNhanVien[0]));
        cmbLNV.setModel(dataModel);
    }


    public String phatSinhMaNV() {
        List<NhanVien> nvs = nv_dao.getalltbNhanVien();
        String temp = null;
        for (NhanVien nv : nvs) {
            temp = nv.getMaNV();
        }
        int count = laySoDuoi(temp);
        count++;

        String maNVien = String.format("NV%03d", count);
        return maNVien;
    }

    public int laySoDuoi(String str) {
        if (str == null) {
            return 0;
        } else {
            String numberStr = str.substring(2);//loại bỏ ký tự "NV"
            int number = Integer.parseInt(numberStr);//chuyển đổi chuỗi thành số nguyên
            return number;
        }
    }

    public boolean validData() {
        String maNV = phatSinhMaNV();
        String tenNV = txtTenNV.getText();
        Boolean gioiTinh = radNam.isSelected();
        String CCCD = txtCCCD.getText();
        String SDT = txtSDT.getText();
        String diaChi = txtDiaChi.getText();
        LoaiNhanVien loaiNV = (LoaiNhanVien) cmbLNV.getSelectedItem();
        /**
         * Tên không được rỗng
         */
        if (!(tenNV.length() > 0 && tenNV.matches("^.+$"))) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được rỗng !!!");
            return false;
        }
        /**
         * Số điện thoại gồm 10 số
         */
        if (!(SDT.length() > 0 && SDT.matches("^0\\d{9}$"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 chữ số và phải bắt đầu bằng số 0 !!!");
            return false;
        }

        /**
         * Địa chỉ
         */
        if (!(diaChi.length() > 0 && diaChi.matches("^[a-zA-Z0-9\\s,]+"))) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống !!!");
            return false;
        }

        /**
         * CCCD
         */
        if (!(CCCD.length() > 0 && CCCD.matches("\\d{12}"))) {
            JOptionPane.showMessageDialog(this, "Căn cước công dân phải gồm 12 số !!!");
            return false;
        }

        /**
         * Phải chọn không được để trống
         */
        if (!(radNam.isSelected() || radNu.isSelected())) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính !!!");
            return false;
        }

        if (!(cmbLNV.getSelectedItem() == null)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại nhân viên !!!");
            return false;
        }

        return true;
    }

    public NhanVien revertNhanVien() {
        String maNV = phatSinhMaNV();
        String tenNV = txtTenNV.getText();
        Boolean gioiTinh = radNam.isSelected();
        String CCCD = txtCCCD.getText();
        String SDT = txtSDT.getText();
        String diaChi = txtDiaChi.getText();
        String caLam = "";
        if (radCa1.isSelected()) {
            caLam = "Ca 1";
        } else if (radCa2.isSelected()) {
            caLam = "Ca 2";
        } else if (radCa3.isSelected()) {
            caLam = "Ca 3";
        }
        LoaiNhanVien loaiNV = (LoaiNhanVien) cmbLNV.getSelectedItem();

        return new NhanVien(maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnThemNV = new javax.swing.JPanel();
        lblThemNV = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblLoaiNV = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblCa = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        radCa1 = new javax.swing.JRadioButton();
        radCa2 = new javax.swing.JRadioButton();
        radCa3 = new javax.swing.JRadioButton();
        cmbLNV = new javax.swing.JComboBox<>();
        btnThoat = new gui.swing.RadiusButton();
        btnThem = new gui.swing.RadiusButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnThemNV.setBackground(new java.awt.Color(255, 255, 255));

        lblThemNV.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblThemNV.setForeground(new java.awt.Color(41, 173, 86));
        lblThemNV.setText("THÊM NHÂN VIÊN");

        lblTenNV.setText("Tên nhân viên:");

        lblDiaChi.setText("Địa chỉ:");

        lblCCCD.setText("CCCD");

        lblSDT.setText("Số điện thoại:");

        lblLoaiNV.setText("Loại nhân viên:");

        lblGioiTinh.setText("Giới tính:");

        lblCa.setText("Ca làm:");

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

        radCa1.setText("Ca 1");
        radCa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCa1ActionPerformed(evt);
            }
        });

        radCa2.setText("Ca 2");
        radCa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCa2ActionPerformed(evt);
            }
        });

        radCa3.setText("Ca 3");
        radCa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCa3ActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 51, 51));
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(41, 173, 86));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnThemNVLayout = new javax.swing.GroupLayout(pnThemNV);
        pnThemNV.setLayout(pnThemNVLayout);
        pnThemNVLayout.setHorizontalGroup(
                pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnThemNVLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblLoaiNV, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnThemNVLayout.createSequentialGroup()
                                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(160, 160, 160))
                                        .addGroup(pnThemNVLayout.createSequentialGroup()
                                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(radCa1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(44, 44, 44)
                                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(radCa2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                .addComponent(radCa3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32))
                                        .addGroup(pnThemNVLayout.createSequentialGroup()
                                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                                                .addComponent(txtDiaChi)
                                                                .addComponent(txtCCCD)
                                                                .addComponent(txtSDT))
                                                        .addComponent(cmbLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThemNVLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(194, 194, 194))
        );
        pnThemNVLayout.setVerticalGroup(
                pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnThemNVLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(lblThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDiaChi)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCCCD)
                                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSDT)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblLoaiNV)
                                        .addComponent(cmbLNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblGioiTinh)
                                        .addComponent(radNam)
                                        .addComponent(radNu))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCa)
                                        .addComponent(radCa1)
                                        .addComponent(radCa2)
                                        .addComponent(radCa3))
                                .addGap(18, 18, 18)
                                .addGroup(pnThemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnThemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnThemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
        return;
    }//GEN-LAST:event_btnThoatActionPerformed

    private void cmbLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLoaiNVActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbLoaiNVActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        System.out.println("Xong");
        //ConnectDB db = ConnectDB.getInstance();
        DatabaseManager db = DatabaseManager.getInstance();
        db.connect();
        try {
            db.connect();
            String maNV = phatSinhMaNV();
            String tenNV = txtTenNV.getText();
            Boolean gioiTinh = radNam.isSelected();
            String CCCD = txtCCCD.getText();
            String SDT = txtSDT.getText();
            String diaChi = txtDiaChi.getText();
            String caLam = "";

            if (radCa1.isSelected()) {
                caLam = "Ca 1";
            } else if (radCa2.isSelected()) {
                caLam = "Ca 2";
            } else if (radCa3.isSelected()) {
                caLam = "Ca 3";
            }
            LoaiNhanVien loaiNV = (LoaiNhanVien) cmbLNV.getSelectedItem();
            System.out.println(loaiNV);

            NhanVien addNV = new NhanVien(maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV);
//            System.out.println("Xong" + addNV);
//            Boolean isSuccess = nv_dao.addStaff(addNV);
////            qlnv.loadTable(nv_dao.getalltbNhanVien());

            if (nv_dao.addStaff(addNV)) {
                taikhoan_dao = new TaiKhoanImpl();
                taikhoan_dao.taoTK(addNV.getMaNV());
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công !");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên không thành công !");
            }
        } catch (Exception ex) {
            Logger.getLogger(DL_ThemNV.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * if (tk_dao.taoTK(nv.getMaNV()) == false) {
         *                 JOptionPane.showInputDialog(null, "Tạo Tài khoant thất bại!");
         *             }
         */
    }//GEN-LAST:event_btnThemActionPerformed

    private void radNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNamActionPerformed
        if (radNam.isSelected()) {
            radNu.setSelected(false);
        }
    }//GEN-LAST:event_radNamActionPerformed

    private void radNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNuActionPerformed
        if (radNu.isSelected()) {
            radNam.setSelected(false);
        }
    }//GEN-LAST:event_radNuActionPerformed

    private void radCa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCa1ActionPerformed
        if (radCa1.isSelected()) {
            radCa2.setSelected(false);
            radCa3.setSelected(false);
        }
    }//GEN-LAST:event_radCa1ActionPerformed

    private void radCa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCa2ActionPerformed
        if (radCa2.isSelected()) {
            radCa1.setSelected(false);
            radCa3.setSelected(false);
        }
    }//GEN-LAST:event_radCa2ActionPerformed

    private void radCa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCa3ActionPerformed
        if (radCa3.isSelected()) {
            radCa1.setSelected(false);
            radCa2.setSelected(false);
        }
    }//GEN-LAST:event_radCa3ActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new DL_ThemNV().setVisible(true);
                DL_ThemNV dialog = new DL_ThemNV();
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
    private javax.swing.JComboBox<LoaiNhanVien> cmbLNV;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblCa;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblLoaiNV;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblThemNV;
    private javax.swing.JPanel pnThemNV;
    private javax.swing.JRadioButton radCa1;
    private javax.swing.JRadioButton radCa2;
    private javax.swing.JRadioButton radCa3;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
