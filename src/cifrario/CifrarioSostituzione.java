/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cifrario;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


/**
 *
 * @author Giuseppe
 */
public class CifrarioSostituzione extends CryptSystem {
    
    protected Map KeyCrypt;
    protected Map KeyDecrypt;
    
    public CifrarioSostituzione()
    {
        KeyCrypt = new Hashtable();
        KeyDecrypt = new Hashtable();
    }

    public CifrarioSostituzione(String metakey)
    {
        super(metakey);
    }
    
    @Override
    public String Crypt(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return CryptDecrypt(s,KeyCrypt);
    }

    @Override
    public String Decrypt(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return CryptDecrypt(s,KeyDecrypt);
    }
    
    private String CryptDecrypt(String s, Map Key)
    {
          String result = "";
          for ( int i = 0 ; i < s.length() ; i++ ) {
              char el = s.charAt(i);
              if(Key.containsKey(el))
              {
                  el=(char)Key.get(el);
              }
              result+=el;
          }
          return result;
    }

    @Override
    public String GenerateKey() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GenerateCryptKey();
        return Serialize();
    
    }
    
    
    private void GenerateCryptKey()
    {
        String alph = alfabeto;
        int tmpleng = length;
        while(tmpleng > 0)
        {
            Random rand = new Random();
            int randomKey = rand.nextInt(tmpleng +1);
            int randomValue = rand.nextInt(tmpleng +1);
            //genera chiave
            KeyCrypt.put(alph.charAt(randomKey), alph.charAt(randomValue));
            KeyDecrypt.put(alph.charAt(randomValue), alph.charAt(randomKey));
            
            //sposta a fine stringa e riduce array
            alph= alph.replace(alph.charAt(randomKey), alph.charAt(tmpleng-1));
            tmpleng--;
            
        }
    }
    
    public String Serialize()
    {
        String result="";
        for (Object key : KeyCrypt.keySet())
        {
            result+=String.valueOf(key)+String.valueOf(KeyCrypt.get(key));
        }
        return result;
    }
    
    public void Deserialize(String metakey)         
    {
        KeyCrypt = new Hashtable();
        KeyDecrypt = new Hashtable();
        
        for(int i=0; i< metakey.length(); i+=2)
        {
            int j=i+1;
            KeyCrypt.put(i, j);
            KeyDecrypt.put(j, i);
        }
        
    }
    
}
