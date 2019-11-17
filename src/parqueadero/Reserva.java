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
    private Lugar lugar;

    public Reserva(Carro carro, Cliente cliente, Lugar lugar) {
        this.carro = carro;
        this.cliente = cliente;
        this.lugar = lugar;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
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

    @Override
    public String toString() {
        return lugar + "," + carro + "," + cliente;
    }
    
    
}
