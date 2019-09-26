/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class Factura {
    private Carro vehiculo;
    private Cliente cliente;
    private Date horaIngreso;
    private Date horaSalida;
    private double costo;

    public Factura(Carro vehiculo, Cliente cliente, Date horaIngreso, Date horaSalida, double costo) {
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.costo = costo;
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

    public Date getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
    
    
}
