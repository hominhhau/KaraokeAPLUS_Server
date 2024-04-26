//package gui.swing;
//
//import java.awt.Component;
//import javax.swing.DefaultCellEditor;
//import javax.swing.JCheckBox;
//import javax.swing.JTable;
//
///**
// *
// * @author RAVEN
// */
//public class TableActionCellEditor extends DefaultCellEditor {
//
//    private TableActionEvent event;
//
//    public TableActionCellEditor(raven.cell.TableActionEvent event) {
//        super(new JCheckBox());
//        this.event = (TableActionEvent) event;
//    }
////
////    public TableActionCellEditor() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    public TableActionCellEditor() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
//        PanelAction action = new PanelAction();
//        action.initEvent(event, row);
//        action.setBackground(jtable.getSelectionBackground());
//        return action;
//    }
//}
