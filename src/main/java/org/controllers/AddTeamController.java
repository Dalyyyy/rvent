package org.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.entities.Team;
import org.services.TeamService;
import org.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class AddTeamController {
    @FXML
    private TextField teamNameField;

    @FXML
    private ComboBox<String> organizerComboBox; // Assuming the organizer's name is a String. Adjust if using a custom object.

    @FXML
    private Button addTeamButton;

    TeamService teamService = new TeamService();
    UserService userService = new UserService();



    @FXML
    private void initialize() {

        loadOrganizerNames();
        // Initialize your ComboBox here. Example:
        // organizerComboBox.setItems(FXCollections.observableArrayList("Organizer 1", "Organizer 2"));
        // If fetching organizers from a database or service, call that method here.
    }



    @FXML
    private void handleAddTeam() throws SQLException {
        String teamName = teamNameField.getText();
        String organizerName = organizerComboBox.getSelectionModel().getSelectedItem();

        if (teamName.isEmpty() || organizerName == null) {
            showAlert("Error", "Team name and organizer are required!");
            return;
        }

        // Assuming you have a method to get userId from organizerName
        int userId = userService.getUserIdFromName(organizerName); // You need to implement this

        Team team = new Team(teamName); // Assuming Team has a constructor that accepts a team name

        teamService.createTeamAndAddUser(team, userId);
        showAlert("Success", "Team added successfully.");
        teamNameField.clear();
        organizerComboBox.getSelectionModel().clearSelection();
    }

    private void loadOrganizerNames() {
        try {
            List<String> organizerNames = userService.getAllOrganizerNamesNotInTeam();
            organizerComboBox.setItems(FXCollections.observableArrayList(organizerNames));
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider showing an error message to the user
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
