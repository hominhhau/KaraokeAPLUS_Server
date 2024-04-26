
package gui.swing.table;

import javax.swing.DefaultCellEditor;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @author 84934
 */
public class PanelActionCellEditor_NhanVien extends DefaultCellEditor {
    private TableActionEvent_NhanVien event;

    public PanelActionCellEditor_NhanVien(TableActionEvent_NhanVien event) {
        super(new JCheckBox());
        this.event = event;
    }


    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelActionNhanVien action = new PanelActionNhanVien();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
