/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import DataStructures.DNode;
import DataStructures.DLinkedList;
import DataStructures.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class Parqueadero {
    // Atributos:
    public final HashMap<Character,Integer> sections;
    /**
     * Numero de pisos del edificio.
     */
    private int numPisos;
    /**
     * Numero de lugares en cada seccion del parqueadero.
     */
    private int espaciosSeccion;
    /**
     * Calendario que contiene el dia y la hora.
     */
    private Calendar calendario;
    /**
     * Arreglo que contiene los espacios disponibles entre el principio de la lista y el final.
     */
    private ArregloDinamico<DNode<Servicio>> disponibles;
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
     * Lista de los espacios reservados en el parqueadero.
     */
    private DLinkedList<Servicio> reservados;
    /**
     * Lista de servicios actuales en el parqueadero.
     */
    private DLinkedList<Servicio> servicios;
    /**
     * Referencias a las secciones de la lista de servicios.
     */
    private Object[][] secciones;
    /**
     * Cantidad de segundos requeridos para tramitar una solicitud.
     */
    private final int salto = 15; 
    /**
     * Costo por minuto del servicio.
     */
    private final double tarifa = 50.0;
    
    /**
     *Arbol de referencias a los servicios. 
     */
    private AVLTree arbol;
    
    /**
     * Pila que almacena las ultimas facturas realizadas para hacer rollback.
     */
    private Pila<Servicio> rollBack;
    
    /**
     * Constructor por defecto del parqueadero, carga las reservaciones hechas el dia anterior y reserva los espacios de dichas
     * reservaciones, crea la clase calendar desde las 0 horas.
     */
    public Parqueadero(int numPisos, int espaciosSeccion){
        this.calendario = Calendar.getInstance();
        this.reservados = new DLinkedList<Servicio>();
        this.disponibles = new ArregloDinamico<DNode<Servicio>>();
        this.facturas = new ArregloDinamico<Factura>();
        this.servicios = new DLinkedList<Servicio>();
        this.solicitudesIngreso = new Cola<Ingreso>();
        this.solicitudesReservas = new ArregloDinamico<Reserva>();
        this.numPisos = numPisos;
        this.espaciosSeccion = espaciosSeccion;
        this.sections =  new HashMap<Character,Integer>();
        this.sections.put('A', 0);
        this.sections.put('B', 1);
        this.sections.put('C', 2);
        this.sections.put('D', 3);
        this.secciones = new Object[numPisos][4];
        this.arbol = new AVLTree();
        this.rollBack = new Pila<Servicio>();
        //cargarReservaciones();
        //ingresarVehiculo(new Ingreso(new Carro("ZXY-987","Renault"),new Cliente("Daniela Yepes Dimate","12387341")));
        //ingresarVehiculoReserva(new Ingreso(new Carro("ABC-123","Chevrolet"),new Cliente("Jose Miguel Aguilar","1015466300")));
        //System.out.println(reservados.topBack().getCliente().getNombre());
        //System.out.println(servicios.topFront().getCliente().getNombre());
        //facturas.add(facturar(1, servicios.topFront().getSeccion(), servicios.topFront().getNumero()));
        //System.out.println(facturas.getLast().getServicio().getCliente().getNombre());
        //System.out.println(disponibles.getLast().getData().getNumero());
    }
    
    /**
     * Metodo que ingresa un vehiculo en el parqueadero si hay espacio disponible en caso contrario lo pone en la cola de espera.
     * @param ingreso 
     */
    public void ingresarVehiculo(Ingreso ingreso){
        if(!this.isFull()){
            if(!disponibles.isEmpty()){
                disponibles.getLast().getData().setHoraIngreso((Calendar)calendario.clone());
                disponibles.getLast().getData().setCliente(ingreso.getCliente());
                disponibles.getLast().getData().setVehiculo(ingreso.getVehiculo());
                //arbol.insertar(disponibles.getLast().getData());
                disponibles.removeLast();
            }else{
                if(servicios.isEmpty() && reservados.isEmpty()){servicios.pushBack(this.siguienteEspacio(null));}
                else if(servicios.isEmpty()){servicios.pushBack(this.siguienteEspacio(reservados.topBack()));}
                else servicios.pushBack(this.siguienteEspacio(servicios.topBack()));
                servicios.topBack().setCliente(ingreso.getCliente());
                servicios.topBack().setVehiculo(ingreso.getVehiculo());
                servicios.topBack().setHoraIngreso((Calendar)calendario.clone());
                //arbol.insertar(servicios.topBack());
                if(servicios.topBack().getNumero() == 1){
                    secciones[servicios.topBack().getPiso() - 1]
                            [this.sections.get(servicios.topBack().getSeccion())] = servicios.getTail();
                }
            }
        }else{
            solicitudesIngreso.enqueue(ingreso);
        }
    }

    public AVLTree getArbol() {
        return arbol;
    }

    public void setArbol(AVLTree arbol) {
        this.arbol = arbol;
    }

    public Pila<Servicio> getRollBack() {
        return rollBack;
    }

    public void setRollBack(Pila<Servicio> rollBack) {
        this.rollBack = rollBack;
    }
    
    /**
     * Metodo que ingresa los vehiculos si tienen reserva.
     */
    public void ingresarVehiculoReserva(Ingreso ingreso){
        if(!reservados.isEmpty()){
            ((Servicio)reservados.getTail().getData()).setCliente(ingreso.getCliente());
            ((Servicio)reservados.getTail().getData()).setVehiculo(ingreso.getVehiculo());
            ((Servicio)reservados.getTail().getData()).setHoraIngreso((Calendar)calendario.clone());
            servicios.setHead(reservados.getTail());
            //arbol.insertar(reservados.topBack());
            reservados.popBack();
        }
    }
    /**
     * Metodo que genera la factura del servicio con el costo.
     */
    public Factura facturar(int piso, char seccion ,int numero){
        DNode<Servicio> nodo = (DNode<Servicio>)secciones[piso-1][sections.get(seccion)];
        //System.out.println(nodo.getData());
        while(nodo.getData().getNumero() != numero) nodo = nodo.next();
        disponibles.add(nodo);
        arbol.borrar(nodo.getData());
        rollBack.push(nodo.getData());
        return new Factura(nodo.getData(), (Calendar)calendario.clone(), this.tarifa);
    }
    
    public void rollBackServicio(){
        Servicio aux = rollBack.pop();
        disponibles.removeLast();
        arbol.insertar(aux);
        facturas.removeLast();
    }
    
    /**
     * Metodo que simula el paso del tiempo del parqueadero luego de atender una solicitud.
     */
    public void avanzar(){
        calendario.add(Calendar.SECOND, salto);
        while(reservados.topFront().getHoraIngreso().after(calendario)){
            disponibles.add(new DNode<Servicio>(reservados.topBack()));
            reservados.popBack();
        }
    }
    
    /**
     * Metodo que carga las reservaciones hechas el dia anterior y las ingresa en la lista de servicios reservados.
     */
    public void cargarReservaciones(){
        try{
            Scanner scan = new Scanner(new File("src\\data\\reservas.txt"));
            Pila<Reserva> pila = new Pila<Reserva>();
            scan.useDelimiter(",|\\n");     
            while(scan.hasNext()){
                Calendar aux = Calendar.getInstance();
                aux.set(Calendar.HOUR, scan.nextInt());
                aux.set(Calendar.SECOND, scan.nextInt());
                pila.push(new Reserva(new Carro(scan.next(), scan.next())
                        , new Cliente(scan.next(), scan.next()), aux));
            }
            
            while(!pila.isEmpty()){
                Reserva r = pila.pop();
                if(reservados.isEmpty())reservados.pushBack(siguienteEspacio(null));
                else reservados.pushBack(siguienteEspacio(reservados.topBack()));
                reservados.topBack().setReservado(true);
                reservados.topBack().setHoraIngreso(r.getFecha());
                reservados.topBack().setCliente(r.getCliente());
                reservados.topBack().setVehiculo(r.getCarro());
                if(reservados.topBack().getNumero() == 1){
                    secciones[reservados.topBack().getPiso() - 1]
                            [this.sections.get(reservados.topBack().getSeccion())] = reservados.getTail();
                }
            }
            
        }catch(FileNotFoundException e){
            System.out.println("No se puedieron cargar las reservas gg :'(");
        }
    }
    /**
     * Metodo que agrega una reserva a lista de reservas para el dia siguiente.
     */
    public void reservar(Reserva reserva){
        solicitudesReservas.add(reserva);
    }
    /**
     * Metodo auxiliar para calcular el siguiente espacio disponible en el parqueadero
     */
    public Servicio siguienteEspacio(Servicio anterior){
        if(anterior == null){
            return new Servicio(1,'A',1, Calendar.getInstance());
        }else{
            int p = anterior.getPiso(), n = anterior.getNumero()+1;
            char s = anterior.getSeccion();
            if(anterior.getNumero() == espaciosSeccion){
                n=1;
                switch(anterior.getSeccion()){
                    case 'D': p = anterior.getPiso() +1; s = 'A'; break;
                    case 'A': s = 'B'; break;
                    case 'B': s = 'C'; break;
                    case 'C': s = 'D'; break;
                }
            }
            return new Servicio(p, s, n, (Calendar) calendario.clone());
        }
    }
    
    public boolean isEmpty(){
        return reservados.isEmpty() && servicios.isEmpty();
    }
    
    /**
     * Metodo auxiliar para saber si el parqueadero ya esta en su maxima capacidad.
     */
    public boolean isFull(){
        if(servicios.isEmpty()) return false;
        Servicio aux = (Servicio)servicios.getTail().getData();
        return  disponibles.isEmpty() 
                && aux.getNumero() == this.espaciosSeccion
                && aux.getPiso() == this.numPisos
                && aux.getSeccion() == 'D';
    }
    
    public void imprimirReferencias(){
        for (int i = 0; i < numPisos; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(((DNode<Servicio>)secciones[i][j]).getData().getCliente());
            }
        }
    }

    public ArregloDinamico<DNode<Servicio>> getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(ArregloDinamico<DNode<Servicio>> disponibles) {
        this.disponibles = disponibles;
    }

    public ArregloDinamico<Factura> getFacturas() {
        return facturas;
    }
    
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero(2,10);
        Ingreso ingreso1 = new Ingreso(new Carro("ASD-123","Chevrolet"), new Cliente("Daniela Yepes","1293874"));
        Ingreso ingreso2 = new Ingreso(new Carro("ASD-124","Honda"), new Cliente("Sebastian Rojas","1293874"));
        parqueadero.ingresarVehiculo(ingreso1);
        parqueadero.ingresarVehiculo(ingreso2);
        parqueadero.servicios.printList();
    }
    public void setFacturas(ArregloDinamico<Factura> facturas) {
        this.facturas = facturas;
    }

    public ArregloDinamico<Reserva> getSolicitudesReservas() {
        return solicitudesReservas;
    }

    public void setSolicitudesReservas(ArregloDinamico<Reserva> solicitudesReservas) {
        this.solicitudesReservas = solicitudesReservas;
    }

    public Cola<Ingreso> getSolicitudesIngreso() {
        return solicitudesIngreso;
    }

    public void setSolicitudesIngreso(Cola<Ingreso> solicitudesIngreso) {
        this.solicitudesIngreso = solicitudesIngreso;
    }

    public DLinkedList<Servicio> getReservados() {
        return reservados;
    }

    public void setReservados(DLinkedList<Servicio> reservados) {
        this.reservados = reservados;
    }

    public DLinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(DLinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Object[][] getSecciones() {
        return secciones;
    }

    public void setSecciones(Object[][] secciones) {
        this.secciones = secciones;
    }
    /**
     * Metodo para guardar las reservas hechas en el dia 
     */
    
    
    
}
