/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import parqueadero.*;
import DataStructures.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que utiliza el parqueadero y simula un dia de solicitudes.
 * @author Sebastian Rojas
 */
public class Main {
    private Parqueadero parqueadero;
    private String[] nombres;
    private String[] marcas;
    public final int prueba = 100000;
    public final int numEspacios = 250;
    public final int numPisos = 100;
    
    
    public static void main(String[] args){
        Main main = new Main();
        //main.generarSolicitudesIngreso();
        //main.generarSolicitudesFacturas();
        main.probarIngreso();
        //main.probarFacturacion();
        //main.generarReservas(100);
        //main.probarReservar();
        //main.probarArbol();
        
        
    }
    public Main(){
        this.cargarMarcas();
        this.cargarNombres();
        this.parqueadero = new Parqueadero(numPisos,numEspacios);
        //this.generarReservas(10);
        
    }
    
    public void probarReservar(){
        try {
            long start = System.currentTimeMillis();
            Scanner scan = new Scanner(new File("src\\data\\solicitudesReservas.txt"));
            ArregloDinamico<Reserva> array = new ArregloDinamico<Reserva>();
            scan.useDelimiter(",|\\n");
            int n=0;
            while(scan.hasNext()){
                Lugar l = new Lugar(scan.nextInt(), scan.next().charAt(0), scan.nextInt());
                array.add(new Reserva(new Carro(scan.next(), scan.next())
                        , new Cliente(scan.next(), scan.next()), l));
                n++;
            }
            for (int i = 0; i < n; i++) {
                parqueadero.reservar(array.get(i));
            }
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void probarFacturacion(){
        try {
            Scanner scan = new Scanner(new File("src\\data\\facturar.txt"));
            scan.useDelimiter(",|\\n");
            int[] p = new int[this.prueba] , n = new int[this.prueba];
            char[] s = new char[this.prueba];
            for (int i = 0; i < this.prueba; i++) {
                p[i] = scan.nextInt();
                s[i] = scan.next().charAt(0);
                n[i] = scan.nextInt();   
            }
            long start = System.currentTimeMillis();
            for (int i = 0; i < this.prueba; i++) {
                parqueadero.facturar(p[i], s[i], n[i]);
            }
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("facturacion: "+timeElapsed+"ms");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void probarIngreso(){
        try {
            Ingreso[] array = new Ingreso[this.prueba];
            Scanner scan = new Scanner(new File("src\\data\\solicitudesIngreso.txt"));
            scan.useDelimiter(",|\\n");
            for (int i = 0; i < this.prueba; i++) {
                array[i] = new Ingreso(new Carro(scan.next(),scan.next()),new Cliente(scan.next(),scan.next()));
            }
            long start = System.currentTimeMillis();
            for (int i = 0; i < this.prueba; i++) {
                parqueadero.ingresarVehiculo(array[i]);// Ingreso al parqueadero
            }
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("ingreso: "+timeElapsed+"ms");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    public void probarArbol(){
        long start = System.currentTimeMillis();
        DNode<Servicio> head = parqueadero.getServicios().getHead();
        while(head != null){
            parqueadero.getArbol().insertar(head.getData());
            head = head.next();
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Insercion en arbol: "+timeElapsed+" ms");
        start = System.currentTimeMillis();
        head = parqueadero.getServicios().getHead();
        while(head != null){
            Servicio s  = parqueadero.getArbol().find(head.getData().getVehiculo().getPlaca());
            head = head.next();
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Busqueda en arbol: "+timeElapsed+" ms");
        start = System.currentTimeMillis();
        head = parqueadero.getServicios().getHead();
        while(head != null){
            parqueadero.getArbol().borrar(head.getData());
            head = head.next();
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Eliminar en arbol: "+timeElapsed+" ms");  
    }
*/
    
    public String randomMarca(){
        Random r = new Random();
        return marcas[r.nextInt(50)];
    }
    
    public String randomNombre(){
        Random r = new Random();
        return nombres[r.nextInt(50)];
    }
    
    public void generarReservas(int n){
        PrintWriter write = null;
        try {
            write = new PrintWriter(new File("src\\data\\reservas.txt"));
            String aux;
            int c = 0;
            for(int i=0; i<numPisos && c < n; i++){
                for(int j=0; j<4 && c < n; j++){
                    for(int k=0; k<numEspacios && c < n; k++){
                        aux = i+","+parqueadero.seccionChar(j)+","+k+","+generarIngreso();
                        write.println(aux);
                        c++;
                    }
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            write.close();
        }
        
    }
    
    public void generarSolicituresReservas(int n){
        PrintWriter write = null;
        try {
            write = new PrintWriter(new File("src\\data\\solicitudesReservas.txt"));
            String aux;
            int c = 0;
            for(int i=0; i<numPisos && c < n; i++){
                for(int j=0; j<4 && c < n; j++){
                    for(int k=0; k<numEspacios && c < n; k++){
                        aux = i+","+parqueadero.seccionChar(j)+","+k+","+generarIngreso();
                        write.println(aux);
                        c++;
                    }
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            write.close();
        }
        
    }
    
    public void generarSolicitudesFacturas(){
        PrintWriter write = null;
        try {
            write = new PrintWriter(new File("src\\data\\facturar.txt"));
            int piso = 0, n=0;
            char s = 'A';
            for (int i = 0; i < this.prueba; i++) {
                write.print(piso+","+s+","+n+"\n");
                if(n == this.numEspacios - 1){
                    if(s == 'D'){ piso++;s = 'A';
                    }else if(s == 'A')s = 'B';
                    else if(s == 'B') s = 'C';
                    else s = 'D';
                    n = 0;   
                }else{
                    n++;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            write.close();
        }
    }
    
    public void generarIngresosReservas(int n, String[] s){
        PrintWriter write = null;
        try {
            write = new PrintWriter(new File("src\\data\\ingresoConReservas.txt"));
            for (int i = 0; i < n/2; i++) {
                write.println("1,"+s[i]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            write.close();
        }
    }
    
    public void generarSolicitudesIngreso(){
        try {
            PrintWriter write1 = new PrintWriter(new File("src\\data\\solicitudesIngreso.txt"));
            for (int i = 0; i < this.prueba; i++) {
                write1.println(this.generarIngreso());
            }
    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String generarIngreso(){
        String s = "";
        s += this.randomPlaca()+",";
        s += this.randomMarca()+",";
        s += this.randomNombre()+",";
        s += this.randomId();
        return s;
    }
    
    public void cargarNombres(){
        try {
            Scanner scan = new Scanner(new File("src\\data\\nombres.txt"));
            this.nombres = new String[50];
            for(int i=0; i<50;i++){
                nombres[i] = scan.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se pudieron leer los nombres");
        }
    }
    
    public void cargarMarcas(){
        try {
            Scanner scan = new Scanner(new File("src\\data\\marcas.txt"));
            this.marcas = new String[50];
            for(int i=0; i<50;i++){
                marcas[i] = scan.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se pudieron leer las marcas");
        }
    }
    
    public String randomId(){
        String id = "";
        Random r = new Random();
        for(int i=0; i<10; i++)
            id += (char)(r.nextInt(10) + '0');
        return id;
    }
    
    public String randomPlaca(){
        String s = "";
        Random r = new Random();
        s += (char)(r.nextInt(26) + 'A');
        s += (char)(r.nextInt(26) + 'A');
        s += (char)(r.nextInt(26) + 'A');
        s += '-';
        s += (char)(r.nextInt(10) + '0');
        s += (char)(r.nextInt(10) + '0');
        s += (char)(r.nextInt(10) + '0');
        return s;
    }
    
    
    
}
