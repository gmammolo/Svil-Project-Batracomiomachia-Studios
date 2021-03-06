/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GuiController;
import model.Messaggio;
import model.Utente;

/**
 *
 * @author Giuseppe
 */
public class ReadMessage extends javax.swing.JFrame {

    Utente User;
    Messaggio Mess;
    ChooseFrame Parent;
    
    /**
     * Creates new form ReadMessage
     */
    public ReadMessage(Utente user, Messaggio mess,ChooseFrame parent) {
        User=user;
        Mess=mess;
        Parent=parent;
        initComponents();
        Sender.setText(mess.Sender);
        Testo.setText(mess.Testo);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sender = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Testo = new javax.swing.JTextArea();
        Indietro = new javax.swing.JButton();
        Rispondi = new javax.swing.JButton();
        Inoltra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Sender.setText("jLabel1");

        Testo.setEditable(false);
        Testo.setColumns(20);
        Testo.setRows(5);
        jScrollPane1.setViewportView(Testo);

        Indietro.setText("Indietro");
        Indietro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IndietroActionPerformed(evt);
            }
        });

        Rispondi.setText("Rispondi");
        Rispondi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RispondiActionPerformed(evt);
            }
        });

        Inoltra.setText("Inoltra");
        Inoltra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InoltraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Sender, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(Indietro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Rispondi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(Inoltra)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Indietro)
                    .addComponent(Rispondi)
                    .addComponent(Inoltra))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RispondiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RispondiActionPerformed
        // TODO add your handling code here:
        GuiController.RispondiMessaggio(User, Parent, Mess.Sender);
    }//GEN-LAST:event_RispondiActionPerformed

    private void IndietroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IndietroActionPerformed
        // TODO add your handling code here:
        GuiController.UndoFrame(Parent, this);
    }//GEN-LAST:event_IndietroActionPerformed

    private void InoltraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InoltraActionPerformed
        // TODO add your handling code here:
        GuiController.InoltraMessaggio(User, Parent, Mess.Testo);
    }//GEN-LAST:event_InoltraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Indietro;
    private javax.swing.JButton Inoltra;
    private javax.swing.JButton Rispondi;
    private javax.swing.JLabel Sender;
    private javax.swing.JTextArea Testo;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
