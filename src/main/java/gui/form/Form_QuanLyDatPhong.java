package gui.form;

import Interface.DonDatPhongDao;
import Interface.KhachHangDao;
import Interface.PhongHatDao;
import Interface.impl.DonDatPhongImpl;
import Interface.impl.KhachHangImpl;
import Interface.impl.PhongHatImpl;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import connectDB.ConnectDB;
import dao.DonDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.PhongHat_DAO;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.PhongHat;
import gui.component.Room;
import gui.component.panelPopUpMoreDatPhong;
import gui.dialog.*;
import gui.event.EventRefreshRoom;
import gui.model.ModelPhongDatTruoc;
import gui.model.ModelRoom;
import gui.swing.notification.Notification;
import gui.swing.scrollbar.ScrollBarCustom;

import java.awt.Color;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

/**
 * @author HO MINH HAU
 */
public final class Form_QuanLyDatPhong extends javax.swing.JPanel {

    private PhongHatDao ph_dao = new PhongHatImpl() ;
    private KhachHangDao kh_dao = new KhachHangImpl() ;

    private static String selectedRoom;
    private static String selectedRoomName;
    private static String selectedDDP;
    private static String selectedKH;

    private DonDatPhongDao ddp_dao;
    private String loaiPhong;
    private float gia;


    public Form_QuanLyDatPhong() {

        initComponents();
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane4.getViewport().setOpaque(false);
        jScrollPane4.setVerticalScrollBar(new ScrollBarCustom());
        ph_dao = new PhongHatImpl();
        phongTrong();
        phongDangSuDung();
        phongCho();


        // add pnlSearch và pnlRoom vào pnlPhongCho
        pnlPhongCho.add(pnlSearch);
        pnlPhongCho.add(pnlRoom);
        createDatePicker();
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            // nếu nhấn enter thì sẽ kiểm tra sdt
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnSearch.doClick();
                }
            }
        });
    }
    // thêm sự kiên tự refreshRoom sau mỗi 2s

    public static void setRoomSelected(String roomID) {
        selectedRoom = roomID;
    }

    public static String getRoomSelected() {
        return selectedRoom;
    }

    public static void setRoomSelectedName(String roomName) {
        selectedRoomName = roomName;
    }

    public static String getRoomSelectedName() {
        return selectedRoomName;
    }

    public static void setDDPSelected(String ddp) {
        selectedDDP = ddp;
    }

    public static String getDDPSelected() {
        return selectedDDP;
    }

    public static void setKHSelected(String kh) {
        selectedKH = kh;
    }

    public static String getKHSelected() {
        return selectedKH;
    }


    public void addPhongTrong(ModelRoom data) {
        Room room = new Room();
        room.setData(data);
        room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setRoomSelected(data.getRoomId());
                DL_KiemTravsAddKH kiemTraVsAddKH = new DL_KiemTravsAddKH((java.awt.Frame) SwingUtilities.getWindowAncestor((Component) e.getSource()), true);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    kiemTraVsAddKH.setLocationRelativeTo(Form_QuanLyDatPhong.this);
                    kiemTraVsAddKH.setVisible(true);
                    // Add a WindowListener to refresh rooms when the frame is activated
                    if (kiemTraVsAddKH.isVisible() == false) {
                        refreshRooms();
                    }
                }
            }
        });
        MigLayout migLayout = new MigLayout("wrap 4, gapx 100, gapy 80,inset 20", "[grow, fill]");
        pnlPhongTrong.setLayout(migLayout);
        pnlPhongTrong.add(room);
        pnlPhongTrong.revalidate();
        pnlPhongTrong.repaint();
    }

    public void phongTrong() {
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//        } catch (SQLException ex) {
//            Logger.getLogger(Form_QuanLyDatPhong.class.getName()).log(Level.SEVERE, null, ex);
//        }
        // Lấy danh sách phòng có trạng thái "Trong" từ cơ sở dữ liệu
        ArrayList<PhongHat> phongTrongList = ph_dao.getPhongByTinhTrang("Trong");
        // check tên phòng all phòng có trong ds đặt trước
        Icon icon;
        Icon iconPhongThuong = new ImageIcon(getClass().getResource("/icon/microphone.png"));
        Icon iconPhongVip = new ImageIcon(getClass().getResource("/icon/microphonevip.png"));
        // check nếu phòng nào có trong đơn đặt phòng nếu giờ nhận phòng > giờ hiện tại thì ko hiển thị
        ddp_dao = new DonDatPhongImpl();
        ArrayList<DonDatPhong> dsDDP = ddp_dao.getalltbDonDatPhong();
        String maPhongDuocDatTruoc = "";
        List<PhongHat> phongCanLoaiBo = new ArrayList<>();

        for (DonDatPhong ddp : dsDDP) {

            if (ddp.getNgayNhan().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                int gioNhan = ddp.getNgayNhan().getHour();
                int gioDat = LocalDateTime.now().getHour();
                if (!(gioDat - gioNhan >= 6 || gioDat - gioNhan <= -6)) {
                    maPhongDuocDatTruoc = ddp.getPhongHat().getMaPhong();
                    for (PhongHat phong : phongTrongList) {
                        if (phong.getMaPhong().equals(maPhongDuocDatTruoc)) {
                            phongCanLoaiBo.add(phong);

                        }
                    }
                }
            }
        }
        phongTrongList.removeAll(phongCanLoaiBo);
        // Thêm từng phòng có trạng thái "Trong" vào giao diện
        for (PhongHat phong : phongTrongList) {
            String loaiPhong = phong.getLoaiPhong().getMaLoaiPhong();
            if ("LP002".equals(loaiPhong)) {
                icon = iconPhongThuong;
            } else {
                icon = iconPhongVip;
            }
            addPhongTrong(new ModelRoom(phong.getMaPhong(), phong.getTenPhong(), icon, null, null));

        }
    }
//        // Thêm từng phòng có trạng thái "Trong" vào giao diện
//        for (PhongHat phong : phongTrongList) {
//            String loaiPhong = phong.getLoaiPhong().getMaLoaiPhong();
//            if ("LP002".equals(loaiPhong)) {
//                icon = iconPhongThuong;
//            } else {
//                icon = iconPhongVip;
//            }
//            addPhongTrong(new ModelRoom(phong.getMaPhong(), phong.getTenPhong(), icon,null,null));
//
//        }
//
//
//    }

    private void setLoaiPhong(PhongHat ph) {
        if (ph.getLoaiPhong().getMaLoaiPhong().equals("LP001")) {
            loaiPhong = "VIP";
            gia = 100000f;
        } else {
            loaiPhong = "Thường";
            gia = 60000f;
        }
    }

    public void refreshRooms() {

        pnlDSPhongDatTruoc.removeAll();
        pnlDSPhongDatTruoc.revalidate();
        pnlDSPhongDatTruoc.repaint();
        pnlPhongTrong.removeAll();
        pnlPhongTrong.revalidate();
        pnlPhongTrong.repaint();
        pnlPhongDangSuDung.removeAll();
        pnlPhongDangSuDung.revalidate();
        pnlPhongDangSuDung.repaint();
        pnlPhongCho.removeAll();
        pnlPhongCho.revalidate();
        pnlPhongCho.repaint();
        phongTrong();
        phongDangSuDung();
        phongCho();

    }

    public void addPhongDangSuDung(ModelRoom data) {
        Room room = new Room();
        room.setData(data);

        // Add a MouseListener if needed
        room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setRoomSelected(data.getRoomId());
                setRoomSelectedName(data.getRoomName());
                DL_PhongDangSuDung pdsd = new DL_PhongDangSuDung((java.awt.Frame) SwingUtilities.getWindowAncestor((Component) e.getSource()), true);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    pdsd.setLocationRelativeTo(Form_QuanLyDatPhong.this);
                    pdsd.setVisible(true);
                    if (pdsd.isVisible() == false) {
                        refreshRooms();
                    }
                }

            }
        });
        MigLayout migLayout = new MigLayout("wrap 4, gapx 80, gapy 80, inset 20", "[grow, fill]");
        pnlPhongDangSuDung.setLayout(migLayout);
        pnlPhongDangSuDung.add(room);
        pnlPhongDangSuDung.revalidate();
        pnlPhongDangSuDung.repaint();
    }

    public void phongDangSuDung() {
        // Xóa tất cả phòng trên giao diện trước khi thêm mới
//
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//        } catch (SQLException ex) {
//            Logger.getLogger(Form_QuanLyDatPhong.class.getName()).log(Level.SEVERE, null, ex);
//        }
        // Lấy danh sách phòng có trạng thái "Dang su dung" từ cơ sở dữ liệu
        ArrayList<PhongHat> phongTrongList = ph_dao.getPhongByTinhTrang("Dang su dung");
        Icon icon;
        Icon iconPhongThuong = new ImageIcon(getClass().getResource("/icon/microphone.png"));
        Icon iconPhongVip = new ImageIcon(getClass().getResource("/icon/microphonevip.png"));

        // Thêm từng phòng có trạng thái "Trong" vào giao diện
        for (PhongHat phong : phongTrongList) {
            String loaiPhong = phong.getLoaiPhong().getMaLoaiPhong();
            if ("LP002".equals(loaiPhong)) {
                icon = iconPhongThuong;
            } else {
                icon = iconPhongVip;
            }
//            System.out.println(phong.getTenPhong());
            addPhongDangSuDung(new ModelRoom(phong.getMaPhong(), phong.getTenPhong(), icon, null, null));
        }
    }
    // lưu thông tin phòng vào model sử dụng ở nơi khác


    ////Get the invoice for the room being used

    public void addPhongCho(ModelRoom data) {
        Room room = new Room();
        room.setData(data);


        // Add a MouseListener if needed
        room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    setDDPSelected(data.getMaddp());
                    setKHSelected(data.getMakh());
                    setRoomSelected(data.getRoomId());
                    DL_NhanPhongDatTruoc nhanPhong = new DL_NhanPhongDatTruoc((java.awt.Frame) SwingUtilities.getWindowAncestor((Component) e.getSource()), true);
                    nhanPhong.setLocationRelativeTo(Form_QuanLyDatPhong.this);
                    nhanPhong.setVisible(true);
                    if (nhanPhong.isVisible() == false) {
                        refreshRooms();
                    }

                }

            }
        });
//        MigLayout migLayout = new MigLayout("wrap 4, gapx 100, gapy 80,inset 20", "[grow, fill]");
//        pnlPhongCho.setLayout(migLayout);
//        pnlPhongCho.add(room);
//        pnlPhongCho.revalidate();
//        pnlPhongCho.repaint();
        // load len pnlDSPhongDatTruoc
        MigLayout migLayout = new MigLayout("wrap 4, gapx 100, gapy 80,inset 20", "[grow, fill]");
        pnlDSPhongDatTruoc.setLayout(migLayout);
        pnlDSPhongDatTruoc.add(room);
        pnlDSPhongDatTruoc.revalidate();
        pnlDSPhongDatTruoc.repaint();


    }

    public void phongCho() {
        // Xóa tất cả phòng trên giao diện trước khi thêm mới
//        pnlPhongCho.removeAll();
//        ConnectDB db = ConnectDB.getInstance();
//        try {
//            db.connect();
//        } catch (SQLException ex) {
//            Logger.getLogger(Form_QuanLyDatPhong.class.getName()).log(Level.SEVERE, null, ex);
//        }

        // Lấy danh sách phòng có trạng thái "Cho" từ cơ sở dữ liệu
        ArrayList<PhongHat> phongList = ph_dao.getAllPhongHat();
        Icon icon;
        Icon iconPhongThuong = new ImageIcon(getClass().getResource("/icon/microphone.png"));
        Icon iconPhongVip = new ImageIcon(getClass().getResource("/icon/microphonevip.png"));
        // lấy ra ds phòng được đặt trước
        ddp_dao = new DonDatPhongImpl();
        // Get the current date
        LocalDate ngayDat = LocalDate.now();


// Set thoiGianBatDau to today
        thoiGianBatDau.getDatePicker().setDate(ngayDat);
        ArrayList<DonDatPhong> dsDonDatPhong = ddp_dao.getDonDatPhongTheoNgayNhanPhong(ngayDat);
        // so sánh phongList và dsDonDatPhong lấy ra thông tin các phòng có trong dsDonDatPhong
        for (PhongHat phong : phongList) {
            for (DonDatPhong donDatPhong : dsDonDatPhong) {
                if (phong.getMaPhong().equals(donDatPhong.getPhongHat().getMaPhong())) {
                    String loaiPhong = phong.getLoaiPhong().getMaLoaiPhong();
                    if ("LP002".equals(loaiPhong)) {
                        icon = iconPhongThuong;
                    } else {
                        icon = iconPhongVip;
                    }

                    addPhongCho(new ModelRoom(phong.getMaPhong(), phong.getTenPhong(), icon, donDatPhong.getMaDonDatPhong(), donDatPhong.getKhachHang().getMaKH()));
                }
            }
        }

    }

    //Open DL_ChonDichVu in frame Form_QuanLyDatPhong
    public void openDL_ChonDichVu() {
        DL_ChonDichVu chonDV = new DL_ChonDichVu((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        chonDV.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        chonDV.setVisible(true);
        if (chonDV.isVisible() == false) {
            refreshRooms();
        }
    }

    public void openDL_ThemDV() {
        DL_ThemDV themDV = new DL_ThemDV((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        themDV.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        themDV.setVisible(true);
        if (themDV.isVisible() == false) {
            refreshRooms();
        }
    }

    public void openDL_CheckKH() {
        DL_KiemTravsAddKHforDatPhong checkKH = new DL_KiemTravsAddKHforDatPhong((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        checkKH.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        checkKH.setVisible(true);
        if (checkKH.isVisible() == false) {
            refreshRooms();
        }
    }

    public void openDL_DatPhong() {
        DL_DatPhongTruoc datPhong = new DL_DatPhongTruoc((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        datPhong.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        datPhong.setVisible(true);
        if (datPhong.isVisible() == false) {
            refreshRooms();
        }
    }

    public void openDL_CheckKHThueNhieuPhong() {
        DL_CheckKHforDatNhieuPhong checkKH = new DL_CheckKHforDatNhieuPhong((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        checkKH.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        checkKH.setVisible(true);
        if (checkKH.isVisible() == false) {
            refreshRooms();
        }

    }

    public void openDL_DatNhieuPhong() {
        DL_ChonNhieuPhong datNhieuPhong = new DL_ChonNhieuPhong((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        datNhieuPhong.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        datNhieuPhong.setVisible(true);
        if (datNhieuPhong.isVisible() == false) {
            refreshRooms();
        }
    }

    public void openDL_ThanhToanNhieuPhong() {
        DL_ThanhToanNhieuphong thanhToanNhieuPhong = new DL_ThanhToanNhieuphong((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        thanhToanNhieuPhong.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        thanhToanNhieuPhong.setVisible(true);
        if (thanhToanNhieuPhong.isVisible() == false) {
            refreshRooms();
        }

    }

    public void openDL_ThanhToan() {
        DL_TraPhongVsThanhToan thanhToan = new DL_TraPhongVsThanhToan((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        thanhToan.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        thanhToan.setVisible(true);
        if (thanhToan.isVisible() == false) {
//            pnlPhongDangSuDung.removeAll();
            refreshRooms();
        }
    }

    public void openDL_ThanhToanNhieu() {
        DL_TraPhongvsThanhToanNhieuPhong thanhToanNhieuPhong = new DL_TraPhongvsThanhToanNhieuPhong((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        thanhToanNhieuPhong.setLocationRelativeTo(Form_QuanLyDatPhong.this);
        thanhToanNhieuPhong.setVisible(true);
        if (thanhToanNhieuPhong.isVisible() == false) {
            refreshRooms();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPopUpMoreDatPhong1 = new gui.component.panelPopUpMoreDatPhong();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnlPhongCho = new javax.swing.JPanel();
        pnlSearch = new javax.swing.JPanel();
        pnlRoom = new javax.swing.JPanel();
        pnlCover = new javax.swing.JPanel();
        tabPhongDatTruoc = new gui.swing.tabbed.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlPhongTrong = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlPhongDangSuDung = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlSearch1 = new javax.swing.JPanel();
        txtSearch = new gui.swing.CustomJTextField();
        btnSearch = new gui.swing.RadiusButton();
        btnReloadDSphongDatTruoc = new gui.swing.RadiusButton();
        thoiGianBatDau = new com.github.lgooddatepicker.components.DateTimePicker();
        lblNgayNhanPhong = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnNgayDatPhong = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        pnlDSPhongDatTruoc = new javax.swing.JPanel();
        btnMore = new gui.swing.RadiusButton();
        btnRefresh = new gui.swing.RadiusButton();

        javax.swing.GroupLayout panelPopUpMoreDatPhong1Layout = new javax.swing.GroupLayout(panelPopUpMoreDatPhong1);
        panelPopUpMoreDatPhong1.setLayout(panelPopUpMoreDatPhong1Layout);
        panelPopUpMoreDatPhong1Layout.setHorizontalGroup(
                panelPopUpMoreDatPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        panelPopUpMoreDatPhong1Layout.setVerticalGroup(
                panelPopUpMoreDatPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane3.setBorder(null);

        pnlPhongCho.setBackground(new java.awt.Color(255, 255, 255));

        pnlSearch.setBackground(new java.awt.Color(0, 204, 153));

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
                pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlSearchLayout.setVerticalGroup(
                pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 38, Short.MAX_VALUE)
        );

        pnlRoom.setBackground(new java.awt.Color(0, 153, 102));

        javax.swing.GroupLayout pnlRoomLayout = new javax.swing.GroupLayout(pnlRoom);
        pnlRoom.setLayout(pnlRoomLayout);
        pnlRoomLayout.setHorizontalGroup(
                pnlRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1038, Short.MAX_VALUE)
        );
        pnlRoomLayout.setVerticalGroup(
                pnlRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 533, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPhongChoLayout = new javax.swing.GroupLayout(pnlPhongCho);
        pnlPhongCho.setLayout(pnlPhongChoLayout);
        pnlPhongChoLayout.setHorizontalGroup(
                pnlPhongChoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhongChoLayout.createSequentialGroup()
                                .addComponent(pnlRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pnlPhongChoLayout.setVerticalGroup(
                pnlPhongChoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPhongChoLayout.createSequentialGroup()
                                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 120, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(pnlPhongCho);

        pnlCover.setBackground(new java.awt.Color(255, 255, 255));
        pnlCover.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jScrollPane1.setBorder(null);

        pnlPhongTrong.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPhongTrongLayout = new javax.swing.GroupLayout(pnlPhongTrong);
        pnlPhongTrong.setLayout(pnlPhongTrongLayout);
        pnlPhongTrongLayout.setHorizontalGroup(
                pnlPhongTrongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1044, Short.MAX_VALUE)
        );
        pnlPhongTrongLayout.setVerticalGroup(
                pnlPhongTrongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 697, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlPhongTrong);

        tabPhongDatTruoc.addTab("Phòng trống", jScrollPane1);

        jScrollPane2.setBorder(null);

        pnlPhongDangSuDung.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPhongDangSuDungLayout = new javax.swing.GroupLayout(pnlPhongDangSuDung);
        pnlPhongDangSuDung.setLayout(pnlPhongDangSuDungLayout);
        pnlPhongDangSuDungLayout.setHorizontalGroup(
                pnlPhongDangSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1044, Short.MAX_VALUE)
        );
        pnlPhongDangSuDungLayout.setVerticalGroup(
                pnlPhongDangSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 697, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlPhongDangSuDung);

        tabPhongDatTruoc.addTab("Phòng đang sử dụng", jScrollPane2);

        pnlSearch1.setBackground(new java.awt.Color(255, 255, 255));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReloadDSphongDatTruoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        btnReloadDSphongDatTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadDSphongDatTruocActionPerformed(evt);
            }
        });

        thoiGianBatDau.setMinimumSize(new java.awt.Dimension(200, 15));

        lblNgayNhanPhong.setText("Ngày đặt:  ");

        jLabel1.setText("Tìm :");

        btnNgayDatPhong.setText("Kiểm tra ");
        btnNgayDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayDatPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearch1Layout = new javax.swing.GroupLayout(pnlSearch1);
        pnlSearch1.setLayout(pnlSearch1Layout);
        pnlSearch1Layout.setHorizontalGroup(
                pnlSearch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearch1Layout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(lblNgayNhanPhong)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNgayDatPhong)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                                .addComponent(btnReloadDSphongDatTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        pnlSearch1Layout.setVerticalGroup(
                pnlSearch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlSearch1Layout.createSequentialGroup()
                                .addGroup(pnlSearch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnReloadDSphongDatTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                        .addGroup(pnlSearch1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(pnlSearch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlSearch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel1)
                                                                .addComponent(thoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblNgayNhanPhong)
                                                                .addComponent(btnNgayDatPhong))
                                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        jScrollPane4.setBorder(null);

        pnlDSPhongDatTruoc.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlDSPhongDatTruocLayout = new javax.swing.GroupLayout(pnlDSPhongDatTruoc);
        pnlDSPhongDatTruoc.setLayout(pnlDSPhongDatTruocLayout);
        pnlDSPhongDatTruocLayout.setHorizontalGroup(
                pnlDSPhongDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1044, Short.MAX_VALUE)
        );
        pnlDSPhongDatTruocLayout.setVerticalGroup(
                pnlDSPhongDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 657, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(pnlDSPhongDatTruoc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlSearch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnlSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabPhongDatTruoc.addTab("Phòng đặt trước ", jPanel1);

        btnMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/application.png"))); // NOI18N
        btnMore.setBorder(null);
        btnMore.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoreActionPerformed(evt);
            }
        });

        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCoverLayout = new javax.swing.GroupLayout(pnlCover);
        pnlCover.setLayout(pnlCoverLayout);
        pnlCoverLayout.setHorizontalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabPhongDatTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCoverLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(btnMore, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );
        pnlCoverLayout.setVerticalGroup(
                pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCoverLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnMore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabPhongDatTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoreActionPerformed

//        panelPopUpMoreDatPhong1.showPopupMenu(this, (getWidth() - panelPopUpMoreDatPhong1.getPreferredSize().width) / 2, (getHeight() - panelPopUpMoreDatPhong1.getPreferredSize().height) / 2);
        //hiển thị panelPopUpMoreDatPhong1 kế bên trái btnMore
        panelPopUpMoreDatPhong1.showPopupMenu(this, btnMore.getX() - panelPopUpMoreDatPhong1.getPreferredSize().width, btnMore.getY() + btnMore.getHeight());

        //add event
        panelPopUpMoreDatPhong1.addEvent(new gui.event.EventPopUpMoreDatPhong() {
            @Override
            public void datPhongTruoc() {

                openDL_CheckKH();

            }

            @Override
            public void thueNhieuPhong() {
                openDL_CheckKHThueNhieuPhong();
            }

            @Override
            public void thanhToanNhieuPhong() {
                openDL_ThanhToanNhieuPhong();
            }

        });

    }//GEN-LAST:event_btnMoreActionPerformed

    public Boolean validDateSDT() {
        String sdt = txtSearch.getText();
        if (sdt.length() != 10 || !sdt.matches("^0[0-9]{9}$")) {
            return false;
        }
        return true;
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // nhập vào sdt sau đó lấy ra maKH , từ maKH tìm ra đơn đặt phòng
        String sdt = txtSearch.getText();

        if (validDateSDT()) {
            kh_dao = new KhachHangImpl();
            ArrayList<KhachHang> kh = kh_dao.getKhachHangTheoSdtKH(sdt);

            if (!kh.isEmpty()) {

                KhachHang khachHang = kh.get(0);
                String maKH = khachHang.getMaKH();
                ddp_dao = new DonDatPhongImpl();
                ArrayList<DonDatPhong> dsDonDatPhong = ddp_dao.getDonDatPhongTheoMaKH(maKH);
                if (dsDonDatPhong.isEmpty()) {
                    Notification noti = new Notification(
                            (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                            Notification.Type.WARNING,
                            Notification.Location.TOP_RIGHT,
                            "Không tìm thấy đơn đặt phòng"
                    );
                    noti.showNotification();
                } else {
                    pnlDSPhongDatTruoc.removeAll();
                    pnlDSPhongDatTruoc.revalidate();
                    pnlDSPhongDatTruoc.repaint();
                    // lấy ra thông tin của phòng từ maPhong
                    ph_dao = new PhongHatImpl();
                    ArrayList<PhongHat> dsPhong = ph_dao.getAllPhongHat();
                    for (DonDatPhong donDatPhong : dsDonDatPhong) {
                        for (PhongHat phongHat : dsPhong) {
                            if (donDatPhong.getPhongHat().getMaPhong().equals(phongHat.getMaPhong())) {
                                String loaiPhong = phongHat.getLoaiPhong().getMaLoaiPhong();
                                Icon icon;
                                Icon iconPhongThuong = new ImageIcon(getClass().getResource("/icon/microphone.png"));
                                Icon iconPhongVip = new ImageIcon(getClass().getResource("/icon/microphonevip.png"));
                                if ("LP002".equals(loaiPhong)) {
                                    icon = iconPhongThuong;
                                } else {
                                    icon = iconPhongVip;
                                }
                                addPhongCho(new ModelRoom(phongHat.getMaPhong(), phongHat.getTenPhong(), icon, donDatPhong.getMaDonDatPhong(), donDatPhong.getKhachHang().getMaKH()));
                            }
                        }
                    }
                }
            } else {
                Notification noti = new Notification(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        Notification.Type.WARNING,
                        Notification.Location.TOP_RIGHT,
                        "Không tìm thấy khách hàng"
                );
                noti.showNotification();


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


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnReloadDSphongDatTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadDSphongDatTruocActionPerformed
        pnlDSPhongDatTruoc.removeAll();
        refreshRooms();
    }//GEN-LAST:event_btnReloadDSphongDatTruocActionPerformed

    private void btnNgayDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayDatPhongActionPerformed
        pnlDSPhongDatTruoc.removeAll();
        pnlDSPhongDatTruoc.revalidate();
        pnlDSPhongDatTruoc.repaint();

        // lấy ra ngày và reload lại phòng đặt trước
        ph_dao = new PhongHatImpl();
        ArrayList<PhongHat> phongList = ph_dao.getAllPhongHat();
        LocalDate ngayDat = thoiGianBatDau.getDatePicker().getDate();
        // tìm ra đơn đặt phòng theo ngày nhận
        ddp_dao = new DonDatPhongImpl();
        ArrayList<DonDatPhong> dsDonDatPhong = ddp_dao.getDonDatPhongTheoNgayNhanPhong(ngayDat);

        // so sánh phongList và dsDonDatPhong lấy ra thông tin các phòng có trong dsDonDatPhong
        for (PhongHat phong : phongList) {
            for (DonDatPhong donDatPhong : dsDonDatPhong) {
                if (phong.getMaPhong().equals(donDatPhong.getPhongHat().getMaPhong())) {

                    Icon icon;
                    Icon iconPhongThuong = new ImageIcon(getClass().getResource("/icon/microphone.png"));
                    Icon iconPhongVip = new ImageIcon(getClass().getResource("/icon/microphonevip.png"));
                    String loaiPhong = phong.getLoaiPhong().getMaLoaiPhong();
                    if ("LP002".equals(loaiPhong)) {
                        icon = iconPhongThuong;
                    } else {
                        icon = iconPhongVip;
                    }
                    addPhongCho(new ModelRoom(phong.getMaPhong(), phong.getTenPhong(), icon, donDatPhong.getMaDonDatPhong(), donDatPhong.getKhachHang().getMaKH()));
                }
            }
        }


    }//GEN-LAST:event_btnNgayDatPhongActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshRooms();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void createDatePicker() {
        thoiGianBatDau.setBackground(Color.WHITE);
//        date
        thoiGianBatDau.getDatePicker().getSettings().setAllowEmptyDates(false);
        thoiGianBatDau.getDatePicker().setBackground(Color.WHITE);
        thoiGianBatDau.getDatePicker().getSettings().setAllowKeyboardEditing(false);
        LocalDate today = LocalDate.now();
        thoiGianBatDau.getDatePicker().getSettings().setDateRangeLimits(today, LocalDate.MAX);
        thoiGianBatDau.getDatePicker().getSettings().setLocale(new Locale("vi"));
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesCommonEra("yyyy-MM-dd");
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesBeforeCommonEra("uuuu-MM-dd");

//        time
        thoiGianBatDau.getTimePicker().getSettings().setAllowEmptyTimes(false);
        thoiGianBatDau.getTimePicker().setBackground(Color.WHITE);
        if (thoiGianBatDau.getDatePicker().getDate().equals(LocalDate.now())) {
            thoiGianBatDau.getTimePicker().setTimeToNow();
            thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(time,
                    LocalTime.now(),
                    LocalTime.MAX, true));

        }
        thoiGianBatDau.getTimePicker().getSettings().generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, null, null);
        thoiGianBatDau.getTimePicker().getSettings().use24HourClockFormat();
        thoiGianBatDau.getTimePicker().getSettings().setFormatForDisplayTime("HH:mm");
        thoiGianBatDau.getTimePicker().getSettings().setFormatForMenuTimes("HH:mm");

        thoiGianBatDau.getDatePicker().addDateChangeListener((var event) -> {
            if (thoiGianBatDau.getDatePicker().getDate().equals(LocalDate.now())) {
                thoiGianBatDau.getTimePicker().setTimeToNow();
                thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(time,
                        LocalTime.now(),
                        LocalTime.MAX, true));
            } else {
                thoiGianBatDau.getTimePicker().setTime(LocalTime.of(6, 0));
                thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(
                        time,
                        LocalTime.of(6, 0),
                        LocalTime.MAX, true));
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.RadiusButton btnMore;
    private javax.swing.JButton btnNgayDatPhong;
    private gui.swing.RadiusButton btnRefresh;
    private gui.swing.RadiusButton btnReloadDSphongDatTruoc;
    private gui.swing.RadiusButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblNgayNhanPhong;
    private gui.component.panelPopUpMoreDatPhong panelPopUpMoreDatPhong1;
    private javax.swing.JPanel pnlCover;
    private javax.swing.JPanel pnlDSPhongDatTruoc;
    private javax.swing.JPanel pnlPhongCho;
    private javax.swing.JPanel pnlPhongDangSuDung;
    private javax.swing.JPanel pnlPhongTrong;
    private javax.swing.JPanel pnlRoom;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlSearch1;
    private gui.swing.tabbed.MaterialTabbed tabPhongDatTruoc;
    private com.github.lgooddatepicker.components.DateTimePicker thoiGianBatDau;
    private gui.swing.CustomJTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
