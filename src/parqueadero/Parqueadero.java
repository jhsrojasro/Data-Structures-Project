/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import DataStructures.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Capacidad total del parqueadero.
     */
    private int capacidad;
    /**
     * Calendario que contiene el dia y la hora.
     */
    private Calendar calendario;
    /**
     * MinHeap que contiene los espacios disponibles en el parqueadero.
     */
    private MinHeapLugar disponibles;
    /**
     * Facturas realizadas el dia actual.
     */
    private ArregloDinamico<Factura> facturas;
    /**
     * Reservas hechas para el dia actual.
     */
    private ArregloDinamico<Reserva> solicitudesReservas;
    /**
     * Cola de solicitudes de ingreso en caso de que el parqueadero este lleno.
     */
    private Cola<Ingreso> solicitudesIngreso;
    /**
     * Arreglo tridimencional que representa el parqueadero.
     */
    private Servicio[][][] servicios; 
    /**
     * Costo por minuto del servicio.
     */
    private final double tarifa = 50.0;
    
    /**
     *Arbol de referencias a los servicios. 
     */
    private AVLTree arbol;
    
    /**
     * Pila que almacena las últimos servicios ingresados para hacer rollback.
     */
    private Pila<Servicio> rollBack;
    
    /**
     * Constructor por defecto del parqueadero, carga las reservaciones hechas el dia anterior y reserva los espacios de dichas
     * reservaciones, crea la clase calendar desde las 0 horas.
     */
    public Parqueadero(int numPisos, int espaciosSeccion){
        this.calendario = Calendar.getInstance();
        this.numPisos = numPisos;
        this.espaciosSeccion = espaciosSeccion;
        this.capacidad = numPisos * 4 * espaciosSeccion;
        this.disponibles = new MinHeapLugar(this.capacidad, numPisos, espaciosSeccion);
        this.facturas = new ArregloDinamico<Factura>();
        this.servicios = new Servicio[numPisos][4][espaciosSeccion];
        //cargarReservaciones();
        this.solicitudesIngreso = new Cola<Ingreso>();
        this.solicitudesReservas = new ArregloDinamico<Reserva>();
        this.arbol = new AVLTree();
        this.rollBack = new Pila<Servicio>();
        
    }
    
    /**
     * Metodo que ingresa un vehiculo en el parqueadero si hay espacio disponible en caso contrario lo pone en la cola de espera.
     * @param ingreso 
     */
    public void ingresarVehiculo(Ingreso ingreso){
        if(!this.isFull()){
            Lugar l = disponibles.extractMin();
            Servicio s = new Servicio(l.piso,l.seccion, l.numero,Calendar.getInstance(),ingreso.getVehiculo(),ingreso.getCliente());
            servicios[l.piso][seccionInt(l.seccion)][l.numero] = s;
            arbol.insertar(s);
            rollBack.push(s);
        }else{
            solicitudesIngreso.enqueue(ingreso);
        }
    }

    public AVLTree getArbol() {
        return arbol;
    }

    public Pila<Servicio> getRollBack() {
        return rollBack;
    }
    
    /**
     * Metodo que ingresa los vehiculos si tienen reserva.
     */
    public void ingresarVehiculoReserva(int piso, char seccion, int numero){
        servicios[piso][seccionInt(seccion)][numero].setHoraIngreso((Calendar)this.calendario.clone());
    }
    /**
     * Metodo que genera la factura del servicio con el costo.
     */
    public Factura facturar(int piso, char seccion ,int numero){
        disponibles.insert(new Lugar(piso, seccion, numero));
        arbol.borrar(servicios[piso][seccionInt(seccion)][numero]);
        Factura factura = new Factura(servicios[piso][seccionInt(seccion)][numero],Calendar.getInstance(),this.tarifa);
        servicios[piso][seccionInt(seccion)][numero] = null;
        return factura;
    }
    
    public void rollBackServicio(){
        Servicio s = rollBack.pop();
        arbol.borrar(s);
        disponibles.insert(new Lugar(s.getPiso(), s.getSeccion(), s.getNumero()));
        servicios[s.getPiso()][seccionInt(s.getSeccion())][s.getNumero()] = null;
    }
    
    /**
     * Metodo que carga las reservaciones hechas el dia anterior y las ingresa en la lista de servicios reservados.
     */
    public void cargarReservaciones(){
        Servicio servicio;
        try{
            Scanner scan = new Scanner(new File("src\\data\\reservas.txt"));
            scan.useDelimiter(",|\\n");
            while(scan.hasNext()){
                servicio = new Servicio(scan.nextInt(),scan.next().charAt(0),scan.nextInt(),
                        new Carro(scan.next(), scan.next()), new Cliente(scan.next(),scan.next()));
                servicios[servicio.getPiso()][seccionInt(servicio.getSeccion())][servicio.getNumero()] = servicio;
                disponibles.extractMin();
            }        
        }catch(FileNotFoundException e){
            System.out.println("No se puedieron cargar las reservas gg :'(");
        }
    }
    
    public void guardarReservas(){
        try {
            PrintWriter write = new PrintWriter(new File("src\\data\\reservas.txt"));
            for(int i=0; i<solicitudesReservas.getSize(); i++){
                write.print(solicitudesReservas.get(i));
            }
            write.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que agrega una reserva a lista de reservas para el dia siguiente.
     */
    public void reservar(Reserva reserva){
        solicitudesReservas.add(reserva);
    }
    
    public boolean isEmpty(){
        return disponibles.isEmpty();
    }
    
    /**
     * Metodo auxiliar para saber si el parqueadero ya esta en su maxima capacidad.
     */
    public boolean isFull(){
        return this.disponibles.isEmpty();
    }
    


    public MinHeapLugar getDisponibles() {
        return disponibles;
    }

    public static int seccionInt(char x){
        if(x == 'A') return 0;
        if(x == 'B') return 1;
        if(x == 'C') return 2;
        else return 3;
    }

    public ArregloDinamico<Factura> getFacturas() {
        return facturas;
    }

    public Servicio[][][] getServicios() {
        return servicios;
    }

    public static char seccionChar(int x){
        if(x == 0) return 'A';
        if(x == 1) return 'B';
        if(x == 2) return 'C';
        else return 'D';
    }
    
}
