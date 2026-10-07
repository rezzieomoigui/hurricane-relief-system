module com.relief_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    //opens com.libary to javafx.fml;
    //exports com.library; 

    opens com.relief_system to javafx.fxml;
    exports com.relief_system;
}
