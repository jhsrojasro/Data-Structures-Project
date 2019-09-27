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
public class ArregloDinamico<T> {
    //Atributos:
    private int size;
    private int capacidad;
    private T[] array;
    
    //Metodos

    public ArregloDinamico() {
        this.size = 0;
        this.capacidad = 1;
        this.array = (T[])new Object[capacidad];
    }
    
    public void add(T value){
        if(isFull()){
            T[] a = this.array.clone();
            capacidad *= 2;
            this.array = (T[])new Object[capacidad];
            for(int i=0; i<size; i++){
                array[i] = a[i];
            }
        }    
        array[size] = value;
        size++;
    }
    
    public void remove(int index){
        if(index < size && index >= 0){
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i+1];
            }
            size--;
            array[size] = null;
        }
        
    }
    
    public T get(int index){
        return array[index];
    }
    
    
    public boolean isFull(){
        return size == capacidad;
    }
    
    
}
