/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edd.proyecto2;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 
 * @author Daniel
 */
public class SHA {
    
    public static String getSHA256(String Entrada){

	String Return = null;
	try {
            
	    MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Instanciamos el metodo que llamada el metodo SHA
	    digest.reset(); //
	    digest.update(Entrada.getBytes("utf8")); // la entrada se valua al formato de caracteres 
	    Return = String.format("%064x", new BigInteger(1, digest.digest())); // generando el hash de 256 bits
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return Return; // se retorna la cadena del hash 
    }
}
