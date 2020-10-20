/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArbolB;

/**
 * 
 * @author Daniel
 * @param <T>
 * @param <V>
 */
public class Clave<T extends Comparable<T>, V> implements Comparable {
    private T key;
    private V Valor;

    
    private Pagina derecha;
    private Pagina izquierda;

    public Clave(T key, V Valor) {
        this.key = key;
        this.Valor = Valor;
        this.derecha = null;
        this.izquierda = null;
    }
    
    @Override
    public int compareTo(Object o) {    
        T aux = (T) o;
        if(this.key.compareTo(aux)<0){
            return -1;
        }else if(this.key.compareTo(aux)>0){
            return 1;
        }
        return 0;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValor() {
        return Valor;
    }

    public void setValor(V Valor) {
        this.Valor = Valor;
    }
    
    public Pagina getDerecha() {
        return derecha;
    }

    public void setDerecha(Pagina derecha) {
        this.derecha = derecha;
    }

    public Pagina getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Pagina izquierda) {
        this.izquierda = izquierda;
    }
}
