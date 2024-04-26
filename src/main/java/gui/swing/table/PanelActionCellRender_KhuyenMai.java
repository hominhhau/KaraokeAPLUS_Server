package gui.swing.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author 84343
 */
public class PanelActionCellRender_KhuyenMai extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelActionKhuyenMai action = new PanelActionKhuyenMai();
        if (isSeleted == false && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());

        }
        return action;
    }
}
