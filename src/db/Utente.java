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
    

    
    public static void AddUtente(String User, String Pass_Chiaro)
    {
        Database.Insert("Utente",new String[]{"Username","Password" },new String[]{User,Pass_Chiaro});
       
    }
    
    public int CheckMessage()
    {
        return Database.CheckMessage(Username);
    }
    
    public static  ArrayList<Utente> GetAllUtenti()
    {
         ArrayList<Utente> list=Database.GetAllUtenti();
         return list;
        
    }
    public boolean UserLogin(String User, String Pass_Chiaro)
    {
            return false;
    }
    
    public static boolean CheckUtente(String User, String Pass_Chiaro)
    {
        return (Database.CheckUtente(User, Pass_Chiaro) == null) ? false : true;
    }
     
    public static Utente GetUtente(String User, String Pass_Chiaro)
    {
        return Database.CheckUtente(User, Pass_Chiaro);
    }


}
