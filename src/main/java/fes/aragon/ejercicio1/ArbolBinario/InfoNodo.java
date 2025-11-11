package fes.aragon.ejercicio1.ArbolBinario;

public class InfoNodo {
    int indice;
    int info;
    int izq;
    int der;

    public InfoNodo(int indice, int info, int izq, int der) {
        this.indice = indice;
        this.info = info;
        this.izq = izq;
        this.der = der;
    }

    public void setDer(int der) {
        this.der = der;
    }

    public void setIzq(int izq) {
        this.izq = izq;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getDer() {
        return der;
    }

    public int getIzq() {
        return izq;
    }

    public int getInfo() {
        return info;
    }

    public int getIndice() {
        return indice;
    }
}