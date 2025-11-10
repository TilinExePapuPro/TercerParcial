package org.tercerparcial.arbolbin.ArbolBinario;

import Cola.Cola;



public class ArbolBinarioOrden <E>{
    protected Nodo<E> raiz;
    public ArbolBinarioOrden(){
        raiz=null;
    }
    public Nodo<E> getRaiz(){
        return raiz;
    }
    public void insertar (E dato){
        Nodo<E> n = raiz;
        Nodo<E> previo = null;
        while (n != null){
            previo = n;
            if (n.mayor(dato)){
                n = n.derecho;
            }else {
                n = n.izquierdo;
            }

        }
    if (raiz == null){
        raiz = new Nodo<E>(dato, "raiz");
    } else if (previo.mayor(dato)) {
        previo.derecho = new Nodo<E>(dato, "derecho,padre= " + previo.dato);
    }else {
        previo.izquierdo = new Nodo<E>(dato, "izquierdo,padre= " + previo.dato);
    }
    }
    public void imprimir(Nodo<E> n){
        System.out.println(n.dato+" "+n.Etiqueta);
    }

    public void recorridoAmplitud() throws Exception {
        Nodo<E> n = raiz;
        Cola<Nodo<E>> cola = new Cola<Nodo<E>>();
        if (n != null) {
            cola.insertar(n);
            while (!cola.estaVacia()){
                n=cola.extraer();
                imprimir(n);
                if (n.izquierdo !=null){
                    cola.insertar(n.izquierdo);
                }
                if (n.derecho != null){
                    cola.insertar(n.derecho);
                }
            }
        }
    }

    public void preorden (Nodo<E> n){
        if (n != null){
            imprimir(n);
            preorden(n.izquierdo);
            preorden(n.derecho);
        }
    }
    public void orden (Nodo<E> n){
        if (n != null){
            orden(n.izquierdo);
            imprimir(n);
            orden(n.derecho);
        }
    }
    public void postorden (Nodo<E> n){
        if (n != null){
            postorden(n.izquierdo);
            postorden(n.derecho);
            imprimir(n);
        }
    }
    public void eliminar(E dato) {
        Nodo<E> tmp;
        Nodo<E> nodo;
        Nodo<E> n = raiz;
        Nodo<E> previo = null;
        while (n != null && !n.equals(dato)) {
            previo = n;
            if (n.mayor(dato)) {
                n = n.derecho;
            } else {
                n = n.izquierdo;
            }
        }
        nodo = n;
        if (n != null && n.equals(dato)) {
            if (nodo.derecho == null) {
                nodo = nodo.izquierdo;
            } else if (nodo.izquierdo == null) {
                nodo = nodo.derecho;
            } else {
                tmp = nodo.izquierdo; // 1
                while (tmp.derecho != null) { // 2
                    tmp = tmp.derecho;
                }
                tmp.derecho = nodo.derecho; // 3
                nodo = nodo.izquierdo; // 4
            }
            if (n == raiz) {
                raiz = nodo;
            } else if (previo.izquierdo == n) {
                previo.izquierdo = nodo;
            } else {
                previo.derecho = nodo; // 5
            }
        } else if (raiz != null) {
            System.out.println("No se encuentra el dato " + dato);
        } else {
            System.out.println("Arbol vacio");
        }
    }
}
