module fes.aragon.ejercicio1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fes.aragon.ejercicio1 to javafx.fxml;
    exports fes.aragon.ejercicio1;
}