/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import java.util.Calendar;

/**
 *
 * @author Sebastian Rojas
 */
public class Servicio {
    private int piso;
    private char seccion;
    private int numero;
    private Calendar horaIngreso;
    private boolean reservado;
    private Carro vehiculo;
    private Cliente cliente;

    public Servicio(int piso, char seccion, int numero, Calendar horaIngreso) {
        this.piso = piso;
        this.seccion = seccion;
        this.numero = numero;
        this.horaIngreso = horaIngreso;
    }
    
    public Servicio(int piso, char seccion, int numero, Calendar horaIngreso, Carro vehiculo, Cliente cliente) {
        this.piso = piso;
        this.seccion = seccion;
        this.numero = numero;
        this.horaIngreso = horaIngreso;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
    }

    public Carro getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Carro vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public char getSeccion() {
        return seccion;
    }

    public void setSeccion(char seccion) {
        this.seccion = seccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Calendar getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Calendar horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    @Override
    public String toString() {
        return "Servicio{" + "piso=" + piso + ", seccion=" + seccion + ", numero=" + numero + ", horaIngreso=" + horaIngreso.get(Calendar.HOUR_OF_DAY)+":"+horaIngreso.get(Calendar.MINUTE)+", reservado=" + reservado + ", vehiculo=" + vehiculo + ", cliente=" + cliente + '}';
    }
    
    
}
