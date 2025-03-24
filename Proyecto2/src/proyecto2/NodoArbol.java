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
    private String info;
    private NodoArbol izHijo;
    private NodoArbol deHijo;
 

    public NodoArbol(int data, String info) {
        this.data = data;
        this.info=info;
        this.izHijo = null;
        this.deHijo = null;
    }

    // Getters y setters
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    

     public boolean isTieneHijo(){
        return this.deHijo!=null || this.izHijo!=null;
    }

    
     
     
   
}