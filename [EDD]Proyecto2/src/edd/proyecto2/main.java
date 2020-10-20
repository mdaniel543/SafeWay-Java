/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
 
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
    }
}
    
