package org.tercerparcial.arbolbin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.tercerparcial.arbolbin.ArbolBinario.ArbolBinarioOrden;
import org.tercerparcial.arbolbin.ArbolBinario.InfoNodo;
import org.tercerparcial.arbolbin.ArbolBinario.Nodo;
import org.tercerparcial.arbolbin.ListaDoble.ListaDoble;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArbolController {

    @FXML
    private TextField campoTexto;
    @FXML
    private Pane arbolPanel;

    private ArbolBinarioOrden<Integer> arbol = new ArbolBinarioOrden<>();

    public void insertarDesdeArchivo(File archivo, ArbolBinarioOrden<Integer> arbol) {
        try {
            String contenido = new String(java.nio.file.Files.readAllBytes(archivo.toPath()));

            String[] datos = contenido.split("\\s+");

            for (String d : datos) {
                if (d == null || d.trim().isEmpty() || d.equalsIgnoreCase("null"))
                    continue;

                try {
                    int valor = Integer.parseInt(d.trim());
                    arbol.insertar(valor);
                    //System.out.println("Insertado: " + valor);

                } catch (NumberFormatException ex) {
                    System.out.println("Ignorado por no ser entero: " + d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void presionarBotonInsertar() {
        try{
            if (arbol != null){
                arbol.insertar(Integer.valueOf(campoTexto.getText()));
                graficarArbol();
                //System.out.println("Dato insertado: " + Integer.valueOf(campoTexto.getText()));
            }
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de entrada");
            alerta.setHeaderText("Dato no válido");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }

    public void presionarBotonEliminar() {
        try{
            Nodo<Integer> n = arbol.getRaiz();
            if (arbol.getRaiz() == null) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error del arbol");
                alerta.setHeaderText("El arbol está vacío");
                alerta.setContentText("Necesita crear una raíz");
                alerta.show();
                return;
            }

            arbol.eliminar(Integer.valueOf(campoTexto.getText()));
            graficarArbol();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error del arbol");
            alerta.setHeaderText("La raiz no existe o se esta intentando eliminar");
            if (campoTexto.getText().isEmpty()){
                alerta.setHeaderText("Dato no válido");
            }
            alerta.setContentText(e.getMessage());
            alerta.show();
        }

    }

    public void presionarBotonOrden() {
        arbol.orden();
    }

    public void presionarBotonPreorden() {
        arbol.preorden();
    }

    public void presionarBotonPostorden() {
        arbol.postorden();
    }

    public void presionarBotonCargarArchivo() {
        try{
            File archivo = new File("src/main/resources/org/tercerparcial/arbolbin/datos");
            if (arbol != null) {
                insertarDesdeArchivo(archivo,arbol);
                graficarArbol();
            }
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de archivo");
            alerta.setHeaderText("El archivo podría ser nulo");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }

    public void botonGuardarArbol() {
        try{
            File archivo = new File("src/main/resources/org/tercerparcial/arbolbin/datos");

        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de archivo");
            alerta.setHeaderText("El archivo podría ser nulo");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }
    private void dibujarNodo(Nodo<Integer> nodo, double x, double y, double offset) {
        if (nodo == null)
            return;

        // DIBUJAR CÍRCULO
        javafx.scene.shape.Circle c = new javafx.scene.shape.Circle(x, y, 18);
        c.setStyle("-fx-fill: #38C72C; -fx-stroke: #12911A; -fx-stroke-width: 2;");


        // DIBUJAR HIJO IZQUIERDO
        if (nodo.getIzquierdo() != null) {
            javafx.scene.shape.Line linea = new javafx.scene.shape.Line(x, y, x - offset, y + 60);
            linea.setStyle("-fx-fill: #BD5E34; -fx-stroke: #BD5E34; -fx-stroke-width: 2;");
            dibujarNodo(nodo.getIzquierdo(), x - offset, y + 60, offset / 2);
            arbolPanel.getChildren().add(linea);
        }

        // DIBUJAR HIJO DERECHO
        if (nodo.getDerecho() != null) {
            javafx.scene.shape.Line linea = new javafx.scene.shape.Line(x, y, x + offset, y + 60);
            linea.setStyle("-fx-fill: #BD5E34; -fx-stroke: #BD5E34; -fx-stroke-width: 2;");
            dibujarNodo(nodo.getDerecho(), x + offset, y + 60, offset / 2);
            arbolPanel.getChildren().add(linea);
        }

        // DIBUJAR TEXTO
        javafx.scene.text.Text t = new javafx.scene.text.Text(x - 5, y + 5, nodo.getDato().toString());

        arbolPanel.getChildren().addAll(c, t);
    }

    private void graficarArbol() {
        arbolPanel.getChildren().clear();
        if (arbol.getRaiz() != null) {
            dibujarNodo(arbol.getRaiz(), arbolPanel.getWidth() / 2, 40, arbolPanel.getWidth() / 4);
        }
    }

    private void botonGuardarEnArchivo(File archivo) {
        try {
            String contenidoAGuardar = arbol.aTexto();
            java.nio.file.Files.write(archivo.toPath(), contenidoAGuardar.getBytes());
            System.out.println("Árbol guardado en: " + archivo.getAbsolutePath());
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al guardar archivo");
            alerta.setHeaderText("No se pudo guardar el árbol");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }
}
