module fes.aragon.ejercicio1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires Herramienta;


    opens fes.aragon.ejercicio1 to javafx.fxml;
    exports fes.aragon.ejercicio1;
    exports fes.aragon.ejercicio1.Controllers;
    opens fes.aragon.ejercicio1.Controllers to javafx.fxml;
}