/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

/**
 *
 * @author Giuseppe
 */
public class Sessione {
    
    public Integer Id;
    public Messaggio Mess;
    public Utente User;
    
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
    }
    
    public static void CreateTable()
    {
        Database.CreateTable("create table \"APP\".SESSIONE\n" +
                            "(\n" +
                            "	ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),\n" +
                            "	IDMESS INTEGER,\n" +
                            "	IDUSER VARCHAR(32)\n" +
                            ")");       
    }
    
    /**
     * Aggiunge una Sessione al Db
     * @param ID
     * @param mess
     * @param userName 
     */
    public static void AddSessione(int ID, String mess,String userName)
    {
        String[] column= new String[]{ "IDMESS", "USERNAME"};
        Database.Insert("SESSIONE", column, new DataBaseElement[]{
                                        //new DataBaseElement(DataBaseElement.Type.INT, ID),
                                        new DataBaseElement(DataBaseElement.Type.STRING, mess),
                                        new DataBaseElement(DataBaseElement.Type.STRING, userName),
                                    });
    }
    
    /**
     * Carica la sessione corrente nel db
     * @deprecated Usare invece Sessione.AddSessione()
     */
    public void Insert()
    {
        String[] column= new String[]{ "IDMESS", "USERNAME"};
        Database.Insert("SESSIONE", column, new DataBaseElement[]{
                                        //new DataBaseElement(DataBaseElement.Type.INT, ID),
                                        new DataBaseElement(DataBaseElement.Type.STRING, Mess.Id),
                                        new DataBaseElement(DataBaseElement.Type.STRING, User.Username),
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
    
    
}
