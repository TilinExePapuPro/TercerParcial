package fes.aragon.ejercicio1.ArbolBinario;

import java.util.Objects;

public class Nodo <E>{
    protected E dato;
    protected Nodo<E> izquierdo,derecho;
    protected String Etiqueta;

    public Nodo() {
        izquierdo=derecho=null;
    }
    public Nodo (E dato,String etiqueta){
        this(dato,null,null,etiqueta);
    }
    public  Nodo (E dato,Nodo<E> izquierdo, Nodo<E> derecho, String etiqueta){
        this.dato=dato;
        this.izquierdo=izquierdo;
        this.derecho=derecho;
        this.Etiqueta=etiqueta;
    }

    public int hashCode() {
        return Objects.hash(dato);
    }

    public boolean equals (Object obj){
        if(obj instanceof Integer && this.dato instanceof Integer) {
            Integer dato1=(Integer)this.dato;
            Integer dato2=(Integer)obj;
            if (dato1.equals(dato2)){
                return true;
            }else{
                return false;
            }
        }
        Nodo other = (Nodo) obj;
        return Objects.equals(dato, other.dato);
    }

    public boolean mayor (Object obj){
        boolean resultado = false;
        if(obj instanceof Integer && this.dato instanceof Integer){
            Integer dato1=(Integer)this.dato;
            Integer dato2=(Integer)obj;
            if(dato1<dato2){
                return true;
            }
        }
        return resultado;
    }

    public void setEtiqueta(String etiqueta) {
        Etiqueta = etiqueta;
    }

    public void setDerecho(Nodo<E> derecho) {
        this.derecho = derecho;
    }

    public void setIzquierdo(Nodo<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public String getEtiqueta() {
        return Etiqueta;
    }

    public Nodo<E> getDerecho() {
        return derecho;
    }

    public Nodo<E> getIzquierdo() {
        return izquierdo;
    }

    public E getDato() {
        return dato;
    }
}
