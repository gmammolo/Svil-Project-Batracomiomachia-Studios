/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Giuseppe
 */
public class Sessione {


    public Integer Id;
    public Messaggio Mess;
    public Utente User;
    public String Codename;
    public String MetaKey;
    public Map Key;
    
    public Sessione(int id, int messId, String userName)
    {
        this(id, Messaggio.GetMessaggioById(messId),Utente.GetUtente(userName));
    }
    
    public Sessione(int id, int messId, Utente user)
    {
        this(id, Messaggio.GetMessaggioById(messId),user);
    }
    
    
    public Sessione(int id, Messaggio mess, Utente user)
    {
        Id=id;
        Mess=mess;
        User=user;
        Key = new Hashtable();
    }
    
    public static void CreateTable()
    {
        Database.CreateTable("create table \"APP\".SESSIONE\n" +
                            "(\n" +
                            "	ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),\n" +
                            "	IDMESS INTEGER,\n" +
                            "	IDUSER VARCHAR(32),\n" +
                            "   CODENAME VARCHAR(20),n" +
                            "   METAKEY VARCHAR(250) \n"+ 
                            ")");       
    }
    
    

    
    /**
     * Aggiunge una Sessione al Db
     * @param ID
     * @param mess
     * @param userName 
     */
    public static void AddSessione(int ID, int mess,String userName,String codename, String metakey)
    {
        String[] column= new String[]{ "IDMESS", "USERNAME","CODENAME","METAKEY"};
        Database.Insert("SESSIONE", column, new DataBaseElement[]{
                                        //new DataBaseElement(DataBaseElement.Type.INT, ID),
                                        new DataBaseElement(DataBaseElement.Type.INT, mess),
                                        new DataBaseElement(DataBaseElement.Type.STRING, userName),
                                        new DataBaseElement(DataBaseElement.Type.STRING, codename),
                                        new DataBaseElement(DataBaseElement.Type.STRING, metakey)
                                    });
    }
    
    /**
     * Carica la sessione corrente nel db
     * @deprecated Usare invece Sessione.AddSessione()
     */
    public void Insert()
    {
        String[] column= new String[]{ "IDMESS", "USERNAME","CODENAME","METAKEY"};
        Database.Insert("SESSIONE", column, new DataBaseElement[]{
                                        //new DataBaseElement(DataBaseElement.Type.INT, ID),
                                        new DataBaseElement(DataBaseElement.Type.INT, Mess.Id),
                                        new DataBaseElement(DataBaseElement.Type.STRING, User.Username),
                                        new DataBaseElement(DataBaseElement.Type.STRING, Codename),
                                        new DataBaseElement(DataBaseElement.Type.STRING, Serialize())
                                    });
    }
    
    /**
     * 
     * @param ID
     * @param user Utente 
     * @return Sessione Restituisce la sessione che rispetta i dati richiesti
     */
    public static Sessione GetSessioneById(int ID, Utente user)
    {
       return Database.GetSessioneById(ID,user);
    }
    
    
    public static Sessione LoadSessione(int id) {
        return Database.LoadSessione(id);
    }
    
    
    public void addKey(char a, char b)
    {
        Key.put(a, String.valueOf(b).toUpperCase());
    }
    
    
    public String Serialize()
    {
        String result="";
        for (Object key : Key.keySet())
        {
            result+=String.valueOf(key)+String.valueOf(Key.get(key));
        }
        return result;
    }
    
    public void Deserialize(String metakey)         
    {
        Key = new Hashtable();
        
        for(int i=0; i< metakey.length(); i+=2)
        {
            int j=i+1;
            Key.put(j, i);
        }
        
    }
    
}
