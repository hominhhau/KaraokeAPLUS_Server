package gui.component;

import gui.event.EventMenu;
import gui.model.ModelMenu;
import gui.swing.MenuAnimation;
import gui.swing.MenuItem;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopUpMenu;

import java.awt.event.ActionListener;

/**
 * Lớp Menu là thành phần giao diện cho thanh menu bên trái của ứng dụng.
 *
 * @author HO MINH HAU
 */
public class Menu extends javax.swing.JPanel {

    /**
     * Thêm sự kiện khi một mục menu được chọn.
     *
     * @param event Sự kiện EventMenuSelected.
     */
    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    /**
     * Đặt cờ cho phép hiển thị menu.
     *
     * @param enbleMenu Cờ cho phép hiển thị menu.
     */
    public void setEnbleMenu(boolean enbleMenu) {
        this.enbleMenu = enbleMenu;
    }

    /**
     * Đặt cờ xác định menu đang hiển thị hay ẩn.
     *
     * @param showMenu Cờ xác định menu đang hiển thị hay ẩn.
     */
    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    /**
     * Kiểm tra xem menu đang hiển thị hay ẩn.
     *
     * @return true nếu menu đang hiển thị, false nếu đang ẩn.
     */
    public boolean isShowMenu() {
        return showMenu;
    }

    /**
     * Thêm sự kiện khi cần hiển thị menu con (pop-up menu).
     *
     * @param eventShowPopUpMenu Sự kiện EventShowPopUpMenu.
     */
    public void addEventShowPopUpMenu(EventShowPopUpMenu eventShowPopUpMenu) {
        this.eventShowPopUpMenu = eventShowPopUpMenu;
    }

    // Các biến và sự kiện quan trọng
    private EventMenuSelected event; // Sự kiện xảy ra khi một mục menu được chọn.
    private boolean enbleMenu = true; // Cờ cho phép hiển thị menu.
    private boolean showMenu = true; // Cờ xác định menu đang hiển thị hay ẩn.
    private final MigLayout layout; // Layout quản lý cách các mục menu được sắp xếp.
    private EventShowPopUpMenu eventShowPopUpMenu; // Sự kiện hiển thị menu con (pop-up menu).

    public Menu() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");

        pnlItem.setLayout(layout);
        pnlSetting.setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]"));

    }

    /**
     * Khởi tạo các mục menu ban đầu cho nhân viên là quản lí , đầy đủ tính năng
     */
    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/TrangChu1.png")), "Trang Chủ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/booking.png")), "Quản Lí Đặt Phòng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/smartphone.png")), "Quản Lí Phòng Hát"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/service.png")), "Quản Lí Khách Hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/dairy-products.png")), "Quản Lí Mặt Hàng", "     Mặt Hàng", "     Dịch Vụ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/bill.png")), "Quản Lí Hóa Đơn"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/teamwork.png")), "Quản Lí Nhân Viên "));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/bar-chart.png")), "Thống Kê", "     Thống Kê Mặt Hàng", "     Thống Kê Doanh Thu"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/setting.png")), "Cài Đặt"));

    }

    /**
     * Khởi tạo các mục menu ban đầu cho tài khoản thu ngân.
     */
    public void initMenuItemTN() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/TrangChu1.png")), "Trang Chủ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/booking.png")), "Quản Lí Đặt Phòng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/smartphone.png")), "Quản Lí Phòng Hát"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/service.png")), "Quản Lí Khách Hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/dairy-products.png")), "Quản Lí Mặt Hàng", "     Mặt Hàng", "     Dịch Vụ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/bill.png")), "Quản Lí Hóa Đơn"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/bar-chart.png")), "Thống Kê"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/setting.png")), "Cài Đặt"));
    }

    /**
     * Thêm một mục menu vào thanh menu. Ở đây có 2 panel là pnlItem và
     * pnlSetting .
     *
     * @param menu ModelMenu đại diện cho mục menu.
     */
    private void addMenu(ModelMenu menu) {
        if (menu.getMenuName().equals("Cài Đặt")) {
            pnlSetting.add(new MenuItem(menu, getEventMenu(), event, pnlItem.getComponentCount()), "h 30!, pushy, growy");

        } else {
            pnlItem.add(new MenuItem(menu, getEventMenu(), event, pnlItem.getComponentCount()), "h 53!");
        }
    }

    /**
     * Lấy sự kiện cho mục menu.
     *
     * @return EventMenu đại diện cho sự kiện menu.
     */
    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enbleMenu) {
                    if (showMenu) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopUpMenu.showPopUp(com);
                    }
                }
                return false;
            }
        };
    }

    /**
     * Ẩn tất cả các menu đang mở.
     */
    public void hideallMenu() {
        for (Component com : pnlItem.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPanel();
        pnlItem = new javax.swing.JPanel();
        pnlSetting = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();

        Menu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
                MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        MenuLayout.setVerticalGroup(
                MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(190, 680));

        pnlItem.setBackground(new java.awt.Color(255, 255, 255));
        pnlItem.setOpaque(false);
        pnlItem.setPreferredSize(new java.awt.Dimension(190, 535));

        javax.swing.GroupLayout pnlItemLayout = new javax.swing.GroupLayout(pnlItem);
        pnlItem.setLayout(pnlItemLayout);
        pnlItemLayout.setHorizontalGroup(
                pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlItemLayout.setVerticalGroup(
                pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 519, Short.MAX_VALUE)
        );

        pnlSetting.setBackground(new java.awt.Color(255, 255, 255));
        pnlSetting.setOpaque(false);
        pnlSetting.setPreferredSize(new java.awt.Dimension(190, 100));

        javax.swing.GroupLayout pnlSettingLayout = new javax.swing.GroupLayout(pnlSetting);
        pnlSetting.setLayout(pnlSettingLayout);
        pnlSettingLayout.setHorizontalGroup(
                pnlSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlSettingLayout.setVerticalGroup(
                pnlSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        lblTitle.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("KARAOKE APLUS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlItem, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                        .addComponent(pnlSetting, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlItem, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlSetting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(
                0, 0, Color.decode("#0081A7"), // Màu bắt đầu
                getWidth(), 0, Color.decode("#00AFB9") // Màu kết thúc
        );
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlItem;
    private javax.swing.JPanel pnlSetting;
    // End of variables declaration//GEN-END:variables
}
