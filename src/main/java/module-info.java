module com.relief_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    //requires junit;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    //requires org.junit.jupiter.api;

    //opens com.libary to javafx.fml;
    //exports com.library; 

    opens com.relief_system to javafx.fxml;
    opens com.controllers to javafx.fxml;
    exports com.relief_system;
}
