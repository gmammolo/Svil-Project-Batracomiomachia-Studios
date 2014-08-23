/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author anna
 */
public class populatetable {
     public static void creaTabelle(Statement st) throws SQLException{
       

        st.execute(   "CREATE TABLE MESSAGGIO(" +
                "IDMESSAGE      INT         NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)," +
                "TESTO          LONG VARCHAR NOT NULL UNIQUE," +
                "CIFRATO        LONG VARCHAR NOT NULL," +
                "CRIPTAGGIO     VARCHAR(40) NOT NULL," +
                "LINGUA         VARCHAR(40) NOT NULL," +
                "SENDER         VARCHAR(40) NOT NULL," +
                "RECEIVER       VARCHAR(40) NOT NULL," +
                "ISREAD         VARCHAR(20) NOT NULL," +
                "PRIMARY KEY(IDMESSAGE))");
        
        st.execute(   "CREATE TABLE UTENTI(" +
                "IDUSER         INT         NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)," +
                "USERNAME       VARCHAR(33) NOT NULL UNIQUE," +
                "PASSWORD       VARCHAR(33) NOT NULL," +
                
                "PRIMARY KEY(IDUSER))");
        
        addUser("sender", "sender", st);
        addUser("receiver", "receiver", st);
        addUser("spy", "spy", st);
     }
     
     public static void addUser(String nome, String password, Statement st) throws SQLException{
        
        st.execute("INSERT INTO UTENTI (USERNAME, PASSWORD) VALUES ('"+nome+"', '"+password+"')");
        
    }
     
     public static void addmessage(String testo, String cifrato, String criptaggio, String lingua, String sender, String receiver, String isread, Statement st) throws SQLException{
        
        st.execute("INSERT INTO UTENTI (TESTO, CIFRATO, CRIPTAGGIO, LINGUA, SENDER, RECEIVER, ISREAD) VALUES ('"+testo+", "+cifrato+", "+criptaggio+", "+lingua+", "+sender+", "+receiver+"', '"+isread+"')");
        
    }
}
