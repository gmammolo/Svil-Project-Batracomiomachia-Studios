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
    
    public class NodeIpotesi
    {
        protected char a;
        protected char b;
        
        protected NodeIpotesi parent;
        protected ArrayList<NodeIpotesi> son;
        
        
        public NodeIpotesi(char a, char b)
        {
            this.a=a;
            this.b=b;
            son=new ArrayList<NodeIpotesi>();
        }
        
        public NodeIpotesi(String value)
        {
            this.a=value.charAt(0);
            this.b=value.charAt(value.length()-1);
            son=new ArrayList<NodeIpotesi>();
        }
        
        public void addNode(NodeIpotesi node)
        {
            node.parent = this;
            son.add(node);
        }
        

        public String toString()
        {
            return a+" => "+b;
        }
    }
            
}
