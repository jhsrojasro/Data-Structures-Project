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
    private double tarifa;

    public Factura(Servicio servicio, Calendar horaSalida, double tarifa) {
        this.servicio = servicio;
        this.horaSalida = horaSalida;
        this.tarifa = tarifa;
        this.costo = 0.0;
        if(servicio.getHoraIngreso().get(Calendar.HOUR_OF_DAY) == horaSalida.get(Calendar.HOUR_OF_DAY)){
            costo += horaSalida.get(Calendar.MINUTE) - servicio.getHoraIngreso().get(Calendar.MINUTE);
        }else{
            costo += 60-servicio.getHoraIngreso().get(Calendar.MINUTE);
            costo += horaSalida.get(Calendar.HOUR_OF_DAY) - servicio.getHoraIngreso().get(Calendar.HOUR_OF_DAY) -1;
            costo += horaSalida.get(Calendar.MINUTE);
        }
        this.costo *= tarifa;
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

    @Override
    public String toString() {
        return "Factura{" + "servicio=" + servicio + ", horaSalida=" + horaSalida.get(Calendar.HOUR_OF_DAY)+":"+horaSalida.get(Calendar.SECOND) + ", costo=" + costo + ", tarifa=" + tarifa + '}';
    }

    
}
