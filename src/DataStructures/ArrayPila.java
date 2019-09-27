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
class ArrayPila<T> {
    //atributos:
    private int size;
    private int top;
    private T[] array;
    
    //metodos:
    public ArrayPila(){
        this.size = 5;
        this.top = 0;
        this.array = (T[])new Object[size];
        
    }
    
    public ArrayPila(int size){
        this.size = size;
        this.top = 0;
        this.array = (T[])new Object[size];

    }
    

    
    public void push(T dato){
        if(!isFull()){
            array[top] = dato;
            top++;
        }
    }
    
    public T pop(){
        if(!isEmpty()){
            return array[--top];
        }
        throw new RuntimeException("La pila está vacia");

    }
    
    public boolean isEmpty(){
        return top == 0;
    }
    
    public boolean isFull(){
        return top == size;
    }
    
    
}
