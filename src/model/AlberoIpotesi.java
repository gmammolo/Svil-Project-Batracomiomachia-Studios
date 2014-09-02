/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Giuseppe
 */
public class AlberoIpotesi {
    
    protected NodeIpotesi root;
    protected NodeIpotesi index;
    
    public AlberoIpotesi()
    {
        this.root = new NodeIpotesi(' ', ' ');
        index = root;
    }
    
    public void CambiaNodoIndice(NodeIpotesi Nodo)
    {
        index=Nodo;
    }
    
        public  void AddNewNode(char a, char b)
    {    
        NodeIpotesi Nodo=new NodeIpotesi(a,b);
        index.addNode(Nodo);
        index = Nodo;
    }
    
    
    public  void AddNode(NodeIpotesi Nodo)
    {    
        index.addNode(Nodo);
        index = Nodo;
    }
            
     public static void GetIndex()
     {
         
     }

    void Undo() {
       index = index.parent;
    }
   
            
}
