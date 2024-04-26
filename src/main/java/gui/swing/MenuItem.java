package gui.swing;

import gui.event.EventMenu;
import gui.model.ModelMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;
import gui.event.EventMenuSelected;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Lớp MenuItem đại diện cho một mục menu trong ứng dụng.
 *
 * @author HO MINH HAU
 */
public class MenuItem extends javax.swing.JPanel {

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(EventMenuSelected eventSelected) {
        this.eventSelected = eventSelected;
    }

    public int getIndex() {
        return index;
    }

    private float alpha;

    public void setMenu(ModelMenu menu) {
        this.menu = menu;
    }

    public ModelMenu getMenu() {
        return menu;
    }

    /**
     * Khởi tạo một MenuItem mới.
     *
     * @param menu          ModelMenu chứa thông tin về mục menu này.
     * @param event         Sự kiện menu.
     * @param eventSelected Sự kiện khi mục menu được chọn.
     * @param index         Chỉ số của mục menu.
     */
    private ModelMenu menu;
    private boolean open;
    private EventMenuSelected eventSelected;
    private int index;
    private int height;

    public MenuItem(ModelMenu menu, EventMenu event, EventMenuSelected eventSelected, int index) {
        initComponents();
        this.menu = menu;
        this.eventSelected = eventSelected;
        this.index = index;
        setOpaque(false);
        setLayout(new MigLayout("wrap, fill, insets 0", "[fill]", "[fill,40!]0[fill, 35!]"));
        MenuButton firstItem = new MenuButton(menu.getIcon(), "     " + menu.getMenuName());
        firstItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menu.getSubMenu().length > 0) {
                    if (event.menuPressed(MenuItem.this, !open)) {
                        open = !open;
                    }
                }
                eventSelected.menuSelect(index, -1);
            }

        });
        add(firstItem);
        int subMenuIndex = -1;
        for (String st : menu.getSubMenu()) {
            MenuButton item = new MenuButton(st);
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eventSelected.menuSelect(index, item.getIndex());
                }
            });
            add(item);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

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

    private void createArrowButton(Graphics2D g2) {
        int size = 4;
        int y = (height / 2) + 20;
        int x = getWidth() - 20;
        float ay = alpha * size;
        float ay1 = (1f - alpha) * size;
        g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
        g2.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (menu.getSubMenu().length > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            createArrowButton(g2);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
