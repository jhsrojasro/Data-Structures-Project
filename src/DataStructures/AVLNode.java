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
public class AVLNode {
    private DNode<Servicio> data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(DNode<Servicio> data, AVLNode left, AVLNode right, int height) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public AVLNode(DNode<Servicio> data, int height) {
        this.data = data;
        this.height = height;
    }

    public DNode<Servicio> getData() {
        return data;
    }

    public void setData(DNode<Servicio> data) {
        this.data = data;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
    
}
