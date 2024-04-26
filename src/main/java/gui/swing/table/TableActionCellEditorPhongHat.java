package gui.swing.table;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @author RAVEN
 */
public class TableActionCellEditorPhongHat extends DefaultCellEditor {
    private TableActionEventPhongHat event;

    public TableActionCellEditorPhongHat(TableActionEventPhongHat event) {
        super(new JCheckBox());
        this.event = (TableActionEventPhongHat) event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelActionPhongHat action = new PanelActionPhongHat();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
