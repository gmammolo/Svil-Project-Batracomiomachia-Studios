/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

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
        Id=id;
        Testo=testo;
        Cifrato=cifrato;
        Metodo_Criptaggio=metodo_criptaggio;
        Lingua=lingua;
        Sender=sender;
        Receiver=receiver;
        IsRead=false;
        
        
    }
    
    public void Insert()
    {
        Database.Insert("MESSAGGIO", new String[0], new String[]{Id,Testo,Cifrato,Metodo_Criptaggio,Lingua,Sender,Receiver,String.valueOf(IsRead)});
    }
    
}
