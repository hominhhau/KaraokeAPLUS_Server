package gui.dialog;

import Interface.*;
import Interface.impl.ChiTietHoaDonPhongImpl;
import Interface.impl.HoaDonImpl;
import Interface.impl.KhachHangImpl;
import Interface.impl.PhongHatImpl;
import connectDB.ConnectDB;
import dao.*;
import entity.*;

import gui.event.EventRefreshRoom;
import gui.form.Form_Login;
import gui.form.Form_QuanLyDatPhong;
import gui.swing.CustomJOptionPane;
import gui.swing.CustomPanel;
import gui.swing.notification.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HO MINH HAU
 */
public class DL_KiemTravsAddKH extends javax.swing.JDialog {

    private KhachHangDao kh_dao;
    private PhongHatDao ph_dao;
    private HoaDonDao hd_dao;
    private ChiTietHoaDonPhongDao cthdp_dao;
    private ChiTietHoaDonDichVuDao cthddv_dao;
    private static String makhAuto;
    private static String mahdAuto;
    private KhachHang khachHang;

    /**
     * Creates new form DL_KiemTravsAddKH
     */
    public DL_KiemTravsAddKH(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConnectDB db = ConnectDB.getInstance();
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            // nếu nhấn enter thì sẽ kiểm tra sdt
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnKiemTra.doClick();
                }
            }
        });

        try {
            db.connect();
        } catch (SQLException ex) {

        }

    }

    public static void setMaKHPublic(String makh) {
        makhAuto = makh;
    }

    public static String getMaKHPublic() {
        return makhAuto;
    }

    public static void setMaHDPublic(String mahd) {
        mahdAuto = mahd;
    }

    public static String getMaHDPublic() {
        return mahdAuto;
    }

    //    public static String setDataPhongDat(String maPhong, String maHD){
//       
//    }
    public String phatSinhMaKH() {

        List<KhachHang> khs = kh_dao.getalltbKhachHang();
        String temp = null;
        for (KhachHang kh : khs) {
            temp = kh.getMaKH();
        }
        int count = laySoDuoi(temp);

        count++;

        String maVe = String.format("KH%03d", count);

        return maVe;
    }

    public int laySoDuoi(String str) {
        if (str == null) {
            return 0;
        } else {
            String numberStr = str.substring(2); // loại bỏ ký tự "KH"
            int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
            return number;
        }
    }

    public String phatSinhMaHD() {

        List<HoaDon> khs = hd_dao.getalltbHoaDon();
        String temp = null;
        for (HoaDon hd : khs) {
            temp = hd.getMaHD();
        }
        int count = laySoDuoi(temp);

        count++;

        String maVe = String.format("HD%03d", count);

        return maVe;
    }

    public int laySoDuoiHD(String str) {
        if (str == null) {
            return 0;
        } else {
            String numberStr = str.substring(2); // loại bỏ ký tự "HD"
            int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
            return number;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlThongTinKH = new javax.swing.JPanel();
        lblThongTinKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        btnKiemTra = new gui.swing.RadiusButton();
        btnThuePhong = new gui.swing.RadiusButton();
        txtSDT = new gui.swing.CustomJTextField();
        txtTenKH = new gui.swing.CustomJTextField();
        lblThongBao = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        btnExit = new gui.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlThongTinKH.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTinKH.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlThongTinKH.setForeground(new java.awt.Color(204, 204, 204));

        lblThongTinKH.setText("THÔNG TIN KHÁCH HÀNG");
        lblThongTinKH.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblThongTinKH.setForeground(new java.awt.Color(41, 173, 86));

        lblTenKH.setText("Tên khách hàng:");
        lblTenKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lblSDT.setText("Số điện thoại:");
        lblSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnKiemTra.setText("Kiểm tra");
        btnKiemTra.setBackground(new java.awt.Color(166, 208, 238));
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        btnThuePhong.setText("Thuê Phòng");
        btnThuePhong.setBackground(new java.awt.Color(41, 173, 86));
        btnThuePhong.setForeground(new java.awt.Color(255, 255, 255));
        btnThuePhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuePhongActionPerformed(evt);
            }
        });

        lblThongBao.setText("không tìm thấy thông tin khách hàng ");
        lblThongBao.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N

        lblGioiTinh.setText("Giới Tính:");
        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        buttonGroup1.add(radNam);
        radNam.setText("Nam");
        radNam.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(radNu);
        radNu.setText("Nữ");
        radNu.setBackground(new java.awt.Color(255, 255, 255));
        radNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNuActionPerformed(evt);
            }
        });

        btnExit.setText("  X  ");
        btnExit.setBackground(new java.awt.Color(255, 0, 51));
        btnExit.setBorder(null);
        btnExit.setEffectColor(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinKHLayout = new javax.swing.GroupLayout(pnlThongTinKH);
        pnlThongTinKH.setLayout(pnlThongTinKHLayout);
        pnlThongTinKHLayout.setHorizontalGroup(
                pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblTenKH)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblGioiTinh)
                                                .addGap(62, 62, 62)
                                                .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(48, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblThongBao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(lblThongTinKH)
                                                .addGap(102, 102, 102))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                                                .addComponent(btnThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37))))
        );
        pnlThongTinKHLayout.setVerticalGroup(
                pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(lblThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSDT)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(lblThongBao)
                                .addGap(18, 18, 18)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTenKH)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblGioiTinh)
                                        .addComponent(radNam)
                                        .addComponent(radNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlThongTinKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        String sdt = txtSDT.getText();

        if (validDateSDT()) {
            kh_dao = new KhachHangImpl();
            ArrayList<KhachHang> kh = kh_dao.getKhachHangTheoSdtKH(sdt);

            if (!kh.isEmpty()) {
                lblThongBao.setText("Tìm thấy thông tin khách hàng");
                khachHang = kh.get(0);

                // Set the customer's information
                setMaKHPublic(khachHang.getMaKH());
                txtTenKH.setText(khachHang.getTenKH());

                // Set the gender radio buttons based on khachHang.isGioitinh()
                radNam.setSelected(khachHang.isGioitinh());
                radNu.setSelected(!khachHang.isGioitinh());
            } else {
                lblThongBao.setText("Nhập lại số điện thoại hoặc thêm khách hàng bên dưới");

//                btnThuePhong.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                    }
//                });
            }
        } else {
            Notification noti = new Notification(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                    Notification.Type.WARNING,
                    Notification.Location.TOP_RIGHT,
                    "Số điện thoại không hợp lệ"
            );
            noti.showNotification();
        }

    }//GEN-LAST:event_btnKiemTraActionPerformed

    // valid data
    public Boolean validDateSDT() {
        String sdt = txtSDT.getText();
        if (sdt.length() != 10 || !sdt.matches("^0[0-9]{9}$")) {
            return false;
        }
        return true;
    }

    public Boolean validDateTenKH() {
        String tenKH = txtTenKH.getText();
        if (!(tenKH.length() > 0 && tenKH.length() < 50)) {
            return false;
        }
        return true;
    }

    public HoaDon revertHoaDonFromTextfields() {
        NhanVien nhanVienDangNhap = Form_Login.getNhanVienDangNhap();
        String maHD = phatSinhMaHD();
        setMaHDPublic(maHD); // đừng xóa dòng này . công dụng lấy ra được maHD mới phát sinh
        LocalDate ngayLapHD = LocalDate.now();
        String maKH = getMaKHPublic();
        String maNV = "";
        if (nhanVienDangNhap != null) {
            maNV = nhanVienDangNhap.getMaNV();
        } else {
            maNV = "NV001";
        }
        // tôi nghĩ là lỗi ở đây
        double tongTien = 0;
        String maKM = null;
//        HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), new KhuyenMai(null), tongTien);
        HoaDon hd = new HoaDon(maHD, ngayLapHD, new KhachHang(maKH), new NhanVien(maNV), null, tongTien);

        return hd;

    }

    public ChiTietHoaDonPhong revertChiTietHoaDonPhongText() {
        String maHD = getMaHDPublic();
        Form_QuanLyDatPhong ph = new Form_QuanLyDatPhong();
        ph_dao = new PhongHatImpl();
        String maPhong = ph.getRoomSelected();
        double gia = 0;
        LocalDateTime gioVao = LocalDateTime.now();
        LocalDateTime gioRa = gioVao;
        String maGiamGia = null;

        ChiTietHoaDonPhong cthdp = new ChiTietHoaDonPhong(new HoaDon(maHD), new PhongHat(maPhong), gia, gioVao, gioRa, maGiamGia);
        return cthdp;
    }


    private void btnThuePhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuePhongActionPerformed
        if (khachHang != null) {
            Form_QuanLyDatPhong ph = new Form_QuanLyDatPhong();
            ph_dao = new PhongHatImpl();
            ph_dao.updateTinhTrangPhong(ph.getRoomSelected(), "Dang su dung");
            hd_dao = new HoaDonImpl();
            //    HoaDon hd = new HoaDon("HD008", LocalDate.now(), new KhachHang("KH008"), new NhanVien("NV001"), null, 1000000);
            //
            //                    if (hoaDonDao.createHoaDon(hd)) {
            //                        System.out.println("Thêm hóa đơn thành công");
            //                    } else {
            //                        System.out.println("Thêm hóa đơn thất bại");
            //                    }
//            hd_dao.createHoaDon(revertHoaDonFromTextfields());
//            System.out.println(revertHoaDonFromTextfields().toString());
            if( hd_dao.createHoaDon(revertHoaDonFromTextfields())){
                System.out.println("Tạo hóa đơn thành công");
            }else{
                System.out.println("Tạo hóa đơn không thành công");
            }

            cthdp_dao = new ChiTietHoaDonPhongImpl();
            cthdp_dao.createChiTietHoaDonPhong(revertChiTietHoaDonPhongText());
            this.dispose();
            ph.openDL_ChonDichVu();
        } else if (validDateTenKH()) {
            kh_dao = new KhachHangImpl();
            String maKH = phatSinhMaKH();
            setMaKHPublic(maKH);
            String tenKH = txtTenKH.getText();
            String sdt = txtSDT.getText();
            Boolean gioiTinh = radNam.isSelected();
            KhachHang addKh = new KhachHang(maKH, tenKH, sdt, gioiTinh);
            if (kh_dao.addCustomer(addKh)) {
                Notification noti = new Notification(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        Notification.Type.SUCCESS,
                        Notification.Location.TOP_RIGHT,
                        "Thuê phòng thành công"
                );
                noti.showNotification();
            } else {
                Notification noti = new Notification(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        Notification.Type.WARNING,
                        Notification.Location.TOP_RIGHT,
                        "Thêm khách hàng không thành công"
                );
                noti.showNotification();
            }

            Form_QuanLyDatPhong ph = new Form_QuanLyDatPhong();
            ph_dao = new PhongHatImpl();
            ph_dao.updateTinhTrangPhong(ph.getRoomSelected(), "Dang su dung");
            hd_dao = new HoaDonImpl();
            hd_dao.createHoaDon(revertHoaDonFromTextfields());
            cthdp_dao = new ChiTietHoaDonPhongImpl();
            cthdp_dao.createChiTietHoaDonPhong(revertChiTietHoaDonPhongText());
            this.dispose();
            System.out.println("ma hd" + getMaHDPublic());

            ph.openDL_ChonDichVu();
        } else {

            Notification noti = new Notification(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                    Notification.Type.WARNING,
                    Notification.Location.TOP_RIGHT,
                    "Tên khách hàng không hợp lệ"
            );
            noti.showNotification();
        }


    }//GEN-LAST:event_btnThuePhongActionPerformed

    private void radNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radNuActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.Button btnExit;
    private gui.swing.RadiusButton btnKiemTra;
    private gui.swing.RadiusButton btnThuePhong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblThongTinKH;
    private javax.swing.JPanel pnlThongTinKH;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private gui.swing.CustomJTextField txtSDT;
    private gui.swing.CustomJTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
