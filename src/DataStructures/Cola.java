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
public class Cola<T> {
    private LinkedList<T> list;

    public Cola() {
        this.list = new LinkedList<T>();
    }
    
    public void enqueue(T value){
        list.pushBack(value);
    }
    
    public T topFront(){
        return list.topFront();
    }
    
    public T dequeue(){
        if(!list.isEmpty()){
            T r = list.topFront();
            list.popFront();
            return r;
        }else{
            throw new RuntimeException("La cola está vacia");
        }
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    
}
