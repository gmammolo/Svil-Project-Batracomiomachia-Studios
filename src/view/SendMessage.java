/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import cifrario.Metodo_Criptaggio;
import controller.GuiController;
import model.Database;
import model.Messaggio;
import model.Utente;

/**
 *
 * @author Giuseppe
 */
public class SendMessage extends javax.swing.JFrame {

    protected Utente Sender;
    protected ChooseFrame Parent;
    
    /**
     * Creates new form SendMessage
     */
    public SendMessage(Utente user, ChooseFrame parent) {
//        this.Sender=user;
//        this.Parent=parent;
//        initComponents();
        this(user,"...",parent,"");
    }
    
    public SendMessage(Utente user,String destinatario, ChooseFrame parent) {
        this(user,destinatario,parent,"");
    }
    
    public SendMessage(Utente user, ChooseFrame parent, String Testo) {
        this(user,"...",parent,Testo);
    }
    
    public SendMessage(Utente user,String destinatario, ChooseFrame parent, String testo) {
        this.Sender=user;
        this.Parent=parent;
        initComponents();
        
        Destinatario.setText(destinatario);
        Testo.setText(testo);
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
        Destinatario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Testo = new javax.swing.JTextArea();
        Send = new javax.swing.JButton();
        Linguaggio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        IsBozza = new javax.swing.JCheckBox();
        MetodoCriptaggio = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        Annulla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Destinatario");

        Destinatario.setText("...");

        Testo.setColumns(20);
        Testo.setRows(5);
        jScrollPane1.setViewportView(Testo);

        Send.setText("Send");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        Linguaggio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Italiano", "Inglese", "Francese", "Tedesco", "Polacco", "Russo" }));
        Linguaggio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LinguaggioActionPerformed(evt);
            }
        });

        jLabel2.setText("Lingua:");

        IsBozza.setText("Bozza");

        MetodoCriptaggio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nessuno", "Cifrario di Cesare", "Sostituzione" }));

        jLabel3.setText("Metodo di Cifratura:");

        Status.setText(".");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MetodoCriptaggio, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Linguaggio, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(IsBozza))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Annulla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Send)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Linguaggio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(IsBozza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MetodoCriptaggio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Send)
                        .addComponent(Annulla))
                    .addComponent(Status, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
        // TODO add your handling code here:
        GuiController.SendMessage(Destinatario, Status, MetodoCriptaggio, Testo,Linguaggio, Sender, Parent, this);
        //verificare il Destinatario
//        String Dest = (Database.CheckUtente(Destinatario.getText())) ? Destinatario.getText() : "";
//        if(Dest.equals(""))
//        {
//            Status.setText("Attenzione: Destinatario non accettabile");
//            return;
//        }
//        //recuperare ID
//        int ID = Messaggio.GetLastID();
//        if(ID < 0)
//        {
//            Status.setText("Attenzione: Errore temporaneo durante il salvataggio nel db");
//            return;
//        }
//        //salvare nel db
//        Metodo_Criptaggio method= Metodo_Criptaggio.NESSUNO;
//        switch ((String)MetodoCriptaggio.getSelectedItem())
//        {
//            case "Sostituzione":
//                method= Metodo_Criptaggio.SOSTITUZIONE;
//                break;
//            case "Cifrario di Cesare":
//                method= Metodo_Criptaggio.CIFRARIO_DI_CESARE;
//                break;
//            default:
//                method= Metodo_Criptaggio.NESSUNO;
//                break;
//        }
//        new Messaggio(ID,Testo.getText(),"",  method.name(), (String)Linguaggio.getSelectedItem(), Sender.Username, Dest).Insert();
//        Parent.sendMessage("Messaggio inviato con successo");
//        Status.setText("Messaggio Inviato con successo");
//        //chiudere
//        Parent.setVisible(true);
//        Parent.pack();
//        this.dispose();
        
    }//GEN-LAST:event_SendActionPerformed

    private void LinguaggioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LinguaggioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LinguaggioActionPerformed

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        // TODO add your handling code here:
        Parent.setVisible(true);
        Parent.pack();
        this.dispose();
    }//GEN-LAST:event_AnnullaActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JTextField Destinatario;
    private javax.swing.JCheckBox IsBozza;
    private javax.swing.JComboBox Linguaggio;
    private javax.swing.JComboBox MetodoCriptaggio;
    private javax.swing.JButton Send;
    private javax.swing.JLabel Status;
    private javax.swing.JTextArea Testo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
