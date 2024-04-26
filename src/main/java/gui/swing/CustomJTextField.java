/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing;

import java.awt.Graphics;
import javax.swing.JTextField;

/**
 * @author 84934 NguyenThiQuynhGiang
 */
public class CustomJTextField extends JTextField {
    @Override
    protected void paintBorder(Graphics g) {
        // Draw a green border line with a thickness of 2 pixels
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
