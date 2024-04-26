package gui.swing.table;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @author 84343
 */
public class TableActionCellEditorKhuyenMai extends DefaultCellEditor {

    private TableActionEvent_KhuyenMai event;

    public TableActionCellEditorKhuyenMai(TableActionEvent_KhuyenMai event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelActionKhuyenMai action = new PanelActionKhuyenMai();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
