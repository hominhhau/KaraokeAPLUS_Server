/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @author 84343
 */
public class PanelActionCellEditor_KhuyenMai extends DefaultCellEditor {

    private TableActionEvent_KhuyenMai event;

    public PanelActionCellEditor_KhuyenMai(TableActionEvent_KhuyenMai event) {
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
