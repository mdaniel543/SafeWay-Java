/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Hash;

import edd.proyecto2.*;

/**
 * 
 * @author Daniel
 */
public class TablaHash {
    static final int tamTabla = 10;
    private int numElementos;
    private double factorCarga;
    private Lugar[] tabla;

    public TablaHash() {
        tabla = new Lugar[tamTabla];
        for (int j = 0; j < tamTabla; j++) {
            tabla[j] = null;
        }
        numElementos = 0;
        factorCarga = 0.0;
    }
    
    public void insertar(Lugar n){
        int posicion;
        posicion = direccion(n.getCategoria());
        tabla[posicion] = n;
        numElementos++;
        factorCarga = (double) (numElementos) / tamTabla;
        if (factorCarga > 0.5) {
            System.out.println("\n!! Factor de carga supera el 50%.!!"
                    + " Conviene aumentar el tamaño.");
        }
        
    }
    
    public void mostrarTabla(){
        for (int i = 0; i < tamTabla; i++) {
            if(tabla[i] == null){
                System.out.println(i+".");
            }else{
                tabla[i].imprimirObjeto();
            }
        }
    }
    
    public int direccion(String clave) {
        int p, i = 0, cte;
        int ascii = obtenerAscii(clave);
        p = (int) (ascii % tamTabla);
        cte = p;
        while (tabla[(int) p] != null) {//si no esta desocupada
            i++;
            System.out.println("Entre al while " + i + "\n");
            p = cte + i;
            p = p % tamTabla;
        }
        return (int) p;
    }
    
    public void recorrer()
    {
        for (int i = 0; i < tamTabla; i++) {
            if(tabla[i] != null){
                ReadJson.graf.addVertice(tabla[i].getId(), tabla[i].getCategoria(), tabla[i].getNombre(), tabla[i].getLatitud(), tabla[i].getLongitud());
            }
        }
    }
    
    public Lugar buscar(int id)
    {
        for (int i = 0; i < tamTabla; i++) {
            if(tabla[i] != null){
                if(tabla[i].getId() == id){
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
        System.out.println("El ascii es: " + valor);
        return valor;
    }

    public double parteEntera(double numero) {
        double parteDecimal = numero % 1;
        double parteEntera = numero - parteDecimal;
        //System.out.println("Parte decimal:"+parteDecimal);
        //System.out.println("Parte entera:"+parteEntera);
        return parteEntera;
    }
}
