/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author joseph.moreno
 */
public class NodoArbol {
    private int data;
    private NodoArbol izHijo;
    private NodoArbol deHijo;

    public NodoArbol(int data) {
        this.data = data;
        this.izHijo = null;
        this.deHijo = null;
    }

    public int getData() {
        return data;
    }

    public NodoArbol getIzHijo() {
        return izHijo;
    }

    public void setIzHijo(NodoArbol izHijo) {
        this.izHijo = izHijo;
    }

    public NodoArbol getDeHijo() {
        return deHijo;
    }

    public void setDeHijo(NodoArbol deHijo) {
        this.deHijo = deHijo;
    }
}