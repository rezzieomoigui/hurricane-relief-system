package com.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.model.HurricaneReliefApplication;
import com.model.ReliefRequestList;
import com.model.ReliefRequest;
import com.model.User;
import com.model.UserList;
import com.model.Volunteer;
import com.model.ReliefRequestStatus;
import java.util.ArrayList;
import com.model.PartyMember;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;

public class AssignedRequestController implements Initializable {

    @FXML
    private Label lbl_submitter;

    @FXML
    private Label lbl_support;

    @FXML
    private Label lbl_address;

    @FXML
    private TableView<PartyMember> partyMembersTable;

    @FXML
    private TableColumn<PartyMember, String> nameColumn;

    @FXML
    private TableColumn<PartyMember, String> categoryColumn;

    @FXML
    private TableColumn<PartyMember, String> injuredColumn;

    @FXML 
    private TableColumn<PartyMember, Number> ageColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HurricaneReliefApplication application = HurricaneReliefApplication.getInstance();
        ArrayList<ReliefRequest> reliefRequests = application.getReliefRequests();
        User currentUser = application.getCurrentUser();
        ReliefRequest assignedRequest = null;
        for ( ReliefRequest reliefRequest : reliefRequests ) {
            if (reliefRequest.getAssignedVolunteerID().equals(currentUser.getUuid()) && reliefRequest.getStatus() == ReliefRequestStatus.IN_PROGRESS) {
                assignedRequest = reliefRequest;
                break;
            }
        }
        if (assignedRequest == null) {
            System.out.println("request is null");
            return;
        }
        displayGeneralInfo(currentUser, assignedRequest);
        prepareTable();
        displayPartyMembers(assignedRequest);
        
    }

    private void displayGeneralInfo(User currentUser, ReliefRequest assignedRequest) {
        lbl_submitter.setText(currentUser.getFirstName()+" "+currentUser.getLastName());
        lbl_support.setText(assignedRequest.getSkillsNeed().str);
        lbl_address.setText(assignedRequest.getAddress() + ", " + assignedRequest.getCity() + ", \n" + assignedRequest.getState() + ", " + assignedRequest.getZipCode());

    }

    private void displayPartyMembers(ReliefRequest request) {
        ArrayList<PartyMember> partyMembers = request.getPartyMembers();
        partyMembersTable.getItems().setAll(partyMembers);
    }

    private void prepareTable() {
        nameColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));
        categoryColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getType().str));
        injuredColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().needsMedicalAssistance() ? "Yes" : "No"));
        ageColumn.setCellValueFactory(cell -> new ReadOnlyIntegerWrapper(2026 - Integer.parseInt(cell.getValue().getYearBorn())));
    }

    //back to requests button
}
