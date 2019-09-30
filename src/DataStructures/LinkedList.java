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
public class LinkedList<T>{
    private Node head;
    private Node tail;
    
    public LinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    public LinkedList(Node n){
        this.head = n;
        this.tail = n;
    }
    
    public Node getHead(){return head;}
    public Node getTail(){return tail;}

    public void setHead(Node head) {
        this.head = head;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
    
    public boolean isEmpty(){return head == null;}
    
    public void pushFront(T key){
        head = new Node(key, head);
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
            Node<T> node = new Node<T>(key);
            head = node;
            tail = node;
        }else{
            Node aux = head;
            while(aux.next() != null){
                aux = aux.next();
            }
            aux.setNext(new Node(key));
            tail = aux.next();
        }
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
        if(tail == null) return null;
        return (T) tail.getData();
    }
    
    public void popBack(){
        if(!isEmpty()){
            if(head.next() == null){ head = null; tail = null;
            }else{
                Node aux = head;
                while(aux.next().next() != null){
                    aux = aux.next();
                }
                aux.setNext(null);
            }
        }
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
    
    public void removeNode(Node<T> node){
        
    }    
    public static void main(String[] args){
        LinkedList<Integer> lista = new LinkedList<Integer>();
        lista.pushBack(1);
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