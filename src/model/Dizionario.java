/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.DataBaseElement.Type;

/**
 *
 * @author Giuseppe
 */
public class Dizionario {
    /*
     *Derby Wildcard:
     * % più caratteri
     * _ un solo carattere
     */

    public Dizionario()
    {
        
    }
    
    public static void AddParola(String Parola)
    {
        String[] column =new String[]{"PAROLA"};
        Database.Insert("DIZIONARIO",column, new DataBaseElement[]
                                            {
                                               // new DataBaseElement(Type.INT, Id),
                                                new DataBaseElement(Type.STRING, Parola),
                                            });
    }
   
//    /**
//     * Metodo di inserimento nel DB
//     * @deprecated consigliato l'utilizzo di Messaggio.AddMessaggio(...)
//     */
//    public void Insert()
//    {
//        String[] column =new String[]{"PAROLA"};
//            Database.Insert("DIZIONARIO",column, new DataBaseElement[]
//                                            {
//                                               // new DataBaseElement(Type.INT, Id),
//                                                new DataBaseElement(Type.STRING, Parola),
//                                            });
//    }
//    
    public static void CreateTable()
    {

        Database.CreateTable("create table \"APP\".DIZIONARIO\n" +
                                "(\n" +
                                "PAROLA VARCHAR(30) not null primary key\n" +
                                ")"
              );
    }
    
    
    public static void GenerateFromTxt(String url) throws FileNotFoundException, IOException
    {
        //C:\Users\Giuseppe\Documents\NetBeansProjects\ProjectSvil\DbTable
        
        FileReader f = new FileReader(url);
        BufferedReader b =new BufferedReader(f);
        String p="";
        while(( p = b.readLine()) != null)
        {
            AddParola(p);
        }
    }
    
    public static ArrayList<String> searchParola(String s)
    {
       return Database.searchParola(s);   
    }
}
