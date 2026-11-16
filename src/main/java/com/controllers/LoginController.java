package com.controllers;
/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;

import com.relief_system.App;

import javafx.fxml.FXML;

/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
public class LoginController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("home");
    }


}