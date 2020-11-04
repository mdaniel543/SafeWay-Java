/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author mdani
 * @param <T>
 * @param <V>
 */
public class ArbolB<T extends Comparable<T>, V> {

    private int k;
    private Pagina raiz;

    public ArbolB(int k) {
        this.k = k;
        this.raiz = new Pagina(k);
    }

    public void insertar(T llave, V valor) {
        Clave newKey = new Clave(llave, valor);
        if (this.raiz.get(0) == null) {
            this.raiz.put(0, newKey);
        } else if (this.raiz.get(0).getIzquierda() == null) {
            int lugarinsertado = -1;
            Pagina node = this.raiz;
            lugarinsertado = colocarNodo(node, newKey);
            if (lugarinsertado != -1) {
                if (lugarinsertado == k - 1) {
                    int midle = node.getMax() / 2;
                    Clave llavecentral = node.get(midle);
                    Pagina derecho = new Pagina(k);
                    Pagina izquierdo = new Pagina(k);
                    int indiceIzquierdo = 0, indiceDerecho = 0;
                    for (int j = 0; j < node.getMax(); j++) {
                        if (node.get(j).compareTo(llavecentral.getKey()) < 0) {
                            izquierdo.put(indiceIzquierdo, node.get(j));
                            indiceIzquierdo++;
                            node.put(j, null);
                        } else if (node.get(j).compareTo(llavecentral.getKey()) > 0) {
                            derecho.put(indiceDerecho, node.get(j));
                            indiceDerecho++;
                            node.put(j, null);
                        }
                    }
                    node.put(midle, null);
                    this.raiz = node;
                    this.raiz.put(0, llavecentral);
                    izquierdo.setPaginaPadre(this.raiz);
                    derecho.setPaginaPadre(this.raiz);
                    llavecentral.setIzquierda(izquierdo);
                    llavecentral.setDerecha(derecho);
                }
            }
        } else if (this.raiz.get(0).getIzquierda() != null) {
            Pagina node = this.raiz;
            while (node.get(0).getIzquierda() != null) {
                int loop = 0;
                for (int i = 0; i < node.getMax(); i++) {
                    if (node.get(i) != null) {
                        if (node.get(i).compareTo(newKey.getKey()) > 0) {
                            node = node.get(i).getIzquierda();
                            break;
                        }
                    } else {
                        node = node.get(i - 1).getDerecha();
                        break;
                    }
                }
                if (loop == node.getMax()) {
                    node = node.get(loop - 1).getDerecha();
                }
            }
            int indiceColocado = colocarNodo(node, newKey);
            if (indiceColocado == k - 1) {
                while (node.getPaginaPadre() != null) {
                    int indicemedio = node.getMax() / 2;
                    Clave llaveCentral = node.get(indicemedio);
                    Pagina izquierdo = new Pagina(k);
                    Pagina derecho = new Pagina(k);
                    int indiceIzquierdo = 0, indiceDerecho = 0;
                    for (int i = 0; i < k; i++) {
                        if (node.get(i).compareTo(llaveCentral.getKey()) < 0) {
                            izquierdo.put(indiceIzquierdo, node.get(i));
                            indiceIzquierdo++;
                            node.put(i, null);
                        } else if (node.get(i).compareTo(llaveCentral.getKey()) > 0) {
                            derecho.put(indiceDerecho, node.get(i));
                            indiceDerecho++;
                            node.put(i, null);
                        }
                    }
                    node.put(indicemedio, null);
                    llaveCentral.setIzquierda(izquierdo);
                    llaveCentral.setDerecha(derecho);
                    node = node.getPaginaPadre();
                    izquierdo.setPaginaPadre(node);
                    derecho.setPaginaPadre(node);
                    for (int i = 0; i < k; i++) {
                        if (izquierdo.get(i) != null) {
                            if (izquierdo.get(i).getIzquierda() != null) {
                                izquierdo.get(i).getIzquierda().setPaginaPadre(izquierdo);
                            }
                            if (izquierdo.get(i).getDerecha() != null) {
                                izquierdo.get(i).getDerecha().setPaginaPadre(izquierdo);
                            }
                        }
                    }
                    for (int i = 0; i < k; i++) {
                        if (derecho.get(i) != null) {
                            if (derecho.get(i).getIzquierda() != null) {
                                derecho.get(i).getIzquierda().setPaginaPadre(izquierdo);
                            }
                            if (derecho.get(i).getDerecha() != null) {
                                derecho.get(i).getDerecha().setPaginaPadre(izquierdo);
                            }
                        }
                    }
                    int lugarColocado = colocarNodo(node, llaveCentral);
                    if (lugarColocado == k - 1) {
                        if (node.getPaginaPadre() == null) {
                            int indicecentralraiz = k / 2;
                            Clave llavecentralraiz = node.get(indicecentralraiz);
                            Pagina izquierdaRaiz = new Pagina(k);
                            Pagina derechaRaiz = new Pagina(k);
                            int indicederecharaiz = 0, indiceizquierdaraiz = 0;
                            for (int i = 0; i < k; i++) {
                                if (node.get(i).compareTo(llavecentralraiz.getKey()) < 0) {
                                    izquierdaRaiz.put(indiceizquierdaraiz, node.get(i));
                                    indiceizquierdaraiz++;
                                    node.put(i, null);
                                } else if (node.get(i).compareTo(llavecentralraiz.getKey()) > 0) {
                                    derechaRaiz.put(indiceizquierdaraiz, node.get(i));
                                    indicederecharaiz++;
                                    node.put(i, null);
                                }
                            }
                            node.put(indicecentralraiz, null);
                            node.put(0, llavecentralraiz);
                            for (int i = 0; i < k; i++) {
                                if (izquierdaRaiz.get(i) != null) {
                                    izquierdaRaiz.get(i).getIzquierda().setPaginaPadre(izquierdaRaiz);
                                    izquierdaRaiz.get(i).getDerecha().setPaginaPadre(izquierdaRaiz);
                                }
                            }
                            for (int i = 0; i < k; i++) {
                                if (derechaRaiz.get(i) != null) {
                                    derechaRaiz.get(i).getIzquierda().setPaginaPadre(derechaRaiz);
                                    derechaRaiz.get(i).getDerecha().setPaginaPadre(derechaRaiz);
                                }
                            }
                            llavecentralraiz.setIzquierda(izquierdaRaiz);
                            llavecentralraiz.setDerecha(derechaRaiz);
                            izquierdaRaiz.setPaginaPadre(node);
                            derechaRaiz.setPaginaPadre(node);
                            this.raiz = node;
                        }
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private int colocarNodo(Pagina node, Clave newKey) {
        int index = -1;
        for (int i = 0; i < k; i++) {
            if (node.get(i) == null) {
                boolean placed = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (node.get(j).compareTo(newKey.getKey()) > 0) {
                        node.put(j + 1, node.get(j));
                    } else {
                        node.put(j + 1, newKey);
                        node.get(j).setDerecha(newKey.getIzquierda());
                        if (j + 2 < k && node.get(j + 2) != null) {
                            node.get(j + 2).setIzquierda(newKey.getDerecha());
                        }
                        placed = true;
                        break;
                    }
                }
                if (placed == false) {
                    node.put(0, newKey);
                    node.get(1).setIzquierda(newKey.getDerecha());
                }
                index = i;
                break;
            }
        }
        return index;
    }

    public void Imprimir() {
        Imprimir(this.raiz, new ArrayList<>());
    }

    private void Imprimir(Pagina actual, ArrayList<Pagina> arr) {
        if (actual != null) {
            if (arr.contains(actual)) {
                arr.remove(actual);
                return;
            } else {
                arr.add(actual);
            }
            System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
            for (int i = 0; i < actual.getMax(); i++) {
                if (actual.get(i) != null) {
                    Usuario usu = (Usuario) actual.get(i).getValor();
                    System.out.println("");
                    System.out.println(usu.getNombre());
                    System.out.println(usu.getUsuario());
                    System.out.println("El lugar del usuario es:");
                    System.out.println(usu.getLugar());
                    //System.out.println(usu.getPass());
                    if (("conductor").equals(usu.getRol())) {
                        System.out.println(usu.getDisponibilidad());
                    }
                    Imprimir(actual.get(i).getIzquierda(), arr);
                    Imprimir(actual.get(i).getDerecha(), arr);

                } else {
                    break;
                }
            }
        }
    }

    public void ImprimirV() {
        ImprimirV(this.raiz, new ArrayList<>());
    }

    private void ImprimirV(Pagina actual, ArrayList<Pagina> arr) {
        if (actual != null) {
            if (arr.contains(actual)) {
                arr.remove(actual);
                return;
            } else {
                arr.add(actual);
            }
            System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
            for (int i = 0; i < actual.getMax(); i++) {
                if (actual.get(i) != null) {
                    Viaje usu = (Viaje) actual.get(i).getValor();
                    System.out.println("");
                    System.out.println("ID Viaje" + usu.getId());
                    System.out.println("Inicio: " + usu.getInicio());
                    System.out.println("Fin: " + usu.getFin());
                    System.out.println(usu.getFecha());

                    ImprimirV(actual.get(i).getIzquierda(), arr);
                    ImprimirV(actual.get(i).getDerecha(), arr);

                } else {
                    break;
                }
            }
        }
    }

    private ArrayList<Factura> fact;

    public void Facturas(int id, boolean vi) {
        fact = new ArrayList<>();
        Facturas(this.raiz, id, new ArrayList<>(), vi);
    }

    private void Facturas(Pagina actual, int id, ArrayList<Pagina> arr, boolean vi) {
        if (actual != null) {
            if (arr.contains(actual)) {
                arr.remove(actual);
                return;
            } else {
                arr.add(actual);
            }
            for (int i = 0; i < actual.getMax(); i++) {
                if (actual.get(i) != null) {
                    if (vi) {
                        if (id == ((Factura) actual.get(i).getValor()).getId_usuario()) {
                            fact.add((Factura) actual.get(i).getValor());
                        }
                    } else {
                        if (id == ((Factura) actual.get(i).getValor()).getId_conductor()) {
                            fact.add((Factura) actual.get(i).getValor());
                        }
                    }
                    Facturas(actual.get(i).getIzquierda(), id, arr, vi);
                    Facturas(actual.get(i).getDerecha(), id, arr, vi);
                } else {
                    break;
                }
            }
        }
    }

    public ArrayList<Factura> getFactura() {
        return fact;
    }

    public void ImprimirF() {
        ImprimirF(this.raiz, new ArrayList<>());
    }

    private void ImprimirF(Pagina actual, ArrayList<Pagina> arr) {
        if (actual != null) {
            if (arr.contains(actual)) {
                arr.remove(actual);
                return;
            } else {
                arr.add(actual);
            }
            System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
            for (int i = 0; i < actual.getMax(); i++) {
                if (actual.get(i) != null) {
                    Factura usu = (Factura) actual.get(i).getValor();
                    System.out.println("");
                    System.out.println("ID: " + usu.getId());
                    System.out.println("ID Usuario " + usu.getId_usuario());
                    System.out.println("Id conductor " + usu.getId_conductor());
                    System.out.println("Id viaje " + usu.getId_viaje());
                    System.out.println("Precio " + usu.getMonto());
                    System.out.println(usu.getFecha());
                    ImprimirF(actual.get(i).getIzquierda(), arr);
                    ImprimirF(actual.get(i).getDerecha(), arr);
                } else {
                    break;
                }
            }
        }
    }

    public Clave Buscar(int id) {
        return Buscar(this.raiz, id, new ArrayList<>(), null);
    }

    private Clave Buscar(Pagina actual, int id, ArrayList<Pagina> arr, Clave regreso) {
        if (actual != null) {
            if (arr.contains(actual)) {
                arr.remove(actual);
                return null;
            } else {
                arr.add(actual);
            }
            for (int i = 0; i < actual.getMax(); i++) {
                if (actual.get(i) != null) {
                    if (id == (Integer) actual.get(i).getKey()) {
                        return actual.get(i);
                    } else if (id < (Integer) actual.get(i).getKey()) {
                        regreso = Buscar(actual.get(i).getIzquierda(), id, arr, regreso);
                        if (regreso != null) {
                            return regreso;
                        }
                    } else if (id > (Integer) actual.get(i).getKey()) {
                        regreso = Buscar(actual.get(i).getDerecha(), id, arr, regreso);
                        if (regreso != null) {
                            return regreso;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Clave BuscarUser(String id) {
        return BuscarUser(this.raiz, id, new ArrayList<>(), null);
    }

    private Clave BuscarUser(Pagina actual, String id, ArrayList<Pagina> arr, Clave regreso) {
        if (actual != null) {
            if (arr.contains(actual)) {
                arr.remove(actual);
                return null;
            } else {
                arr.add(actual);
            }
            for (int i = 0; i < actual.getMax(); i++) {
                if (actual.get(i) != null) {
                    if (id.equals(((Usuario) actual.get(i).getValor()).getUsuario())) {
                        return actual.get(i);
                    }
                    regreso = BuscarUser(actual.get(i).getIzquierda(), id, arr, regreso);
                    if (regreso != null) {
                        return regreso;
                    }
                    regreso = BuscarUser(actual.get(i).getDerecha(), id, arr, regreso);
                    if (regreso != null) {
                        return regreso;
                    }
                } else {
                    break;
                }
            }
        }
        return null;
    }

    public int Aleatorio() {
        Random random = new Random();
        int id = random.nextInt(200) + 1;
        if (Buscar(id) == null) {
            return id;
        }
        System.out.println("****************");
        System.out.println(id);
        return Aleatorio();
    }

    public String[] colores = {"cadetblue", "darkorchid", "mediumseagreen", "palegreen1", "dodgerblue3", "gold1", "indigo",
        "red", "blue", "yellow", "deeppink2", "cyan1", "gray", "darkseagreen1", "green", "cadetblue1", "darkslategray"};
    int x = colores.length;
    int y = -1;

    public void Graficar(String nombre) {
        StringBuilder s = new StringBuilder();
        s.append("digraph G{\n").append("node[shape=record style=filled]\n");
        if (nombre.equals("conductor") || nombre.equals("usuario")) {
            Graficar(this.raiz, s, new ArrayList<>(), null, 0);
        } else if (nombre.equals("viaje")) {
            GraficarV(this.raiz, s, new ArrayList<>(), null, 0);
        } else if (nombre.equals("factura")) {
            GraficarF(this.raiz, s, new ArrayList<>(), null, 0);
        }

        s.append("}");
        //System.out.println(s.toString());
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombre + ".dot");
            pw = new PrintWriter(fichero);
            pw.append(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();;
            }
            try {
                String cmd = "dot -Tpdf ./" + nombre + ".dot " + "-o " + nombre + ".pdf";
                Runtime.getRuntime().exec(cmd);
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
    }

    private void Graficar(Pagina actual, StringBuilder cad, ArrayList<Pagina> arr, Pagina padre, int pos) {
        if (actual == null) {
            return;
        }
        int j = 0;
        if (arr.contains(actual)) {
            arr.remove(actual);
            return;
        } else {
            arr.add(actual);
        }
        cad.append("node").append(actual.hashCode()).append("[label = \"");
        x--;
        boolean enlace = true;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                return;
            } else {
                if (enlace) {
                    if (i != actual.getMax() - 1) {
                        cad.append("<f").append(j).append(">|");
                    } else {
                        cad.append("<f").append(j).append(">");
                        break;
                    }
                    enlace = false;
                    i--;
                    j++;
                } else {
                    Usuario usu = (Usuario) actual.get(i).getValor();
                    j++;
                    cad.append("{").append(usu.getKey()).append("|").append(usu.getNombre()).append("|").append(usu.getUsuario()).append("}|");
                    enlace = true;
                    if (1 < actual.getMax() - 1) {
                        if (actual.get(i + 1) == null) {
                            cad.append("<f").append(j++).append(">");
                            break;
                        }
                    }
                }
            }
        }
        cad.append("\"][fillcolor = \"").append(colores[x]).append("\"]\n");
        if (x == 0) {
            x = colores.length;
        }
        int ji = 0;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                break;
            }
            Graficar(actual.get(i).getIzquierda(), cad, arr, actual, ji++);
            ji++;
            Graficar(actual.get(i).getDerecha(), cad, arr, actual, ji++);
            ji--;
        }
        if (padre != null) {
            cad.append("node").append(padre.hashCode()).append(":f").append(pos).append("->node").append(actual.hashCode()).append("\n");
        }
    }

    private void GraficarV(Pagina actual, StringBuilder cad, ArrayList<Pagina> arr, Pagina padre, int pos) {
        if (actual == null) {
            return;
        }
        int j = 0;
        if (arr.contains(actual)) {
            arr.remove(actual);
            return;
        } else {
            arr.add(actual);
        }
        cad.append("node").append(actual.hashCode()).append("[label = \"");
        y++;
        boolean enlace = true;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                return;
            } else {
                if (enlace) {
                    if (i != actual.getMax() - 1) {
                        cad.append("<f").append(j).append(">|");
                    } else {
                        cad.append("<f").append(j).append(">");
                        break;
                    }
                    enlace = false;
                    i--;
                    j++;
                } else {
                    Viaje usu = (Viaje) actual.get(i).getValor();
                    j++;
                    cad.append("{").append(usu.getId()).append("|").append(usu.getInicio()).append("|").append(usu.getFin()).append("}|");
                    enlace = true;
                    if (1 < actual.getMax() - 1) {
                        if (actual.get(i + 1) == null) {
                            cad.append("<f").append(j++).append(">");
                            break;
                        }
                    }
                }
            }
        }
        cad.append("\"][fillcolor = \"").append(colores[y]).append("\"]\n");
        if (y == colores.length - 1) {
            y = -1;
        }
        int ji = 0;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                break;
            }
            GraficarV(actual.get(i).getIzquierda(), cad, arr, actual, ji++);
            ji++;
            GraficarV(actual.get(i).getDerecha(), cad, arr, actual, ji++);
            ji--;
        }
        if (padre != null) {
            cad.append("node").append(padre.hashCode()).append(":f").append(pos).append("->node").append(actual.hashCode()).append("\n");
        }
    }

    private void GraficarF(Pagina actual, StringBuilder cad, ArrayList<Pagina> arr, Pagina padre, int pos) {
        if (actual == null) {
            return;
        }
        int j = 0;
        if (arr.contains(actual)) {
            arr.remove(actual);
            return;
        } else {
            arr.add(actual);
        }
        cad.append("node").append(actual.hashCode()).append("[label = \"");
        y++;
        boolean enlace = true;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                return;
            } else {
                if (enlace) {
                    if (i != actual.getMax() - 1) {
                        cad.append("<f").append(j).append(">|");
                    } else {
                        cad.append("<f").append(j).append(">");
                        break;
                    }
                    enlace = false;
                    i--;
                    j++;
                } else {
                    Factura usu = (Factura) actual.get(i).getValor();
                    j++;
                    cad.append("{").append(usu.getId()).append("|{").append("Id Usuario: ").append(usu.getId_usuario()).append("|");
                    cad.append("Id conductor :").append(usu.getId_conductor()).append("|").append("Id Viaje ").append(usu.getId_viaje());
                    cad.append("}|").append(usu.getFecha()).append("|").append(usu.getMonto()).append("}|");
                    enlace = true;
                    if (1 < actual.getMax() - 1) {
                        if (actual.get(i + 1) == null) {
                            cad.append("<f").append(j++).append(">");
                            break;
                        }
                    }
                }
            }
        }
        cad.append("\"][fillcolor = \"").append(colores[y]).append("\"]\n");
        if (y == colores.length - 1) {
            y = -1;
        }
        int ji = 0;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                break;
            }
            GraficarF(actual.get(i).getIzquierda(), cad, arr, actual, ji++);
            ji++;
            GraficarF(actual.get(i).getDerecha(), cad, arr, actual, ji++);
            ji--;
        }
        if (padre != null) {
            cad.append("node").append(padre.hashCode()).append(":f").append(pos).append("->node").append(actual.hashCode()).append("\n");
        }
    }
}
