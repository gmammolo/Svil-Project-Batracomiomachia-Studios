/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cifrario;

import java.util.* ;
public class CifrarioCesare extends CryptSystem {

    protected static String alfabeto = new String("abcdefghilmnopqrstuvz") ;
    protected static int length = alfabeto.length();
    
    public String GenerateKey() {return String.valueOf(GenerateKeyCesare());}
    
    
    public Integer GenerateKeyCesare()
    {
        return 1 + (int)(Math.random()*length); 
    }
    
    public String Crypt( String s , String chiave ) 
    {
        return Crypt(  s , Integer.parseInt(chiave) );
    }
    
    public String Crypt( String s , int chiave ) {

                String stringa = new String("");
                int index ;
                int j = s.length();
                char at ;
                for ( int i = 0 ; i < j ; i++ ) {
                    at = s.charAt(i);
                    index = alfabeto.indexOf(at);
                    if(index == -1)
                        stringa = stringa + at ;
                    else
                    {
                        at = alfabeto.charAt( (index+chiave) % length );
                        stringa = stringa + at ;
                    }
               }
               return stringa ;
       }
    
    
    public String Decrypt( String s , String chiave ) 
    {
        return Decrypt(  s , Integer.parseInt(chiave) );
    }
    
    public String Decrypt(String s , int chiave)
    {
        String stringa = new String("");
        int index ;
        int j = s.length();
        char at ;
        for ( int i = 0 ; i < j ; i++ ) {
            at = s.charAt(i);
            index = alfabeto.indexOf(at);
            if(index == 1)
                stringa = stringa + at ;
            else
            {
                at = alfabeto.charAt( (index+length-chiave) % length );
                stringa = stringa + at ;
            }         
        }
        return stringa;
    }
       
       

}