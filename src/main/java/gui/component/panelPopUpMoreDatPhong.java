/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.component;

import gui.dialog.DL_DatPhongTruoc;
import gui.event.EventPopUpMoreDatPhong;
import gui.form.Form_QuanLyDatPhong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author HO MINH HAU
 */
public class panelPopUpMoreDatPhong extends javax.swing.JPanel {

    private JPopupMenu popupMenu;
    private JMenuItem menuItem;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;

    public panelPopUpMoreDatPhong() {
        initComponents();
        initPopupMenu();
        addMouseListenerToClosePopup();
    }

    // Initialize the popup menu
    private void initPopupMenu() {
        popupMenu = new JPopupMenu();
        // Create and add a menu item
        // list item
        menuItem = new JMenuItem("Đặt Phòng Trước");


        menuItem1 = new JMenuItem("Thuê nhiều phòng");

        menuItem2 = new JMenuItem("Thanh toán nhiều phòng");


        popupMenu.add(menuItem);
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
    }

    // event click
    public void addEvent(EventPopUpMoreDatPhong event) {
        menuItem.addActionListener(e -> {
            event.datPhongTruoc();
        });
        menuItem1.addActionListener(e -> {
            event.thueNhieuPhong();
        });
        menuItem2.addActionListener(e -> {
            event.thanhToanNhieuPhong();
        });
    }

    // Display the popup menu
    public void showPopupMenu(Component component, int x, int y) {
        if (popupMenu != null) {
            popupMenu.show(component, x, y);
        } else {
            System.out.println("Error: PopupMenu not initialized");
        }
    }

    private void addMouseListenerToClosePopup() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (popupMenu != null && popupMenu.isVisible() && !popupMenu.getBounds().contains(e.getPoint())) {
                    popupMenu.setVisible(false);
                }
            }
        };

        addMouseListener(mouseAdapter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
