/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import ArbolB.*;
import edd.proyecto2.*;
import static edd.proyecto2.ReadJson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Daniel
 */
public class Grafo {

    LinkedList<Vertice> vertices;
    private int NumeroVertice;
    private double[][] MatrizPeso;

    public Grafo(int mx) {
        this.vertices = new LinkedList<>();
        MatrizPeso = new double[mx][mx];
        for (int i = 0; i < mx; i++) {
            for (int j = 0; j < mx; j++) {
                MatrizPeso[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        NumeroVertice = 0;
    }

    public void addVertice(long id, String categoria, String nombre, double latitud, double longitud) {
        Vertice a = existe(nombre);
        if (a != null) {
            System.out.println("Ya existe el vertice");
            return;
        }
        getVertices().add(new Vertice(id, categoria, nombre, latitud, longitud, getNumeroVertice()));
        setNumeroVertice(getNumeroVertice() + 1);
    }

    public void enlazar(String a, String b, double peso, String unidad, double precio, String moneda) {
        Vertice origen, destino;
        origen = existe(a);
        destino = existe(b);
        if (origen == null || destino == null) {
            System.out.println("Error al enlazar, no se encontro el origen o destino");
            return;
        }
        Arista camino = new Arista(peso, unidad, precio, moneda, b);
        Arista cami = new Arista(peso, unidad, precio, moneda, a);
        
        origen.getDetalle().add(camino);
        destino.getDetalle().add(cami);

        getMatrizPeso()[origen.getNumVertice()][destino.getNumVertice()] = peso;
        getMatrizPeso()[destino.getNumVertice()][origen.getNumVertice()] = peso;
    }

    //busca el vertice y nos lo retorna si lo encuentra
    public Vertice existe(String i) {
        for (Vertice e : this.vertices) {
            if (e.getNombre().equals(i)) {
                return e;
            }
        }
        return null;
    }

    public Vertice Buscar(String i) {
        for (Vertice e : this.vertices) {
            if (e.getNombre().equals(i)) {
                return e;
            }
        }
        return null;
    }

    public void recorrer() {
        for (Vertice e : this.vertices) {
            e.imprimirObjeto();
        }
    }

    public Usuario VerticeCercano() {
        int k = minimo.conductorCerca(); ///ReadJson
        if (k == -1) {
            System.out.println("-----------------");
            System.out.println("--No hay conductores disponibles-");
            System.out.println("-----------------");
            return null;
        }
        for (Vertice e : this.vertices) {
            if (k == e.getNumVertice()) {
                System.out.print("El vertice mas cercano es el: ");
                System.out.println(e.getNombre() + " " + e.getNumVertice());
                Usuario sele = ConductorCercano(e);
                e.getConductor().remove(sele);
                return sele;
            }
        }
        return null;
    }
     public Vertice BuscarNumero(int i) {
        for (Vertice e : this.vertices) {
            if (e.getNumVertice() == i) {
                return e;
            }
        }
        return null;
    }

    public Usuario ConductorCercano(Vertice cerca) {
        for (Usuario e : cerca.getConductor()) {
            if (e.getDisponibilidad()) {
                System.out.println("EL conductor disponible es: " + e.getNombre());
                return e;
            }
        }
        System.out.println("No encontro conductor disponible en este vertice");
        System.out.println("Se procede buscar otro vertice");
        return VerticeCercano();
    }
    
    public void imprimirMatriz() {
        System.out.println("");
        System.out.println("MATRIZ DE ADYACENCIA");
        for (double matriz[] : getMatrizPeso()) {
            System.out.println(Arrays.toString(matriz));
        }
    }

    public LinkedList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(LinkedList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public int getNumeroVertice() {
        return NumeroVertice;
    }

    public void setNumeroVertice(int NumeroVertice) {
        this.NumeroVertice = NumeroVertice;
    }

    public double[][] getMatrizPeso() {
        return MatrizPeso;
    }

    public void setMatrizPeso(double[][] MatrizPeso) {
        this.MatrizPeso = MatrizPeso;
    }

}
