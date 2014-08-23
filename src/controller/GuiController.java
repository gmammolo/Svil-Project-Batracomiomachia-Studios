/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import cifrario.Metodo_Criptaggio;
import db.Database;
import db.Messaggio;
import db.Utente;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import user.ChooseFrame;
import user.LoginForm;
import user.ReadMessages;
import user.SendMessage;

/**
 *
 * @author Giuseppe
 */
public class GuiController {
    
    public static void LoginController(JTextField Username , JPasswordField Password, JLabel StatusMessage, LoginForm Loginform )
    {
        /*
            NOTA: a causa del suo status di progetto a scopo dimostrativo,
            Il field password sarà convertito in una stringa e trattato come
            una normale stringa.
        */
        String pass= "";
        for(char c :  Password.getPassword() )
        {
            pass+=String.valueOf(c);
        }
        
        
        if(Utente.CheckUtente(Username.getText(), pass ))
        {
            StatusMessage.setText("Login Avvenuto con Successo!");
            JFrame frame= new ChooseFrame(Utente.GetUtente(Username.getText(), pass ),Loginform);
            frame.setVisible(true);
            
            
        }
        else
        {
            StatusMessage.setText("Login Fallito!");
        }
    }
    
    public static String NotImplementYet()
    {
        return "OPS, Pare che questo tasto non sia stato implementato in questa versione";
    }
    
    
    
    public static void Logout(JFrame Parent, JFrame Son )
    {
        UndoFrame(Parent, Son);
    }
    
    public static void UndoFrame(JFrame Parent, JFrame Son )
    {
        Parent.setVisible(true);
        Parent.pack();
        Son.dispose();
    }
    
    
    public static void GoSendMessage(Utente user, JFrame This)
    {
        This.setVisible(false);
        JFrame sendM = new SendMessage(user,(ChooseFrame)This);
        sendM.setVisible(true);
    }
    
    public static void GoReadMessage(Utente user, JFrame This)
    {
        This.setVisible(false);
        JFrame readM= new ReadMessages(user,(ChooseFrame)This);
        readM.setVisible(true);
    }
    
    public static void ReadMessage(int MessageID)
    {
            Messaggio.ReadMessage(MessageID);
            
    }
    
    
    public static void SendMessage(JTextField Destinatario, JLabel Status, JComboBox MetodoCriptaggio, JTextArea Testo, JComboBox Linguaggio, Utente Sender, ChooseFrame Parent, SendMessage This)
    {
        String Dest = (Database.CheckUtente(Destinatario.getText())) ? Destinatario.getText() : "";
        if(Dest.equals(""))
        {
            Status.setText("Attenzione: Destinatario non accettabile");
            return;
        }
        //recuperare ID
        int ID = Messaggio.GetLastID();
        if(ID < 0)
        {
            Status.setText("Attenzione: Errore temporaneo durante il salvataggio nel db");
            return;
        }
        //salvare nel db
        Metodo_Criptaggio method= Metodo_Criptaggio.NESSUNO;
        switch ((String)MetodoCriptaggio.getSelectedItem())
        {
            case "Sostituzione":
                method= Metodo_Criptaggio.SOSTITUZIONE;
                break;
            case "Cifrario di Cesare":
                method= Metodo_Criptaggio.CIFRARIO_DI_CESARE;
                break;
            default:
                method= Metodo_Criptaggio.NESSUNO;
                break;
        }
        new Messaggio(ID,Testo.getText(),"",  method.name(), (String)Linguaggio.getSelectedItem(), Sender.Username, Dest).Insert();
        Parent.sendMessage("Messaggio inviato con successo");
        Status.setText("Messaggio Inviato con successo");
        //chiudere
        Parent.setVisible(true);
        Parent.pack();
        This.dispose();
        
    }
}
