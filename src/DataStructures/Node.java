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
public class Node<T>{
    private T data;
    private Node next;
    
    public Node(){
        this.data = null;
        this.next = next;
    }
    
    public Node(T data){
        this.data = data;
        this.next = null;
    }
    
    public Node(T data, Node next){
        this.data = data;
        this.next = next;
    }
    
    public T getData(){return data;}
    public void setData(T data){this.data = data;}
    public Node next(){return next;}
    public void setNext(Node n){this.next = n;}
}
