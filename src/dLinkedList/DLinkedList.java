/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dLinkedList;

import DataStructures.*;

/**
 *
 * @author Sebastian Rojas
 */


public class DLinkedList<T>{
    private DNode head;
    private DNode tail;
    
    public DLinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    public DLinkedList(DNode n){
        this.head = n;
        this.tail = n;
    }

    public void setHead(DNode head) {
        this.head = head;
    }

    public void setTail(DNode tail) {
        this.tail = tail;
    }
    
    public DNode<T> getHead(){return head;}
    public DNode<T> getTail(){return tail;}
    
    public boolean isEmpty(){return head == null;}
    
    public void pushFront(T key){
        head = new DNode(key, head, null);
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
        if(isEmpty()){
            DNode<T> node = new DNode<T>(key,null,null);
            head = node;
            tail = node;
        }else{
            DNode<T> node = new DNode<T>(key, null , tail);
            tail.setNext(node);
            tail = node;
        }
    }
    
    public void printList(){
        DNode aux = head;
        while(aux != null){
            System.out.println(aux.getData()+" ");
            aux = aux.next();
        }
    }
    
    public void printListRecursive(DNode node){
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
        DNode aux = head;
        while(aux.next() != null){
            if(aux.getData() == data) return true;
            aux = aux.next();
        }
        return false;
    }
    
    public void erase(T data){
        DNode aux = head;
        while(aux.next().getData() != data && aux.next() != null){
            aux.setNext(aux.next().next()); 
        }
    }
    
    
    
    public static void main(String[] args){
        DLinkedList<Integer> lista = new DLinkedList<Integer>(new DNode(1));
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