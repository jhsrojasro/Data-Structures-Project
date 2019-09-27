/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import java.util.Calendar;

/**
 *
 * @author Estudiante
 */
public class Reserva {
    private Carro carro;
    private Cliente cliente;
    private Calendar fecha;
    private double recargo;

    public Reserva(Carro carro, Cliente cliente, Calendar fecha, double recargo) {
        this.carro = carro;
        this.cliente = cliente;
        this.fecha = fecha;
        this.recargo = recargo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }
    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
