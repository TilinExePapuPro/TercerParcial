module org.tercerparcial.arbolbin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.tercerparcial.arbolbin to javafx.fxml;
    exports org.tercerparcial.arbolbin;
}