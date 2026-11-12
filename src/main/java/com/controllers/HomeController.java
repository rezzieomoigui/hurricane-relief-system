package com.controllers;
/**
 * PrimaryController class handles the primary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.relief_system.App;
/**
 * PrimaryController class handles the primary view of the application
 * @author Fantastic Four
 */
public class HomeController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
