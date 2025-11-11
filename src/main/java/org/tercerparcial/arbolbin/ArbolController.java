package org.tercerparcial.arbolbin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.tercerparcial.arbolbin.ArbolBinario.Nodo;
import java.util.Stack;

public class ArbolController {

    @FXML
    private TextField campoTexto;

    @FXML
    private Pane arbolPanel;

    private Nodo<String> raizActual;

    // -----------------------------
    // BOTÓN: DIBUJAR ÁRBOL
    // -----------------------------
    @FXML
    public void presionarBotonDibujar() {
        try {
            String infija = campoTexto.getText().replaceAll("\\s+", "");
            if (infija.isEmpty()) {
                mostrarAlerta("Error", "Por favor ingresa una expresión.");
                return;
            }

            // 1️⃣ Convertir infija a postfija
            String postfija = infijaAPostfija(infija);

            // 2️⃣ Construir árbol desde postfija
            raizActual = construirArbol(postfija);
            arbolPanel.getChildren().clear();
            dibujarNodo(raizActual, arbolPanel.getWidth() / 2, 40, arbolPanel.getWidth() / 4);

        } catch (Exception e) {
            mostrarAlerta("Error", "Expresión inválida: " + e.getMessage());
        }
    }

    // -----------------------------
    // BOTÓN: EVALUAR EXPRESIÓN
    // -----------------------------
    @FXML
    public void presionarBotonEvaluar() {
        if (raizActual == null) {
            mostrarAlerta("Error", "Primero debes dibujar el árbol.");
            return;
        }

        double resultado = evaluar(raizActual);
        mostrarAlerta("Resultado", "El resultado de la expresión es: " + resultado);
    }

    // -----------------------------
    // CONVERSIÓN INFIX → POSTFIX
    // -----------------------------
    private String infijaAPostfija(String exp) {
        StringBuilder salida = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // 🧮 Leer números de varias cifras
            if (Character.isDigit(c)) {
                StringBuilder numero = new StringBuilder();
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    numero.append(exp.charAt(i));
                    i++;
                }
                i--; // retrocede una posición
                salida.append(numero).append(' ');
            }
            // Paréntesis de apertura
            else if (c == '(') {
                pila.push(c);
            }
            // Paréntesis de cierre
            else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(')
                    salida.append(pila.pop()).append(' ');
                if (!pila.isEmpty()) pila.pop();
            }
            // Operadores
            else if ("+-*/".indexOf(c) != -1) {
                while (!pila.isEmpty() && prioridad(pila.peek()) >= prioridad(c))
                    salida.append(pila.pop()).append(' ');
                pila.push(c);
            }
        }

        while (!pila.isEmpty())
            salida.append(pila.pop()).append(' ');

        return salida.toString().trim();
    }

    private int prioridad(char op) {
        return (op == '+' || op == '-') ? 1 : (op == '*' || op == '/') ? 2 : 0;
    }

    // -----------------------------
    // CREAR ÁRBOL DESDE POSTFIJA
    // -----------------------------
    private Nodo<String> construirArbol(String postfija) {
        Stack<Nodo<String>> pila = new Stack<>();
        for (String token : postfija.split("\\s+")) {
            if ("+-*/".contains(token)) {
                Nodo<String> der = pila.pop();
                Nodo<String> izq = pila.pop();
                Nodo<String> nodo = new Nodo<>(token, "operador");
                nodo.setIzquierdo(izq);
                nodo.setDerecho(der);
                pila.push(nodo);
            } else {
                pila.push(new Nodo<>(token, "operando"));
            }
        }
        return pila.pop();
    }

    // -----------------------------
    // EVALUAR ÁRBOL
    // -----------------------------
    private double evaluar(Nodo<String> nodo) {
        if (nodo == null)
            return 0;

        String dato = nodo.getDato();
        if (!"+-*/".contains(dato))
            return Double.parseDouble(dato);

        double izq = evaluar(nodo.getIzquierdo());
        double der = evaluar(nodo.getDerecho());

        return switch (dato) {
            case "+" -> izq + der;
            case "-" -> izq - der;
            case "*" -> izq * der;
            case "/" -> izq / der;
            default -> 0;
        };
    }

    // -----------------------------
    // DIBUJAR NODOS
    // -----------------------------
    private void dibujarNodo(Nodo<String> nodo, double x, double y, double offset) {
        if (nodo == null) return;

        if (nodo.getIzquierdo() != null) {
            Line linea = new Line(x, y, x - offset, y + 60);
            linea.setStyle("-fx-stroke: #BD5E34; -fx-stroke-width: 2;");
            arbolPanel.getChildren().add(linea);
            dibujarNodo(nodo.getIzquierdo(), x - offset, y + 60, offset / 2);
        }

        if (nodo.getDerecho() != null) {
            Line linea = new Line(x, y, x + offset, y + 60);
            linea.setStyle("-fx-stroke: #BD5E34; -fx-stroke-width: 2;");
            arbolPanel.getChildren().add(linea);
            dibujarNodo(nodo.getDerecho(), x + offset, y + 60, offset / 2);
        }

        Circle c = new Circle(x, y, 18);
        c.setStyle("-fx-fill: #38C72C; -fx-stroke: #12911A; -fx-stroke-width: 2;");

        Text t = new Text(x - 7, y + 5, nodo.getDato());

        arbolPanel.getChildren().addAll(c, t);
    }

    // -----------------------------
    // ALERTAS
    // -----------------------------
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}
