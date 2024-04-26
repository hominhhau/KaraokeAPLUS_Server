package gui.dialog;

import Interface.ChiTietHoaDonDichVuDao;
import Interface.impl.ChiTietHoaDonDichVuImpl;
import Interface.impl.MatHangImpl;
import dao.ChiTietHoaDonDichVu_DAO;
import dao.ChiTietHoaDonPhong_Dao;
import dao.MatHang_DAO;
import entity.ChiTietHoaDonDV;
import entity.HoaDon;
import entity.MatHang;
import entity.PhongHat;
import gui.form.Form_QuanLyDatPhong;
import gui.form.MainForm;
import gui.main.Main;
import gui.swing.scrollbar.ScrollBarCustom;
import gui.swing.table.PanelAction;
//import gui.swing.table.TableActionCellEditor;
import gui.swing.table.TableActionCellEditor;
import gui.swing.table.TableActionCellRender;
import gui.swing.table.TableActionEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DL_ChonDichVu extends javax.swing.JDialog {

    private MatHangImpl mh_dao;
    private DefaultTableModel dtmMatHang;
    private DefaultTableModel dtmDVThem;
    private ChiTietHoaDonDichVuDao cthddv_dao;
    private int selectedRowIndex = -1;
    private PanelAction panelAction;
    private ChiTietHoaDonPhong_Dao cthdp_dao;
    private int currentSL;
    private double gia;
    private String maHD;


    public DL_ChonDichVu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        mh_dao = new MatHangImpl();
        dtmMatHang = (DefaultTableModel) tblDSDV.getModel();
        dtmDVThem = (DefaultTableModel) tblDVThem.getModel();
        DocDuLieu();
        tblDSDV.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblDSDV.getSelectedRow();
                if (selectedRow != -1) {
                    MatHang selectedMatHang = getSelectedMatHang(selectedRow);
                    cthddv_dao = new ChiTietHoaDonDichVuImpl();
                    addToDVThemTable(selectedMatHang);

                }
            }
        });

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void tangSoLuong(int row) {
                currentSL = (int) dtmDVThem.getValueAt(row, 2);
                // lấy giá từ dao
                mh_dao = new MatHangImpl();
                MatHang mh = mh_dao.findMatHang((String) dtmDVThem.getValueAt(row, 0));
                gia = mh.getGia();
                dtmDVThem.setValueAt(currentSL + 1, row, 2);

                // Tính lại tổng tiền dựa trên số lượng mới
                double newTotalPrice = (currentSL + 1) * gia;
                dtmDVThem.setValueAt(newTotalPrice, row, 3);
            }

            @Override
            public void giamSoLuong(int row) {
                currentSL = (int) dtmDVThem.getValueAt(row, 2);
                mh_dao = new MatHangImpl();
                MatHang mh = mh_dao.findMatHang((String) dtmDVThem.getValueAt(row, 0));
                gia = mh.getGia();

                if (currentSL > 1) {
                    dtmDVThem.setValueAt(currentSL - 1, row, 2);

                    // Tính lại tổng tiền dựa trên số lượng mới
                    double newTotalPrice = (currentSL - 1) * gia;
                    dtmDVThem.setValueAt(newTotalPrice, row, 3);
                } else { // currentSL == 1
                    dtmDVThem.removeRow(row);
                }
            }
        };

        tblDVThem.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        tblDVThem.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));


    }

    private MatHang getSelectedMatHang(int row) {
        String maMH = (String) tblDSDV.getValueAt(row, 0);
        return mh_dao.findMatHang(maMH);
    }

    private void addToDVThemTable(MatHang selectedMatHang) {
        String selectedCode = selectedMatHang.getMaMH();
        int rowIndex = findRowIndexByCode(selectedCode);

        if (rowIndex != -1) {
            // Row already exists, update quantity and total price
            currentSL = (int) dtmDVThem.getValueAt(rowIndex, 2);
            gia = (double) dtmDVThem.getValueAt(rowIndex, 3);


            dtmDVThem.setValueAt(currentSL + 1, rowIndex, 2);

            // Calculate the new total price based on the updated quantity
            double newTotalPrice = (currentSL + 1) * gia;

            // Set the new total price in the table
            dtmDVThem.setValueAt(newTotalPrice, rowIndex, 3);
        } else {
            // Row does not exist, add a new row
            dtmDVThem.addRow(new Object[]{selectedMatHang.getMaMH(), selectedMatHang.getTenMH(), 1, selectedMatHang.getGia()});
        }
    }

    private int findRowIndexByCode(String code) {
        for (int i = 0; i < dtmDVThem.getRowCount(); i++) {
            String codeInDVThem = String.valueOf(dtmDVThem.getValueAt(i, 0));
            if (Objects.equals(codeInDVThem, code)) {
                return i;
            }
        }
        return -1; // Code not found in the table
    }


    public List<MatHangModel> getDVThemData() {
        List<MatHangModel> dvThemData = new ArrayList<>();

        for (int i = 0; i < dtmDVThem.getRowCount(); i++) {
            String maMH = (String) dtmDVThem.getValueAt(i, 0);
            String tenMH = (String) dtmDVThem.getValueAt(i, 1);
            int SL = (int) dtmDVThem.getValueAt(i, 2);
            double gia = (double) dtmDVThem.getValueAt(i, 3);

            MatHangModel data = new MatHangModel();
            data.setMaMH(maMH);
            data.setTenMH(tenMH);

            data.setSL(SL);
            data.setGia(gia);

            dvThemData.add(data);
        }

        return dvThemData;
    }


    public void DocDuLieu() {
        dtmMatHang.setRowCount(0);
        ArrayList<MatHang> ds = mh_dao.getalltbMatHang();
        for (MatHang mh : ds) {
            if (mh.isTrangThai()) {
                dtmMatHang.addRow(new Object[]{mh.getMaMH(), mh.getTenMH(), mh.getGia()});
            }
        }

    }

    public void clearJTable() {
        while (tblDSDV.getRowCount() > 0) {
            dtmMatHang.removeRow(0);
        }
    }

    public void loadTable(ArrayList<MatHang> ds) {
        dtmMatHang.setRowCount(0);
        if (ds == null) {
            clearJTable();
            return;
        }
        clearJTable();
        for (MatHang mh : ds) {
            dtmMatHang.addRow(new Object[]{mh.getTenMH()});
        }
    }

    public void clearDataOnModel() {
        DefaultTableModel dtm = (DefaultTableModel) tblDSDV.getModel();
        dtm.getDataVector().removeAllElements();
    }

    public class MatHangModel {
        private String maMH;
        private String tenMH;
        private int SL;
        private double gia;

        // Constructors
        public MatHangModel() {
        }

        public MatHangModel(String maMH, String tenMH, int SL, double gia) {
            this.maMH = maMH;
            this.tenMH = tenMH;
            this.SL = SL;
            this.gia = gia;
        }

        // Getters and setters
        public String getMaMH() {
            return maMH;
        }

        public void setMaMH(String maMH) {
            this.maMH = maMH;
        }

        public String getTenMH() {
            return tenMH;
        }

        public void setTenMH(String tenMH) {
            this.tenMH = tenMH;
        }

        public int getSL() {
            return SL;
        }

        public void setSL(int SL) {
            this.SL = SL;
        }

        public double getGia() {
            return gia;
        }

        public void setGia(Double gia) {
            this.gia = gia;
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblDV1 = new javax.swing.JLabel();
        pnDSDV = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSDV = new javax.swing.JTable();
        lblDSDV = new javax.swing.JLabel();
        pnDSDVThem = new javax.swing.JPanel();
        lblDVThem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDVThem = new javax.swing.JTable();
        btnXong = new gui.swing.RadiusButton();
        btnExit2 = new gui.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDV1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDV1.setForeground(new java.awt.Color(41, 173, 86));
        lblDV1.setText("DỊCH VỤ");

        pnDSDV.setBackground(new java.awt.Color(255, 255, 255));

        tblDSDV.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null}
                },
                new String[]{
                        "Mã mặt hàng", "Tên mặt hàng"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblDSDV.setRowHeight(30);
        jScrollPane1.setViewportView(tblDSDV);
        if (tblDSDV.getColumnModel().getColumnCount() > 0) {
            tblDSDV.getColumnModel().getColumn(0).setResizable(false);
            tblDSDV.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblDSDV.getColumnModel().getColumn(1).setResizable(false);
            tblDSDV.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        lblDSDV.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDSDV.setText("Danh sách dịch vụ");

        javax.swing.GroupLayout pnDSDVLayout = new javax.swing.GroupLayout(pnDSDV);
        pnDSDV.setLayout(pnDSDVLayout);
        pnDSDVLayout.setHorizontalGroup(
                pnDSDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDSDVLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDSDV, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                        .addGroup(pnDSDVLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE))
        );
        pnDSDVLayout.setVerticalGroup(
                pnDSDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnDSDVLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblDSDV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );

        pnDSDVThem.setBackground(new java.awt.Color(255, 255, 255));

        lblDVThem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDVThem.setText("Dịch vụ đã thêm");

        tblDVThem.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã", "Tên", "SL", "Thành tiền", "Hành động"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblDVThem.setRowHeight(30);
        tblDVThem.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane2.setViewportView(tblDVThem);
        if (tblDVThem.getColumnModel().getColumnCount() > 0) {
            tblDVThem.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblDVThem.getColumnModel().getColumn(1).setPreferredWidth(110);
            tblDVThem.getColumnModel().getColumn(2).setPreferredWidth(2);
            tblDVThem.getColumnModel().getColumn(3).setPreferredWidth(30);
            tblDVThem.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        btnXong.setBackground(new java.awt.Color(65, 194, 33));
        btnXong.setForeground(new java.awt.Color(255, 255, 255));
        btnXong.setText("Xong");
        btnXong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDSDVThemLayout = new javax.swing.GroupLayout(pnDSDVThem);
        pnDSDVThem.setLayout(pnDSDVThemLayout);
        pnDSDVThemLayout.setHorizontalGroup(
                pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnDSDVThemLayout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(lblDVThem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(126, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDSDVThemLayout.createSequentialGroup()
                                .addGroup(pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDSDVThemLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnXong, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        pnDSDVThemLayout.setVerticalGroup(
                pnDSDVThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnDSDVThemLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDVThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        btnExit2.setBackground(new java.awt.Color(255, 0, 51));
        btnExit2.setBorder(null);
        btnExit2.setForeground(new java.awt.Color(255, 255, 255));
        btnExit2.setText("  X  ");
        btnExit2.setEffectColor(new java.awt.Color(255, 255, 255));
        btnExit2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExit2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(270, 270, 270)
                                .addComponent(btnExit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnDSDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(pnDSDVThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pnDSDVThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnDSDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExit2ActionPerformed

    private void btnXongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXongActionPerformed
        try {
            List<MatHangModel> dvThemData = getDVThemData();
            cthddv_dao = new ChiTietHoaDonDichVuImpl();

            // Create an instance of DL_KiemTravsAddKH and show it
            DL_KiemTravsAddKH dataHD = new DL_KiemTravsAddKH((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
            Form_QuanLyDatPhong frm = new Form_QuanLyDatPhong();
            // bắt sưj kiện khi mở từ DL_NhanPhongDatTruoc
            if (dataHD.getMaHDPublic() != null) {
                maHD = dataHD.getMaHDPublic();

            } else {
                DL_NhanPhongDatTruoc dataHD1 = new DL_NhanPhongDatTruoc((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
                maHD = dataHD1.getMaHDPublic();
            }

            // Call getMaHDPublic method

            for (MatHangModel data : dvThemData) {
                ChiTietHoaDonDV cthddv = new ChiTietHoaDonDV();

                ChiTietHoaDonDV cthddvadd = new ChiTietHoaDonDV(new HoaDon(maHD), new MatHang(data.getMaMH()), new PhongHat(frm.getRoomSelected()), data.getSL(), data.getGia());

                // check chưa tồn tại thì create, đã tồn tại thì update sl, nếu trong database có mà trong table không có thì xóa trong database do sl dưới 0 thì đã xóa khỏi table\
                if (cthddv_dao.findChiTietHoaDonDV(maHD, data.getMaMH()) != null) {
                    if (data.getSL() > 0) {
                        cthddv_dao.updateChiTietHoaDonDV(cthddvadd);
                    } else {
                        cthddv_dao.deleteChiTietHoaDonDV(maHD, data.getMaMH());
                    }
                } else {
                    cthddv_dao.createChiTietHoaDonDV(cthddvadd);

                }

            }

            this.dispose();
        } catch (Exception e) {
            // Log the exception or display an error message to the user.
            e.printStackTrace();
        }

        this.dispose();

    }//GEN-LAST:event_btnXongActionPerformed

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
            java.util.logging.Logger.getLogger(DL_ChonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_ChonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_ChonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_ChonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_ChonDichVu dialog = new DL_ChonDichVu(new javax.swing.JFrame(), true);
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
    private gui.swing.Button btnExit2;
    private gui.swing.RadiusButton btnXong;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDSDV;
    private javax.swing.JLabel lblDV1;
    private javax.swing.JLabel lblDVThem;
    private javax.swing.JPanel pnDSDV;
    private javax.swing.JPanel pnDSDVThem;
    private javax.swing.JTable tblDSDV;
    private javax.swing.JTable tblDVThem;
    // End of variables declaration//GEN-END:variables
}
