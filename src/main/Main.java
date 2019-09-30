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
    
    
    public static void main(String[] args){
        Main main = new Main();
        //main.generarSolicitudesIngreso(1000000);
        main.generarSolicitudesFacturas(100, 25);
        main.probarIngreso(10000);
        //main.probarFacturacion(100, 25);
        
    }
    public Main(){
        this.cargarMarcas();
        this.cargarNombres();
        this.parqueadero = new Parqueadero(10000,25);
        //this.generarReservas(10);
        
    }
    
    public void probarFacturacion(int m, int espaciosSeccion){
        try {
            Scanner scan = new Scanner(new File("src\\data\\facturar.txt"));
            scan.useDelimiter(",|\\n");
            int[] p = new int[m] , n = new int[m];
            char[] s = new char[m];
            for (int i = 0; i < m; i++) {
                p[i] = Integer.parseInt(scan.next());
                s[i] = scan.next().charAt(0);
                n[i] = Integer.parseInt(scan.next());   
            }
            long start = System.currentTimeMillis();
            for (int i = 0; i < m; i++) {
                parqueadero.facturar(p[i], s[i], n[i]);
            }
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void probarIngreso(int m){
        try {
            Ingreso[] array = new Ingreso[m];
            Scanner scan = new Scanner(new File("src\\data\\solicitudesIngreso.txt"));
            scan.useDelimiter(",|\\n");
            for (int i = 0; i < m; i++) {
                array[i] = new Ingreso(new Carro(scan.next(),scan.next()),new Cliente(scan.next(),scan.next()));
            }
            //System.out.println(array[m-1].getCliente().getNombre());
            long start = System.currentTimeMillis();
            for (int i = 0; i < m; i++) {
                parqueadero.ingresarVehiculo(array[i]);
            }
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            //System.out.println(timeElapsed);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        String[] s =  new String[n];
        try {
            write = new PrintWriter(new File("src\\data\\reservas.txt"));
            
            int i=0, minutos = 0, horas = 5;
            while(i<n){
                if(minutos == 0) horas=(horas+1)%24;
                s[i] = this.generarIngreso();
                write.println(horas+","+minutos+","+s[i]); 
                minutos = (minutos +1)%60;
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            write.close();
        }
        this.generarIngresosReservas(n,s);
    }
    
    
    
    public void generarSolicitudesFacturas(int m, int espaciosSeccion){
       PrintWriter write = null;
        try {
            write = new PrintWriter(new File("src\\data\\facturar.txt"));
            int piso = 1, n=1;
            char s = 'A';
            for (int i = 0; i < m; i++) {
                write.print(piso+","+s+","+n+",");
                if(n == espaciosSeccion){
                    if(s == 'D'){ piso++;s = 'A';
                    }else if(s == 'A')s = 'B';
                    else if(s == 'B') s = 'C';
                    else s = 'D';
                    n = 1;   
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
    
    public void generarSolicitudesIngreso(int m){
        try {
            PrintWriter write1 = new PrintWriter(new File("src\\data\\solicitudesIngreso.txt"));
            for (int i = 0; i < m; i++) {
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
