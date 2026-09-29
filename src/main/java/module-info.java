module com.relief_system {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.relief_system to javafx.fxml;
    exports com.relief_system;
}
