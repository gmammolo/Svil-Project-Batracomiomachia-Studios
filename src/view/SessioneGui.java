/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GestoreIpotesi;
import controller.GestoreSessione;
import controller.GuiController;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.AlberoIpotesi;
import model.ListaIpotesi;
import model.Sessione;

/**
 *
 * @author Giuseppe
 */
public class SessioneGui extends javax.swing.JFrame {

    public Sessione Session;
    
    /**
     * Creates new form SessioneGui
     */
    public SessioneGui(Sessione session) {
        Session=session;
        initComponents();
        UpdateSession(session.Ipotesi);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Mittente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Destinatario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Lingua = new javax.swing.JTextField();
        CryptMethod = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Cifrato = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        Storico = new javax.swing.JTextPane();
        Salva = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        a = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        b = new javax.swing.JTextField();
        sostiuisci = new javax.swing.JButton();
        Dizionario = new javax.swing.JButton();
        Codename = new javax.swing.JTextField();
        Undo = new javax.swing.JButton();
        frequenze = new javax.swing.JButton();
        showStorico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mittente:");

        Mittente.setEditable(false);
        Mittente.setText(Session.Mess.Sender);

        jLabel2.setText("Destinatario");

        Destinatario.setEditable(false);
        Destinatario.setText(Session.Mess.Receiver);

        jLabel3.setText("Lingua");

        jLabel4.setText("Metodo");

        Lingua.setEditable(false);
        Lingua.setText(Session.Mess.Lingua);
        Lingua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LinguaActionPerformed(evt);
            }
        });

        CryptMethod.setEditable(false);
        CryptMethod.setText(Session.Mess.CryptMethod.name());

        Cifrato.setEditable(false);
        Cifrato.setText(Session.Mess.Cifrato);
        jScrollPane2.setViewportView(Cifrato);

        Storico.setEditable(false);
        jScrollPane3.setViewportView(Storico);

        Salva.setText("Salva");
        Salva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvaActionPerformed(evt);
            }
        });

        jLabel5.setText("Sostituisci:");

        a.setText("a");

        jLabel6.setText("=>");

        b.setText("b");

        sostiuisci.setText("Sostituisci");
        sostiuisci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sostiuisciActionPerformed(evt);
            }
        });

        Dizionario.setText("Dizionario");
        Dizionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DizionarioActionPerformed(evt);
            }
        });

        Codename.setText("Nome...");

        Undo.setText("Undo");
        Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoActionPerformed(evt);
            }
        });

        frequenze.setText("Frequenze");
        frequenze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frequenzeActionPerformed(evt);
            }
        });

        showStorico.setText("Storico");
        showStorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStoricoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(Mittente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Destinatario)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Lingua)
                                    .addComponent(CryptMethod)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Dizionario, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sostiuisci)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                    .addComponent(Salva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Codename)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(Undo))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(frequenze, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showStorico)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Mittente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(Lingua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Codename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(CryptMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salva))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sostiuisci)
                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Dizionario)
                        .addComponent(jLabel5)
                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Undo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frequenze)
                    .addComponent(showStorico))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LinguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LinguaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LinguaActionPerformed

    private void DizionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DizionarioActionPerformed
        // TODO add your handling code here:
        GuiController.OpenDizionario(this,Cifrato);
        
    }//GEN-LAST:event_DizionarioActionPerformed

    private void sostiuisciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sostiuisciActionPerformed
        // TODO add your handling code here:
        GuiController.AddKeyComponent(this,a.getText(),b.getText());
    }//GEN-LAST:event_sostiuisciActionPerformed

    private void SalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvaActionPerformed
        // TODO add your handling code here:
        GuiController.SaveSession(this,Codename.getText());
                
    }//GEN-LAST:event_SalvaActionPerformed

    private void UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionPerformed
        // TODO add your handling code here:
        GestoreIpotesi.Undo(Session);
        UpdateSession(Session.Ipotesi);
        
    }//GEN-LAST:event_UndoActionPerformed

    private void showStoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showStoricoActionPerformed
        // TODO add your handling code here:
        GuiController.ShowStorico(this);

    }//GEN-LAST:event_showStoricoActionPerformed

    private void frequenzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frequenzeActionPerformed
        // TODO add your handling code here:
        GuiController.CheckFreq(Session.Mess.Cifrato);
        
    }//GEN-LAST:event_frequenzeActionPerformed

    /**
     * Varifica se il messaggio trovato è corretto
     */
    public void CheckResult()
    {

        if( Session.Mess.Testo.toLowerCase().equals(Cifrato.getText().toLowerCase()))
        {
            JOptionPane.showMessageDialog(null,"MESSAGGIO TROVATO");
            System.out.println("MESSAGGIO TROVATO");
        }
    }
    
    public void UpdateSession(ListaIpotesi ipotesi)
    {
        Storico.setText(ipotesi.toString());
        String s= GestoreIpotesi.UpdateText(Session);
        Cifrato.setText(s);
        CheckResult();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Cifrato;
    private javax.swing.JTextField Codename;
    private javax.swing.JTextField CryptMethod;
    private javax.swing.JTextField Destinatario;
    private javax.swing.JButton Dizionario;
    private javax.swing.JTextField Lingua;
    private javax.swing.JTextField Mittente;
    private javax.swing.JButton Salva;
    private javax.swing.JTextPane Storico;
    private javax.swing.JButton Undo;
    private javax.swing.JTextField a;
    private javax.swing.JTextField b;
    private javax.swing.JButton frequenze;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton showStorico;
    private javax.swing.JButton sostiuisci;
    // End of variables declaration//GEN-END:variables
}
