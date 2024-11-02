module com.absanr.factutrack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens com.absanr.factutrack to javafx.fxml;
    opens com.absanr.factutrack.controller to javafx.fxml;
    exports com.absanr.factutrack;
    exports com.absanr.factutrack.controller;
}
