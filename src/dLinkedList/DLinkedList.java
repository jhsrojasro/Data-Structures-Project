/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dLinkedList;

import DataStructures.LinkedList;

/**
 *
 * @author Sebastian Rojas
 */
class Node<T>{
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

public class DLinkedList<T>{
    private Node head;
    private Node tail;
    
    public DLinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    public DLinkedList(Node n){
        this.head = n;
        this.tail = n;
    }
    
    public Node getHead(){return head;}
    public Node getTail(){return head;}
    
    public boolean isEmpty(){return head == null;}
    
    public void pushFront(T key){
        head = new Node(key, head, null);
    }
    
    public T topFront(){
        if(!isEmpty()) return (T) head.getData();
        throw new RuntimeException("La lista está vacia");
    }
    
    public void popFront(){
        if(!isEmpty()) head = head.next();
        else throw new RuntimeException("La lista está vacia");
    }
    
    public void pushBack(T key){
        Node<T> node = new Node<T>(key, null , tail);
        tail.setNext(node);
        tail = node;
    }
    
    public void printList(){
        Node aux = head;
        while(aux != null){
            System.out.println(aux.getData()+" ");
            aux = aux.next();
        }
    }
    
    public void printListRecursive(Node node){
        System.out.println(node.getData());
        if(node.next()!= null) printListRecursive(node.next());
    }
    
    public T topBack(){
        return (T) tail.getData();
    }
    
    public void popBack(){
        tail.prev().setNext(null);
        tail = tail.prev();
    }
    
    public boolean findData(T data){
        boolean find = false;
        Node aux = head;
        while(aux.next() != null){
            if(aux.getData() == data) return true;
            aux = aux.next();
        }
        return false;
    }
    
    public void erase(T data){
        Node aux = head;
        while(aux.next().getData() != data && aux.next() != null){
            aux.setNext(aux.next().next()); 
        }
    }
    
    public static void main(String[] args){
        DLinkedList<Integer> lista = new DLinkedList<Integer>(new Node(1));
        lista.pushBack(2);
        lista.pushBack(3);
        lista.pushBack(4);
        lista.pushBack(5);
        lista.pushBack(6);
        lista.pushBack(7);
        lista.pushBack(8);
        lista.pushBack(9);
        lista.pushBack(10);
        lista.printListRecursive(lista.getHead());
    }
}