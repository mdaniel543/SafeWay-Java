/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import edd.proyecto2.ReadJson;
import java.util.LinkedList;

/**
 *
 * @author Daniel
 */
public class Camino {

    public LinkedList<Integer> descartados;
    public int[] vertices;
    public int c;
    public double[] arreglo;
    private double[][] Pesos;
    private int[] ultimo;
    private double[] D;
    private boolean[] F;
    private int s, n;

    public Camino(Grafo gp, int origen) {
        descartados = new LinkedList<>();
        n = gp.getNumeroVertice();
        s = origen;
        Pesos = gp.getMatrizPeso();
        ultimo = new int[n];
        D = new double[n];
        arreglo = new double[n];
        F = new boolean[n];     
        c = -1;
    }

    public void caminoMinimos() {
        // valores iniciales
        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = Pesos[s][i];
            ultimo[i] = s;
        }
        F[s] = true;
        D[s] = 0;
        // Pasos para marcar los n-1 vértices
        for (int i = 0; i < n; i++) {
            int v = minimo();
            /* selecciona vértice no marcado
            de menor distancia */
            F[v] = true;
            // actualiza distancia de vértices no marcados
            for (int w = 0; w < n; w++) {
                if (!F[w]) {
                    if ((D[v] + Pesos[v][w]) < D[w]) {
                        D[w] = D[v] + Pesos[v][w];
                        ultimo[w] = v;
                    }
                }
            }
        }
        System.out.print("[ ");
        for (int i = 0; i < D.length; i++) {
            System.out.print(D[i] + ", ");
        }
        System.out.println("]");
    }

    private int minimo() {
        double mx = Double.POSITIVE_INFINITY;
        int v = 1;
        for (int j = 0; j < n; j++) {
            if (!F[j] && (D[j] <= mx)) {
                mx = D[j];
                v = j;
            }
        }
        return v;
    }
    public int CaminoMostrado(int v, int i) {
        i++;
        int anterior = ultimo[v];
        if (v != s) {
            int j = CaminoMostrado(anterior, i);
            System.out.print(" -> V" + v);
            return j;
        } else {
            System.out.print("V" + s);
        }
        return i;
    }
    
    public int[] ArregloCamino(int v) { 
        int anterior = ultimo[v];
        if (v != s) {
            ArregloCamino(anterior); 
            c++;  
            vertices[c] = v;
        } else {
            c++;
            vertices[c] = s; 
        }
        return vertices;
    }

    public int conductorCerca() {
        for (int j : descartados) {
            System.out.println(j + " soy yo de nuevo");
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (!descartados.contains(i)) {
                descartados.add(i);
                return i;
            }
        }
        return -1;
    }

    public void Ordenar() {
        arreglo = D.clone();
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    double tmp = arreglo[j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        System.out.print("[ ");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i] + ", ");
        }
        System.out.println("]");
    }

}
