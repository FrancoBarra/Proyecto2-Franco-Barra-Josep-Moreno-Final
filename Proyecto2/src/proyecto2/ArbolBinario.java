/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author frank
 */
public class ArbolBinario {

    private NodoArbol raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public boolean isEmpty() {
        return raiz == null;
    }
    /**
     * Se agrega un nuevo nodo al arbol binario
     * @param nuevo nodo al agregar
     * @param root punto de partida
     */
     public void add(NodoArbol nuevo, NodoArbol root) {
       
        if (isEmpty()) {
            this.raiz = nuevo;
        } else {

            if (nuevo.getData() < root.getData()) {
                if (root.getIzHijo() == null) {
                    root.setIzHijo(nuevo);

                } else {
                    add(nuevo, root.getIzHijo());
                }

            } 
                else {
                if (root.getDeHijo() == null) {
                    root.setDeHijo(nuevo);


                } else {
                    add(nuevo, root.getDeHijo());
                }

            }
        }

    }

    /**
     * Calcula el tiempo  de la operación buscar inorder
     * @param i indice que se asigna al nodo
     * @param nodo nodo a buscar
     * @return nodo encontrado
     */
    public NodoArbol buscarInorder(int i, NodoArbol nodo) {
        long startTime = System.nanoTime();
        NodoArbol resultado = buscarInorderRecursivo(i, nodo);
        long endTime = System.nanoTime(); 
        long duration = (endTime - startTime);  //esta es la duracion, si quieres puedes retornarla
        System.out.println("Tiempo de busqueda inOrder: " + duration + " nanosegundos");
        return resultado; //,duration;   asi quedaria
    }
    /**
     * Recorre el arbol de manera inOrder y es llamado dentro de buscarInorder para calcular el tiempo de la operación
     * @param i indice que se asigna al nodo
     * @param nodo nodo a buscar
     * @return nodo encontrado
     */
    private NodoArbol buscarInorderRecursivo(int i, NodoArbol nodo) {
        if (nodo != null) {
            NodoArbol resultadoIzquierdo = buscarInorderRecursivo(i, nodo.getIzHijo());
            if (resultadoIzquierdo != null) {
                return resultadoIzquierdo;
            }

            if (nodo.getData() == i) {
                return nodo;
            }

            return buscarInorderRecursivo(i, nodo.getDeHijo());
        }
        return null;
    }
    /**
     * Busca la planta a traves de preguntas
     * @param respuesta respuesta de la pregunta
     * @param nodo nodo actual
     * @return nodo que contiene el nombre de la planta
     */
    public NodoArbol buscarPlanta(boolean respuesta, NodoArbol nodo) {
        
            if (respuesta) {
                nodo = nodo.getIzHijo();
            } else {
                nodo = nodo.getDeHijo();
            }
        if (!nodo.isTieneHijo()){
            return nodo;
        } 
            return null;
    }
}