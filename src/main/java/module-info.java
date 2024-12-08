module com.kayr.projetjava.polyprojetjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.kayr.projetjava.polyprojetjava to javafx.fxml;
    exports com.kayr.projetjava.polyprojetjava;
}