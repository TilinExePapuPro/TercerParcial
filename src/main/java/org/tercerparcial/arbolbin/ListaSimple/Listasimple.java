package org.tercerparcial.arbolbin.ListaSimple;

public class Listasimple<E> {
    private Nodo<E> cabeza;
    private Nodo<E> cola;
    private int longitud = 0;

    public Listasimple() {
        this.cabeza = this.cola = null;
    }

    public void agregarEnCabeza(E dato) {
        this.cabeza = new Nodo(dato, this.cabeza);
        if (this.cola == null) {
            this.cola = this.cabeza;
        }
        ++this.longitud;
    }

    public void agregarEnCola(E dato) {
        if (this.cabeza == null) {
            this.cabeza = this.cola = new Nodo(dato);
        } else {
            this.cola.setSiguiente(new Nodo(dato));
            this.cola = this.cola.getSiguiente();
        }
        ++this.longitud;
    }

    public void imprimirElementos() {
        for(Nodo<E> tmp = this.cabeza; tmp != null; tmp = tmp.getSiguiente()) {
            System.out.println(tmp.getDato());
        }
    }

    public E obtenerCabeza() {
        return (E)this.cabeza.getDato();
    }

    public E obtenerCola() {
        return (E)this.cola.getDato();
    }

    public boolean eliminar(E dato) {
        boolean borrado = false;
        if (this.cabeza != null) {
            if (this.cabeza == this.cola && dato.equals(this.cabeza.getDato())) {
                this.cabeza = this.cola = null;
                borrado = true;
                --this.longitud;
            } else if (dato == this.cabeza.getDato()) {
                this.cabeza = this.cabeza.getSiguiente();
                borrado = true;
                --this.longitud;
            } else {
                Nodo<E> prd = this.cabeza;
                Nodo<E> tmp;
                for(tmp = this.cabeza.getSiguiente(); tmp != null && !tmp.getDato().equals(dato); tmp = tmp.getSiguiente()) {
                    prd = prd.getSiguiente();
                }

                if (tmp != null) {
                    borrado = true;
                    --this.longitud;
                    prd.setSiguiente(tmp.getSiguiente());
                    if (tmp == this.cola) {
                        this.cola = prd;
                    }
                }
            }
        }
        return borrado;
    }

    public int getLongitud() {
        if (this.longitud < 0) {
            this.longitud = 0;
        }
        return this.longitud;
    }

    public boolean esVacia() {
        return this.cabeza == null;
    }
    public void eliminarEnCabeza() {
        if (this.cabeza != null) {
            if (this.cabeza == this.cola) {
                this.cabeza = this.cola = null;
                --this.longitud;
            } else {
                this.cabeza = this.cabeza.getSiguiente();
                --this.longitud;
            }
        }
    }

    public void eliminarEnCola() {
        if (this.cabeza != null) {
            if (this.cabeza == this.cola) {
                this.cabeza = this.cola = null;
                --this.longitud;
            } else {
                Nodo<E> prd = this.cabeza;

                Nodo<E> tmp;
                for(tmp = this.cabeza.getSiguiente(); tmp != null; tmp = tmp.getSiguiente()) {
                    prd = prd.getSiguiente();
                }

                if (tmp != null) {
                    if (prd == this.cabeza) {
                        this.cabeza.setSiguiente((Nodo)null);
                        this.cola = this.cabeza;
                    } else {
                        prd.setSiguiente((Nodo)null);
                        this.cola = prd;
                    }
                }
            }
        }
    }

    public Nodo<E> obtenerNodo(int indice) {
        Nodo<E> tmp = null;
        if (indice <= this.longitud) {
            tmp = this.cabeza;

            for(int contador = 0; contador < indice && tmp != null; tmp = tmp.getSiguiente()) {
                ++contador;
            }
        }
        return tmp;
    }

    public int estaEnLista(E dato) {
        Nodo<E> tmp = null;
        tmp = this.cabeza;

        int indice;
        for(indice = 0; indice < this.longitud - 1 && tmp != null && tmp.getDato().equals(dato); tmp = tmp.getSiguiente()) {
            ++indice;
        }

        return tmp != null ? indice : -1;
    }


    public boolean eliminarEnIndice(int indice) {
        boolean borrado = false;
        if (indice >= 0 && indice <= this.longitud - 1 && this.cabeza != null) {
            if (this.cabeza == this.cola && indice == 0) {
                this.cabeza = this.cola = null;
                borrado = true;
                --this.longitud;
            } else if (indice == 0) {
                this.cabeza = this.cabeza.getSiguiente();
                borrado = true;
                --this.longitud;
            } else {
                int contador = 1;
                Nodo<E> prd = this.cabeza;

                Nodo<E> tmp;
                for(tmp = this.cabeza.getSiguiente(); contador < indice; ++contador) {
                    prd = prd.getSiguiente();
                    tmp = tmp.getSiguiente();
                }

                if (tmp != null) {
                    borrado = true;
                    --this.longitud;
                    prd.setSiguiente(tmp.getSiguiente());
                    if (tmp == this.cola) {
                        this.cola = prd;
                    }
                }
            }
        }

        return borrado;
    }


    public boolean insertarEnIndice(E dato, int indice) {
        boolean seInserto = false;
        if (indice >= 0 && indice <= this.longitud - 1) {
            if (indice == 0) {
                this.agregarEnCabeza(dato);
                seInserto = true;
            } else if (indice == this.longitud - 1) {
                this.agregarEnCola(dato);
                seInserto = true;
            } else {
                Nodo<E> tmp = null;
                int contador = 0;
                Nodo<E> prv = null;

                for(tmp = this.cabeza; contador < indice; tmp = tmp.getSiguiente()) {
                    ++contador;
                    prv = tmp;
                }
                prv.setSiguiente(new Nodo(dato, tmp));
                seInserto = true;
            }
        }

        return seInserto;
    }

    public boolean asignar(E dato, int indice) {
        Nodo<E> tmp = null;
        if (indice <= this.longitud - 1) {
            tmp = this.cabeza;

            for(int contador = 0; contador < indice && tmp != null; tmp = tmp.getSiguiente()) {
                ++contador;
            }
        }

        if (tmp != null) {
            tmp.setDato(dato);
            return true;
        } else {
            return false;
        }
    }

    public void asignar(E dato, E nuevoDato, boolean todos) {
        Nodo<E> tmp = null;
        if (!todos) {
            for(Nodo<E> var5 = this.cabeza; var5 != null; var5 = var5.getSiguiente()) {
                if (var5.getDato().equals(dato)) {
                    System.out.println("entre una 2");
                    var5.setDato(nuevoDato);
                    return;
                }
            }
        } else {
            for(Nodo<E> var6 = this.cabeza; var6 != null; var6 = var6.getSiguiente()) {
                if (var6.getDato().equals(dato)) {
                    var6.setDato(nuevoDato);
                }
            }
        }

    }
}
