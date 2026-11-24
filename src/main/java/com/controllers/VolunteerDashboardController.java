package com.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import com.relief_system.App;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

public class VolunteerDashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No initialization is required.
    }

    @FXML
    private void btnViewDetailsClicked(MouseEvent event) throws IOException {
        App.setRoot("assignedRequest");
    }
}
