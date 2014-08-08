/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectsvil;

import db.Database;
import db.Utente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import user.*;

/**
 *
 * @author giuseppe
 */
public class ProjectSvil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Database.Initializate();
        
        
        JFrame login=new LoginForm();
        login.setVisible(true);
        
        //Utente.AddUtente("Pluto", "pass");
        
        Utente.GetAllUtenti();
        
        
        
        //Database.Close();
        //Utente.AddUtente("pippo", "pass");
        //Utente.AddUtente("pluto", "pass");
        
        //System.exit(0);


        
        
//         try {
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//        } catch (ClassNotFoundException cnfe) {
//            System.err.println("Derby driver not found.");
//        }
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:derby://localhost/test;create=true;user=APP;pass=APP");
//            Statement s = conn.createStatement();
//            //s.execute("CREATE TABLE test (id integer primary key not null, text varchar(32))");            
//            //s.execute("INSERT INTO test VALUES (1, 'hello world!')");
//            s.execute("SELECT * FROM test");
//            ResultSet rs = s.getResultSet();
//            while (rs.next()) {
//                System.out.println("Derby says: "+rs.getString("text"));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        

        
        
    }
    
}
