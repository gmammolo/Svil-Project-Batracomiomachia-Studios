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
public class NodeIpotesi
{
    protected char a;
    protected char b;

    protected NodeIpotesi parent;
    protected ArrayList<NodeIpotesi> son;


    public NodeIpotesi(char a, char b)
    {
        this.a=a;
        this.b=String.valueOf(b).toUpperCase().charAt(0);
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

    @Override
    public String toString()
    {
        return a+" => "+b;
    }

    
    public String toStringNode()
    {
        String ris=toString();
        for(int i=0;i<son.size();i++)
        {
            ris+="\t"+son.get(i).toStringNode();
        }
        ris+="\n";
        return ris;
    }
    
    /**
     * Solo per Lista ipotesi
     * @return 
     */
    public boolean Next() {
        return (son == null || son.size() == 0) ? false : true;
    }
    
    public NodeIpotesi GetNext()
    {
        if(son.size()<1)
            return null;
        return son.get(son.size()-1);
    }
    
    public String Serialize()
    {
        String ris=toString()+String.valueOf(son.size())+";";
        for(int i=0; i<son.size();i++)
        {
            if(son.size()>i && son.get(i)!=null)
                ris+=son.get(i).Serialize();
        } 
        return ris;  
    }
    
    public NodeIpotesi Deserialize(String text)
    {
        if(text.length()<7)
            return new NodeIpotesi(' ',' ');
        NodeIpotesi n=new NodeIpotesi(text.substring(0,6));
        Integer element=Integer.parseInt(text.substring(6,  text.indexOf(";")));     
        for(int i=0; i<element; i++)
        {
            text = text.substring( String.valueOf( element ).length() + 7 ) ;
            n.son.add(n.Deserialize( text ));
            n.son.get(i).parent = n;
        }
        
        return n;
        
    }
    
    public static NodeIpotesi Clone(NodeIpotesi Node)
    {
        return new NodeIpotesi(Node.a,Node.b);
    }
   
}