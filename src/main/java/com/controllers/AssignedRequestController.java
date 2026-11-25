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
        displayGeneralInfo(application);
        
    }

    private void displayGeneralInfo(HurricaneReliefApplication application) {
        // consider fixing spacing
        ArrayList<ReliefRequest> reliefRequests = application.getReliefRequests();
        System.out.println(reliefRequests.get(0).getAssignedVolunteerID());
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
        lbl_submitter.setText(currentUser.getFirstName()+" "+currentUser.getLastName());
        lbl_support.setText(assignedRequest.getSkillsNeed().str);
        lbl_address.setText(assignedRequest.getAddress() + ", " + assignedRequest.getCity() + ", \n" + assignedRequest.getState() + ", " + assignedRequest.getZipCode());

    }

    private void displayPartyMembers() {
        nameColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));
        /**categoryColumn.setCellValueFactory(cell.getValue().getType().str);
        if (cell.getValue().needsMedicalAssistance() == true)
            injuredColumn.setCellValueFactory("Yes");
        else
            injuredColumn.setCellValueFactory("No");**/
    }

    //back to requests button
}
