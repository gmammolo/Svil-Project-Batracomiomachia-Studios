/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.Sessione;

/**
 *
 * @author Giuseppe
 */
public class GestoreIpotesi {
    
    public static void Undo()
    {
        
    }
    
    public static void SostituisciLettera(Sessione session, char a, char b)
    {

        if( (a>='a' && a <='z') && (b>='a' && b<='z') )
        {
            session.addKey(a, b);
        }
    }
    
    
}
