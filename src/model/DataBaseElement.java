/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.sun.org.apache.bcel.internal.generic.ObjectType;

/**
 * Classe generata con lo scopo di contenente gli elementi che andranno inseriti nel db
 * con le informazioni necessarie per poterle caricare correttamente nel db
 * @author Giuseppe
 */
public class DataBaseElement {
    
    /**
     * Tipo dell' object da inserire nel DB.
     * QUesta enumerazione serve per gestire al meglio i vari elementi che hanno
     * sintassi di inserimento diverse nel DB
     */
    public enum Type {
        NULL,
        INT,
        STRING,
        BOOL 
    }
    
    private Type type;
    private Object element;
    
    public DataBaseElement(Type type, Object element )
    {
        this.type=type;
        this.element=element;
    }
    
    /**
     * Restituisce l'elemento con la giusta sintassi per essere inserito nel DB
     * @param comma Virgolette da utilizzare nel caso in cui siano richieste nella sintassi dell' elemento
     * @return Stringa con la giusta sintassi per essere inserita nel db
     */
    public String GetElementForDB(String comma)
    {
//            int iCheck = Integer.parseInt((String)element);
//            return (String)element;
            if( type == Type.INT )
                return element.toString();
            else if(type == Type.STRING)
                return comma +element+ comma;
            else if(type == Type.BOOL)
                return (String)element;

            else return "";
    }
    
}


