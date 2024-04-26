package gui.dialog;

/**
 * @author 84343
 */
public class DL_SuaNV extends javax.swing.JFrame {


    public DL_SuaNV() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        pnSuaNV = new javax.swing.JPanel();
        lblSuaNV = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblLoaiNV = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblCa = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        radQL = new javax.swing.JRadioButton();
        radTN = new javax.swing.JRadioButton();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        radCa1 = new javax.swing.JRadioButton();
        radCa2 = new javax.swing.JRadioButton();
        radCa3 = new javax.swing.JRadioButton();
        btnSua = new gui.swing.RadiusButton();
        btnThoat = new gui.swing.RadiusButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnSuaNV.setBackground(new java.awt.Color(255, 255, 255));

        lblSuaNV.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSuaNV.setForeground(new java.awt.Color(41, 173, 86));
        lblSuaNV.setText("SỬA NHÂN VIÊN");

        lblMaNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaNV.setText("Mã nhân viên:");

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenNV.setText("Tên nhân viên:");

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");

        lblCCCD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCCCD.setText("CCCD:");

        lblSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSDT.setText("Số điện thoại:");

        lblLoaiNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLoaiNV.setText("Loại nhân viên:");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGioiTinh.setText("Giới tính:");

        lblCa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCa.setText("Ca làm:");

        txtMaNV.setEditable(false);
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        radQL.setText("NVQL");

        radTN.setText("NVTN");

        radNam.setText("Nam");

        radNu.setText("Nữ");

        radCa1.setText("Ca 1");

        radCa2.setText("Ca 2");

        radCa3.setText("Ca 3");
        radCa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCa3ActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(41, 173, 86));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");

        btnThoat.setBackground(new java.awt.Color(205, 13, 13));
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSuaNVLayout = new javax.swing.GroupLayout(pnSuaNV);
        pnSuaNV.setLayout(pnSuaNVLayout);
        pnSuaNVLayout.setHorizontalGroup(
                pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                                                .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSuaNVLayout.createSequentialGroup()
                                                                .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtTenNV))
                                                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                                                .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(lblCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtDiaChi)
                                                                        .addComponent(txtCCCD))))
                                                .addGap(72, 72, 72))
                                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblLoaiNV, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                                        .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(radQL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(radCa1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(radTN, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                                                .addComponent(radCa2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(radCa3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(50, 50, 50))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSuaNVLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(191, 191, 191))
                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnSuaNVLayout.setVerticalGroup(
                pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnSuaNVLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTenNV)
                                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDiaChi)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblCCCD)
                                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSDT)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblLoaiNV)
                                        .addComponent(radQL)
                                        .addComponent(radTN))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblGioiTinh)
                                        .addComponent(radNam)
                                        .addComponent(radNu))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCa)
                                        .addComponent(radCa1)
                                        .addComponent(radCa2)
                                        .addComponent(radCa3))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnSuaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnSuaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radCa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radCa3ActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
        return;
    }//GEN-LAST:event_btnThoatActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DL_SuaNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnSua;
    private gui.swing.RadiusButton btnThoat;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblCa;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblLoaiNV;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSuaNV;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JPanel pnSuaNV;
    private javax.swing.JRadioButton radCa1;
    private javax.swing.JRadioButton radCa2;
    private javax.swing.JRadioButton radCa3;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JRadioButton radQL;
    private javax.swing.JRadioButton radTN;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
