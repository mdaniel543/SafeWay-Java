/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

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

    public void imprimirMatriz() {
        System.out.println("\n\nLA MATERIZ DE ADYACENCIA");
        for (double matriz[] : getMatrizPeso()) {
            System.out.println(Arrays.toString(matriz));
        }
    }

//    public void dibujar() {
//        LinkedList<Vertice> aux = new LinkedList<>();
//        StringBuilder sc = new StringBuilder();
//        sc.append("digraph G{\n");
//        for (int i = 0; i < this.vertices.size(); i++) {
//            Vertice temp = this.vertices.get(i);
//            if (aux.contains(temp) == false) {
//                aux.add(temp);
//                sc.append("node").append(temp.hashCode()).append(" [label=\"").append(temp.getId()).append("\"];\n");
//            }
//            for (int j = 0; j < temp.getDetalle().size(); j++) {
//                Arista temp2 = temp.getDetalle().get(j);
//                sc.append("node").append(temp.hashCode()).append("->node").append(temp.getAdyacentes().get(j).hashCode()).append("[label = \"").append(temp2.getPeso()).append("\"];\n");
//                if (!aux.contains(temp.getAdyacentes().get(j))) {
//                    aux.add(temp.getAdyacentes().get(j));
//                    sc.append("node").append(temp.getAdyacentes().get(j).hashCode()).append(" [label=\"").append(temp.getAdyacentes().get(j).getId()).append("\"];\n");
//                } 
//            }
//        }
//        sc.append("}");
//
//        GuardarGrafo(sc);
//    }

//    public void GuardarGrafo(StringBuilder sc) {
//        FileWriter fichero = null;
//        PrintWriter pw = null;
//        try {
//            fichero = new FileWriter("Grafo.dot");
//            pw = new PrintWriter(fichero);
//            pw.append(sc.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != fichero) {
//                    fichero.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();;
//            }
//            try {
//                String cmd = "dot -Tpdf ./ Grafo.dot " + "-o Grafo.pdf";
//                Runtime.getRuntime().exec(cmd);
//            } catch (IOException ioe) {
//                System.out.println(ioe);
//            }
//        }
//    }

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
