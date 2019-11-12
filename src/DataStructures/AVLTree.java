/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import parqueadero.*;

/**
 *
 * @author Sebastian Rojas
 */
public class AVLTree {
    private static class AVLNode{
        Servicio data;
        AVLNode left;
        AVLNode right;
        int height;

        public AVLNode(Servicio servicio, AVLNode left, AVLNode right, int height) {
            this.data = servicio;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        public AVLNode(Servicio dato, int height) {
            this.data = dato;
            this.height = height;
            this.left = null;
            this.right = null;
        }
    }
    
    private AVLNode root;
    
    //Metodos:

    public AVLTree() {
        this.root = null;
    }
    
    public AVLTree(Servicio root) {
        this.root = new AVLNode(root,0);
    }
    
    public void update(String placa, String nueva){
        
    }
    
    public Servicio find(String placa){
        return find(this.root, placa);
    }
    
    private Servicio find(AVLNode node, String placa){
        if(node.data.getVehiculo().getPlaca().equals(placa)) return node.data;
        else if(placa.compareTo(node.data.getVehiculo().getPlaca()) < 0){
            return find(node.left, placa);
        }else{
            return find(node.right, placa);
        }
    }
    
    public void insertar(Servicio n){
        this.root  = insertar(this.root, n);
    }
    
    private AVLNode insertar(AVLNode root, Servicio n){
        if(root == null){ root = new AVLNode(n, 0); return root;}
        if(n.compareTo(root.data) == 0) return root;
        if(root.data.compareTo(n) > 0){ 
            root.left = insertar(root.left,n);
            if(height(root.left) - height(root.right) == 2){
                if(root.left.data.compareTo(n) > 0){
                    root = rotacionSimpleDerecha(root);
                }else{
                    root = rotacionDobleIzqDer(root);
                }
            }
        }else if(root.data.compareTo(n) < 0){
            root.right = insertar(root.right, n);
            if(root.right.height - (root.left != null ? root.left.height : -1) == 2){
                if(root.right.data.compareTo(n) < 0) root = rotacionSimpleIzquierda(root);
                else root  = rotacionDobleDerIzq(root);
            }
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }
    
    private Servicio findMin(AVLNode root){
        if(root != null){
            AVLNode aux = root;
            while(aux.left != null) aux = aux.left;
            return aux.data;
        }else{
            return null;
        }
    }
    
    private Servicio findMax(AVLNode root){
        if(root != null){
            AVLNode aux = root;
            while(aux.right != null) aux = aux.right;
            return aux.data;
        }else{
            return null;
        }
    }
    
    public void borrar(Servicio n){
        this.root = borrar(this.root, n);
    }
    
    private AVLNode borrar(AVLNode root, Servicio n){
        if(root == null) return null;
        if(root.data.compareTo(n)  == 0){
            if(root.right != null){
                root.right = borrar(root.right, findMin(root.right));
            }else if(root.left != null){
                root.left = borrar(root.left, findMax(root.left));
            }else{
                root = null;
            }
            return root;
        }else if(root.data.compareTo(n) > 0){
            root.left = borrar(root.left, n);
            if(root.left == null && root.right != null && root.right.height == 1){
                root = rotacionSimpleIzquierda(root);
                root.height++;
            }
        }else{
            root.right = borrar(root.right, n);
            if(root.right == null && root.left != null && root.left.height == 1){
                root = rotacionSimpleDerecha(root);
                root.height++;
            }
        }
        return root;
    }
    
    public void print(){
        printPreOrder(this.root);
        System.out.println();
    }
    
    public void printHeight(){
        printPreOrderHeight(this.root);
        System.out.println();
    }
    
    private int height(AVLNode x){
        return (x == null ? -1 : x.height);
    }
    
    private void printPreOrderHeight(AVLNode root){
       if(root != null){
            if(root.left != null) printPreOrderHeight(root.left);
            System.out.print(root.height+" ");
            if(root.right != null) printPreOrderHeight(root.right);
        } 
    }
    
    private void printPreOrder(AVLNode root){
        if(root != null){
            if(root.left != null) printPreOrder(root.left);
            System.out.print(root.data.getVehiculo().getPlaca()+" ");
            if(root.right != null) printPreOrder(root.right);
        }
    }
    
    public boolean isEmpty(){
        return this.root == null;
    }
    
    private AVLNode rotacionSimpleDerecha(AVLNode root){
        root.height--;
        AVLNode temp = root.left;
        root.left = temp.right;
        temp.right = root;
        root = temp;
        return root;
    }
    
    private AVLNode rotacionSimpleIzquierda(AVLNode root){
        root.height--;
        AVLNode temp = root.right;
        root.right = temp.left;
        temp.left = root;
        root = temp;
        return root;
    }
    
    private AVLNode rotacionDobleIzqDer(AVLNode root){
        root.left = rotacionSimpleIzquierda(root.left);
        root = rotacionSimpleDerecha(root);
        return root;
        
    }
    
    private AVLNode rotacionDobleDerIzq(AVLNode root){
        root.right = rotacionSimpleDerecha(root.right);
        root = rotacionSimpleIzquierda(root);
        return root;
    }
    
    public static void main(String[] args) {
        AVLTree arbolito = new AVLTree();
    }
    
}
