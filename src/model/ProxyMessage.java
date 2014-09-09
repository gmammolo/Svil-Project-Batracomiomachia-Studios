/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Giuseppe
 */
public class ProxyMessage extends Messaggio {

    public ProxyMessage(String testo) {
        super(testo);
    }
    
    public String getText()
    {
        return Testo;
    }
    
    public String getSender()
    {
        return Sender;
    }
    
    public String getReceiver()
    {
        return Receiver;
    }
    
//    public String getMethod()
//    {
//        return ;
//    }

    

    
    
}
