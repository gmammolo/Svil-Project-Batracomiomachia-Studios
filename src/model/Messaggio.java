/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import cifrario.CifrarioCesare;
import cifrario.CifrarioSostituzione;
import cifrario.CryptSystem;
import cifrario.Metodo_Criptaggio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Database.conn;

/**
 *
 * @author giuseppe
 */



public class Messaggio {


    
    public Integer Id;
    public String Testo;
    public String Cifrato;
    public Metodo_Criptaggio CryptMethod;
    public String Lingua;
    public String Sender;
    public String Receiver;
    public boolean IsRead;
    
    private String metakey;
    
    public CifraturaOptions Informazioni_Cifratura;
    
    public Messaggio(String testo)
    {
        this(-1,testo,"",Metodo_Criptaggio.NESSUNO.name(),"","","");
    }
    
    public Messaggio(String testo, String cifrato, String metodo_criptaggio, String lingua, String sender, String receiver)
    {
        this(-1,testo,cifrato,metodo_criptaggio,lingua,sender,receiver);
    }
    
    public Messaggio(Integer id, String testo, String cifrato, String metodo_criptaggio, String lingua,String sender, String receiver)
    {
        this(id,testo,cifrato,metodo_criptaggio,"",lingua,sender,receiver, false);
    }
    
    public Messaggio(Integer id, String testo, String cifrato, String metodo_criptaggio, String metakey, String lingua,String sender, String receiver, boolean isRead)
    {
        Id=id;
        Testo=testo;
        Cifrato=cifrato;
        CryptMethod= Metodo_Criptaggio.valueOf(metodo_criptaggio);
        this.metakey=metakey;
        Lingua=lingua;
        Sender=sender;
        Receiver=receiver;
        IsRead=isRead;
        Informazioni_Cifratura=new CifraturaOptions();
        if("".equals(Cifrato))
            Cifra();
        
    }
    
    
    /**
     * Aggiunge un nuovo messaggio al DB ottenuto dalle seguenti info
     * @param Testo
     * @param Cifrato
     * @param CryptMethod
     * @param Lingua
     * @param Sender
     * @param Receiver
     * @param IsRead
     * @param metakey 
     */
    public static void AddMessaggio(String Testo,String Cifrato, String CryptMethod, String Lingua, String Sender, String Receiver, boolean  IsRead, String metakey)
    {
        try {
            PreparedStatement pr=conn.prepareStatement("INSERT INTO MESSAGGIO (TESTO,CIFRATO,METODO_CRIPTAGGIO,LINGUA,SENDER,RECEIVER,ISREAD,METAKEY) VALUES (? , ? , ? , ? , ? , ? , ? , ?)");
            pr.setString(1, Testo);
            pr.setString(2, Cifrato);
            pr.setString(3, CryptMethod);
            pr.setString(4, Lingua);
            pr.setString(5, Sender);
            pr.setString(6, Receiver);
            pr.setBoolean(7, IsRead);
            pr.setString(8, metakey);
            
            pr.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
   
    }
   
    /**
     * Metodo di inserimento nel DB
     */
    public void Insert()
    {
        Messaggio.AddMessaggio(Testo, Cifrato, CryptMethod.name(), Lingua, Sender, Receiver, IsRead, metakey);

    }
    
    /**
     * Metodo che cifra il messaggio con il metodo scelto.
     * I risultati verranno salvati nelle variabili interne.
     */
    public void Cifra()
    {
        if(Informazioni_Cifratura.InfoMethod == null )
        {
            metakey="";
            Cifrato="";
            return;
        }
       metakey =  Informazioni_Cifratura.InfoMethod.GenerateKey();
       Cifrato = Informazioni_Cifratura.InfoMethod.Crypt(Testo.toLowerCase());
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

        ArrayList<Messaggio> list= new  ArrayList<Messaggio>();
        
        try {    
            PreparedStatement pr=conn.prepareStatement("SELECT * FROM MESSAGGIO \n" +
                    "WHERE  RECEIVER = ? \n" +
                    "ORDER BY ISREAD ASC,ID\n" +
                    "LIMIT ?");
            pr.setString(1, reader.Username);
            pr.setInt(2, limit);
            pr.execute();
            ResultSet rs = pr.getResultSet();
            while(rs.next())
            {
                list.add(new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
     }
     
     /**
      * Setta un messaggio come già letto
      * @param MessageID Id del messaggio da settare
      */
    public static void ReadMessage(int MessageID) {
        try {
            PreparedStatement pr=conn.prepareStatement("UPDATE MESSAGGIO SET ISREAD = true WHERE ID = ? ");
            pr.setInt(1, MessageID);
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    /**
     * 
     * @param id
     * @return Messaggio restituisce il messaggio in base al suo ID
     */
    public static Messaggio GetMessaggioById(int id)
    {
        try {
            PreparedStatement pr=conn.prepareStatement("SELECT * FROM MESSAGGIO WHERE  ID = ?");
            pr.setInt(1, id);
            pr.executeQuery();
            ResultSet rs=pr.getResultSet();
            if(rs.next())
            {
               return new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Crea la tabella MEssaggio nel DB
     */
    public static void CreateTable()
    {
        Database.CreateTable("create table IF NOT EXISTS MESSAGGIO"+
                            "("+
//                                "ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
                                "ID INT not null AUTO_INCREMENT ,"+
                                "TESTO VARCHAR(250),"+
                                "CIFRATO VARCHAR(250),"+
                                "METODO_CRIPTAGGIO VARCHAR(30),"+
                                "LINGUA VARCHAR(30),"+
                                "SENDER VARCHAR(32),"+
                                "RECEIVER VARCHAR(32),"+
                                "ISREAD BOOLEAN default FALSE not null,"+
                                "PRIMARY KEY(ID)"+
                            ")");
    }
    
    
    /**
     * Classe contenente tutte le informazioni per la cifratura
     */
    protected class CifraturaOptions {
        
        /**
         * Metodo di Cifratura scelto.
         * Da qui è possibile accedere a tutte le opzioni necessarie per cifrare
         * e decifrare il messaggio
         */
        public CryptSystem InfoMethod;
        
        /**
         * Setta InfoMethod a seconda del metodo di cifratura scelto
         */
        public CifraturaOptions()
        {
            if(CryptMethod ==  Metodo_Criptaggio.CIFRARIO_DI_CESARE)
            {
                if(metakey != null && !metakey.equals(""))
                    InfoMethod = new CifrarioCesare(metakey);
                else
                    InfoMethod = new CifrarioCesare(); 
            }
            else if(CryptMethod ==  Metodo_Criptaggio.SOSTITUZIONE)
            {
                if(metakey != null && !metakey.equals(""))
                    InfoMethod = new CifrarioSostituzione(metakey);
                else
                    InfoMethod = new CifrarioSostituzione(); 
            }
            
            
        }
    }
    
    
    public static Messaggio GetRandomMessageExluseUser(String user)
    {
        try {
            PreparedStatement pr = conn.prepareStatement("select * from MESSAGGIO  \n" +
                    "WHERE\n" +
                    "SENDER != ? AND \n" +
                    "RECEIVER != ?\n" +
                    "ORDER BY RAND() LIMIT 0,1");
            pr.setString(1, user);
            pr.setString(2, user);
            pr.execute();
            ResultSet rs= pr.getResultSet();
            if(rs.next())
            {
                return new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD"));
            }
            else
                throw new Exception("Nessun Messaggio valido salvato nel db");

        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    

}



