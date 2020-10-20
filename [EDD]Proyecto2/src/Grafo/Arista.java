/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafo;

/**
 * 
 * @author Daniel
 */
public class Arista {
    private double peso;
    private String unidad;
    private double precio;
    private String Moneda;
    private int destino;

    public Arista(double peso, String unidad, double precio, String Moneda, int destino) {
        this.peso = peso;
        this.unidad = unidad;
        this.precio = precio;
        this.Moneda = Moneda;
        this.destino = destino;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }
    
}
