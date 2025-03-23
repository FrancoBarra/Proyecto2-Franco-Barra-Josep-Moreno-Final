/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author frank
 */
public class HashTable {

    private static final int TAMANO_TABLA = 101;
    private Planta[] tabla;

    public HashTable() {
        tabla = new Planta[TAMANO_TABLA];
    }

    public Planta[] getTabla() {
        return tabla;
    }

    public void setTabla(Planta[] tabla) {
        this.tabla = tabla;
    }
    
    /**
     * Se le asigna un indidice a cada elemento de la tabla hash
     * @param nombrePlanta nombre de la planta
     * @return el indice de la planta deseada
     */
    private int obtenerIndice(String nombrePlanta) {
        int codigoHash = nombrePlanta.hashCode();
        return Math.abs(codigoHash % TAMANO_TABLA);
    }
    
    /**
     * Se agrega un nuevo elememto a la tabla
     * @param nombrePlanta nombre del nuevo elemento
     * @param informacion iformacion del elemento
     * 
     */
    public void insertar(String nombrePlanta, String informacion) {
        int indice = obtenerIndice(nombrePlanta);
        Planta nuevaEntrada = new Planta(nombrePlanta, informacion);

        if (tabla[indice] == null) {
            tabla[indice] = nuevaEntrada;
        } else {
            Planta actual = tabla[indice];
            while (actual.getpNext() != null) {
                if (actual.getNombrePlanta().equals(nombrePlanta)) {
                    actual.setValue(informacion); 
                    return;
                }
                actual = actual.getpNext();
            }
            if (actual.getNombrePlanta().equals(nombrePlanta)) {
                actual.setValue(informacion); 
            }else{
                actual.setpNext(nuevaEntrada);
            }

        }
    }

    /**
     * Busca el elemento por el nombre ingresado
     * @param nombrePlanta nombre del elemento a buscar
     * @return nombre
     */
    public String buscar(String nombrePlanta) {
        int indice = obtenerIndice(nombrePlanta);
        Planta actual = tabla[indice];

        while (actual != null) {
            if (actual.getNombrePlanta().equals(nombrePlanta)) {
                return actual.getValue();
            }
            actual = actual.getpNext();
        }

        return null; // No encontrada
    }
    
    /**
     * Elimina el elemento por el nombre ingresado
     * @param nombrePlanta nombre a eliminar
     */
    public void eliminar(String nombrePlanta) {
        int indice = obtenerIndice(nombrePlanta);
        Planta actual = tabla[indice];
        Planta anterior = null;

        while (actual != null) {
            if (actual.getNombrePlanta().equals(nombrePlanta)) {
                if (anterior == null) {
                    tabla[indice] = actual.getpNext(); // Primer elemento
                } else {
                    anterior.setpNext(actual.getpNext());
                }
                return;
            }
            anterior = actual;
            actual = actual.getpNext();
        }
    }

 
        
}
