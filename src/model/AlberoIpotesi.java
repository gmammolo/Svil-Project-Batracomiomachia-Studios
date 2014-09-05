/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.io.Serializable;


/**
 *
 * @author Giuseppe
 */
public class AlberoIpotesi implements Serializable {
  
    private static final long serialVersionUID = 2890710187018635891L;
    
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
            

    public void Undo() throws Exception {
       index = root.GetParentNode(index);
    }   
    
    public ListaIpotesi GetLista()
    {
        ListaIpotesi lista=new ListaIpotesi();
        if(root.HasNext())
            lista.AddNode(root.GenerateLista(index));
        return lista;
//        ListaIpotesi lista=new ListaIpotesi();
//        NodeIpotesi node=root;
//        for(int i=0; i< node.son.size(); i++)
//        {
//            if(node.son.get(i).HasNode(index))
//            {
//                node = node.son.get(i);
//                lista.AddNode(NodeIpotesi.Clone(node));
//                i=0;
//            }
//        }
//        return lista;
    }
    
    @Override
    public String toString()
    {
        return root.toStringNode(1);
    }
}
