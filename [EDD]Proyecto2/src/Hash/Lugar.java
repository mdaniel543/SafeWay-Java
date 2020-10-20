/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Hash;

/**
 * 
 * @author Daniel
 */
public class Lugar {
    private long id;
    private String categoria;
    private String nombre;
    private double latitud;
    private double longitud;

    public Lugar(long id, String categoria, String nombre, double latitud, double longitud) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public void imprimirObjeto(){
        System.out.println("Id: "+ this.id);
        System.out.println("Categoria: "+ this.categoria);
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Latitud: "+this.latitud);
        System.out.println("Longitud: "+this.longitud);
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
}
