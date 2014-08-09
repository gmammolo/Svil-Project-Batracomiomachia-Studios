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


    
    public Integer Id;
    public String Testo;
    public String Cifrato;
    public String Metodo_Criptaggio;
    public String Lingua;
    public String Sender;
    public String Receiver;
    public boolean IsRead;
    
    
    public Messaggio(String testo)
    {
        this(-1,testo,"","","","","");
    }
    
    public Messaggio(String testo, String cifrato, String metodo_criptaggio, String lingua, String sender, String receiver)
    {
        this(-1,testo,cifrato,metodo_criptaggio,lingua,sender,receiver);
    }
    
    public Messaggio(Integer id, String testo, String cifrato, String metodo_criptaggio, String lingua,String sender, String receiver)
    {
        this(id,testo,cifrato,metodo_criptaggio,lingua,sender,receiver, false);
    }
    
    public Messaggio(Integer id, String testo, String cifrato, String metodo_criptaggio, String lingua,String sender, String receiver, boolean isRead)
    {
        Id=id;
        Testo=testo;
        Cifrato=cifrato;
        Metodo_Criptaggio=metodo_criptaggio;
        Lingua=lingua;
        Sender=sender;
        Receiver=receiver;
        IsRead=isRead;
        
        
    }
    
   
    /**
     * Metodo di inserimento nel DB
     */
    public void Insert()
    {
        if(Id >= 0)
            Database.Insert("MESSAGGIO", new String[]{""}, new String[]{String.valueOf(Id),Testo,Cifrato,Metodo_Criptaggio,Lingua,Sender,Receiver,String.valueOf(IsRead)});
    }
 
    /**
     * Metodo che restituisce l'id dell' ultimo messaggio nel DB
     * @return l'ID dell' ultimo messaggio
     */
     public static int GetLastID()
     {
        return Database.GetLastValue("MESSAGGIO", "ID");
     }
    
     /**
      * 
      * @param reader Utente che legge
      * @param limit limite massimo di messaggi da ricevere
      * @return Lista del messaggio
      */
     public static ArrayList<Messaggio> GetMessageList(Utente reader, int limit)
     {
        return Database.GetMessageList(reader, limit);
     }
     
     /**
      * Setta un messaggio come già letto
      * @param MessageID Id del messaggio da settare
      */
    public static void ReadMessage(int MessageID) {
        Database.setReadMessage(MessageID);
    
    }
}
