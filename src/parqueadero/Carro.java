/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

/**
 *
 * @author Estudiante
 */
public class Carro {
    private String placa;
    private String marca;
    

    public Carro(String placa, String marca) {
        this.placa = placa;
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Carro{" + "placa=" + placa + ", marca=" + marca + '}';
    }

    public boolean equals(Carro carro) {
        return carro.marca.equals(this.marca) && carro.placa.equals(this.placa);  //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
