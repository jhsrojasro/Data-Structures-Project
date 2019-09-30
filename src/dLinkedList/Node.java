/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dLinkedList;

/**
 *
 * @author Sebastian Rojas
 */
public class Node<T>{
    private T data;
    private Node next;
    private Node prev;
    
    public Node(){
        this.data = null;
        this.next = null;
        this.prev = null;
    }
    
    public Node(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public Node(T data, Node next, Node prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    
    
    
    public T getData(){return data;}
    public void setData(T data){this.data = data;}
    public Node next(){return next;}
    public void setNext(Node n){this.next = n;}
    public Node prev(){return prev;}
    public void setPrev(Node n){this.prev = n;}
}
