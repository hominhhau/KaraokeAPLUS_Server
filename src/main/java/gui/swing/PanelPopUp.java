package gui.swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * @author HO MINH HAU
 */
public class PanelPopUp extends JPanel {

    public PanelPopUp() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(
                0, 0, Color.GRAY, // Màu bắt đầu
                getWidth(), 0, Color.decode("#00AFB9") // Màu kết thúc
        );

        g2.setPaint(gp);
        g2.fillRect(8, 0, getSize().width - 8, getSize().height);

        int x[] = {0, 10, 10};
        int y[] = {20, 13, 27};
        g2.fillPolygon(x, y, x.length);
    }

}
