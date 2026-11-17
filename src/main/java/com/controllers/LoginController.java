package com.controllers;
/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.model.HurricaneReliefApplication;
import com.relief_system.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * SecondaryController class handles the secondary view of the application
 * @author Fantastic Four
 */
public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No initialization is required.
    }

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label lbl_error;
    
    /**@FXML 
    void login(ActionEvent event) {
        HurricaneReliefApplication app = new HurricaneReliefApplication();
        String username = txt_username.getText();
        String password = txt_password.getText();
        return app.login(username, password);
    }**/
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
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        App.setRoot("home");
    }
}