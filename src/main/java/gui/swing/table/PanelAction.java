package gui.swing.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author RAVEN
 */
public class PanelAction extends javax.swing.JPanel {

    /**
     * Creates new form PanelAction
     */
    public PanelAction() {
        initComponents();

    }

    public void initEvent(TableActionEvent event, int row) {
        cmdAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.tangSoLuong(row);
            }
        });
        cmdMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.giamSoLuong(row);
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMinus = new gui.swing.table.ActionButton();
        cmdAdd = new gui.swing.table.ActionButton();

        cmdMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/minus (4).png"))); // NOI18N
        cmdMinus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMinusActionPerformed(evt);
            }
        });

        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus.png"))); // NOI18N
        cmdAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmdMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(cmdAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmdMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMinusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdMinusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.table.ActionButton cmdAdd;
    private gui.swing.table.ActionButton cmdMinus;
    // End of variables declaration//GEN-END:variables
}
