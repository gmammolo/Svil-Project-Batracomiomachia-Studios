/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import db.DataBaseElement.Type;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author giuseppe
 */
public class Utente {
 
    public String Username;
    public String Password;
    
    public Utente()
    {
        this("","");
    }
    
    public Utente(String Username,String Password)
    {
        this.Username=Username;
        this.Password=Password;
    }
    

    public static void CreateTable()
    {
        Database.CreateTable("create table \"APP\".UTENTE"+
                                "("+
                                    "USERNAME VARCHAR(32) not null primary key,"+
                                    "PASSWORD VARCHAR(32)"+
                                ")");      
    }
    
    /**
     * Aggiunge un utente al Db
     * @param User
     * @param Pass_Chiaro 
     */
    public static void AddUtente(String User, String Pass_Chiaro)
    {
        String[] column= new String[]{"USERNAME", "PASSWORD"};
        Database.Insert("Utente", column, new DataBaseElement[]{
                                        new DataBaseElement(Type.STRING, User),
                                        new DataBaseElement(Type.STRING, Pass_Chiaro)
                                    });
    }
                
                
//                new String[]{User,Pass_Chiaro});
    
    /**
     * Verifica la presenza di messaggi dell' utente
     * @return numero dei messaggi
     */
    public int CheckMessage()
    {
        return Database.CheckMessage(Username);
    }
    
    /**
     * Restituisce la Lista di tutti gli utenti
     * @return 
     */
    public static  ArrayList<Utente> GetAllUtenti()
    {
         ArrayList<Utente> list=Database.GetAllUtenti();
         return list;
        
    }
    
    /**
     * @deprecated
     * @param User
     * @param Pass_Chiaro
     * @return 
     */
    public boolean UserLogin(String User, String Pass_Chiaro)
    {
            return false;
    }
    
    /**
     * Verifica l'esistenza di un utente nel DB
     * @param User
     * @param Pass_Chiaro
     * @return true se esiste, false altrimenti
     */
    public static boolean CheckUtente(String User, String Pass_Chiaro)
    {
        return (Database.CheckUtente(User, Pass_Chiaro) == null) ? false : true;
    }
     
    
    public static Utente GetUtente(String User)
    {
        return Database.CheckUtente(User, null);
    }
    
    
    /**
     * Restituisce l'utente con i dati insetiti
     * @param User
     * @param Pass_Chiaro facoltativa: pass dell' utente
     * @return 
     */
    public static Utente GetUtente(String User, String Pass_Chiaro)
    {
        return Database.CheckUtente(User, Pass_Chiaro);
    }


}
