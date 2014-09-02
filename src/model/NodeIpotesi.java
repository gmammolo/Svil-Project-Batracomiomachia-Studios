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

    /**
     * Solo per Lista ipotesi
     * @return 
     */
    public boolean Next() {
        return (son == null) ? false : true;
    }
    
    public NodeIpotesi GetNext()
    {
        if(son.size()<1)
            return null;
        return son.get(son.size()-1);
    }
}