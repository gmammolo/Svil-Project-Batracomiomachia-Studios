/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import com.sun.org.apache.bcel.internal.generic.ObjectType;

/**
 *
 * @author Giuseppe
 */
public class DataBaseElement {
    
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


