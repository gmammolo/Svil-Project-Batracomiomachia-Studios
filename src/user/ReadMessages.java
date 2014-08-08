/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import db.Messaggio;
import db.Utente;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author Giuseppe
 */
public class ReadMessages extends javax.swing.JFrame {

    
    public Utente reader;
    public ChooseFrame parent;
    /**
     * Creates new form ReadMessages
     */
    public ReadMessages(Utente reader, ChooseFrame parent) {
        this.reader=reader;
        this.parent=parent;
        initComponents();
        ArrayList<Messaggio> list=Messaggio.GetMessageList(reader,10);
        PanelMenu.setLayout(new GridLayout(list.size()+1 , 4 ));
        PanelMenu.add(new JLabel("Read"));
        PanelMenu.add(new JLabel("Testo"));
        PanelMenu.add(new JLabel("Author"));
        PanelMenu.add(new JLabel("Letto"));
        
        for(Messaggio mess : list)
        {
            JButton but=new JButton("Read");
            but.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelMenu.add(but);
            
            JLabel label=new JLabel(mess.Testo);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelMenu.add(label);
            
            label=new JLabel(mess.Sender);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelMenu.add(label);
            
            label=new JLabel(mess.IsRead ? "Letto" : "Non Letto");
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelMenu.add(label);
        }
        PanelMenu.setBorder(BorderFactory.createLineBorder(Color.black));
        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMenu = new javax.swing.JPanel();
        Annulla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PanelMenuLayout = new javax.swing.GroupLayout(PanelMenu);
        PanelMenu.setLayout(PanelMenuLayout);
        PanelMenuLayout.setHorizontalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelMenuLayout.setVerticalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        Annulla.setText("Annulla");
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(Annulla)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(Annulla)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        // TODO add your handling code here:
        parent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AnnullaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JPanel PanelMenu;
    // End of variables declaration//GEN-END:variables
}
