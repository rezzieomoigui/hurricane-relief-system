package com.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.model.HurricaneReliefApplication;
import com.model.ReliefRequest;
import com.model.User;
import com.model.UserList;
import com.model.Volunteer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UserHomeController implements Initializable {
// volunteer needs to be able to mark as submitted
    @FXML 
    private Label lbl_volunteer; 

    @FXML 
    private Label lbl_assigned; 

    @FXML
    private Label lbl_support; 

    @FXML
    private Circle circle_submitted;

    @FXML
    private Circle circle_assigned;

    @FXML
    private Circle circle_completed;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No initialization is required.
    }

    private void displayRequest(User currentUser, HurricaneReliefApplication application) {
        // what to do if user does not already have a request?
        ReliefRequest request = application.getReliefRequest(currentUser);
        UserList userList = application.getUserList();
        if (request.getAssignedVolunteerID() == null)
            lbl_assigned.setText("Pending");
        else {
            Volunteer volunteer = (Volunteer)userList.getUser(request.getAssignedVolunteerID());
            lbl_volunteer.setText(volunteer.getFirstName() + " " + volunteer.getLastName());
        }
        lbl_assigned.setText(request.getStatus().str);
        lbl_support.setText(request.getSkillsNeed().str);
        circle_submitted.setFill(Color.BLACK); //black for now; need to coordinate w/ style sheet
        if (request.getStatus().str.equals("Assigned")) {
            circle_assigned.setFill(Color.BLACK);
        }
        else if(request.getStatus().str.equals("Completed")) {
            circle_assigned.setFill(Color.BLACK);
            circle_completed.setFill(Color.BLACK);
        }
    }
}
