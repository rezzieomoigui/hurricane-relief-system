package com.controllers;
/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;
import javafx.fxml.FXML;
import com.relief_system.App;

/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
public class LoginController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}