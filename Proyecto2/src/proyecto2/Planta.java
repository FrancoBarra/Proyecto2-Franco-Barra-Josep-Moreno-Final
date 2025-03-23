/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author frank
 */
public class Planta {
        private String nombrePlanta;
        private String value;
        private Planta pNext; 

        public Planta(String nombrePlanta, String informacion) {
            this.nombrePlanta = nombrePlanta;
            this.value = informacion;
            this.pNext = null;
        }

    public String getNombrePlanta() {
        return nombrePlanta;
    }

    public void setNombrePlanta(String nombrePlanta) {
        this.nombrePlanta = nombrePlanta;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    

    public Planta getpNext() {
        return pNext;
    }

    public void setpNext(Planta siguiente) {
        this.pNext = siguiente;
    }
        
    }


