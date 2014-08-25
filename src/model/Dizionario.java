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
import model.DataBaseElement.Type;

/**
 *
 * @author Giuseppe
 */
public class Dizionario {
    
    public Integer ID;
    public String Parola;
    
    public Dizionario(String parola)
    {
        this(0,parola);
    }
    
    public Dizionario(Integer id, String parola)
    {
        ID=id;
        Parola=parola;
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
   
    /**
     * Metodo di inserimento nel DB
     * @deprecated consigliato l'utilizzo di Messaggio.AddMessaggio(...)
     */
    public void Insert()
    {
        String[] column =new String[]{"PAROLA"};
            Database.Insert("DIZIONARIO",column, new DataBaseElement[]
                                            {
                                               // new DataBaseElement(Type.INT, Id),
                                                new DataBaseElement(Type.STRING, Parola),
                                            });
    }
    
    public static void CreateTable()
    {

        Database.CreateTable("create table \"APP\".DIZIONARIO\n" +
                                "(\n" +
                                "ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
                                "PAROLA VARCHAR(20)\n" +
                                ")"
              );
    }
    
    
    public static void GenerateFromTxt(String url) throws FileNotFoundException, IOException
    {
        FileReader f = new FileReader(url);
        BufferedReader b =new BufferedReader(f);
        String p="";
        while(( p = b.readLine()) != null)
        {
            AddParola(p);
        }
    }
}
