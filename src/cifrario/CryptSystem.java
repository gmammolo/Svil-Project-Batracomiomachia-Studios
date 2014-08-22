/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cifrario;

/**
 *
 * @author Giuseppe
 */
public abstract class CryptSystem {
    
    protected static String alfabeto = new String("abcdefghilmnopqrstuvz") ;
//    protected static String alfabeto = new String("abcde") ;
    protected static int length = alfabeto.length();
    
    public abstract String Crypt( String s );
    
    public abstract String Decrypt( String s );
    
    public abstract String GenerateKey() ;
    
    public abstract String Serialize() ;
    
    public abstract void Deserialize(String Metakey) ;
    
    public CryptSystem()
    {
        
    }
    
    public CryptSystem(String Metakey)
    {
        if(!Metakey.equals(""))
            Deserialize(Metakey);
    }
}
