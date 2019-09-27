/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import DataStructures.*;

/**
 *
 * @author Estudiante
 */
public class Parqueadero {
    // Atributos:
    /**
     * Numero de pisos del edificio.
     */
    private int numPisos;
    /**
     * Numero de lugares en cada seccion del parqueadero.
     */
    private int espaciosSeccion;
    /**
     * Facturas realizadas el dia actual.
     */
    private ArregloDinamico<Factura> facturas;
    /**
     * Reservas hechas para el dia actual.
     */
    private ArregloDinamico<Reserva> reservas;
    /**
     * Cola de solicitudes de ingreso en caso de que el parqueadero este lleno.
     */
    private Cola<Ingreso> solicitudesIngreso;
    /**
     * Cola de solicitudes de reserva para el dia siguiente en el parqueadero.
     */
    private Cola<Reserva> solicitudesReserva;
    /**
     * Lista de servicios actuales en el parqueadero.
     */
    private LinkedList<Servicio> servicios;
    /**
     * Referencias a las secciones de la lista de servicios.
     */
    private Node<Servicio> secciones;
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
