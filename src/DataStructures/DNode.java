/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author Sebastian Rojas
 */
public class DNode<T>{
    private T data;
    private DNode next;
    private DNode prev;
    
    public DNode(){
        this.data = null;
        this.next = null;
        this.prev = null;
    }
    
    public DNode(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public DNode(T data, DNode next, DNode prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    
    
    
    public T getData(){return data;}
    public void setData(T data){this.data = data;}
    public DNode next(){return next;}
    public void setNext(DNode n){this.next = n;}
    public DNode prev(){return prev;}
    public void setPrev(DNode n){this.prev = n;}
}
