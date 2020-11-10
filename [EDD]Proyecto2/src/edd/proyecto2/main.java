/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2;

import ArbolB.*;
import Grafo.*;
import Interfaz.*;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.MapViewOptions;
import static edd.proyecto2.ReadJson.*;
import java.awt.BorderLayout;

import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.WindowConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {

    public static ReadJson lectura = new ReadJson();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        try {
//            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel");
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        lectura.Lugares("Lugares.json");
//        lectura.Grafo("Grafo.json");
//        lectura.Usuario("Usuarios.json");
//        lectura.LocalidadUsuario("Localidades Usuarios.json");
//        lectura.LocalidadConductor("Localidades.json");

        new Login().setVisible(true);
//        new Admin().setVisible(true);
//        new User().setVisible(true);
//       new Place().setVisible(true);
//       new Pilot().setVisible(true);
//       new Travel().setVisible(true);


    }
}
