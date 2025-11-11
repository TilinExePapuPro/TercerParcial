package fes.aragon.ejercicio1.Pila;

import fes.aragon.ejercicio1.ListaSimple.Listasimple;



public class Pila <E>{
   Listasimple<E> pila = new Listasimple<E>();
   public void borrar(){
    pila = new Listasimple<E>();
   }

   public boolean estaVacia(){
       return pila.esVacia();
   }

   public void insertar (E dato){
       pila.agregarEnCabeza(dato);
   }

   public E extraer(){
       E tmp= pila.obtenerCabeza();
       pila.eliminarEnCabeza();
       return tmp;
   }

    public E elementoSuperior(){
    return pila.obtenerCabeza();
    }

    public int tamano(){
       return pila.getLongitud();
    }

}
