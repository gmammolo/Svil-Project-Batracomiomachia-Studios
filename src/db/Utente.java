/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

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
    

    /**
     * Aggiunge un utente al Db
     * @param User
     * @param Pass_Chiaro 
     */
    public static void AddUtente(String User, String Pass_Chiaro)
    {
        Database.Insert("Utente",new String[]{"Username","Password" },new String[]{User,Pass_Chiaro});
       
    }
    
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
     * Deprecated (?)
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
