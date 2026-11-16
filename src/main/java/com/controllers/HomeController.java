package com.controllers;
/**
 * PrimaryController class handles the primary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;

import com.relief_system.App;

import javafx.fxml.FXML;
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
