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

    public void Undo() {
        if(index!= null && index.parent!= null)
            index = index.parent;
    }

    public String Serialize() {
        return root.Serialize();
    }
   
    public void Deserialize(String text)
    {
        root=root.Deserialize(text);
        NodeIpotesi  n = root;
        while(n.son.size()> 0 )
            n =  n.son.get(n.son.size()-1);
        index = n ;
    }
       
    
    
    public ListaIpotesi GetLista()
    {
        ListaIpotesi lista=new ListaIpotesi();
        NodeIpotesi node=index;
        while(node.a != ' ')
        {
            lista.AddNode(NodeIpotesi.Clone(node));
            node = node.parent;
        }
        
        return lista;
    }
    
    @Override
    public String toString()
    {
        return root.toStringNode();
    }
}
