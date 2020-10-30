/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash;

import edd.proyecto2.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Daniel
 */
public class TablaHash {

    static int size = 10;
    private int Elementos;
    private double Carga;
    private Lugar[] tabla;

    public TablaHash() {
        tabla = new Lugar[size];
        for (int j = 0; j < size; j++) {
            tabla[j] = null;
        }
        Elementos = 0;
        Carga = 0.0;
    }

    public void insertar(Lugar n) {
        int posicion;
        posicion = direccion(n.getNombre());
        tabla[posicion] = n;
        Elementos++;
        Carga = (double) (Elementos) / size;

        if (Carga > 0.75) {

            System.out.println("\n!! Factor de carga supera el 75%.!!"
                    + " Conviene aumentar el tamaño.");

            int nuevoTamTabla = size;
            System.out.println("Antiguo: " + nuevoTamTabla);
            System.out.println("Numero elemento " + Elementos);
            do {
                nuevoTamTabla++;
                Carga = (double) (Elementos) / nuevoTamTabla;
                System.out.println("Tamaño crecido:  " + nuevoTamTabla);
            } while (Carga > 0.30);

            System.out.println("El nuevo tamaño es " + nuevoTamTabla);

            nuevoTamTabla = (nuevoTamTabla % 2) == 0 ? nuevoTamTabla + 1 : nuevoTamTabla;

            Lugar tablaN[] = new Lugar[nuevoTamTabla];
            Lugar antiguo[] = tabla;
            tabla = tablaN;
            size = nuevoTamTabla;

            int aux = 0;
            for (Lugar e : antiguo) {
                if (e != null) {
                    aux = direccion(e.getNombre());
                    System.out.println("La nueva posicion seria " + aux);
                    tabla[aux] = e;
                }
            }

            System.out.println("Nueva:");
            mostrarTabla();
        }
    }

    public void mostrarTabla() {
        for (int i = 0; i < size; i++) {
            if (tabla[i] == null) {
                System.out.println(i + ".");
            } else {
                tabla[i].imprimirObjeto();
            }
        }
    }

    public int direccion(String clave) {
        int p, i = 0, cte;
        int ascii = obtenerAscii(clave);
        p = (int) (ascii % size);
        cte = p;
        while (tabla[(int) p] != null) {
            i++;
            p = cte + i;
            p = p % size;
        }
        return (int) p;
    }

    public Lugar buscar(String nombre) {
        for (int i = 0; i < size; i++) {
            if (tabla[i] != null) {
                if (tabla[i].getNombre().equals(nombre)) {
                    return tabla[i];
                }
            }
        }
        return null;
    }

    public int obtenerAscii(String cadena) {
        int valor = 0;
        for (int i = 0; i < cadena.length(); i++) {
            valor = valor + (int) cadena.charAt(i);
        }
        return valor;
    }
    
    public Lugar[] getTabla(){
        return tabla;
    }
    public int getsize(){
        return size;
    }

    public void Graficar() {
        String[] colores = {"red", "blue", "yellow", "deeppink2", "cyan1","gray","darkseagreen1", "green", "cadetblue1", "darkslategray"
        ,"cadetblue", "darkorchid", "mediumseagreen", "palegreen1", "dodgerblue3", "gold1", "indigo"};
        StringBuilder cad = new StringBuilder();
        cad.append("digraph g{\n");
        cad.append("node[shape=record fontsize=18, fontname=chiller];\n");
        int a = 1, n = 0, contador = 0;
        cad.append("node").append(n).append("[label=\"");
        boolean termino = false;
        int j = -1;
        for (Lugar t : this.tabla) {
            if (a == 5) {
                j++;
                cad.append(t != null ? t.getNombre() : "").append("\"][color=").append(colores[j]).append("];\n");
                a = 1;
                n++;
                if (j == colores.length - 1) {
                    j = -1;
                }
                if (contador == Elementos - 1) {
                    termino = true;
                    break;
                }
                cad.append("node").append(n).append("[label=\"");
                
                continue;
            } else {
                cad.append(t != null ? t.getNombre() : "").append("|");
            }
            a++;
            contador++;

        }
        if (!termino) {
            cad.append("Carga: ").append(Carga).append(" Size: ").append(Elementos).append("\"]\n");
        }
        for (int i = 0; i < n; i++) {
            cad.append("node").append(i).append("->node").append(i + 1).append("[style=\"invis\"]").append("\n");
        }
        cad.append("} \n");
        System.out.println(cad.toString());
        GuardarTabla(cad);
    }
    
    public void GuardarTabla(StringBuilder sc) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("TablaHash.dot");
            pw = new PrintWriter(fichero);
            pw.append(sc.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();;
            }
            try {
                String cmd = "dot -Tpdf ./ TablaHash.dot -o TablaHash.pdf";
                Runtime.getRuntime().exec(cmd);
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
    }
}
