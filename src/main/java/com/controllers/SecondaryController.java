package com.relief_system;
/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;
import javafx.fxml.FXML;
/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}