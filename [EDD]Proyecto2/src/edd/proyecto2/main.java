/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2;

import ArbolB.*;
import Grafo.*;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.MapViewOptions;
import static edd.proyecto2.ReadJson.*;
import java.awt.BorderLayout;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReadJson lectura = new ReadJson();
        lectura.Lugares();
        lectura.Grafo();
        lectura.Usuario();
        lectura.LocalidadUsuario();
        lectura.LocalidadConductor();

        Clave p = normal.Buscar(1);
        Usuario pasajero = (Usuario) p.getValor();
        Vertice origen = graf.Buscar(pasajero.getLugar());
        minimo = new Camino(graf, origen.getNumVertice());
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("");
        minimo.caminoMinimos();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("");
        minimo.Ordenar();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("");
        Usuario piloto = graf.VerticeCercano();
        if (piloto != null) {
            System.out.println(piloto.getNombre() + " HOLA");
            Vertice destino = graf.Buscar("Los Andes");
            HacerCamino(piloto, pasajero, destino);
        } else {
            System.out.println("NULL");
        }

    }

    public static void HacerCamino(Usuario piloto, Usuario pasajero, Vertice destino) {
        System.out.println("--------------------------------");
        int contador = minimo.CaminoMostrado(destino.getNumVertice(), 0);
        System.out.println("\n---------------------------");
        System.out.println("Vertices: " + contador);
        System.out.println("--------------------------------");
        System.out.println("");
        minimo.vertices = new int[contador];
        System.out.println("=================================");
        int f[] = minimo.ArregloCamino(destino.getNumVertice());
        System.out.println("////////////////////////////////////////////////");

        inicio(f);

        System.out.println("////////////////////////////////////////////////");
        destino.getConductor().add(piloto);
        graf.recorrer();
        piloto.setLugar(destino.getNombre());
        pasajero.setLugar(destino.getNombre());
        normal.Imprimir();
        System.out.println("");
        System.out.println("////////////////////////////////////////////////");
        System.out.println("");
        conductor.Imprimir();
        System.out.println("");
        System.out.println("////////////////////////////////////////////////");
        System.out.println("");
    }

    public static void inicio(int[] f) {
        MapViewOptions opciones = new MapViewOptions();
        opciones.importPlaces();
        opciones.setApiKey("AIzaSyAVc2H04cFYXpMT2H3oct0IYNx_NQv3HhU");
        Mapa mapa = new Mapa(opciones);
        LatLng[] c = new LatLng[f.length];
        for (int i = 0; i < f.length; i++) {
            System.out.println("Traje el vertice" + f[i]);
            Vertice nuevo = graf.BuscarNumero(f[i]);
            System.out.println(nuevo.getLatitud() + " " + nuevo.getLongitud());
            c[i] = (new LatLng(nuevo.getLatitud(), nuevo.getLongitud()));
        }
        mapa.agregarGrafo(c, true);
        JFrame frame = new JFrame("Mapa");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 800);
        frame.add(mapa, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
