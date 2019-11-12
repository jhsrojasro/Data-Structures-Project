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
public class Pila<T>{
    private DLinkedList<T> lista;

    public Pila() {
        this.lista = new DLinkedList<T>();
    }
    
    public void push(T value){
        lista.pushBack(value);
    }
    
    public T pop(){
        if(!isEmpty()){
            T aux = lista.topBack();
            lista.popBack();
            return aux;
        }else throw new RuntimeException("La pila está vacia"); 
    }
    
    public boolean isEmpty(){
        return lista.isEmpty();
    }
    
}
