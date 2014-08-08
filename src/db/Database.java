/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author giuseppe
 */
public class Database {
    

    protected static Connection conn;
    protected static Statement s ;
    
    
    /**
     * Aggiunge al db gli attributi passati nella giusta tabella
     * @param Table Nome della tabella in cui inserire la tupla
     * @param attr i valori della tupla
     * @return 
     */
    
    public static void Initializate()
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Derby driver not found.");
            System.exit(0);
        }
        
        try{
            conn = DriverManager.getConnection("jdbc:derby://localhost/Svil;create=true;user=APP;pass=APP");
            s = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Close();
        }
    }
    
    public static void Close()
    {
         try {
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Error During Close");
            System.exit(0);
        }
    }
    
    public static boolean Insert(String Table, String[] attr, String[] value)
    {
        try{
            Statement s = conn.createStatement();
            //System.out.println("INSERT INTO " +Table+ " ( "+join(attr,",","")+" ) VALUES ("+join(value,",","'")+")");
            s.execute("INSERT INTO " +Table+ "  ( "+join(attr,",","")+" ) VALUES ("+join(value,",","'")+")");
  
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
   
    
  public static ArrayList<Utente> GetAllUtenti()
  {
    ResultSet rs=  GetAllTuple("Utente");
    ArrayList<Utente> list= new  ArrayList<Utente>();
    try{
        while (rs.next()) {
              //System.out.println("Derby says: "+rs.getString("Username")+" - "+rs.getString("Password"));
            list.add(new Utente(rs.getString("Username"),rs.getString("Password")));
        
        }
    } catch (SQLException ex) {
            ex.printStackTrace();
    }
    
    return list;
           
  }
    
  public static int CheckMessage(String receiver)
  {
      try{
          s.execute("SELECT * FROM MESSAGGIO WHERE RECEIVER = '"+receiver+"' AND ISREAD = FALSE");
          ResultSet rs = s.getResultSet();
          int ris=0;
          while (rs.next()) {
              ris++;
          }
          return ris;
      } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
      
  }
  
  
  private static ResultSet GetAllTuple(String Tables)
  {
      try{
          s.execute("SELECT * FROM "+Tables);
          ResultSet rs = s.getResultSet();
          //while (rs.next()) {
         //     System.out.println("Derby says: "+rs.getString("Username")+" - "+rs.getString("Password"));
         // }
        return rs;
      } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
      
      
  }
  
    public static Utente CheckUtente(String User, String Pass_Chiaro)
    {
        try{
          s.execute("SELECT * FROM UTENTE WHERE USERNAME='"+User+"' AND PASSWORD = '"+Pass_Chiaro+"'" );
          ResultSet rs = s.getResultSet();
          if(!rs.next())
              return null;
          else
              return new Utente(rs.getString("Username"), rs.getString("Password"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
  
    
    /**
     * Valida il testo inserito, ovvero rimuove i caratteri non accettabili
     * @param value testo potenzialmente non sicuro
     * @return testo sicuro
     */
    public static String Validate(String value)
    {
        return value;
    }
    
    
    
    public static String join(String[] s, String glue,String comma)
    {
      int k = s.length;
      if ( k == 0 )
      {
        return null;
      }
      StringBuilder out = new StringBuilder();
      out.append( comma+s[0]+comma );
      for ( int x=1; x < k; ++x )
      {
        out.append(glue).append( comma+s[x]+comma);
      }
      return out.toString();
    }

    

    
}
