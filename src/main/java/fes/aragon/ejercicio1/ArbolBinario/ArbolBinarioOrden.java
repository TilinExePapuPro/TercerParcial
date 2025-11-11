package fes.aragon.ejercicio1.ArbolBinario;

import fes.aragon.ejercicio1.Cola.Cola;
import fes.aragon.ejercicio1.Pila.Pila;

public class ArbolBinarioOrden <E>{
    protected Nodo<E> raiz;

    public ArbolBinarioOrden(){
        raiz = null;
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
            } else {
                n = n.izquierdo;
            }
        }

        if (raiz == null){
            raiz = new Nodo<E>(dato, "raiz");
        } else if (previo.mayor(dato)) {
            previo.derecho = new Nodo<E>(dato, "derecho,padre= " + previo.dato);
        } else {
            previo.izquierdo = new Nodo<E>(dato, "izquierdo,padre= " + previo.dato);
        }
    }

    public void imprimir(Nodo<E> n){
        System.out.println(n.dato + " " + n.Etiqueta);
    }

    public void recorridoAmplitud() throws Exception {
        Nodo<E> n = raiz;
        Cola<Nodo<E>> cola = new Cola<Nodo<E>>();

        if (n != null) {
            cola.insertar(n);
            while (!cola.estaVacia()){
                n = cola.extraer();
                imprimir(n);
                if (n.izquierdo != null){
                    cola.insertar(n.izquierdo);
                }
                if (n.derecho != null){
                    cola.insertar(n.derecho);
                }
            }
        }
    }

    public void preorden(){
        if (this.raiz != null) {
            Pila<Nodo<E>> pila = new Pila();
            pila.insertar(this.raiz);

            System.out.println("Preorden: \n");
            while(!pila.estaVacia()) {
                Nodo<E> actual = (Nodo)pila.extraer();
                this.imprimir(actual);

                if (actual.derecho != null) {
                    pila.insertar(actual.derecho);
                }
                if (actual.izquierdo != null) {
                    pila.insertar(actual.izquierdo);
                }
            }
        }
    }

    public void orden(){
        if (this.raiz != null) {
            Pila<Nodo<E>> pila = new Pila();
            Nodo<E> actual;

            System.out.println("Orden: \n");
            for(actual = this.raiz; actual != null || !pila.estaVacia(); actual = actual.derecho) {
                while(actual != null) {
                    pila.insertar(actual);
                    actual = actual.izquierdo;
                }

                actual = (Nodo)pila.extraer();
                this.imprimir(actual);
            }
        }
    }

    public void postorden(){
        if (this.raiz != null) {
            Pila<Nodo<E>> pila1 = new Pila();
            Pila<Nodo<E>> pila2 = new Pila();

            pila1.insertar(this.raiz);

            while(!pila1.estaVacia()) {
                Nodo<E> actual = (Nodo)pila1.extraer();
                pila2.insertar(actual);

                if (actual.izquierdo != null) {
                    pila1.insertar(actual.izquierdo);
                }
                if (actual.derecho != null) {
                    pila1.insertar(actual.derecho);
                }
            }

            System.out.println("Postorden: \n");
            while(!pila2.estaVacia()) {
                this.imprimir((Nodo)pila2.extraer());
            }
        }
    }

    public void eliminar(E dato) {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        Nodo<E> n = raiz;
        Nodo<E> previo = null;

        while (n != null) {
            if (n.dato.equals(dato) && n != raiz)
                break;

            previo = n;
            if (n.mayor(dato))
                n = n.derecho;
            else
                n = n.izquierdo;

        }

        if (n == null) {
            if (raiz.dato.equals(dato))
                System.out.println("No se puede eliminar la raíz del árbol.");
            else
                System.out.println("El dato " + dato + " no se encuentra en el árbol.");

            return;
        }

        Nodo<E> nodo = n;
        Nodo<E> tmp;

        if (nodo.derecho == null) {
            nodo = nodo.izquierdo;
        } else if (nodo.izquierdo == null) {
            nodo = nodo.derecho;
        } else {
            tmp = nodo.izquierdo;
            while (tmp.derecho != null) {
                tmp = tmp.derecho;
            }
            tmp.derecho = nodo.derecho;
            nodo = nodo.izquierdo;
        }

        if (previo.izquierdo == n)
            previo.izquierdo = nodo;
        else if (previo.derecho == n)
            previo.derecho = nodo;

    }

    public String aTexto() {
        StringBuilder sb = new StringBuilder();
        aTextoEnOrden(raiz, sb);
        return sb.toString().trim();
    }

    private void aTextoEnOrden(Nodo<E> nodo, StringBuilder sb) {
        if (nodo != null) {
            aTextoEnOrden(nodo.izquierdo, sb);
            sb.append(nodo.dato).append(" ");
            aTextoEnOrden(nodo.derecho, sb);
        }
    }
}
