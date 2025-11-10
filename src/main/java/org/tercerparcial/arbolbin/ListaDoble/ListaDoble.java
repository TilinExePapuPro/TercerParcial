package org.tercerparcial.arbolbin.ListaDoble;

public class ListaDoble<E> {
    protected Nodo<E> cabeza;
    protected Nodo<E> cola;
    protected int longitud = 0;

    public ListaDoble() {
        this.cabeza = this.cola = null;
    }

    public void agregarEnCabeza(E dato) {
        Nodo<E> nuevo = new Nodo(dato, this.cabeza, (Nodo)null);
        if (this.cabeza != null) {
            this.cabeza.setAnterior(nuevo);
        } else {
            this.cola = nuevo;
        }

        this.cabeza = nuevo;
        ++this.longitud;
    }

    public void agregarEnCola(E dato) {
        Nodo<E> nuevo = new Nodo(dato, (Nodo)null, this.cola);
        if (this.cola != null) {
            this.cola.setSiguiente(nuevo);
        } else {
            this.cabeza = nuevo;
        }

        this.cola = nuevo;
        ++this.longitud;
    }

    public void eliminarEnCabeza() {
        if (this.cabeza != null) {
            if (this.cabeza == this.cola) {
                this.cabeza = this.cola = null;
            } else {
                this.cabeza = this.cabeza.getSiguiente();
                this.cabeza.setAnterior((Nodo)null);
            }

            --this.longitud;
        }

    }

    public void eliminarEnCola() {
        if (this.cola != null) {
            if (this.cabeza == this.cola) {
                this.cabeza = this.cola = null;
            } else {
                this.cola = this.cola.getAnterior();
                this.cola.setSiguiente((Nodo)null);
            }

            --this.longitud;
        }

    }

    public E obtenerNodo(int indice) {
        if (indice >= 0 && indice < this.longitud) {
            Nodo<E> tmp = this.cabeza;

            for(int i = 0; i < indice; ++i) {
                tmp = tmp.getSiguiente();
            }

            return (E)tmp.getDato();
        } else {
            return null;
        }
    }

    public int estaEnLista(E dato) {
        int indice = 0;

        for(Nodo<E> tmp = this.cabeza; tmp != null; ++indice) {
            if (tmp.getDato().equals(dato)) {
                return indice;
            }

            tmp = tmp.getSiguiente();
        }

        return -1;
    }

    public boolean eliminarEnIndice(int indice) {
        if (indice >= 0 && indice < this.longitud) {
            if (indice == 0) {
                this.eliminarEnCabeza();
                return true;
            } else if (indice == this.longitud - 1) {
                this.eliminarEnCola();
                return true;
            } else {
                Nodo<E> tmp = this.cabeza;

                for(int i = 0; i < indice; ++i) {
                    tmp = tmp.getSiguiente();
                }

                Nodo<E> ant = tmp.getAnterior();
                Nodo<E> sig = tmp.getSiguiente();
                ant.setSiguiente(sig);
                sig.setAnterior(ant);
                --this.longitud;
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean insertarEnIndice(E dato, int indice) {
        if (indice >= 0 && indice <= this.longitud) {
            if (indice == 0) {
                this.agregarEnCabeza(dato);
                return true;
            } else if (indice == this.longitud) {
                this.agregarEnCola(dato);
                return true;
            } else {
                Nodo<E> tmp = this.cabeza;

                for(int i = 0; i < indice; ++i) {
                    tmp = tmp.getSiguiente();
                }

                Nodo<E> nuevo = new Nodo(dato, tmp, tmp.getAnterior());
                tmp.getAnterior().setSiguiente(nuevo);
                tmp.setAnterior(nuevo);
                ++this.longitud;
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean asignar(E dato, int indice) {
        if (indice >= 0 && indice < this.longitud) {
            Nodo<E> tmp = this.cabeza;

            for(int i = 0; i < indice; ++i) {
                tmp = tmp.getSiguiente();
            }

            tmp.setDato(dato);
            return true;
        } else {
            return false;
        }
    }

    public void asignar(E dato, E nuevoDato, boolean todos) {
        for(Nodo<E> tmp = this.cabeza; tmp != null; tmp = tmp.getSiguiente()) {
            if (tmp.getDato().equals(dato)) {
                tmp.setDato(nuevoDato);
                if (!todos) {
                    return;
                }
            }
        }

    }

    public void imprimirElementos() {
        for(Nodo<E> tmp = this.cabeza; tmp != null; tmp = tmp.getSiguiente()) {
            System.out.println(tmp.getDato());
        }

    }

    public void imprimirReversa() {
        for(Nodo<E> tmp = this.cola; tmp != null; tmp = tmp.getAnterior()) {
            System.out.println(tmp.getDato());
        }

    }

    public E obtenerCabeza() {
        return (E)(this.cabeza != null ? this.cabeza.getDato() : null);
    }

    public E obtenerCola() {
        return (E)(this.cola != null ? this.cola.getDato() : null);
    }

    public boolean eliminar(E dato) {
        for(Nodo<E> tmp = this.cabeza; tmp != null; tmp = tmp.getSiguiente()) {
            if (tmp.getDato().equals(dato)) {
                if (tmp == this.cabeza) {
                    this.eliminarEnCabeza();
                } else if (tmp == this.cola) {
                    this.eliminarEnCola();
                } else {
                    tmp.getAnterior().setSiguiente(tmp.getSiguiente());
                    tmp.getSiguiente().setAnterior(tmp.getAnterior());
                    --this.longitud;
                }

                return true;
            }
        }

        return false;
    }

    public int getLongitud() {
        return Math.max(0, this.longitud);
    }

    public boolean esVacia() {
        return this.cabeza == null;
    }
}
