/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArbolB;

/**
 * 
 * @author Daniel
 */
public class Viaje {
    private int id;
    private String inicio;
    private String fin;
    private String fecha;

    public Viaje(int id, String inicio, String fin, String fecha) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
