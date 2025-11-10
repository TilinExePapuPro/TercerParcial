package org.tercerparcial.arbolbin.Cola;

import ListaSimple.Listasimple;



public class Cola <E>{
    Listasimple<E> cola = new Listasimple<E>();
    public void borrar(){
        cola = new Listasimple<E>();
    }

    public boolean estaVacia(){
        return cola.esVacia();
    }

    public void insertar (E dato){
        cola.agregarEnCola(dato);
    }

    public E extraer(){
        E tmp= cola.obtenerCabeza();
        cola.eliminarEnCabeza();
        return tmp;
    }

    public E elementoInferior(){
        return cola.obtenerCabeza();
    }

}

