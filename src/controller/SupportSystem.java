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
     * 
     * @param parola
     * @param dizionario attualmente superflup in quanto esiste solo il dizionario italiano
     */
    public static void inviaParola(String parola, String dizionario)
    {
        Dizionario.AddParola(parola);
    }
    
    public static ArrayList<String> chiediParola(String parola)
    {
        String par="";
        for(int i=0;i<parola.length();i++)
        {
            char a= parola.charAt(i);
            if(a>='A' && a<='Z' )
                par+="_";
            else
                par+=a;
        }
        
        return Dizionario.searchParola(par);
    }
}
