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
            //System.out.println("INSERT INTO " +Table+ "  VALUES ("+join(value,",","'")+")");
            s.execute("INSERT INTO " +Table+ "  VALUES ("+join(value,",","'")+")");
  
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
          String pass= (Pass_Chiaro == null ) ? ""  : "AND PASSWORD = '"+Pass_Chiaro+"'" ;
          s.execute("SELECT * FROM UTENTE WHERE USERNAME='"+User+"' "+ pass );
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
    
    
   
    
    
    public static boolean CheckUtente(String User)
    {
        
        try{
         
          s.execute("SELECT * FROM UTENTE WHERE USERNAME='"+User+"' ");
          ResultSet rs = s.getResultSet();
          if(!rs.next())
              return false;
          else
              return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
  
    
    public static int GetLastValue(String Table, String column)
    {
        try{
          //System.out.println("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1"); //non so perchè vuole 1 anzichè column  
          s.execute("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1");
          ResultSet rs = s.getResultSet();
          if(rs.next())
          {
               //int num=Integer.parseInt(rs.getString("NUM"))+1;
              int num=rs.getInt("NUM")+1;
               return num;
          }
          else
              return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static ArrayList<Messaggio> GetMessageList(Utente reader, int limit)
    {
        ArrayList<Messaggio> list= new  ArrayList<Messaggio>();
        try{
            //System.out.println("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1"); //non so perchè vuole 1 anzichè column  
            s.execute("SELECT * FROM MESSAGGIO \n" +
                      "WHERE  RECEIVER = '"+reader.Username+"' \n" +
                      "ORDER BY ISREAD ASC,ID\n" +
                      "FETCH FIRST "+limit+" ROWS ONLY");
            ResultSet rs = s.getResultSet();

            while(rs.next())
            {
                list.add(new Messaggio(String.valueOf(rs.getInt("ID")), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") , rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD") ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
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
      //out.append( comma+s[0]+comma );
      out.append( getIntFormat(s[0],comma) );
      for ( int x=1; x < k; ++x )
      {
       // out.append(glue).append( comma+s[x]+comma);
        out.append(glue).append( getIntFormat(s[x],comma) );
      }
      return out.toString();
    }

    /**
     * Restituisce il valore nel giusto formato come stringa o come int
     * per inserirlo al db
     * @param val valore da testare
     * @param comma virgole da usare
     * @return 
     */
    private static String getIntFormat(String val,String comma)
    {
        try{
            int iCheck = Integer.parseInt(val);
            return val;
        }
        catch(NumberFormatException e) { return comma +val+ comma; }
    }

    
}
