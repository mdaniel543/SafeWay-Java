/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import edd.proyecto2.ReadJson;

/**
 *
 * @author Daniel
 */
public class Camino {

    private double[][] Pesos;
    private int[] ultimo;
    private double[] D;
    private boolean[] F;
    private int s, n; 

    public Camino(Grafo gp, int origen) {
        n = gp.getNumeroVertice();
        s = origen;
        Pesos = gp.getMatrizPeso();
        ultimo = new int[n];
        D = new double[n];
        F = new boolean[n];
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
        System.out.print("[");
        for (int i = 0; i < 6; i++) {
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
}
