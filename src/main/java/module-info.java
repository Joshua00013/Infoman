module com.example.sqlinserttest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.sqlinserttest to javafx.fxml;
    exports com.example.sqlinserttest;
}