/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import ArbolB.*;
import java.util.LinkedList;

/**
 *
 * @author Daniel
 */
public class Vertice {

    private long id;
    private String categoria;
    private String nombre;
    private double latitud;
    private double longitud;
    private int numVertice;
    private LinkedList<Arista> detalle;
    private LinkedList<Usuario> conductor;

    public Vertice(long id, String categoria, String nombre, double latitud, double longitud, int numVertice) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.detalle = new LinkedList<>();
        this.numVertice = numVertice;
        this.conductor = new LinkedList<>();
    }

    public void imprimirObjeto() {
        System.out.println("Id: " + this.id);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Latitud: " + this.latitud);
        System.out.println("Longitud: " + this.longitud);
        System.out.println("Numero de vertice: " + getNumVertice());
        for (Arista a : this.detalle) {
            System.out.println("---------------------------");
            System.out.println("Destino: " + a.getDestino());
            System.out.println("Peso: " + a.getPeso());
            System.out.println("Unidad: " + a.getUnidad());
            System.out.println("Precio: " + a.getPrecio());
            System.out.println("Moneda: " + a.getMoneda());
        }
        System.out.println("---------------------------");
        for (Usuario c: this.conductor){
            System.out.println("---------------------------");
            System.out.print(c.getKey() + " ");
            System.out.println(c.getNombre());
            System.out.println(c.getDisponibilidad());
            System.out.println(c.getTelefono());
        }
        System.out.println();
        System.out.println();
        System.out.println("===============================");
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public LinkedList<Arista> getDetalle() {
        return detalle;
    }

    public void setDetalle(LinkedList<Arista> detalle) {
        this.detalle = detalle;
    }

    public int getNumVertice() {
        return numVertice;
    }

    public void setNumVertice(int numVertice) {
        this.numVertice = numVertice;
    }

    public LinkedList<Usuario> getConductor() {
        return conductor;
    }

    public void setConductor(LinkedList<Usuario> conductor) {
        this.conductor = conductor;
    }
}
