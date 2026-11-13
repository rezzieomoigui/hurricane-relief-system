package com.controllers;
/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.model.HurricaneReliefApplication;
import com.relief_system.App;

/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
public class LoginController {

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;
    
    @FXML 
    void login(ActionEvent event) {
        String userName = txt_username.getText();
        String password = txt_password.getText();
        System.out.println("Your name is " + userName + " pass " + password);
    }
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void btnLoginClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        HurricaneReliefApplication app = new HurricaneReliefApplication();

        if (!app.login(username, password)) {
            lbl_error.setTxt("Invalid login credentials.");
            return;
        }

        App.setRoot("user_home");
    }
}