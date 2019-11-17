/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

/**
 *
 * @author Sebastian Rojas
 */
public class Lugar implements Comparable{
    public int piso;
    public char seccion;
    public int numero;

    public Lugar(int piso, char seccion, int numero) {
        this.piso = piso;
        this.seccion = seccion;
        this.numero = numero;
    }
    
    public int compareTo(Lugar lugar) {
        if(this.piso == lugar.piso && this.seccion == lugar.seccion && this.numero == lugar.numero) return 0;
        if(this.piso < lugar.piso) return -1;
        else if(this.piso > lugar.piso) return 1;
        else if(this.seccion < lugar.seccion) return -1;
        else if(this.seccion > lugar.seccion) return 1;
        else if(this.numero < lugar.numero) return -1;
        else return 1;
    }

    @Override
    public String toString() {
        return piso + "," + seccion + "," + numero;
    }
    
    @Override
    public int compareTo(Object o) {
        Lugar lugar = (Lugar) o;
        return compareTo(lugar);
    }
    
}
