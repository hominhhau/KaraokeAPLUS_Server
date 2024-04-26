
package gui.dialog;

import Interface.*;
import Interface.impl.*;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import connectDB.ConnectDB;
import dao.*;
import entity.*;
import gui.form.Form_QuanLyDatPhong;
import gui.swing.notification.Notification;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author HO MINH HAU
 */
public class DL_TraPhongVsThanhToan extends javax.swing.JDialog {
    private HoaDonDao hd_dao;
    private ChiTietHoaDonPhongDao cthdp_dao;
    private ChiTietHoaDonDichVuDao cthddv_dao;
    private KhachHangDao kh_dao;
    private PhongHatDao ph_dao;
    private Float gia;
    private Float thanhTien;
    private LocalDateTime gioVao;
    private static String maHDDSD;
    private Double tongTien;
    private KhuyenMaiDao km_dao;
    private double tienGiam = 0.0;
    private double tongTienThanhToan = 0.0;
    private double thue = 0.05;
    private String maKM = "";
    private String moTa = "";


    /**
     * Creates new form DL_TraPhongVsThanhToan
     */
    public DL_TraPhongVsThanhToan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        hd_dao = new HoaDonImpl();
        cthdp_dao = new ChiTietHoaDonPhongImpl();
        cthddv_dao = new ChiTietHoaDonDichVuImpl();
        kh_dao = new KhachHangImpl();
        ph_dao = new PhongHatImpl();
        tinhTien();
        setThanhToan();

    }

    public String getMaHDDSD() {
        return maHDDSD;
    }

    public void setMaHDDSD(String maHDDSD) {
        this.maHDDSD = maHDDSD;
    }

    public void inHoaDon() {

        try (PdfWriter writer = new PdfWriter("src/main/resources/HoaDonPDF/HoaDon" + maHDDSD + ".pdf")) {
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

            // Thiết kế giống với DL_TraPhongVsThanhToan

            document.add(new Paragraph("HOA DON THANH TOAN").setFont(font));
            document.add(new Paragraph("KARAOKEAPLUS").setFont(font));
            document.add(new Paragraph("Dia chi: Go Vap, TPHCM").setFont(font));
            document.add(new Paragraph("----------------------------------------------------------").setFont(font));
            document.add(new Paragraph("Ten khach hang: " + lblTenKH.getText()).setFont(font));
            document.add(new Paragraph("So dien thoai: " + lblSDT.getText()).setFont(font));
            document.add(new Paragraph("Gio nhan phong: " + lblGiovao.getText()).setFont(font));
            document.add(new Paragraph("Gio tra phong: " + lblGioRa.getText()).setFont(font));

            // Tạo bảng
            Table table = new Table(4);
            table.addCell("STT").setFont(font);
            table.addCell("Ten").setFont(font);
            table.addCell("Thoi gian / So luong").setFont(font);
            table.addCell("Thanh tien").setFont(font);

            for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
                table.addCell(tblHoaDon.getValueAt(i, 0).toString()).setFont(font);
                table.addCell(tblHoaDon.getValueAt(i, 1).toString()).setFont(font);
                table.addCell(tblHoaDon.getValueAt(i, 2).toString()).setFont(font);
                table.addCell(tblHoaDon.getValueAt(i, 3).toString()).setFont(font);
            }

            document.add(table);
            document.add(new Paragraph("----------------------------------------------------------").setFont(font));

            document.add(new Paragraph("Tong tien: " + lblTongTien.getText()).setFont(font));
            document.add(new Paragraph("Khuyen mai: " + lblMoTaKhuyenMai.getText()).setFont(font));
            document.add(new Paragraph("Tien giam: " + lblTienGiam.getText()).setFont(font));
            document.add(new Paragraph("VAT: " + jLabel7.getText()).setFont(font));
            document.add(new Paragraph("Tong tien can thanh toan: " + lblTongTienThanhtoan.getText()).setFont(font));

            document.close();

            // Mở file PDF trên desktop
            File file = new File("src/main/resources/HoaDonPDF/HoaDon" + maHDDSD + ".pdf");
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }
            } else {
                System.out.println("File is not exists!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void CheckKhuyenMai() {
        // Lấy ra thời gian hiện tại để so sánh với thời gian khuyến mãi
        LocalDate now = LocalDate.now();

        // Lấy ra danh sách khuyến mãi từ database hoặc từ nguồn dữ liệu nào đó
        km_dao = new KhuyenMaiImpl();
        ArrayList<KhuyenMai> km = km_dao.getAllKhuyenMai();

        // Kiểm tra từng khuyến mãi trong danh sách
        for (KhuyenMai khuyenMai : km) {
            // lấy ra khuyến mãi mà thời gian hiện tại nằm trong khoảng thời gian khuyến mãi
            if (now.isAfter(khuyenMai.getGioBatDau()) && now.isBefore(khuyenMai.getGioKetThuc()) && khuyenMai.getMaKM() != null) {
                // chuyển thành chuỗi ngăn cách bỏi , và loại bỏ chữ null
                maKM += khuyenMai.getMaKM() + ",";
                tienGiam += (khuyenMai.getPhanTram() / 100);
                moTa += khuyenMai.getMoTa() + ",";

            }

        }
        lblMoTaKhuyenMai.setText(moTa);

    }

    public void tinhTien() {
        //check connect
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//            if (db != null) {

                DL_PhongDangSuDung dialog = new DL_PhongDangSuDung(new javax.swing.JFrame(), true);
                String maHD = dialog.getMaHDPDSD();
                setMaHDDSD(maHD);

                Form_QuanLyDatPhong frmPH = new Form_QuanLyDatPhong();

                PhongHat ph = ph_dao.getPhongHatByMaPhong(frmPH.getRoomSelected());
                if (ph.getLoaiPhong().getMaLoaiPhong().equals("LP001")) {
                    gia = 100000f;
                } else {
                    gia = 60000f;
                }

                ChiTietHoaDonPhong ct = cthdp_dao.getChiTietHoaDonPhongTheoMaHD(maHD, frmPH.getRoomSelected());
                gioVao = ct.getGioVao();
                LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
                if (ct.getGioVao().isBefore(oneHourAgo)) {
                    long durationInMinutes = Duration.between(gioVao, LocalDateTime.now()).toMinutes();
                    thanhTien = (durationInMinutes / 60.0f) * gia;
                } else {
                    thanhTien = gia;
                }

                cthdp_dao.updateGioRaVsGia(maHD, LocalDateTime.now(), thanhTien, frmPH.getRoomSelected());

//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void setThanhToan() {
        //check connect
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//            if (db != null) {
                HoaDon hd = hd_dao.getHoaDonTheoMaHD(maHDDSD);
//                db.connect();
                KhachHang loadKH = kh_dao.findCustomer(hd.getKhachHang().getMaKH());

                lblTenKH.setText(loadKH.getTenKH());
                lblSDT.setText(loadKH.getSdt());
                Form_QuanLyDatPhong frmPHforctp = new Form_QuanLyDatPhong();
                ChiTietHoaDonPhong ct = cthdp_dao.getChiTietHoaDonPhongTheoMaHD(maHDDSD, frmPHforctp.getRoomSelected());
                ChiTietHoaDonDV ctdv = cthddv_dao.getChiTietHoaDonDVTheoMaHD(maHDDSD, frmPHforctp.getRoomSelected());

                // Lấy giờ vào
                int gioVao = ct.getGioVao().getHour();
                int phutVao = ct.getGioVao().getMinute();
                int gioRa = ct.getGioRa().getHour();
                int phutRa = ct.getGioRa().getMinute();

                lblGiovao.setText(String.format("%02d:%02d", gioVao, phutVao));
                lblGioRa.setText(String.format("%02d:%02d", gioRa, phutRa));

                //loaddata in table
                cthdp_dao = new ChiTietHoaDonPhongImpl();
                cthddv_dao = new ChiTietHoaDonDichVuImpl();
                int i = 1;

                DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
                model.setRowCount(0);
                for (ChiTietHoaDonPhong cthdp : cthdp_dao.getalltbChiTietHoaDonPhong()) {
                    if (cthdp.getHoaDon().getMaHD().equals(maHDDSD) && cthdp.getPhongHat().getMaPhong().equals(frmPHforctp.getRoomSelected())) {
                        int gioVaoHour = cthdp.getGioVao().getHour();
                        int gioVaoMinute = cthdp.getGioVao().getMinute();
                        int gioRaHour = cthdp.getGioRa().getHour();
                        int gioRaMinute = cthdp.getGioRa().getMinute();

                        // Calculate duration in minutes
                        int durationMinutes = (gioRaHour - gioVaoHour) * 60 + (gioRaMinute - gioVaoMinute);

                        // If duration is less than 1 hour, set it to 1 hour
                        if (durationMinutes < 60) {
                            durationMinutes = 60;
                        }

                        // Calculate hours and minutes for display
                        int displayHours = durationMinutes / 60;
                        int displayMinutes = durationMinutes % 60;

                        String thoigian = gioVaoHour + ":" + gioVaoMinute + " - " + gioRaHour + ":" + gioRaMinute;
                        String displayTime = displayHours + " giờ " + displayMinutes + " phút";

                        model.addRow(new Object[]{
                                i++, cthdp.getPhongHat().getMaPhong(), displayTime, cthdp.getGia()
                        });
                    }
                }
                for (ChiTietHoaDonDV cthddv : cthddv_dao.getalltbChiTietHoaDonDV()) {
                    if (cthddv.getHoaDon().getMaHD().equals(maHDDSD) && cthddv.getPhongHat().getMaPhong().equals(frmPHforctp.getRoomSelected())) {
                        model.addRow(new Object[]{
                                i++, cthddv.getMatHang().getMaMH(), cthddv.getSoLuong(), cthddv.getGia()

                        });
                    }
                }

                //tinh tong tien
                tongTien = 0.0;
                for (int j = 0; j < tblHoaDon.getRowCount(); j++) {
                    tongTien += Double.parseDouble(tblHoaDon.getValueAt(j, 3).toString());
                }
                CheckKhuyenMai();
                Double Giam = tongTien * tienGiam;
                Double tien = tongTien - Giam;
                Double tinhThue = tien * thue;

                tongTienThanhToan = tien + tinhThue;
                // Format the total amount to VND currency
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String formattedTongTien = currencyFormat.format(tongTien);
                String formattedTienGiam = currencyFormat.format(Giam);
                String formattedTongTienThanhToan = currencyFormat.format(tongTienThanhToan);


                lblTongTien.setText(formattedTongTien);

                lblTienGiam.setText(formattedTienGiam);
                lblTongTienThanhtoan.setText(formattedTongTienThanhToan);

//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCover = new javax.swing.JPanel();
        lblThanhToan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblGiovao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblGioRa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTienGiam = new javax.swing.JLabel();
        btnThanhToan = new gui.swing.RadiusButton();
        jLabel11 = new javax.swing.JLabel();
        lblTongTienThanhtoan = new javax.swing.JLabel();
        cbInHoaDon = new javax.swing.JCheckBox();
        lblMoTaKhuyenMai = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlCover.setBackground(new java.awt.Color(255, 255, 255));
        pnlCover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblThanhToan.setForeground(new java.awt.Color(41, 173, 86));
        lblThanhToan.setText("THANH TOÁN");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên khách hàng:");

        lblTenKH.setText("Hồ Minh Hậu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số điện thoại:");

        lblSDT.setText("0585576500");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Giờ nhận phòng:");

        lblGiovao.setText("11h30");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giờ trả phòng");

        lblGioRa.setText("11h30");

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "STT", "Tên", "Thời gian/ Số lượng", "Thành tiền"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblHoaDon.setRowHeight(30);
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tổng tiền:");

        lblTongTien.setText("40000");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Khuyến mãi :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tiền giảm:");

        lblTienGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTienGiam.setText("0");

        btnThanhToan.setBackground(new java.awt.Color(41, 173, 86));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("Tổng tiền cần thanh toán:");

        lblTongTienThanhtoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTienThanhtoan.setForeground(new java.awt.Color(204, 51, 0));
        lblTongTienThanhtoan.setText("10000000");

        cbInHoaDon.setSelected(true);
        cbInHoaDon.setText("Xuất hóa đơn");
        cbInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInHoaDonActionPerformed(evt);
            }
        });

        lblMoTaKhuyenMai.setText("không có");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("VAT:");

        jLabel7.setText("5%");

        javax.swing.GroupLayout pnlCoverLayout = new javax.swing.GroupLayout(pnlCover);
        pnlCover.setLayout(pnlCoverLayout);
        pnlCoverLayout.setHorizontalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGap(194, 194, 194)
                                                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblTenKH))
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblGiovao, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(33, 33, 33)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblGioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 6, Short.MAX_VALUE))
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                                                        .addComponent(cbInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                                                        .addComponent(jLabel11)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(lblTongTienThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(26, 26, 26))))
                                                .addGap(25, 25, 25))
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel8)
                                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(lblMoTaKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlCoverLayout.setVerticalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(lblTenKH)
                                        .addComponent(jLabel3)
                                        .addComponent(lblSDT))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(lblGiovao)
                                        .addComponent(jLabel5)
                                        .addComponent(lblGioRa))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(lblTongTien))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(lblMoTaKhuyenMai))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(lblTienGiam))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(lblTongTienThanhtoan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbInHoaDon))
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInHoaDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        //check connect
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//            if (db != null) {

                HoaDon hd = hd_dao.getHoaDonTheoMaHD(maHDDSD);
//                db.connect();
                // check if khach hang da thanh toan
                if (hd.getTongTien() == 0) {
                    hd_dao.updateTongTien(maHDDSD, tongTien, maKM);
                } else {
                    hd_dao.updateTongTien(maHDDSD, tongTien + hd.getTongTien(), maKM);
                }
//                Notification noti = new Notification(
//                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
//                        Notification.Type.SUCCESS,
//                        Notification.Location.TOP_RIGHT,
//                        "Thanh toán thành công"
//                );
//                noti.showNotification();
                this.dispose();
//            }
            // bắt sự kiện nếu cbInHoaDon được check thì in hóa đơn
            if (cbInHoaDon.isSelected()) {
                inHoaDon();
            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

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
            java.util.logging.Logger.getLogger(DL_TraPhongVsThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_TraPhongVsThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_TraPhongVsThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_TraPhongVsThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_TraPhongVsThanhToan dialog = new DL_TraPhongVsThanhToan(new javax.swing.JFrame(), true);
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
    private gui.swing.RadiusButton btnThanhToan;
    private javax.swing.JCheckBox cbInHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGioRa;
    private javax.swing.JLabel lblGiovao;
    private javax.swing.JLabel lblMoTaKhuyenMai;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienGiam;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienThanhtoan;
    private javax.swing.JPanel pnlCover;
    private javax.swing.JTable tblHoaDon;
    // End of variables declaration//GEN-END:variables
}
