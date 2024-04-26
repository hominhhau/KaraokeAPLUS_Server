
package gui.dialog;

/**
 * @author 84343
 */
public class DL_SuaPhong extends javax.swing.JFrame {

    public DL_SuaPhong() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSuaPhong = new javax.swing.JPanel();
        lblSuaPhong = new javax.swing.JLabel();
        lblMaPhong = new javax.swing.JLabel();
        lblTenPhong = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        lblMaLoaiPhong = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        txtMaPhong = new javax.swing.JTextField();
        txtTenPhong = new javax.swing.JTextField();
        txtMaLoaiPhong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        radVIP = new javax.swing.JRadioButton();
        radThuong = new javax.swing.JRadioButton();
        radTrong = new javax.swing.JRadioButton();
        radSuDung = new javax.swing.JRadioButton();
        radCho = new javax.swing.JRadioButton();
        btnSua = new gui.swing.RadiusButton();
        btnThoat = new gui.swing.RadiusButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnSuaPhong.setBackground(new java.awt.Color(255, 255, 255));

        lblSuaPhong.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSuaPhong.setForeground(new java.awt.Color(41, 173, 86));
        lblSuaPhong.setText("SỬA PHÒNG HÁT");

        lblMaPhong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaPhong.setText("Mã phòng:");

        lblTenPhong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenPhong.setText("Tên phòng:");

        lblLoaiPhong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLoaiPhong.setText("Loại phòng:");

        lblMaLoaiPhong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaLoaiPhong.setText("Mã loại phòng:");

        lblGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGia.setText("Giá:");

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTrangThai.setText("Trạng thái:");

        txtMaPhong.setEditable(false);

        txtMaLoaiPhong.setEditable(false);

        txtGia.setEditable(false);

        radVIP.setText("Phòng VIP");

        radThuong.setText("Phòng thường");

        radTrong.setText("Phòng trống");

        radSuDung.setText("Phòng đang sử dụng");

        radCho.setText("Phòng chờ");

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

        javax.swing.GroupLayout pnSuaPhongLayout = new javax.swing.GroupLayout(pnSuaPhong);
        pnSuaPhong.setLayout(pnSuaPhongLayout);
        pnSuaPhongLayout.setHorizontalGroup(
                pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnSuaPhongLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblMaLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                        .addComponent(lblGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblMaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(radCho, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnSuaPhongLayout.createSequentialGroup()
                                                .addComponent(radVIP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(59, 59, 59)
                                                .addComponent(radThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(radTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMaPhong)
                                        .addComponent(txtTenPhong)
                                        .addComponent(txtMaLoaiPhong)
                                        .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                        .addGroup(pnSuaPhongLayout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(lblSuaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(radSuDung))
                                .addGap(76, 76, 76))
                        .addGroup(pnSuaPhongLayout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(107, 107, 107)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnSuaPhongLayout.setVerticalGroup(
                pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnSuaPhongLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblSuaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMaPhong)
                                        .addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTenPhong)
                                        .addComponent(txtTenPhong))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblLoaiPhong)
                                        .addComponent(radVIP)
                                        .addComponent(radThuong))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMaLoaiPhong)
                                        .addComponent(txtMaLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblGia)
                                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTrangThai)
                                        .addComponent(radTrong))
                                .addGap(18, 18, 18)
                                .addComponent(radSuDung)
                                .addGap(18, 18, 18)
                                .addComponent(radCho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnSuaPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnSuaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnSuaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
        return;
    }//GEN-LAST:event_btnThoatActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DL_SuaPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnSua;
    private gui.swing.RadiusButton btnThoat;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaLoaiPhong;
    private javax.swing.JLabel lblMaPhong;
    private javax.swing.JLabel lblSuaPhong;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnSuaPhong;
    private javax.swing.JRadioButton radCho;
    private javax.swing.JRadioButton radSuDung;
    private javax.swing.JRadioButton radThuong;
    private javax.swing.JRadioButton radTrong;
    private javax.swing.JRadioButton radVIP;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaLoaiPhong;
    private javax.swing.JTextField txtMaPhong;
    private javax.swing.JTextField txtTenPhong;
    // End of variables declaration//GEN-END:variables
}
