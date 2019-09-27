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
public class ArrayCola<T>{
    // Atributos
    private int size, begin, end, count;
    private T[] array;
    
    //Métodos
    public ArrayCola(){
        this.size = 5;
        this.begin = 0;
        this.end = 0;
        this.count = 0;
        this.array =(T[])new Object[size];
    }
    
    public ArrayCola(int size){
        this.size = size;
        this.begin = 0;
        this.end = 0;
        this.count = 0;
        this.array =(T[])new Object[size];
    }
    
    public boolean isFull(){
        return count == size; 
    }
    public boolean isEmpty(){
        return count == 0;
    }
    
    public void push_back(T numero){
        if(!isFull()){
            array[end] = numero;
            end = (end+1)%size;
            count = count == size ? 0 : count + 1;
        }else{
           throw new RuntimeException("La cola está llena"); 
        }
        
    }
    
    public T pop_front(){
        if(!isEmpty()){
            T x = array[begin];
            begin = (begin+1)%size;
            count--;
            return x;
        }
        throw new RuntimeException("La cola está vacia");
    }
}
