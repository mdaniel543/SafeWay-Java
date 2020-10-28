/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafo;

import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;
import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * 
 * @author Daniel
 */
public class Mapa extends MapView{
    
    private Map map;

    public Mapa(MapViewOptions opciones) {
        super(opciones);
        setOnMapReadyHandler(new MapReadyHandler(){
            @Override
            public void onMapReady(MapStatus ms){
                if(ms == MapStatus.MAP_STATUS_OK){
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
        try{
            for (int i = 1; i < 4; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(i + "...");
            }
        }catch(Exception e){
            System.out.println("Hubo un erro con el delay");
        }
        System.out.println("Generado...");
    }
    
    public Marker agregarMarcador(LatLng coordenada){
        Marker marcador = new Marker (map);
        marcador.setPosition(coordenada);
        map.setCenter(coordenada);
        System.out.println("Marcador Agregado");
        return marcador;
    }
    public void agregarLinea (LatLng inicio, LatLng fin, boolean marcador){
        LatLng[] linea = {inicio, fin};
        Polyline pl =new Polyline(map);
        pl.setPath(linea);
        if(marcador){
            agregarMarcador(inicio);
            agregarMarcador(fin);
        }
    }
    public void agregarGrafo(LatLng[] camino, boolean marcadores){
        Polyline pl =new Polyline(map);
        pl.setPath(camino);
        if (marcadores) {
            for (LatLng c: camino) {
                agregarMarcador(c);
            }
        }
    }
}
