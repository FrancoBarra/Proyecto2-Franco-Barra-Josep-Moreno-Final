/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author joseph.moreno
 */
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

public class DicotomicKeyGUI extends JFrame {
    private JTextArea questionArea;
    private JTextField searchField;
    private JButton loadButton, searchHashButton, searchTreeButton, showGraphButton;
    private ArbolBinario arbol;
    private HashTable tabla;

    public DicotomicKeyGUI() {
        setTitle("Clave Dicotómica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para cargar el archivo
        JPanel topPanel = new JPanel();
        loadButton = new JButton("Cargar Clave Dicotómica");
        topPanel.add(loadButton);
        add(topPanel, BorderLayout.NORTH);

        // Área de texto para mostrar preguntas
        questionArea = new JTextArea();
        questionArea.setEditable(false);
        add(new JScrollPane(questionArea), BorderLayout.CENTER);

        // Panel inferior para búsqueda y mostrar grafo
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField(20);
        searchHashButton = new JButton("Buscar por Hash");
        searchTreeButton = new JButton("Buscar por Árbol");
        showGraphButton = new JButton("Mostrar Grafo");
        bottomPanel.add(searchField);
        bottomPanel.add(searchHashButton);
        bottomPanel.add(searchTreeButton);
        bottomPanel.add(showGraphButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Inicializar estructuras de datos
        arbol = new ArbolBinario();
        tabla = new HashTable();

        // Listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    cargarClaveDicotomica(selectedFile);
                }
            }
        });

        searchHashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlanta = searchField.getText();
                long startTime = System.nanoTime(); // Captura el tiempo de inicio
                String informacion = tabla.buscar(nombrePlanta);
                long endTime = System.nanoTime(); // Captura el tiempo de finalización
                long duration = (endTime - startTime); // Calcula la duración

                if (informacion != null) {
                    questionArea.setText("Información de la planta: " + informacion + "\nDuración de la búsqueda: " + duration + " nanosegundos");
                } else {
                    questionArea.setText("Planta no encontrada.\nDuración de la búsqueda: " + duration + " nanosegundos");
                }
            }
        });

        searchTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlanta = searchField.getText();
                String resultado = buscarEnArbol(nombrePlanta);
                questionArea.setText(resultado);
            }
        });

        showGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGrafo();
            }
        });
    }

    private void cargarClaveDicotomica(File file) {
        try (FileReader fileReader = new FileReader(file);
             JsonReader jsonReader = Json.createReader(fileReader)) {

            JsonObject jsonObject = jsonReader.readObject();

            // Obtener el nombre de la clave dicotómica
            String nombreClave = jsonObject.keySet().iterator().next();
            JsonArray especiesArray = jsonObject.getJsonArray(nombreClave);

            // Recorrer las especies
            for (javax.json.JsonValue especieValue : especiesArray) {
                JsonObject especieObj = (JsonObject) especieValue;

                // Obtener el nombre de la especie
                String nombreEspecie = especieObj.keySet().iterator().next();
                JsonArray preguntasArray = especieObj.getJsonArray(nombreEspecie);

                // Recorrer las preguntas de la especie
                for (javax.json.JsonValue preguntaValue : preguntasArray) {
                    JsonObject preguntaObj = (JsonObject) preguntaValue;

                    // Obtener la pregunta y su valor booleano
                    String preguntaTexto = preguntaObj.keySet().iterator().next();
                    boolean respuesta = preguntaObj.getBoolean(preguntaTexto);

                    // Insertar en la tabla hash
                    tabla.insertar(nombreEspecie, preguntaTexto);

                    // Insertar en el árbol binario
                    NodoArbol nuevo = new NodoArbol(nombreEspecie.hashCode(), nombreEspecie);
                    arbol.add(nuevo, arbol.getRaiz());

                    // Mostrar la información en el área de texto
                    questionArea.append("Especie: " + nombreEspecie + "\n");
                    questionArea.append("Pregunta: " + preguntaTexto + " -> " + respuesta + "\n");
                }
            }

            questionArea.append("Clave dicotómica cargada correctamente.\n");
        } catch (Exception e) {
            e.printStackTrace();
            questionArea.setText("Error al cargar la clave dicotómica.");
            JOptionPane.showMessageDialog(this, "El archivo cargado no es valido");
        }
    }

    private String buscarEnArbol(String nombrePlanta) {
        long startTime = System.nanoTime();
        NodoArbol nodo = arbol.buscarInorder(nombrePlanta.hashCode(), arbol.getRaiz());
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        String resultado;
        if (nodo != null) {
            resultado = "Planta encontrada: " + nombrePlanta;
        } else {
            resultado = "Planta no encontrada en el árbol.";
        }

        resultado += "\nDuración de la búsqueda: " + duration + " nanosegundos";
        return resultado;
    }

    private void mostrarGrafo() {
        Graph graph = new SingleGraph("Árbol Binario");
        construirGrafo(arbol.getRaiz(), graph, null);
        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        viewer.addDefaultView(true);
    }

    private void construirGrafo(NodoArbol nodo, Graph graph, String parentId) {
    if (nodo == null) return;

    // Generar un ID único para el nodo usando el nombre de la planta
    String nodeId = nodo.getInfo() + "_" + nodo.getData(); // Combina nombre y hash
    graph.addNode(nodeId).setAttribute("ui.label", nodeId);

    if (parentId != null) {
        // Generar un ID único para la arista
        String edgeId = parentId + "-" + nodeId;
        graph.addEdge(edgeId, parentId, nodeId);
    }

    // Recursivamente construir el grafo para los hijos
    construirGrafo(nodo.getIzHijo(), graph, nodeId);
    construirGrafo(nodo.getDeHijo(), graph, nodeId);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DicotomicKeyGUI().setVisible(true);
            }
        });
    }
}