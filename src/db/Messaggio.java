/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.util.ArrayList;

/**
 *
 * @author giuseppe
 */
public class Messaggio {
    
    public String Id;
    public String Testo;
    public String Cifrato;
    public String Metodo_Criptaggio;
    public String Lingua;
    public String Sender;
    public String Receiver;
    public boolean IsRead;
    
    
    public Messaggio(String testo)
    {
        this("",testo,"","","","","");
    }
    
    public Messaggio(String testo, String cifrato, String metodo_criptaggio, String lingua, String sender, String receiver)
    {
        this("",testo,cifrato,metodo_criptaggio,lingua,sender,receiver);
    }
    
    public Messaggio(String id, String testo, String cifrato, String metodo_criptaggio, String lingua,String sender, String receiver)
    {
        this("",testo,cifrato,metodo_criptaggio,lingua,sender,receiver, false);
    }
    
    public Messaggio(String id, String testo, String cifrato, String metodo_criptaggio, String lingua,String sender, String receiver, boolean isRead)
    {
        Id=id;
        Testo=testo;
        Cifrato=cifrato;
        Metodo_Criptaggio=metodo_criptaggio;
        Lingua=lingua;
        Sender=sender;
        Receiver=receiver;
        IsRead=IsRead;
        
        
    }
    
   
    
    public void Insert()
    {
        Database.Insert("MESSAGGIO", new String[]{""}, new String[]{Id,Testo,Cifrato,Metodo_Criptaggio,Lingua,Sender,Receiver,String.valueOf(IsRead)});
    }
 
    
     public static int GetLastID()
     {
        return Database.GetLastValue("MESSAGGIO", "ID");
     }
    
     public static ArrayList<Messaggio> GetMessageList(Utente reader, int limit)
     {
        return Database.GetMessageList(reader, limit);
     }
}
