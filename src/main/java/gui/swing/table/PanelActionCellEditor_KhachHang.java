package gui.swing.table;

import javax.swing.DefaultCellEditor;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @author 84934
 */
public class PanelActionCellEditor_KhachHang extends DefaultCellEditor {

    private TableActionEvent_KhachHang event;

    public PanelActionCellEditor_KhachHang(TableActionEvent_KhachHang event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction_KhachHang action = new PanelAction_KhachHang();
//        action.initEvent(event, row);
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
