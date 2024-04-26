
package gui.swing;

import java.awt.Graphics;
import javax.swing.JPasswordField;


public class CustomJPasswordField extends JPasswordField {
    @Override
    protected void paintBorder(Graphics g) {
        // Draw a green border line with a thickness of 2 pixels
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
