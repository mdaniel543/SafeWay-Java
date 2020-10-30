/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import ArbolB.Usuario;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;
import static edd.proyecto2.ReadJson.*;
import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Daniel
 */
public class Mapa extends MapView {
    public static int precio;
    private Map map;

    public Mapa(MapViewOptions opciones) {
        super(opciones);
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus ms) {
                if (ms == MapStatus.MAP_STATUS_OK) {
                    map = getMap();
                    MapOptions mapOptions = new MapOptions();
                    MapTypeControlOptions mt = new MapTypeControlOptions();
                    mt.setPosition(ControlPosition.BOTTOM_LEFT);
                    mapOptions.setMapTypeControlOptions(mt);
                    map.setOptions(mapOptions);
                    map.setCenter(new LatLng(14.561049, -90.532655));
                    map.setZoom(12);
                }
            }
        });
        System.out.println("Esperando a que se genere el mapa...");
        try {
            for (int i = 1; i < 4; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(i + ". ");
            }
        } catch (Exception e) {
            System.out.println("Hubo un erro con el delay");
        }
        System.out.println("Generado...");
    }

    public Marker agregarMarcador(LatLng coordenada) {
        Marker marcador = new Marker(map);
        marcador.setPosition(coordenada);
        map.setCenter(coordenada);
        System.out.println("Marcador Agregado");
        return marcador;
    }

    public void agregarLinea(LatLng inicio, LatLng fin, boolean marcador) {
        LatLng[] linea = {inicio, fin};
        Polyline pl = new Polyline(map);
        pl.setPath(linea);
        if (marcador) {
            agregarMarcador(inicio);
            agregarMarcador(fin);
        }
    }

    public void agregarGrafo(LatLng[] camino, boolean marcadores) {
        Polyline pl = new Polyline(map);
        pl.setPath(camino);
        if (marcadores) {
            for (LatLng c : camino) {
                agregarMarcador(c);
            }
        }
    }

    public static void HacerCaminoMuestra(Usuario pasajero, Vertice destino) {
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
        
        precio = 0;
        inicio(f);
        
        System.out.println("////////////////////////////////////////////////");
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
            if(i != f.length -1){
                PrecioArista(nuevo, f[i+1]);
            }
            System.out.println(nuevo.getLatitud() + " " + nuevo.getLongitud());
            c[i] = (new LatLng(nuevo.getLatitud(), nuevo.getLongitud()));
        }
        mapa.agregarGrafo(c, true);
        JFrame frame = new JFrame("Mapa");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 800);
        frame.add(mapa, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    public static void PrecioArista(Vertice v, int destino){
        Vertice d = graf.BuscarNumero(destino);
        for (Arista a : v.getDetalle()) {
            if(a.getDestino().equals(d.getNombre())){
                precio += a.getPrecio();
            }
        }
    }
}