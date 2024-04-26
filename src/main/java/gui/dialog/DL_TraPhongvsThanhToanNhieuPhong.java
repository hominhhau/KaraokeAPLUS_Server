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
import gui.swing.notification.Notification;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
public class DL_TraPhongvsThanhToanNhieuPhong extends javax.swing.JDialog {


    private HoaDonDao hd_dao;
    private ChiTietHoaDonPhongDao cthdp_dao;
    private ChiTietHoaDonDichVuDao cthddv_dao;
    private KhachHangDao kh_dao;
    private PhongHatDao ph_dao;
    private Float gia;
    private Double tien;
    private Float thanhTien;
    private LocalDateTime gioVao;
    private KhachHang khachHang;
    private MatHangDao mh_dao;

    private String maHD;


    private Double tongTien;
    private KhuyenMaiDao km_dao;
    private double tienGiam = 0.0;
    private double tongTienThanhToan = 0.0;
    private double thue = 0.05;
    private String maKM = "";
    private String mota = "";
    private ArrayList<String> dsHD = new ArrayList<>();
    private String tenKH;
    private String sdtKH;
    private String formattedTongTien;
    private String formattedTienGiam;
    private String formattedTongTienThanhToan;


    public DL_TraPhongvsThanhToanNhieuPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        tinhTien();

        getData();


    }

    public void tinhTien(String maHD) {
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
            hd_dao = new HoaDonImpl();
//            DL_ThanhToanNhieuphong dl = new DL_ThanhToanNhieuphong(new javax.swing.JFrame(), true);
//            maHD = dl.getMaHDDSD();
            HoaDon hd = hd_dao.getHoaDonTheoMaHD(maHD);

//            db.connect();
            kh_dao = new KhachHangImpl();
            KhachHang loadKH = kh_dao.findCustomer(hd.getKhachHang().getMaKH());
            lblTenKH.setText(loadKH.getTenKH());
            lblSDT.setText(loadKH.getSdt());

            // load lên table
            kh_dao = new KhachHangImpl();
            ArrayList<KhachHang> kh = kh_dao.getKhachHangTheoSdtKH(loadKH.getSdt());

            if (!kh.isEmpty()) {
                khachHang = kh.get(0);
                hd_dao = new HoaDonImpl();
                ArrayList<HoaDon> hd1 = hd_dao.getHoaDonTheoMaKH(khachHang.getMaKH());
//                setMaHDDSD(hd.get(0).getMaHD());
                cthdp_dao = new ChiTietHoaDonPhongImpl();
                for (HoaDon hoaDon : hd1) {
//                    db.connect();
                    ArrayList<ChiTietHoaDonPhong> dsCTHDP = cthdp_dao.getAllTheMaHDArray(hoaDon.getMaHD());
                    for (ChiTietHoaDonPhong cthdp : dsCTHDP) {
                        if (cthdp.getGia() == 0) {
                           ph_dao = new PhongHatImpl();
//                            db.connect();
                            PhongHat addph = ph_dao.getPhongHatByMaPhong(cthdp.getPhongHat().getMaPhong());
                            if (addph.getLoaiPhong().getMaLoaiPhong().equals("LP001")) {
                                gia = 100000f;
                            } else {
                                gia = 60000f;
                            }
//                            ChiTietHoaDonPhong ct = cthdp_dao.getChiTietHoaDonPhongTheoMaHD(maHD, cthdp.getPhongHat().getMaPhong());
                            gioVao = cthdp.getGioVao();
                            LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
                            if (cthdp.getGioVao().isBefore(oneHourAgo)) {
                                long durationInMinutes = Duration.between(gioVao, LocalDateTime.now()).toMinutes();
                                thanhTien = (durationInMinutes / 60.0f) * gia;
                            } else {
                                thanhTien = gia;
                            }
                            cthdp_dao.updateGioRaVsGia(maHD, LocalDateTime.now(), thanhTien, cthdp.getPhongHat().getMaPhong());
                        }
                    }
                }
            }

//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void getData() {
        int i = 1;
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
            hd_dao = new HoaDonImpl();
            DL_ThanhToanNhieuphong dl = new DL_ThanhToanNhieuphong(new javax.swing.JFrame(), true);
            String maHD = dl.getMaHDDSD();
            HoaDon hd = hd_dao.getHoaDonTheoMaHD(maHD);

//
//            db.connect();
            kh_dao = new KhachHangImpl();
            KhachHang loadKH = kh_dao.findCustomer(hd.getKhachHang().getMaKH());
            lblTenKH.setText(loadKH.getTenKH());
            lblSDT.setText(loadKH.getSdt());
            tenKH = loadKH.getTenKH();
            sdtKH = loadKH.getSdt();


            // load lên table
            kh_dao = new KhachHangImpl();
            ArrayList<KhachHang> kh = kh_dao.getKhachHangTheoSdtKH(loadKH.getSdt());

            if (!kh.isEmpty()) {
                khachHang = kh.get(0);
                hd_dao = new HoaDonImpl();
                ArrayList<HoaDon> hd1 = hd_dao.getHoaDonTheoMaKH(khachHang.getMaKH());
//                setMaHDDSD(hd.get(0).getMaHD());
                cthdp_dao = new ChiTietHoaDonPhongImpl();
                for (HoaDon hoaDon : hd1) {
                    ArrayList<ChiTietHoaDonPhong> dsCTHDP = cthdp_dao.getAllTheMaHDArray(hoaDon.getMaHD());
                    ArrayList<ChiTietHoaDonPhong> dsCTHDP2 = new ArrayList<>();
                    for (ChiTietHoaDonPhong cthdp : dsCTHDP) {
                        if (dsCTHDP.size() > 1) {
                            dsCTHDP2.add(cthdp);
                        }
                    }
                    for (ChiTietHoaDonPhong cthdp : dsCTHDP2) {
                        if (cthdp.getGia() == 0) {
                            tinhTien(cthdp.getHoaDon().getMaHD());
                            dsHD.add(cthdp.getHoaDon().getMaHD());
                            // cập nhật lại cthdp
//                            db.connect();
                            cthdp = cthdp_dao.getChiTietHoaDonPhongTheoMaHD(hoaDon.getMaHD(), cthdp.getPhongHat().getMaPhong());

                    ph_dao = new PhongHatImpl();
//                            db.connect();
                            PhongHat addph = ph_dao.getPhongHatByMaPhong(cthdp.getPhongHat().getMaPhong());

                            if (cthdp.getHoaDon().getMaHD().equals(hoaDon.getMaHD()) && cthdp.getPhongHat().getMaPhong().equals(addph.getMaPhong())) {
                                int gioVaoHour = cthdp.getGioVao().getHour();
                                int gioVaoMinute = cthdp.getGioVao().getMinute();
                                int gioRaHour = cthdp.getGioRa().getHour();
                                int gioRaMinute = cthdp.getGioRa().getMinute();
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


                                DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
                                model.addRow(new Object[]{
                                        i++, addph.getTenPhong(), displayTime, cthdp.getGia()
                                });
                                cthddv_dao = new ChiTietHoaDonDichVuImpl();
                                ArrayList<ChiTietHoaDonDV> dsCTHDDV = cthddv_dao.getAllTheMaHDDVforRoomArray(hoaDon.getMaHD(), cthdp.getPhongHat().getMaPhong());
                                for (ChiTietHoaDonDV cthddv : dsCTHDDV) {
                                    mh_dao = new MatHangImpl();
                                    MatHang addmh = mh_dao.findMatHang(cthddv.getMatHang().getMaMH());
                                    model.addRow(new Object[]{
                                            i++, addmh.getTenMH(), cthddv.getSoLuong(), cthddv.getGia()
                                    });
                                }
                            }
                        }
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
                formattedTongTien = currencyFormat.format(tongTien);
                formattedTienGiam = currencyFormat.format(Giam);
                formattedTongTienThanhToan = currencyFormat.format(tongTienThanhToan);


                lblTongTien.setText(formattedTongTien);

                lblTienGiam.setText(formattedTienGiam);
                lblTongTienThanhtoan.setText(formattedTongTienThanhToan);


            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
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
            if (now.isAfter(khuyenMai.getGioBatDau()) && now.isBefore(khuyenMai.getGioKetThuc())) {
                // chuyển thành chuỗi ngăn cách bỏi , và loại bỏ chữ null
                maKM += khuyenMai.getMaKM() + ",";
                tienGiam += (khuyenMai.getPhanTram() / 100);
                mota += khuyenMai.getMoTa() + ",";
            }
        }
        lblMoTaKhuyenMai.setText(mota);

    }

    public void inHoaDon() {

        try (PdfWriter writer = new PdfWriter("src/main/resources/HoaDonPDF/HoaDon" + ".pdf")) {
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            // Thiết kế giống với DL_TraPhongVsThanhToan

            document.add(new Paragraph("HOA DON THANH TOAN").setFont(font));
            document.add(new Paragraph("KARAOKEAPLUS").setFont(font));
            document.add(new Paragraph("Dia chi: Go Vap, TPHCM").setFont(font));
            document.add(new Paragraph("----------------------------------------------------------").setFont(font));
            document.add(new Paragraph("Ten khach hang: " + tenKH).setFont(font));
            document.add(new Paragraph("So dien thoai: " + sdtKH).setFont(font));


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

            document.add(new Paragraph("Tong tien: " + formattedTongTien).setFont(font));
            document.add(new Paragraph("Khuyen mai: " + lblMoTaKhuyenMai.getText()).setFont(font));
            document.add(new Paragraph("Tien giam: " + formattedTienGiam).setFont(font));
            document.add(new Paragraph("VAT: " + jLabel7.getText()).setFont(font));
            document.add(new Paragraph("Tong tien can thanh toan: " + formattedTongTienThanhToan).setFont(font));

            document.close();

            // Mở file PDF trên desktop
            File file = new File("src/main/resources/HoaDonPDF/HoaDon" + ".pdf");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCover = new javax.swing.JPanel();
        lblThanhToan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnThanhToan = new gui.swing.RadiusButton();
        jLabel11 = new javax.swing.JLabel();
        lblTongTienThanhtoan = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        lblMoTaKhuyenMai = new javax.swing.JLabel();
        lblTienGiam = new javax.swing.JLabel();
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

        lblTenKH.setText("Hồ Minh Hậu test");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số điện thoại:");

        lblSDT.setText("01585576500");

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
        lblTongTienThanhtoan.setForeground(new java.awt.Color(204, 0, 0));
        lblTongTienThanhtoan.setText("10000000");

        jCheckBox1.setText("Xuất hóa đơn");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        lblMoTaKhuyenMai.setText("noel");

        lblTienGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTienGiam.setText("0");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("VAT:");

        jLabel7.setText("5%");

        javax.swing.GroupLayout pnlCoverLayout = new javax.swing.GroupLayout(pnlCover);
        pnlCover.setLayout(pnlCoverLayout);
        pnlCoverLayout.setHorizontalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addGap(194, 194, 194)
                                                                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addGap(37, 37, 37)
                                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel3))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblTenKH))))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblMoTaKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                                .addGap(157, 157, 157)
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(25, 25, 25))
                                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblTongTienThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16))))
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pnlCoverLayout.setVerticalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(lblTenKH))
                                .addGap(14, 14, 14)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(lblSDT))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
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
                                .addGap(3, 3, 3)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(lblTongTienThanhtoan))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox1))
                                .addContainerGap(12, Short.MAX_VALUE))
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

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        //check connect
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//            if (db != null) {
                // duyệt từng dsHD
                for (String s : dsHD) {
                    System.out.println(s);
                    HoaDon hd = hd_dao.getHoaDonTheoMaHD(s);
                    System.out.println(hd.getTongTien() + hd.getMaHD());
//                    db.connect();
                    // check if khach hang da thanh toan
                    if (hd.getTongTien() == 0) {
                        hd_dao.updateTongTien(s, tongTienThanhToan, maKM);
                        break;
                    } else if (hd.getTongTien() != 0) {
                        hd_dao.updateTongTien(s, tongTienThanhToan + hd.getTongTien(), maKM);
                        break;
                    }


                }
                if (jCheckBox1.isSelected()) {
                    inHoaDon();
                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        this.dispose();


    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(DL_TraPhongvsThanhToanNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_TraPhongvsThanhToanNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_TraPhongvsThanhToanNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_TraPhongvsThanhToanNhieuPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_TraPhongvsThanhToanNhieuPhong dialog = new DL_TraPhongvsThanhToanNhieuPhong(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
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
