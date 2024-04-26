package gui.form;

import Interface.KhachHangDao;
import Interface.impl.KhachHangImpl;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.KhachHang;
import entity.NhanVien;
import gui.dialog.DL_SuaKhachHang;
import gui.dialog.DL_ThongTinKhachHang;
import gui.swing.CustomJOptionPane;
import gui.swing.scrollbar.ScrollBarCustom;
import gui.swing.table.PanelActionCellEditor_KhachHang;
import gui.swing.table.PanelActionCellRender_KhachHang;
import gui.swing.table.TableActionEvent_KhachHang;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author HO MINH HAU
 */
public class Form_QuanLyKhachHang extends javax.swing.JPanel {

    //private KhachHang_DAO kh_dao;
    private KhachHangDao kh_dao = new KhachHangImpl();

    private DefaultTableModel dtmKhachHang;
    private ArrayList<KhachHang> customers;

    public Form_QuanLyKhachHang() {
        initComponents();
        TableActionEvent_KhachHang event = new TableActionEvent_KhachHang() {
            @Override
            public void Sua(int row) {
                //System.out.println("sua" +row);
                //Viết sự kiên dô đây 
                //kh_dao = new KhachHang_DAO();
                kh_dao = new KhachHangImpl();
                if (tblDSKH.getSelectedRowCount() > 0) {
                    int[] selectedRows = tblDSKH.getSelectedRows();
                    for (int i = 0; i < selectedRows.length; i++) {
                        int rowIndex = selectedRows[i];
                        String maKH = tblDSKH.getValueAt(rowIndex, 0).toString();
                        String tenKH = tblDSKH.getValueAt(rowIndex, 1).toString();
                        String sdt = tblDSKH.getValueAt(rowIndex, 2).toString();
                        Boolean gioiTinh = Boolean.parseBoolean(tblDSKH.getValueAt(rowIndex, 3).toString());

                        KhachHang kh = new KhachHang(maKH, tenKH, sdt, gioiTinh);
                        tblDSKH.setValueAt(tenKH, rowIndex, 1);
                        tblDSKH.setValueAt(sdt, rowIndex, 2);
                        tblDSKH.setValueAt(gioiTinh, rowIndex, 3);
                        if (kh_dao.editCustomer(kh)) {
                            System.out.println("Sửa thành công");
                            JOptionPane.showMessageDialog(null, "Sửa thành công");
                        } else {
                            System.out.println("Sửa thất bại");
                            JOptionPane.showMessageDialog(null, "Sửa thất bại");
                        }
                    }
                }

                clearJTable();
                DocDuLieu();

            }
        };
        tblDSKH.getColumnModel().getColumn(4).setCellRenderer(new PanelActionCellRender_KhachHang());
        tblDSKH.getColumnModel().getColumn(4).setCellEditor(new PanelActionCellEditor_KhachHang(event));

        scr.getViewport().setOpaque(false);
        scr.setVerticalScrollBar(new ScrollBarCustom());
        //kh_dao = new KhachHang_DAO();
        kh_dao = new KhachHangImpl();
        dtmKhachHang = (DefaultTableModel) tblDSKH.getModel();
        //KhachHang_DAO KH = new KhachHang_DAO();
        KhachHangDao KH = new KhachHangImpl();
        long tongKH = KH.getSoLuongKhachHang();
        lblTong.setText(String.valueOf(tongKH));
        DocDuLieu();
        updateTotalCustomer();
    }

    public void loadTable(ArrayList<KhachHang> ds) {
        dtmKhachHang.setRowCount(0);//reset nd trong bang ve 0
        if (ds == null) {
            clearJTable();
            return;
        }
        clearJTable();
        for (KhachHang kh : ds) {
            dtmKhachHang.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getSdt(), kh.isGioitinh() ? "Nam" : "Nữ"});
        }
    }

    public void clearJTable() {
        while (tblDSKH.getRowCount() > 0) {
            dtmKhachHang.removeRow(0);
        }
    }

    public void clearDataOnModel() {
        DefaultTableModel dtm = (DefaultTableModel) tblDSKH.getModel();
        dtm.getDataVector().removeAllElements();
    }

    public void DocDuLieu() {
        List<KhachHang> list = kh_dao.getalltbKhachHang();
        for (KhachHang kh : list) {
            dtmKhachHang.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getSdt(), kh.isGioitinh() ? "Nam" : "Nữ"});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlKhachHang = new javax.swing.JPanel();
        scr = new javax.swing.JScrollPane();
        tblDSKH = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pnlHeader = new javax.swing.JPanel();
        pnlTraCuu = new javax.swing.JPanel();
        lblTim = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new gui.swing.RadiusButton();
        btnThem = new gui.swing.RadiusButton();
        btnRefesh = new gui.swing.RadiusButton();
        lblTong = new javax.swing.JLabel();
        btnExcel = new gui.swing.RadiusButton();

        pnlKhachHang.setBackground(new java.awt.Color(235, 249, 249));

        tblDSKH.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính", "Hành động"
                }
        ));
        tblDSKH.setRowHeight(40);
        tblDSKH.setSelectionBackground(new java.awt.Color(0, 169, 183));
        tblDSKH.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scr.setViewportView(tblDSKH);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tổng");

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlTraCuu.setBackground(new java.awt.Color(255, 255, 255));

        lblTim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTim.setText("Tìm kiếm");

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
                                .addComponent(lblTim)
                                .addGap(28, 28, 28)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(251, Short.MAX_VALUE))
        );
        pnlTraCuuLayout.setVerticalGroup(
                pnlTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTraCuuLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTim)
                                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(163, 163, 163))
                        .addGroup(pnlTraCuuLayout.createSequentialGroup()
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-customer-48.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnRefesh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        btnRefesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
                pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHeaderLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(pnlTraCuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(385, 385, 385)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefesh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
        );
        pnlHeaderLayout.setVerticalGroup(
                pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHeaderLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnRefesh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnlTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTong.setText("jLabel2");

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Excel32.png"))); // NOI18N
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
                pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlKhachHangLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addComponent(lblTong)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                        .addGroup(pnlKhachHangLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scr, javax.swing.GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlKhachHangLayout.setVerticalGroup(
                pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlKhachHangLayout.createSequentialGroup()
                                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(scr, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(lblTong)))
                                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String sdtKH = txtTim.getText().trim();
        if (!(sdtKH.length() > 0 && sdtKH.matches("\\d{10}"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm 10 số !!!");
        } else {
            String maTim = txtTim.getText();
            ArrayList<KhachHang> dsKHtim = null;

            for (KhachHang kh : kh_dao.getalltbKhachHang()) {
                if (kh.getSdt().equals(maTim)) {
                    dsKHtim = new ArrayList<KhachHang>();
                    dsKHtim.add(kh);
                }
            }
            if (dsKHtim != null) {
                clearDataOnModel();
                for (KhachHang kh : dsKHtim) {
                    dtmKhachHang.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getSdt(), kh.isGioitinh() ? "Nam" : "Nữ"});
                }
            } else if (dsKHtim == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy !!!");
            }
        }
        updateTotalCustomer();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        new DL_ThongTinKhachHang().setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnRefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshActionPerformed
        clearJTable();
        loadTable(kh_dao.getalltbKhachHang());
        updateTotalCustomer();

    }//GEN-LAST:event_btnRefeshActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        try {
            writeFileExcell();
//            System.out.println("Lỗi hệ thống");
            JOptionPane.showMessageDialog(null, "Xuất thành công!");
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Lỗi hệ thống");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcelActionPerformed

    private void updateTotalCustomer() {
        int totalCustomer = dtmKhachHang.getRowCount();
        lblTong.setText("" + totalCustomer);
    }

    public void writeFileExcell() throws IOException {
        FileOutputStream file = new FileOutputStream("DSKhachHang.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("name");
        XSSFRow row;
        XSSFCell cellA; // 0
        XSSFCell cellB; // 1
        XSSFCell cellC; // 2
        XSSFCell cellD; // 3
        int j = 0;
        //kh_dao = new KhachHang_DAO();
        kh_dao = new KhachHangImpl();
        List<KhachHang> list = new ArrayList<KhachHang>();
        list = kh_dao.getalltbKhachHang();
        row = sheet.createRow(j++);
        String[] headers = {"Mã khách hàng", "Tên Khách Hàng", "SĐT", "Giới tính"};
        for (int i = 0; i <= 3; i++) {
            cellA = row.createCell(i);
            cellA.setCellValue(headers[i]);
        }
        for (KhachHang c : list) {
            row = sheet.createRow(j++);

            cellA = row.createCell(0);
            cellA.setCellValue(c.getMaKH());

            cellB = row.createCell(1);
            cellB.setCellValue(c.getTenKH());

            cellC = row.createCell(2);
            cellC.setCellValue(c.getSdt());

            cellD = row.createCell(3);
            String gioiTinh = c.isGioitinh() ? "Nam" : "Nữ";
            cellD.setCellValue(gioiTinh);

        }
        workbook.write(file);
        workbook.close();
        file.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnExcel;
    private gui.swing.RadiusButton btnRefesh;
    private gui.swing.RadiusButton btnThem;
    private gui.swing.RadiusButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTim;
    private javax.swing.JLabel lblTong;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlTraCuu;
    private javax.swing.JScrollPane scr;
    private javax.swing.JTable tblDSKH;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
