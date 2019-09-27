/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class Factura {
    private Servicio servicio;
    private Calendar horaSalida;
    private double costo;

    public Factura(Servicio servicio, Calendar horaSalida, double costo) {
        this.servicio = servicio;
        this.horaSalida = horaSalida;
        this.costo = costo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Calendar getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Calendar horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }    
}
