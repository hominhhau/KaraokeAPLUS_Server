package gui.dialog;

/**
 * @author 84343
 */
public class DL_DichVu extends javax.swing.JFrame {

    public DL_DichVu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDV = new javax.swing.JLabel();
        pnDSDV = new javax.swing.JPanel();
        lblDSDV = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSDV = new javax.swing.JTable();
        pnDSDVThem = new javax.swing.JPanel();
        lblDVThem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDVThem = new javax.swing.JTable();
        lblTong = new javax.swing.JLabel();
        txtTong = new javax.swing.JTextField();
        btnCapNhat = new gui.swing.RadiusButton();
        btnThoat = new gui.swing.RadiusButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setUndecorated(true);

        lblDV.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDV.setForeground(new java.awt.Color(41, 173, 86));
        lblDV.setText("DỊCH VỤ");

        pnDSDV.setBackground(new java.awt.Color(166, 208, 238));

        lblDSDV.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDSDV.setText("Danh sách dịch vụ");

        tblDSDV.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Mã", "Tên", "Đơn giá", "Thêm"
                }
        ));
        jScrollPane1.setViewportView(tblDSDV);
        if (tblDSDV.getColumnModel().getColumnCount() > 0) {
            tblDSDV.getColumnModel().getColumn(0).setHeaderValue("Mã");
            tblDSDV.getColumnModel().getColumn(1).setHeaderValue("Tên");
            tblDSDV.getColumnModel().getColumn(2).setHeaderValue("Đơn giá");
            tblDSDV.getColumnModel().getColumn(3).setHeaderValue("Thêm");
        }

        javax.swing.GroupLayout pnDSDVLayout = new javax.swing.GroupLayout(pnDSDV);
        pnDSDV.setLayout(pnDSDVLayout);
        pnDSDVLayout.setHorizontalGroup(
                pnDSDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnDSDVLayout.createSequentialGroup()
                                .addGroup(pnDSDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnDSDVLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnDSDVLayout.createSequentialGroup()
                                                .addGap(127, 127, 127)
                                                .addComponent(lblDSDV, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnDSDVLayout.setVerticalGroup(
                pnDSDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnDSDVLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblDSDV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnDSDVThem.setBackground(new java.awt.Color(255, 255, 255));

        lblDVThem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDVThem.setText("Dịch vụ đã thêm");

        tblDVThem.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "STT", "Tên", "SL", "Thành tiền", "Hành động"
                }
        ));
        jScrollPane2.setViewportView(tblDVThem);

        lblTong.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblTong.setText("Tổng");

        txtTong.setEnabled(false);

        btnCapNhat.setBackground(new java.awt.Color(65, 194, 33));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");

        btnThoat.setBackground(new java.awt.Color(205, 13, 13));
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDSDVThemLayout = new javax.swing.GroupLayout(pnDSDVThem);
        pnDSDVThem.setLayout(pnDSDVThemLayout);
        pnDSDVThemLayout.setHorizontalGroup(
                pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnDSDVThemLayout.createSequentialGroup()
                                .addContainerGap(19, Short.MAX_VALUE)
                                .addGroup(pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDSDVThemLayout.createSequentialGroup()
                                                .addComponent(lblDVThem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(156, 156, 156))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDSDVThemLayout.createSequentialGroup()
                                                .addGroup(pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(pnDSDVThemLayout.createSequentialGroup()
                                                                .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(17, 17, 17))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDSDVThemLayout.createSequentialGroup()
                                                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(53, 53, 53)
                                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100))))
        );
        pnDSDVThemLayout.setVerticalGroup(
                pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnDSDVThemLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblDVThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTong)
                                        .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnDSDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblDV)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(pnDSDVThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pnDSDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnDSDVThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                new DL_DichVu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnCapNhat;
    private gui.swing.RadiusButton btnThoat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDSDV;
    private javax.swing.JLabel lblDV;
    private javax.swing.JLabel lblDVThem;
    private javax.swing.JLabel lblTong;
    private javax.swing.JPanel pnDSDV;
    private javax.swing.JPanel pnDSDVThem;
    private javax.swing.JTable tblDSDV;
    private javax.swing.JTable tblDVThem;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
