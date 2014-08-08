/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import db.Utente;
import java.util.Timer;
import javax.swing.JLabel;
import message.TimeMessage;



/**
 * questa Classe contiene le operazioni che verranno effettuate dentro il ChooseFrame
 * @author Giuseppe
 */
public class Body {
    Utente user;
    JLabel mess;
    
    public Body( Utente user , JLabel mess)
    {
        this.user = user;
        this.mess = mess;
    }
    
    
    public void Run()
    {
        TimeMessage trd1 = new TimeMessage(user, mess);
        Timer orologio = new Timer();
        orologio.schedule(trd1, 0, 10000);
    }
    
}
