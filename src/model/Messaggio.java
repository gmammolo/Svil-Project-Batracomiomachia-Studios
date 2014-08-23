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
import model.DataBaseElement.Type;
import java.util.ArrayList;

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
        if(Cifrato=="")
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
    public static void AddMessaggio(String Testo,String Cifrato, Metodo_Criptaggio CryptMethod, String Lingua, String Sender, String Receiver, boolean  IsRead, String metakey)
    {
        String[] column =new String[]{"TESTO","CIFRATO","METODO_CRIPTAGGIO","LINGUA","SENDER","RECEIVER","ISREAD","METAKEY"};
        Database.Insert("MESSAGGIO",column, new DataBaseElement[]
                                            {
                                               // new DataBaseElement(Type.INT, Id),
                                                new DataBaseElement(Type.STRING, Testo),
                                                new DataBaseElement(Type.STRING, Cifrato),
                                                new DataBaseElement(Type.STRING, CryptMethod.name()),
                                                new DataBaseElement(Type.STRING, Lingua),
                                                new DataBaseElement(Type.STRING, Sender),
                                                new DataBaseElement(Type.STRING, Receiver),
                                                new DataBaseElement(Type.BOOL, String.valueOf(IsRead)),
                                                new DataBaseElement(Type.STRING, metakey)
                                            });
    }
   
    /**
     * Metodo di inserimento nel DB
     * @deprecated consigliato l'utilizzo di Messaggio.AddMessaggio(...)
     */
    public void Insert()
    {
        String[] column =new String[]{"TESTO","CIFRATO","METODO_CRIPTAGGIO","LINGUA","SENDER","RECEIVER","ISREAD","METAKEY"};
        if(Id >= 0)
            Database.Insert("MESSAGGIO",column, new DataBaseElement[]
                                            {
                                               // new DataBaseElement(Type.INT, Id),
                                                new DataBaseElement(Type.STRING, Testo),
                                                new DataBaseElement(Type.STRING, Cifrato),
                                                new DataBaseElement(Type.STRING, CryptMethod.name()),
                                                new DataBaseElement(Type.STRING, Lingua),
                                                new DataBaseElement(Type.STRING, Sender),
                                                new DataBaseElement(Type.STRING, Receiver),
                                                new DataBaseElement(Type.BOOL, String.valueOf(IsRead)),
                                                new DataBaseElement(Type.STRING, metakey)
                                            });
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
       Cifrato = Informazioni_Cifratura.InfoMethod.Crypt(Testo);
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
        return Database.GetMessageList(reader, limit);
     }
     
     /**
      * Setta un messaggio come già letto
      * @param MessageID Id del messaggio da settare
      */
    public static void ReadMessage(int MessageID) {
        Database.setReadMessage(MessageID);
    
    }
    
    /**
     * 
     * @param id
     * @return Messaggio restituisce il messaggio in base al suo ID
     */
    public static Messaggio GetMessaggioById(int id)
    {
        return Database.GetMessaggioByID(id);
    }
    
    /**
     * Crea la tabella MEssaggio nel DB
     */
    public static void CreateTable()
    {
        Database.CreateTable("create table \"APP\".MESSAGGIO"+
                            "("+
                                "ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
                                "TESTO VARCHAR(250),"+
                                "CIFRATO VARCHAR(250),"+
                                "METODO_CRIPTAGGIO VARCHAR(30),"+
                                "LINGUA VARCHAR(30),"+
                                "SENDER VARCHAR(32),"+
                                "RECEIVER VARCHAR(32),"+
                                "ISREAD BOOLEAN default FALSE not null"+
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
    

}



