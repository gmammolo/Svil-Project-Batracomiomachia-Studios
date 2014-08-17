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
    
    public abstract String Crypt( String s , String chiave );
    
    public abstract String Decrypt( String s , String chiave );
    
    public abstract String GenerateKey() ;
    
    public CryptSystem()
    {
        
    }
}
