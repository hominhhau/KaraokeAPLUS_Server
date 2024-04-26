package gui.swing;

import javax.swing.*;
import java.awt.*;

public class CustomJtextFieldRadis extends JTextField {
    // bo tròn và làm mịn
    @Override
    protected void paintComponent(Graphics g) {
        // Draw a rounded rectangle border with a thickness of 2 pixels
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    // bo tròn và làm mịn
    @Override
    protected void paintBorder(Graphics g) {
        // Draw a rounded rectangle border with a thickness of 2 pixels
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

}
