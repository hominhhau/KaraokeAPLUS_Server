package gui.swing.table;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @author RAVEN
 */
public class TableActionCellEditorMatHang extends DefaultCellEditor {
    private TableActionEventMatHang event;

    public TableActionCellEditorMatHang(TableActionEventMatHang event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelActionMatHang action = new PanelActionMatHang();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
