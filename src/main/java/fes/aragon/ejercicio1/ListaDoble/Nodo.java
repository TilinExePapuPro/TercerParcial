package fes.aragon.ejercicio1.ListaDoble;

public class Nodo<E> {
    private E dato;
    private Nodo<E> siguiente;
    private Nodo<E> anterior;

    public Nodo(E dato) {
        this(dato, (Nodo)null, (Nodo)null);
    }

    public Nodo(E dato, Nodo<E> siguiente, Nodo<E> anterior) {
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public E getDato() {
        return this.dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public Nodo<E> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(Nodo<E> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<E> getAnterior() {
        return this.anterior;
    }

    public void setAnterior(Nodo<E> anterior) {
        this.anterior = anterior;
    }
}
