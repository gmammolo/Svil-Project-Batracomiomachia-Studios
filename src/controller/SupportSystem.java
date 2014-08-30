/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import model.Dizionario;

/**
 * Classe che gestisce i vari supporti.
 * Attualmente supporta solo il dizionario (italiano)
 * @author Giuseppe
 */
public class SupportSystem {
    
    
    /**
     * Manda una parola automaticamente al Dizionario Italiano
     * @param parola 
     */
    public static void inviaParola(String parola)
    {
        Dizionario.AddParola(parola);
    }
    
    /**
     * 
     * @param parola
     * @param dizionario attualmente superflup in quanto esiste solo il dizionario italiano
     */
    public static void inviaParola(String parola, String dizionario)
    {
        Dizionario.AddParola(parola);
    }
    
    /**
     * Cerca una parola nel dizionario
     * @param parola
     * @return 
     */
    public static ArrayList<String> chiediParola(String parola)
    {
       
      String ris="";
       for(int i=0; i< parola.length(); i++)
       {
           if(parola.charAt(i) >= 'A' && parola.charAt(i) <= 'Z')
               ris+=parola.charAt(i);
           else
               ris+='_';
       }
        
        return Dizionario.searchParola(ris);
    }
}
