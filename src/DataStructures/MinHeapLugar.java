/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import parqueadero.Lugar;
import parqueadero.Parqueadero;


/**
 *
 * @author Sebastian Rojas
 */
public class MinHeapLugar{
    private int maxSize;
    private int size;
    private Lugar[] array;
    
    public MinHeapLugar(int maxSize, int numPisos, int espaciosSeccion){
        this.maxSize = maxSize;
        this.size = 0;
        this.array =  new Lugar[maxSize];
        long start = System.currentTimeMillis();
        for(int i=0; i<numPisos; i++)
            for(int j=0; j<4; j++)
                for(int k=0; k<espaciosSeccion; k++)
                    insert(new Lugar(i,Parqueadero.seccionChar(j),k));
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("heap: "+timeElapsed+"ms");
                
    }
    
     public boolean isEmpty(){
        return size == 0;
    }
    
    public Lugar getMin(){
        return array[0];
    }
    
    public Lugar extractMin(){
        Lugar result = array[0];
        size--;
        array[0] = array[size];
        siftDown(0);
        return result;
    }
    
    public void siftUp(int index){
        Lugar temp = array[index];
        while(index > 0 && array[parent(index)].compareTo(array[index]) > 0){
            array[index] = array[parent(index)];
            index = parent(index);
        }
        array[index] = temp;
    }
    
    public void siftDown(int index){
        int maxIndex = index;
        int l = leftChild(index);
        if(l <= size && array[l].compareTo(array[maxIndex]) < 0) maxIndex = l;
        int r  = rightChild(index);
        if(r <= size && array[r].compareTo(array[maxIndex]) < 0) maxIndex = r;
        if(index != maxIndex){
            swap(index, maxIndex);
            siftDown(maxIndex);
        }
    }
    
    public void insert(Lugar data){
        if(size == maxSize) throw new RuntimeException("La cola está llena");
        else{
            array[size] = data;
            siftUp(size);
            size++;
        }
    }
    
    private void swap(int x, int y){
        Lugar temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
    
    private int parent(int index){
        return (index-1)/2;
    }
    
    private int leftChild(int index){
        return 2*index + 1;
    }
    
    private int rightChild(int index){
        return (index+1)*2;
    }
    
    
    
            
}
