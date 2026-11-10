package com.relief_system;
/**
 * PrimaryController class handles the primary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;
import javafx.fxml.FXML;
/**
 * PrimaryController class handles the primary view of the application
 * @author Fantastic Four
 */
public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
