package org.tercerparcial.arbolbin.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import ArbolBinario.Nodo;
import java.util.Stack;

public class ArbolController {

    @FXML
    private TextField campoTexto;

    @FXML
    private Pane arbolPanel;

    private Nodo<String> raizActual;

    @FXML
    public void presionarBotonDibujar() {
        try {
            String infija = campoTexto.getText().replaceAll("\\s+", "");
            if (infija.isEmpty()) {
                mostrarAlerta("Error", "Por favor ingresa una expresión.");
                return;
            }

            String postfija = infijaAPostfija(infija);
            raizActual = construirArbol(postfija);
            arbolPanel.getChildren().clear();
            int altura = calcularAltura(raizActual);

            double anchoPanel = arbolPanel.getWidth();
            double offsetInicial = Math.max(anchoPanel / (Math.pow(2, altura - 1)), 60);

            double anchoInicial = Math.max(arbolPanel.getWidth() / (Math.pow(2, altura - 1)), 40);
            dibujarNodo(raizActual, arbolPanel.getWidth() / 2, 40, anchoInicial);


        } catch (Exception e) {
            mostrarAlerta("Error", "Expresión inválida: " + e.getMessage());
        }
    }

    @FXML
    public void presionarBotonEvaluar() {
        if (raizActual == null) {
            mostrarAlerta("Error", "Primero debes dibujar el árbol.");
            return;
        }

        double resultado = evaluar(raizActual);
        mostrarAlerta("Resultado", "El resultado de la expresión es: " + resultado);
    }

    private String infijaAPostfija(String exp) {
        StringBuilder salida = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder numero = new StringBuilder();
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    numero.append(exp.charAt(i));
                    i++;
                }
                i--;
                salida.append(numero).append(' ');
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(')
                    salida.append(pila.pop()).append(' ');
                if (!pila.isEmpty()) pila.pop();
            } else if ("+-*/".indexOf(c) != -1) {
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

    private void dibujarNodo(Nodo<String> nodo, double x, double y, double offset) {
        if (nodo == null) return;

        double minOffset = 35;
        double nextOffset = Math.max(offset / 1.8, minOffset);

        if (nodo.getIzquierdo() != null) {
            double xIzq = x - nextOffset;
            Line linea = new Line(x, y, xIzq, y + 60);
            linea.setStyle("-fx-stroke: #BD5E34; -fx-stroke-width: 2;");
            arbolPanel.getChildren().add(linea);
            dibujarNodo(nodo.getIzquierdo(), xIzq, y + 60, nextOffset);
        }

        if (nodo.getDerecho() != null) {
            double xDer = x + nextOffset;
            Line linea = new Line(x, y, xDer, y + 60);
            linea.setStyle("-fx-stroke: #BD5E34; -fx-stroke-width: 2;");
            arbolPanel.getChildren().add(linea);
            dibujarNodo(nodo.getDerecho(), xDer, y + 60, nextOffset);
        }

        Circle c = new Circle(x, y, 18);
        c.setStyle("-fx-fill: #38C72C; -fx-stroke: #12911A; -fx-stroke-width: 2;");

        Text t = new Text(x - 7, y + 5, nodo.getDato());

        arbolPanel.getChildren().addAll(c, t);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    private int calcularAltura(Nodo<String> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(calcularAltura(nodo.getIzquierdo()), calcularAltura(nodo.getDerecho()));
    }

    private int contarNodos(Nodo<String> nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }
}
