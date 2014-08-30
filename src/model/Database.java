/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

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
     * Metodo che inizializza la connessione al Db
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
            System.err.println("Connessione Fallita");
            Close();
            System.exit(0);
        }
    }
    
    /**
     * La connessione rimane aperta in modo statico finchè non verrà chiusa
     * tramite questo metodo
     */
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

    /**
     * Aggiunge al db gli attributi passati nella giusta tabella
     * @param Table  Nome della tabella in cui inserire la tupla
     * @param column Array con i nomi dellle colonne
     * @param elements i valori della tupla
     * @return true in caso di successo, false altrimenti
     */
    public static boolean Insert(String Table,String[] column, DataBaseElement[] elements)
    {
//        INSERT INTO APP.SESSIONE (ID, IDMESS, IDUSER) 
//	VALUES (1, 1, 'pippo')
        
        try{
            String strcolumn=GetColumnToString(column);
            
            Statement s = conn.createStatement();
            System.out.println("INSERT INTO " +Table+" "+strcolumn+ "  VALUES ("+join(elements,",","'")+")");
            s.execute("INSERT INTO " +Table+" "+strcolumn+ "  VALUES ("+join(elements,",","'")+")");
  
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Metodo Privato per l'inserimento delle tabelle. Genera la stringa delle colonne
     * per creare una tabella nel db
     * @param column array di stringhe contenete il nome delle colonne da inserire
     * @return 
     */
    private static String GetColumnToString(String[] column)
    {
        if(column == null || column.length == 0)
            return "";
        String ris="("+column[0];
        for(int i=1; i< column.length;i++)
        {
            ris+=(", ");
            ris+=column[i];
        }
        ris+=") " ;
        return ris;
        
    }
    
    /**
     * Metodo che crea una nuova tabella nel DB
     * @param sql Stringa della query per creare la tabella
     * @return true in caso di successo, false in caso negativo 
     */
    public static boolean CreateTable(String sql)
    {
        try{
            Statement s = conn.createStatement();
            s.execute(sql);
  
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
   
  /**
   * @return ArrayList&lt;Utente&gt; Ritorna la lista di tutti gli utenti che esistono
   * nel db.
   */  
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
    
  /**
   * Verifica l'esistenza di messaggi non letti
   * @param receiver Id-Username dell' utente da verificare
   * @return il numero di messaggi
   */
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
  
  /**
   * Metodo Privato: Ritorna tutte le tuple di una determinata tabella
   * @param Tables tabella in cui cercare le tuple
   * @return ResultSet con il risultato o null in caso di fallimento
   */
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
  
  /**
   * Verifica e ritorna l'utente con un username o password. 
   * @param User Username dell' utente
   * @param Pass_Chiaro FACOLTATIVA: verifica della password per login
   * @return Utente che corrisponde ai dati inseriti
   */
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
    
    
   
    
    /**
     * @deprecated : Metodo di verifica dell' esistenza di un utente nel db 
     * @param User
     * @return True: esiste False: non esiste
     */
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
  
    /**
     * Metodo utilizzato per sopperire alla mancanza dell' autoincrement.
     * @param Table Nome della tabella
     * @param column Nome della colonna di Integer
     * @return Valore più grande della colonna
     */
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
    
    /**
     * 
     * @param id Id del messaggio
     * @return Messaggio il messaggio che corrisponde all' id inserito
     */
    public static Messaggio GetMessaggioByID(int id)
    {
        try{
            //System.out.println("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1"); //non so perchè vuole 1 anzichè column  
            s.execute("SELECT * FROM MESSAGGIO \n" +
                      "WHERE  ID = " + id );
            ResultSet rs = s.getResultSet();

            if(rs.next())
            {
               return new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @param id id della sessione
     * @param user nome dell' utente
     * @return Sessione restituisce la sessione con un determinato id che appartiene a un determinato utente (possibilmente collegato)
     */
    public static Sessione GetSessioneById(int id, Utente user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{ 
          s.execute("SELECT * FROM SESSIONE WHERE ID = "+id+" AND USERNAME= '"+user.Username+" '" );
          ResultSet rs = s.getResultSet();
          if(rs.next())
          {
              return new Sessione(rs.getInt("ID"),rs.getInt("IDMESS"), rs.getString("USERNAME"));
          }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        
        return null;
    
    }
    
    /**
     * Restituisce la lista dei messaggi 
     * @param reader Username dell' utente richiedente
     * @param limit numero massimo di messaggi da leggere
     * @return ArrayList&lt;Messaggio&gt; Lista messaggi
     */
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
                list.add(new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD")));
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
    
    
    /**
     * Effettua il join di un array di string nella formattazione adatta per l'inserimento
     * nel database
     * @param elements di Stringhe
     * @param glue Carattere di separazione (es. ",")
     * @param comma Carattere di virgolette (es " o ' )
     * @return Stringa formattata per essere inserita nelle quary insert del db
     */
    public static String join(DataBaseElement[] elements, String glue,String comma)
    {
      int k = elements.length;
      if ( k == 0 )
      {
        return null;
      }
      StringBuilder out = new StringBuilder();
      //out.append( comma+s[0]+comma );
      out.append( elements[0].GetElementForDB(comma) );
      for ( int x=1; x < k; ++x )
      {
       // out.append(glue).append( comma+s[x]+comma);
        out.append(glue).append( elements[x].GetElementForDB(comma) );
      }
      return out.toString();
    }

    /** 
     * @deprecated
     * Restituisce il valore nel giusto formato come stringa o come int
     * per inserirlo al db
     * @param val valore da testare
     * @param comma virgole da usare
     * @return 
     * 
     */
    private static String getIntFormat(String val,String comma)
    {
        try{
            int iCheck = Integer.parseInt(val);
            return val;
        }
        catch(NumberFormatException e) { return comma +val+ comma; }
    }

    /**
     * Setta un messaggio come letto
     * @param MessageID Id del messaggio
     */
    public static void setReadMessage(int MessageID) {
        
        try{ 
          s.execute("UPDATE APP.MESSAGGIO SET \"ISREAD\" = true WHERE ID = "+MessageID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static ArrayList searchParola(String parola) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       ArrayList<String> list= new  ArrayList<String>();
        try{
//            System.out.println("SELECT * FROM DIZIONARIO \n" +
//                      "WHERE  PAROLA LIKE '"+parola+"'"); //non so perchè vuole 1 anzichè column  
            s.execute("SELECT * FROM DIZIONARIO \n" +
                      "WHERE  PAROLA LIKE '"+parola+"'");
            ResultSet rs = s.getResultSet();

            while(rs.next())
            {
                list.add(rs.getString("PAROLA"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }

    public static Messaggio GetRandomMessageExluseUser(String user) {
       try{
            //System.out.println("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1"); //non so perchè vuole 1 anzichè column  
            s.execute(  "select * from MESSAGGIO  \n" +
                        "WHERE \n" +
                        "SENDER != '"+user+"' AND\n" +
                        "RECEIVER != '"+user+"'\n" +
                        "ORDER BY RANDOM()");
            //s.setMaxRows(1);
            ResultSet rs = s.getResultSet();
            
            if(rs.next())
            {
               return new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
       return null;
    }

    static Sessione LoadSessione(String Codename, String User) {
        try{
            //System.out.println("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1"); //non so perchè vuole 1 anzichè column  
            System.out.println( "SELECT * FROM SESSIONE WHERE CODENAME = '"+Codename+"' AND USERNAME = '"+User+"'");
            s.execute(  "SELECT * FROM SESSIONE WHERE CODENAME = '"+Codename+"' AND USERNAME = '"+User+"'"+
            "FETCH FIRST 1 ROWS ONLY");
//            s.setMaxRows(1); //Attenzione: questo valore rimane attivo. se si usa quessto metodo va poi resettato
            ResultSet rs = s.getResultSet();
            
            if(rs.next())
            {
               return new Sessione(rs.getInt("ID"), rs.getInt("IDMESS"), rs.getString("USERNAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
       return null;
    }



    
}