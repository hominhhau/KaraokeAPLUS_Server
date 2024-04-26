package gui.form;

//import dao.DichVu_DAO;
//import dao.MatHang_DAO;
import entity.DichVu;
import entity.MatHang;
import gui.dialog.DL_ThemDV;
import gui.swing.scrollbar.ScrollBarCustom;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Interface.MatHangDao;
import Interface.impl.MatHangImpl;

/**
 * @author HO MINH HAU
 */
public class Form_DichVu extends javax.swing.JPanel {

    private MatHangDao mh_dao = new MatHangImpl();
    private DefaultTableModel dtmMatHang;
//    private DichVu_DAO dv_dao;
    private DefaultTableModel dtmDichVu;

    public Form_DichVu() {
        initComponents();
        scr.getViewport().setOpaque(false);
        scr.setVerticalScrollBar(new ScrollBarCustom());
        mh_dao = new  MatHangImpl();
        dtmMatHang = (DefaultTableModel) tblDichVu.getModel();

        DocDuLieu();
    }

    public void DocDuLieu() {
        List<MatHang> list = mh_dao.getalltbMatHang();
        int stt = 1;

        for (MatHang mh : list) {
            if (mh.isTrangThai()) {
                dtmMatHang.addRow(new Object[]{stt++ + "", mh.getMaMH(), mh.getTenMH(), mh.getGia()});
            }

        }
    }

    public void clearJTable() {
        while (tblDichVu.getRowCount() > 0) {
            dtmMatHang.removeRow(0);
        }
    }

    public void loadTable(ArrayList<MatHang> ds) {
        dtmDichVu.setRowCount(0);
        if (ds == null) {
            clearJTable();
            return;
        }
        clearJTable();
        for (MatHang mh : ds) {
            dtmDichVu.addRow(new Object[]{mh.getMaMH(), mh.getTenMH(), mh.getGia()});
        }
    }

    public void clearDataOnModel() {
        DefaultTableModel dtm = (DefaultTableModel) tblDichVu.getModel();
        dtm.getDataVector().removeAllElements();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDichVu = new javax.swing.JPanel();
        scr = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        pnlHeader = new javax.swing.JPanel();
        pnlTraCuu = new javax.swing.JPanel();
        lblTimKiem = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new gui.swing.RadiusButton();

        pnlDichVu.setBackground(new java.awt.Color(235, 249, 249));

        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "STT", "Mã dịch vụ", "Tên dịch vụ", "Đơn giá"
                }
        ));
        tblDichVu.setRowHeight(40);
        tblDichVu.setSelectionBackground(new java.awt.Color(0, 169, 183));
        scr.setViewportView(tblDichVu);
        if (tblDichVu.getColumnModel().getColumnCount() > 0) {
            tblDichVu.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlTraCuu.setBackground(new java.awt.Color(255, 255, 255));

        lblTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTimKiem.setText("Tìm kiếm");

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        btnTim.setBorder(null);
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass.png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTraCuuLayout = new javax.swing.GroupLayout(pnlTraCuu);
        pnlTraCuu.setLayout(pnlTraCuuLayout);
        pnlTraCuuLayout.setHorizontalGroup(
                pnlTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTraCuuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(79, Short.MAX_VALUE))
        );
        pnlTraCuuLayout.setVerticalGroup(
                pnlTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTraCuuLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(pnlTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTraCuuLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
                pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHeaderLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(pnlTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(418, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
                pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHeaderLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(pnlTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDichVuLayout = new javax.swing.GroupLayout(pnlDichVu);
        pnlDichVu.setLayout(pnlDichVuLayout);
        pnlDichVuLayout.setHorizontalGroup(
                pnlDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDichVuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scr)
                                .addContainerGap())
        );
        pnlDichVuLayout.setVerticalGroup(
                pnlDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlDichVuLayout.createSequentialGroup()
                                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(scr, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                                .addGap(94, 94, 94))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed

    }//GEN-LAST:event_txtTimActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String maMH = txtTim.getText().trim();
        if (!(maMH.length() > 0)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã mặt hàng");
        } else {
            String maTim = txtTim.getText();
            ArrayList<MatHang> dsMHTim = null;
            for (MatHang mh : mh_dao.getalltbMatHang()) {
                if (mh.getMaMH().equals(maTim)) {
                    dsMHTim = new ArrayList<MatHang>();
                    dsMHTim.add(mh);
                }
            }
            if (dsMHTim != null) {
                clearDataOnModel();
                int stt = 1;
                for (MatHang mh : dsMHTim) {
                    dtmMatHang.addRow(new Object[]{stt++ + "", mh.getMaMH(), mh.getTenMH(), mh.getGia()});
                }
            } else if (dsMHTim == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnTim;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JPanel pnlDichVu;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlTraCuu;
    private javax.swing.JScrollPane scr;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
