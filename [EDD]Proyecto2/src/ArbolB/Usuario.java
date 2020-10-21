/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArbolB;

import Hash.*;

/**
 * 
 * @author Daniel
 */
public class Usuario {
    private int key;
    private String Nombre;
    private String usuario;
    private String correo;
    private String pass;
    private String telefono;
    private String rol;
    private boolean disponibilidad;
    private Lugar lugar;

    public Usuario(int key, String Nombre, String usuario, String correo, String pass, String telefono, String rol) {
        this.key = key;
        this.Nombre = Nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.pass = pass;
        this.telefono = telefono;
        this.rol = rol;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    
}
