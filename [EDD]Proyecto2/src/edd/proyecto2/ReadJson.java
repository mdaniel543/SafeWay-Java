/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2;

import ArbolB.*;
import Grafo.*;
import Hash.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Daniel
 */
public class ReadJson {

    public static TablaHash Tabla = new TablaHash();
    public static Grafo graf = new Grafo();

    public static ArbolB<Integer, Usuario> normal = new ArbolB<>(5);

    public static ArbolB<Integer, Usuario> conductor = new ArbolB<>(5);

    public void Lugares() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Lugares.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lugar = (JSONArray) jsonObject.get("Lugares");
            for (Object l : lugar) {
                JSONObject jsonNumber = (JSONObject) l;
                long id = (Long) jsonNumber.get("id");
                System.out.println(id);
                String Categoria = (String) jsonNumber.get("Categoria");
                System.out.println(Categoria);
                String Nombre = (String) jsonNumber.get("Nombre");
                System.out.println(Nombre);
                double Lat = (Double) jsonNumber.get("Lat");
                System.out.println(Lat);
                double Lon = (Double) jsonNumber.get("Lat");
                System.out.println(Lon);
                Lugar ll = new Lugar(id, Categoria, Nombre, Lat, Lon);
                Tabla.insertar(ll);
                Tabla.mostrarTabla();
            }
            Tabla.recorrer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void Grafo() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Grafo.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lugar = (JSONArray) jsonObject.get("Grafo");
            for (Object l : lugar) {
                JSONObject jsonNumber = (JSONObject) l;
                int inicio = Integer.parseInt(jsonNumber.get("inicio").toString());
                System.out.println(inicio);
                int fin = Integer.parseInt(jsonNumber.get("final").toString());
                System.out.println(fin);
                double peso = Double.parseDouble(jsonNumber.get("peso").toString());
                System.out.println(peso);
                String unidad = (String) jsonNumber.get("unidad");
                System.out.println(unidad);
                double precio = Double.parseDouble(jsonNumber.get("precio").toString());
                System.out.println(precio);
                String moneda = (String) jsonNumber.get("moneda");
                System.out.println(moneda);

                graf.enlazar(inicio, fin, peso, unidad, precio, moneda);
            }
            graf.BFS();
            graf.DFS();
            graf.dibujar();
            graf.recorrer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void Usuario() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Usuarios.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lugar = (JSONArray) jsonObject.get("usuarios");
            for (Object l : lugar) {
                JSONObject jsonNumber = (JSONObject) l;
                int id = Integer.parseInt(jsonNumber.get("id").toString());
                System.out.println(id);
                String nombre = (String) jsonNumber.get("nombre");
                System.out.println(nombre);
                String usuario = (String) jsonNumber.get("usuario");
                System.out.println(usuario);
                String correo = (String) jsonNumber.get("correo");
                System.out.println(correo);
                String pass = (String) jsonNumber.get("pass");
                System.out.println(pass);
                String telefono = (String) jsonNumber.get("telefono");
                System.out.println(telefono);
                String rol = (String) jsonNumber.get("rol");
                System.out.println(rol);
                if ("normal".equals(rol)) {
                    normal.insertar(id, new Usuario(id, nombre, usuario, correo, pass, telefono, rol));
                } else {
                    conductor.insertar(id, new Usuario(id, nombre, usuario, correo, pass, telefono, rol));
                }
            }
            normal.Graficar("usuario");
            normal.Imprimir();

            conductor.Graficar("conductor");
            conductor.Imprimir();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void LocalidadUsuario() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Localidades Usuarios.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lugar = (JSONArray) jsonObject.get("localidades");
            for (Object l : lugar) {
                JSONObject jsonNumber = (JSONObject) l;
                int idU = Integer.parseInt(jsonNumber.get("id_usuario").toString());
                System.out.println(idU);
                int idL = Integer.parseInt(jsonNumber.get("id_lugar").toString());
                System.out.println(idL);
                Clave edit = normal.Buscar(idU);
                if (edit != null) {
                    Lugar aedit = Tabla.buscar(idL);
                    if (aedit != null) {
                        ((Usuario) edit.getValor()).setLugar(aedit);
                    } else {
                        System.out.println("No se ha podido colocar el lugar " + idL);
                    }
                }else{
                    System.out.println("No se ha podido encontrar el usuario " + idU);
                }
            }
            normal.Graficar("usuario");
            normal.Imprimir();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void LocalidadConductor() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Localidades.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lugar = (JSONArray) jsonObject.get("localidades");
            for (Object l : lugar) {
                JSONObject jsonNumber = (JSONObject) l;
                int idU = Integer.parseInt(jsonNumber.get("id_conductor").toString());
                System.out.println(idU);
                int idL = Integer.parseInt(jsonNumber.get("id_lugar").toString());
                System.out.println(idL);
                boolean dis = (Boolean) jsonNumber.get("disponibilidad");
                Clave edit = conductor.Buscar(idU);
                if (edit != null) {
                    Lugar aedit = Tabla.buscar(idL);
                    if (aedit != null) {
                        ((Usuario) edit.getValor()).setLugar(aedit);
                    } else {
                        System.out.println("No se ha podido colocar el lugar " + idL);
                    }
                    ((Usuario) edit.getValor()).setDisponibilidad(dis);
                }else{
                    System.out.println("No se ha podido encontrar el usuario " + idU);
                }
            }
            conductor.Graficar("conductor");
            conductor.Imprimir();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}